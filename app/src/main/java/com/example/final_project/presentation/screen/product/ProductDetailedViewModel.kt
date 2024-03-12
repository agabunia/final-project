package com.example.final_project.presentation.screen.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.data.common.Resource
import com.example.final_project.domain.usecase.product.GetProductDetailedUseCase
import com.example.final_project.presentation.event.product.ProductEvent
import com.example.final_project.presentation.mapper.product.toPresenter
import com.example.final_project.presentation.state.product.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailedViewModel @Inject constructor(
    private val getProductDetailedUseCase: GetProductDetailedUseCase
) : ViewModel() {

    private val _productState = MutableStateFlow(ProductState())
    val productState: SharedFlow<ProductState> = _productState.asStateFlow()

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.FetchProductDetailed -> fetchProductDetailed(id = event.id)
            is ProductEvent.ResetErrorMessage -> errorMessage(message = null)
        }
    }

    private fun fetchProductDetailed(id: Int) {
        viewModelScope.launch {
            getProductDetailedUseCase(id = id).collect {
                when (it) {
                    is Resource.Success -> {
                        _productState.update { currentState ->
                            currentState.copy(productDetails = it.data.toPresenter())
                        }
                    }

                    is Resource.Error -> {
                        errorMessage(message = it.errorMessage)
                    }

                    is Resource.Loading -> {
                        _productState.update { currentState ->
                            currentState.copy(isLoading = it.loading)
                        }
                    }
                }
            }
        }
    }

    private fun errorMessage(message: String?) {
        _productState.update { currentState -> currentState.copy(errorMessage = message) }
    }
}