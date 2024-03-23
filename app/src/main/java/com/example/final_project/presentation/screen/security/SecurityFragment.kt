package com.example.final_project.presentation.screen.security

import androidx.navigation.fragment.findNavController
import com.example.final_project.databinding.FragmentSecurityBinding
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.event.maps.MapsEvents
import com.example.final_project.presentation.screen.address.AddressFragmentDirections
import com.example.final_project.presentation.screen.map.BottomSheetMapsFragment
import com.example.final_project.presentation.screen.profile.ProfileFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecurityFragment : BaseFragment<FragmentSecurityBinding>(FragmentSecurityBinding::inflate) {
    override fun bind() {

    }

    override fun bindListeners() {
        binding.btnBackArrow.setOnClickListener {
            navigateToProfileFragment()
        }


    }

    override fun bindObserves() {

    }

    private fun navigateToProfileFragment(){
        val action = SecurityFragmentDirections.actionSecurityFragmentToProfileFragment()
        findNavController().navigate(action)
    }

}