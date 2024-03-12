package com.example.final_project.presentation.screen.home

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.R
import com.example.final_project.databinding.FragmentHomeBinding
import com.example.final_project.presentation.MainActivity
import com.example.final_project.presentation.adapter.home.WrapperRecyclerAdapter
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.event.home.HomeEvent
import com.example.final_project.presentation.model.home.CategoryList
import com.example.final_project.presentation.screen.profile.ProfileFragment
import com.example.final_project.presentation.screen.search.SearchFragmentDirections
import com.example.final_project.presentation.screen.settings.SettingsFragment
import com.example.final_project.presentation.state.home.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var wrapperRecyclerAdapter: WrapperRecyclerAdapter

    override fun bind() {
        (activity as? MainActivity)?.showBottomNavigationBar()
        setWrapperAdapter()
    }

    override fun bindListeners() {
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect {
                    handleState(state = it)
                }
            }
        }
    }

    private fun setWrapperAdapter() {
        wrapperRecyclerAdapter = WrapperRecyclerAdapter()
        wrapperRecyclerAdapter.onWrapperItemClick = {
            navigateToProductDetails(id = it)
        }
        binding.apply {
            rvWrapper.layoutManager = LinearLayoutManager(requireContext())
            rvWrapper.setHasFixedSize(true)
            rvWrapper.adapter = wrapperRecyclerAdapter
        }
        viewModel.onEvent(
            HomeEvent.FetchProducts(
                listOf(
                    "smartphones",
                    "laptops",
                    "groceries",
                    "womens-dresses",
                    "womens-bags"
                )
            )
        )
    }

    private fun handleState(state: HomeState) {
        binding.progressBar.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.productsList?.let {
            wrapperRecyclerAdapter.submitList(it)
        }

        state.errorMessage?.let {
            toastMessage(it)
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun navigateToProductDetails(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailedFragment(id = id)
        findNavController().navigate(action)
    }

}
