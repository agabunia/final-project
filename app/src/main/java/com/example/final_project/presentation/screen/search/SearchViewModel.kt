package com.example.final_project.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.data.common.Resource
import com.example.final_project.domain.usecase.search.GetCategoryUseCase
import com.example.final_project.domain.usecase.search.GetProductsUseCase
import com.example.final_project.presentation.event.search.SearchEvent
import com.example.final_project.presentation.mapper.search.toPresenter
import com.example.final_project.presentation.state.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _searchState = MutableStateFlow(SearchState())
    val searchState: SharedFlow<SearchState> = _searchState.asStateFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.FetchCategory -> fetchCategory()
            is SearchEvent.FetchAllProducts -> fetchProducts()
            is SearchEvent.ResetErrorMessage -> errorMessage(message = null)
        }
    }

    private fun fetchCategory() {
        viewModelScope.launch {
            getCategoryUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _searchState.update { currentState ->
                            currentState.copy(category = it.data.map { category ->
                                category.toPresenter()
                            })
                        }
                    }

                    is Resource.Error -> {
                        errorMessage(message = it.errorMessage)
                    }

                    is Resource.Loading -> {
                        _searchState.update { currentState ->
                            currentState.copy(isLoading = it.loading)
                        }
                    }
                }
            }
        }
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            getProductsUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _searchState.update { currentState ->
                            currentState.copy(productsList = it.data.map { product ->
                                product.toPresenter()
                            })
                        }
                    }

                    is Resource.Error -> {
                        errorMessage(message = it.errorMessage)
                    }

                    is Resource.Loading -> {
                        _searchState.update { currentState ->
                            currentState.copy(isLoading = it.loading)
                        }
                    }
                }
            }
        }
    }

    private fun errorMessage(message: String?) {
        _searchState.update { currentState -> currentState.copy(errorMessage = message) }
    }

}
