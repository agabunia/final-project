package com.example.final_project.presentation.screen.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.data.common.Resource
import com.example.final_project.domain.local.usecase.db_manipulators.InsertProductInLocalUseCase
import com.example.final_project.domain.remote.usecase.product.GetProductDetailedUseCase
import com.example.final_project.presentation.event.product.ProductEvent
import com.example.final_project.presentation.mapper.common_product_list.toDomain
import com.example.final_project.presentation.mapper.product.toDomain
import com.example.final_project.presentation.mapper.product.toPresenter
import com.example.final_project.presentation.model.common_product_list.Products
import com.example.final_project.presentation.model.product.ProductDetailed
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
    private val getProductDetailedUseCase: GetProductDetailedUseCase,
    private val insertProductInLocalUseCase: InsertProductInLocalUseCase
) : ViewModel() {

    private val _productState = MutableStateFlow(ProductState())
    val productState: SharedFlow<ProductState> = _productState.asStateFlow()

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.FetchProductDetailed -> fetchProductDetailed(id = event.id)
            is ProductEvent.ResetErrorMessage -> errorMessage(message = null)
            is ProductEvent.SaveProduct -> saveProductInDatabase(product = event.product)
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

    private fun saveProductInDatabase(product: ProductDetailed) {
        viewModelScope.launch {
            insertProductInLocalUseCase(product = product.toDomain())
        }
    }

    private fun errorMessage(message: String?) {
        _productState.update { currentState -> currentState.copy(errorMessage = message) }
    }
}