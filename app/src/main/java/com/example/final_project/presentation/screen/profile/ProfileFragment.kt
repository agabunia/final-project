package com.example.final_project.presentation.screen.profile

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.final_project.databinding.FragmentProfileBinding
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.extensions.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val profileViewModel: ProfileViewModel by viewModels()
    override fun bind() {
    }

    override fun bindListeners() {
        binding.tvProfile.setOnClickListener{
            navigateToEditProfileFragment()
        }
        binding.btnEditProfile.setOnClickListener{
            navigateToEditProfileFragment()
        }
        binding.tvAddress.setOnClickListener{
            navigateToAddressFragment()
        }
        binding.btnAddress.setOnClickListener{
            navigateToAddressFragment()
        }
        binding.tvPayment.setOnClickListener{
            navigateToPaymentFragment()
        }
        binding.btnPayment.setOnClickListener{
            navigateToPaymentFragment()
        }
        binding.tvSecurity.setOnClickListener{
            navigateToSecurityFragment()
        }
        binding.btnSecurity.setOnClickListener{
            navigateToSecurityFragment()
        }
        binding.tvPrivacy.setOnClickListener{
            navigateToPrivacyFragment()
        }
        binding.btnPrivacy.setOnClickListener{
            navigateToPrivacyFragment()
        }
        binding.tvLogout.setOnClickListener {
            logout()
        }
    }

    override fun bindObserves() {
        lifecycleScope.launch {
            profileViewModel.name.collect { name ->
                binding.tvUsername.text = name
            }
        }

        lifecycleScope.launch {
            profileViewModel.phone.collect { phone ->
                binding.phone.text = phone
            }
        }
    }

    private fun navigateToEditProfileFragment(){
        val action = ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment()
        findNavController().navigate(action)
    }

    private fun navigateToAddressFragment(){
        val action = ProfileFragmentDirections.actionProfileFragmentToAddressFragment()
        findNavController().navigate(action)
    }

    private fun navigateToPaymentFragment(){
        val action = ProfileFragmentDirections.actionProfileFragmentToPaymentFragment()
        findNavController().navigate(action)
    }

    private fun navigateToSecurityFragment(){
        val action = ProfileFragmentDirections.actionProfileFragmentToSecurityFragment()
        findNavController().navigate(action)
    }

    private fun navigateToPrivacyFragment(){
        val action = ProfileFragmentDirections.actionProfileFragmentToPrivacyFragment()
        findNavController().navigate(action)
    }

    private fun logout(){
    }
}