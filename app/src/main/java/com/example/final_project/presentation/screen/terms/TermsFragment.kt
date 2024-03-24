package com.example.final_project.presentation.screen.terms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.final_project.R
import com.example.final_project.databinding.FragmentTermsBinding
import com.example.final_project.presentation.MainActivity
import com.example.final_project.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TermsFragment : BaseFragment<FragmentTermsBinding>(FragmentTermsBinding::inflate) {

    override fun bind() {
        (activity as? MainActivity)?.hideBottomNavigationBar()
    }

    override fun bindListeners() {
        binding.btnBack.setOnClickListener {
            navigateToProfile()
        }
    }

    override fun bindObserves() {
    }

    private fun navigateToProfile() {
        findNavController().navigate(TermsFragmentDirections.actionTermsFragmentToProfileFragment())
    }


}