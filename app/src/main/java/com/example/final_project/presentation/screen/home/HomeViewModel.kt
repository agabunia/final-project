package com.example.final_project.presentation.screen.home

import android.util.Log
import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.data.common.Resource
import com.example.final_project.domain.local.usecase.datastore.language.ChangeLanguageDataStoreUseCase
import com.example.final_project.domain.local.usecase.datastore.language.GetLanguageDataStoreUseCase
import com.example.final_project.domain.local.usecase.datastore.theme.ChangeThemeDataStoreUseCase
import com.example.final_project.domain.local.usecase.datastore.theme.GetThemeDataStoreUseCase
import com.example.final_project.domain.local.usecase.db_manipulators.InsertProductInLocalUseCase
import com.example.final_project.domain.remote.usecase.home.GetProductsByCategoryUseCase
import com.example.final_project.presentation.event.home.HomeEvent
import com.example.final_project.presentation.mapper.common_product_list.toDomain
import com.example.final_project.presentation.mapper.common_product_list.toPresenter
import com.example.final_project.presentation.model.common_product_list.Products
import com.example.final_project.presentation.model.home.CategoryList
import com.example.final_project.presentation.state.app_state.AppState
import com.example.final_project.presentation.state.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val changeThemeDataStoreUseCase: ChangeThemeDataStoreUseCase,
    private val getThemeDataStoreUseCase: GetThemeDataStoreUseCase,
    private val changeLanguageDataStoreUseCase: ChangeLanguageDataStoreUseCase,
    private val getLanguageDataStoreUseCase: GetLanguageDataStoreUseCase,
    private val insertProductInLocalUseCase: InsertProductInLocalUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: SharedFlow<HomeState> = _homeState.asStateFlow()

    private val _appState = MutableStateFlow(AppState())
    val appState: SharedFlow<AppState> = _appState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.FetchProducts -> fetchProducts(categories = event.category)
            is HomeEvent.ResetErrorMessage -> errorMessage(message = null)
            is HomeEvent.ChangeTheme -> setLightTheme(isLight = event.isLight)
            is HomeEvent.ChangeLanguage -> changeLanguage(isGeorgian = event.isGeorgian)
            is HomeEvent.SaveProduct -> saveProductInDatabase(product = event.product)
        }
    }

    init {
        getTheme()
        getLanguage()
    }

    private val productListsMap = mutableMapOf<String, List<CategoryList>>()

    private fun fetchProducts(categories: List<String>) {
        viewModelScope.launch {
            for (category in categories) {
                getProductsByCategoryUseCase(category = category).collect {
                    when (it) {
                        is Resource.Success -> {
                            val newList = listOf(CategoryList(category, it.data.toPresenter()))
                            productListsMap[category] = newList
                            _homeState.update { currentState ->
                                currentState.copy(
                                    productsList = productListsMap.values.flatten()
                                )
                            }
                        }

                        is Resource.Error -> {
                            errorMessage(message = it.errorMessage)
                        }

                        is Resource.Loading -> {
                            _homeState.update { currentState -> currentState.copy(isLoading = it.loading) }
                        }
                    }
                }
            }
        }
    }

    private fun errorMessage(message: String?) {
        _homeState.update { currentState -> currentState.copy(errorMessage = message) }
    }


    private fun setLightTheme(isLight: Boolean) {
        viewModelScope.launch {
            changeThemeDataStoreUseCase(isLight = isLight)
        }
    }

    private fun getTheme() {
        viewModelScope.launch {
            getThemeDataStoreUseCase().collect {
                d("homeTest", "fragment viewmodel: $it")
                if (it == "dark") {
                    _appState.update { themeState ->
                        themeState.copy(isLight = false)
                    }
                } else {
                    _appState.update { themeState ->
                        themeState.copy(isLight = true)
                    }
                }
            }
        }
    }

    private fun changeLanguage(isGeorgian: Boolean) {
        viewModelScope.launch {
            changeLanguageDataStoreUseCase(isGeorgian = isGeorgian)
        }
    }

    private fun getLanguage() {
        viewModelScope.launch {
            getLanguageDataStoreUseCase().collect {
                if (it == "ka") {
                    _appState.update { languageState ->
                        languageState.copy(isGeorgian = true)
                    }
                } else {
                    _appState.update { languageState ->
                        languageState.copy(isGeorgian = false)
                    }
                }
            }
        }
    }

    private fun saveProductInDatabase(product: Products.ProductDetailed) {
        viewModelScope.launch {
            insertProductInLocalUseCase(product = product.toDomain())
        }
    }

}