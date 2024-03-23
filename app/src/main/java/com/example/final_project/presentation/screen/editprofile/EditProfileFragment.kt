package com.example.final_project.presentation.screen.editprofile

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.final_project.databinding.FragmentEditProfileBinding
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.screen.profile.ProfileFragmentDirections
import com.example.final_project.presentation.screen.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment :
    BaseFragment<FragmentEditProfileBinding>(FragmentEditProfileBinding::inflate) {
    private val profileViewModel: ProfileViewModel by viewModels()


    override fun bind() {
    }

    override fun bindListeners() {
        binding.btnBackArrow.setOnClickListener {
            navigateToProfileFragment()
        }
        binding.btnSaveChanges.setOnClickListener {
            navigateToProfileFragment()
        }


    }

    override fun bindObserves() {
    }

    private fun navigateToProfileFragment() {
        val defaultName = binding.tvName.text.toString()
        val bundle = Bundle().apply {
            putString("Default Name", defaultName)
        }
        val defaultPhone = binding.tvPhoneNumber.text.toString()

        profileViewModel.setNameAndPhone(defaultName, defaultPhone)

        val action = EditProfileFragmentDirections.actionEditProfileFragmentToProfileFragment()
        findNavController().navigate(action)
    }

}