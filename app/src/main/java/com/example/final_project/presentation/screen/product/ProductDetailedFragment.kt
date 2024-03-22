package com.example.final_project.presentation.screen.product

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.final_project.R
import com.example.final_project.databinding.FragmentProductDetailedBinding
import com.example.final_project.presentation.MainActivity
import com.example.final_project.presentation.adapter.product.ImageSlideViewPagerAdapter
import com.example.final_project.presentation.adapter.product.StarRecyclerAdapter
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.event.product.ProductEvent
import com.example.final_project.presentation.event.search.SearchEvent
import com.example.final_project.presentation.model.product.ProductDetailed
import com.example.final_project.presentation.screen.search.SearchViewModel
import com.example.final_project.presentation.state.product.ProductState
import com.example.final_project.presentation.state.search.SearchState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailedFragment :
    BaseFragment<FragmentProductDetailedBinding>(FragmentProductDetailedBinding::inflate) {
    private val viewModel: ProductDetailedViewModel by viewModels()
    private lateinit var imageSlideViewPagerAdapter: ImageSlideViewPagerAdapter
    private lateinit var starRecyclerAdapter: StarRecyclerAdapter
    private val productArgs: ProductDetailedFragmentArgs by navArgs()

    private var amount: Int = 0
    private var stockAmount: Int = 0

    override fun bind() {
        (activity as? MainActivity)?.hideBottomNavigationBar()
        viewModel.onEvent(ProductEvent.FetchProductDetailed(productArgs.id))
    }

    override fun bindListeners() {
        binding.btnBack.setOnClickListener {
            goBack()
        }

        binding.btnMinus.setOnClickListener {
            decreaseQuantity()
        }

        binding.btnPlus.setOnClickListener {
            increaseQuantity()
        }

        binding.btnBuyNow.setOnClickListener {
            val quantity = binding.tvQuantity.text.toString().toInt()
            viewModel.onEvent(ProductEvent.BuyProduct(quantity * amount))
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productState.collect {
                    handleState(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleUiState(it)
                }
            }
        }
    }

    private fun handleState(state: ProductState) {
        state.productDetails?.let {
            bindProductItems(it)
        }

        state.errorMessage?.let {
            toastMessage(it)
            viewModel.onEvent(ProductEvent.ResetErrorMessage)
        }

        binding.progressBar.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE
    }

    private fun handleUiState(event: ProductDetailedViewModel.UIEvent) {
        when (event) {
            is ProductDetailedViewModel.UIEvent.navigateToPayment -> navigateToPayment(event.isSuccessful)
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun bindProductItems(product: ProductDetailed) {
        binding.apply {
            tvProductTitle.text = product.title
            tvProductBrand.text = product.brand
            tvProductCategory.text = product.category
            tvProductPrice.text = product.price.toString()
            tvProductDescription.text = product.description
            tvProductDiscountRate.text = product.discountPercentage.toString()
            tvProductStock.text = product.stock.toString()

            amount = product.price
            stockAmount = product.stock

            setViewPagerAdapter(product.images)
            setStarRecyclerAdapter(product.rating)

            btnAddToWishlist.setOnClickListener {
                viewModel.onEvent(ProductEvent.SaveProduct(product))
            }
        }
    }

    private fun setViewPagerAdapter(images: List<String>) {
        imageSlideViewPagerAdapter = ImageSlideViewPagerAdapter(images)
        binding.apply {
            viewPager.adapter = imageSlideViewPagerAdapter
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    private fun setStarRecyclerAdapter(stars: Int) {
        starRecyclerAdapter = StarRecyclerAdapter(stars)
        binding.apply {
            rvStar.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvStar.setHasFixedSize(true)
            rvStar.adapter = starRecyclerAdapter
        }
    }

    private fun increaseQuantity() {
        var quantity = binding.tvQuantity.text.toString().toInt()
        if (quantity < stockAmount) {
            quantity += 1
        }
        binding.tvQuantity.text = quantity.toString()
    }

    private fun decreaseQuantity() {
        var quantity = binding.tvQuantity.text.toString().toInt()
        if (quantity > 1) {
            quantity -= 1
        }
        binding.tvQuantity.text = quantity.toString()
    }

    private fun goBack() {
        findNavController().popBackStack()
    }

    private fun navigateToPayment(isSuccessful: Boolean) {
        val action =
            ProductDetailedFragmentDirections.actionProductDetailedFragmentToPaymentFragment(
                isSuccessful
            )
        findNavController().navigate(action)
    }

}