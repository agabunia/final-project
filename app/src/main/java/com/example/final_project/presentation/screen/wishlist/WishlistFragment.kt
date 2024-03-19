package com.example.final_project.presentation.screen.wishlist

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.R
import com.example.final_project.databinding.FragmentWishlistBinding
import com.example.final_project.presentation.adapter.wishlist.WishlistProductRecyclerAdapter
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.event.wishlist.WishlistEvent
import com.example.final_project.presentation.state.wishlist.WishlistState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WishlistFragment : BaseFragment<FragmentWishlistBinding>(FragmentWishlistBinding::inflate) {
    private lateinit var wishlistProductRecyclerAdapter: WishlistProductRecyclerAdapter
    private val viewModel: WishlistViewModel by viewModels()

    override fun bind() {
        setWishlistProductAdapter()
    }

    override fun bindListeners() {
        binding.btnDeleteAll.setOnClickListener {
            viewModel.onEvent(WishlistEvent.DeleteAllItem)
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.wishlistState.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun setWishlistProductAdapter() {
        wishlistProductRecyclerAdapter = WishlistProductRecyclerAdapter()
        wishlistProductRecyclerAdapter.apply {
            onDeleteClick = { viewModel.onEvent(WishlistEvent.DeleteItem(it)) }
            onMinusClick = { viewModel.onEvent(WishlistEvent.DecreaseItemQuantity(it)) }
            onPlusClick = { viewModel.onEvent(WishlistEvent.IncreaseItemQuantity(it)) }
        }
        binding.apply {
            rvWishlistProducts.layoutManager = LinearLayoutManager(requireContext())
            rvWishlistProducts.setHasFixedSize(true)
            rvWishlistProducts.adapter = wishlistProductRecyclerAdapter
        }
        viewModel.onEvent(WishlistEvent.FetchAllProducts)
    }

    private fun handleState(state: WishlistState) {
        state.productsList?.let {
            wishlistProductRecyclerAdapter.submitList(it)
        }

        state.errorMessage?.let {
            toastMessage(it)
            viewModel.onEvent(WishlistEvent.ResetErrorMessage)
        }

        state.productsTotalSum?.let {
            val buttonText = "${resources.getText(R.string.buy_now_price)} $it"
            binding.btnBuyNow.text = buttonText
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}