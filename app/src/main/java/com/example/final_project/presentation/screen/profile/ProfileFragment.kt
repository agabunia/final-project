package com.example.final_project.presentation.screen.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.final_project.databinding.FragmentProfileBinding
import com.example.final_project.presentation.MainActivity
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.event.profile.ProfileEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val viewModel: ProfileViewModel by viewModels()

    override fun bind() {
        (activity as? MainActivity)?.showBottomNavigationBar()
    }

    override fun bindListeners() {
        binding.layoutLogout.setOnClickListener {
            viewModel.onEvent(ProfileEvent.LogOut)
        }

        binding.layoutTerms.setOnClickListener {
            viewModel.onEvent(ProfileEvent.Terms)
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleEvent(it)
                }
            }
        }
    }

    private fun handleEvent(event: ProfileViewModel.UiEvent) {
        when (event) {
            is ProfileViewModel.UiEvent.NavigateToLogin -> navigateToLogin()
            is ProfileViewModel.UiEvent.NavigateToTerms -> navigateToTerms()
        }
    }

    private fun navigateToLogin() {
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
    }

    private fun navigateToTerms() {
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToTermsFragment())
    }

}