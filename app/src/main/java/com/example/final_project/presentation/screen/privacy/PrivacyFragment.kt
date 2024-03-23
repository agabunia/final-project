package com.example.final_project.presentation.screen.privacy

import androidx.navigation.fragment.findNavController
import com.example.final_project.databinding.FragmentPrivacyBinding
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.screen.address.AddressFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrivacyFragment : BaseFragment<FragmentPrivacyBinding>(FragmentPrivacyBinding::inflate) {
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
        val action = PrivacyFragmentDirections.actionPrivacyFragmentToProfileFragment()
        findNavController().navigate(action)
    }

}