package com.example.final_project.presentation.screen.search

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.final_project.databinding.FragmentSearchBinding
import com.example.final_project.presentation.MainActivity
import com.example.final_project.presentation.adapter.common_product_adapter.ProductRecyclerAdapter
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.event.search.SearchEvent
import com.example.final_project.presentation.extention.hideKeyboard
import com.example.final_project.presentation.state.search.SearchState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var productRecyclerAdapter: ProductRecyclerAdapter


    override fun bind() {
        (activity as? MainActivity)?.showBottomNavigationBar()
        setProductAdapter()
    }

    override fun bindListeners() {
        binding.btnSearch.setOnClickListener {
            val search = binding.etSearch.text.toString()
            if (search.isNotEmpty()) {
                viewModel.onEvent(SearchEvent.FetchSearchProducts(search))
                binding.etSearch.text?.clear().toString()
            } else {
                viewModel.onEvent(SearchEvent.FetchAllProducts)
            }
            it.hideKeyboard()
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchState.collect {
                    handleState(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleNavigationEvent(it)
                }
            }
        }
    }

    private fun setProductAdapter() {
        productRecyclerAdapter = ProductRecyclerAdapter()
        productRecyclerAdapter.onItemClick = {
            navigateToProductDetails(it)
            d("searchFragmentTestClick", "$it")
        }
        productRecyclerAdapter.saveProductClick = {
            viewModel.onEvent(SearchEvent.SaveProduct(it))
        }
        binding.apply {
            rvProduct.layoutManager = GridLayoutManager(requireContext(), 2)
            rvProduct.setHasFixedSize(true)
            rvProduct.adapter = productRecyclerAdapter
        }
        viewModel.onEvent(SearchEvent.FetchAllProducts)
    }

    private fun handleState(state: SearchState) {
        state.productsList?.let {
            productRecyclerAdapter.submitList(it)
        }

        state.errorMessage?.let {
            toastMessage(it)
            viewModel.onEvent(SearchEvent.ResetErrorMessage)
        }

        binding.progressBar.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun handleNavigationEvent(event: SearchViewModel.SearchUIEvent) {
        when (event) {
            is SearchViewModel.SearchUIEvent.NavigateToDetailed -> navigateToProductDetails(id = event.id)
        }
    }

    private fun navigateToProductDetails(id: Int) {
        val action = SearchFragmentDirections.actionSearchFragmentToProductDetailedFragment(id = id)
        findNavController().navigate(action)
    }

}