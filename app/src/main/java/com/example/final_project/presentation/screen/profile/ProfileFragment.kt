package com.example.final_project.presentation.screen.profile

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.final_project.databinding.FragmentProfileBinding
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.extensions.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
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