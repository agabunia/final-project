package com.example.final_project.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.data.common.Resource
import com.example.final_project.domain.usecase.home.GetProductsByCategoryUseCase
import com.example.final_project.presentation.event.home.HomeEvent
import com.example.final_project.presentation.event.search.SearchEvent
import com.example.final_project.presentation.mapper.common_product_list.toPresenter
import com.example.final_project.presentation.model.home.CategoryList
import com.example.final_project.presentation.state.home.HomeState
import com.example.final_project.presentation.state.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: SharedFlow<HomeState> = _homeState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.FetchProducts -> fetchProducts(categories = event.category)
            is HomeEvent.ResetErrorMessage -> errorMessage(message = null)
        }
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


}