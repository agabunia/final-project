package com.example.final_project.presentation.screen.home

import android.app.LocaleManager
import android.os.Build
import android.os.LocaleList
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.os.LocaleListCompat
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
import com.example.final_project.presentation.screen.search.SearchFragmentDirections
import com.example.final_project.presentation.screen.search.SearchViewModel
import com.example.final_project.presentation.state.app_state.AppState
import com.example.final_project.presentation.state.home.HomeState
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var wrapperRecyclerAdapter: WrapperRecyclerAdapter

    private lateinit var switchTheme: SwitchCompat
    private lateinit var switchLanguage: SwitchCompat

    override fun bind() {
        (activity as? MainActivity)?.showBottomNavigationBar()
        setWrapperAdapter()
        setChangeSwitch()
    }

    override fun bindListeners() {
        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            changeTheme(isLight = isChecked)
        }

        switchLanguage.setOnCheckedChangeListener { _, isChecked ->
            changeLanguage(isGeorgian = isChecked)
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect {
                    handleState(state = it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.appState.collect {
                    handleAppStateChange(it)
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

    private fun setWrapperAdapter() {
        wrapperRecyclerAdapter = WrapperRecyclerAdapter()
        wrapperRecyclerAdapter.onWrapperItemClick = {
            viewModel.onEvent(HomeEvent.MoveToDetailed(id = it))
        }
        wrapperRecyclerAdapter.onWrapperSaveProductClick = {
            viewModel.onEvent(HomeEvent.SaveProduct(it))
        }
        binding.apply {
            rvWrapper.layoutManager = LinearLayoutManager(requireContext())
            rvWrapper.setHasFixedSize(true)
            rvWrapper.adapter = wrapperRecyclerAdapter
        }
        viewModel.onEvent(HomeEvent.FetchCategoryList) // saxeli shevucvalo
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


    private fun setChangeSwitch() {
        val navigationView = activity?.findViewById<NavigationView>(R.id.drawerMenu)

        switchTheme =
            navigationView?.menu?.findItem(R.id.themeMode)?.actionView?.findViewById(R.id.switchTheme)!!
        switchLanguage =
            navigationView.menu.findItem(R.id.language)?.actionView?.findViewById(R.id.switchLanguage)!!
    }

    private fun changeTheme(isLight: Boolean) {
        viewModel.onEvent(HomeEvent.ChangeTheme(isLight = isLight))
    }

    private fun changeLanguage(isGeorgian: Boolean) {
        viewModel.onEvent(HomeEvent.ChangeLanguage(isGeorgian = isGeorgian))
    }

    private fun handleAppStateChange(state: AppState) {
        if (state.isLight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            switchTheme.isChecked = true
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            switchTheme.isChecked = false
        }

        if (state.isGeorgian) {
            switchLanguage.isChecked = true
        } else {
            switchLanguage.isChecked = false
        }

        changeLanguageConfig(state.isGeorgian)

    }

    private fun changeLanguageConfig(isGeorgian: Boolean) {
        val language = if (isGeorgian) "ka" else "en"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context?.getSystemService(LocaleManager::class.java)
                ?.applicationLocales = LocaleList.forLanguageTags(language)
        } else {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(
                    language
                )
            )
        }
    }

    private fun handleNavigationEvent(event: HomeViewModel.UIEvent) {
        when (event) {
            is HomeViewModel.UIEvent.NavigateToDetailed -> navigateToProductDetails(id = event.id)
        }
    }

    private fun navigateToProductDetails(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailedFragment(id = id)
        findNavController().navigate(action)
    }

}
