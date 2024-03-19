package com.example.final_project.presentation.screen.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.domain.local.usecase.db_manipulators.DecreaseLocalProductQuantityUseCase
import com.example.final_project.domain.local.usecase.db_manipulators.DeleteAllLocalProductsUseCase
import com.example.final_project.domain.local.usecase.db_manipulators.DeleteLocalProductUseCase
import com.example.final_project.domain.local.usecase.db_manipulators.GetAllLocalProductsUseCase
import com.example.final_project.domain.local.usecase.db_manipulators.GetSumOfAllLocalProductsUseCase
import com.example.final_project.domain.local.usecase.db_manipulators.IncreaseLocalProductQuantityUseCase
import com.example.final_project.presentation.event.wishlist.WishlistEvent
import com.example.final_project.presentation.mapper.wishlist.toDomain
import com.example.final_project.presentation.mapper.wishlist.toPresenter
import com.example.final_project.presentation.model.wishlist.WishlistProduct
import com.example.final_project.presentation.state.wishlist.WishlistState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val getAllLocalProductsUseCase: GetAllLocalProductsUseCase,
    private val deleteAllLocalProductsUseCase: DeleteAllLocalProductsUseCase,
    private val deleteLocalProductUseCase: DeleteLocalProductUseCase,
    private val increaseLocalProductQuantityUseCase: IncreaseLocalProductQuantityUseCase,
    private val decreaseLocalProductQuantityUseCase: DecreaseLocalProductQuantityUseCase,
    private val getSumOfAllLocalProductsUseCase: GetSumOfAllLocalProductsUseCase
) : ViewModel() {

    private val _wishlistState = MutableStateFlow(WishlistState())
    val wishlistState: SharedFlow<WishlistState> = _wishlistState.asStateFlow()

    fun onEvent(event: WishlistEvent) {
        when (event) {
            is WishlistEvent.FetchAllProducts -> fetchAllProducts()
            is WishlistEvent.DeleteAllItem -> deleteAllProducts()
            is WishlistEvent.DeleteItem -> deleteProduct(product = event.product)
            is WishlistEvent.IncreaseItemQuantity -> increaseQuantity(id = event.id)
            is WishlistEvent.DecreaseItemQuantity -> decreaseQuantity(id = event.id)
            is WishlistEvent.ResetErrorMessage -> errorMessage(message = null)
        }
    }

    init {
        getTotalPrice()
    }

    private fun fetchAllProducts() {
        viewModelScope.launch {
            getAllLocalProductsUseCase().collect { list ->
                if (list.isEmpty()) {
                    errorMessage(message = "The Wishlist Is Empty")
                } else {
                    _wishlistState.update { currentState ->
                        currentState.copy(productsList = list.map {
                            it.toPresenter()
                        })
                    }
                }
            }
        }
    }

    private fun getTotalPrice() {
        viewModelScope.launch {
            getSumOfAllLocalProductsUseCase().collect {
                _wishlistState.update { currentState ->
                    currentState.copy(productsTotalSum = it)
                }
            }
        }
    }

    private fun deleteAllProducts() {
        viewModelScope.launch {
            deleteAllLocalProductsUseCase()
        }
    }

    private fun deleteProduct(product: WishlistProduct) {
        viewModelScope.launch {
            deleteLocalProductUseCase(product = product.toDomain())
        }
    }

    private fun increaseQuantity(id: Int) {
        viewModelScope.launch {
            increaseLocalProductQuantityUseCase(id = id)
        }
    }

    private fun decreaseQuantity(id: Int) {
        viewModelScope.launch {
            decreaseLocalProductQuantityUseCase(id = id)
        }
    }

    private fun errorMessage(message: String?) {
        _wishlistState.update { currentState -> currentState.copy(errorMessage = message) }
    }

}