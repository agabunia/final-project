package com.example.final_project.presentation.screen.search

import android.util.Log.d
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.databinding.FragmentSearchBinding
import com.example.final_project.presentation.MainActivity
import com.example.final_project.presentation.adapter.search.CategoryRecyclerAdapter
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.event.login.LoginEvent
import com.example.final_project.presentation.event.search.SearchEvent
import com.example.final_project.presentation.state.search.SearchState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var categoryRecyclerAdapter: CategoryRecyclerAdapter


    override fun bind() {
        (activity as? MainActivity)?.showBottomNavigationBar()
        setCategoryAdapter()
    }

    override fun bindListeners() {
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchState.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun setCategoryAdapter() {
        categoryRecyclerAdapter = CategoryRecyclerAdapter()
        binding.apply {
            rvCategory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvCategory.setHasFixedSize(true)
            rvCategory.adapter = categoryRecyclerAdapter
        }
        viewModel.onEvent(SearchEvent.FetchCategory)
        d("testSearchFragment", "setCategoryAdapter is initialised")
    }

    private fun handleState(state: SearchState) {
        state.category?.let {
            categoryRecyclerAdapter.submitList(it)
            d("testSearchFragment", "submitted list $it")
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

}