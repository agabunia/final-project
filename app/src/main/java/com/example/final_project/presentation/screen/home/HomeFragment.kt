package com.example.final_project.presentation.screen.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.final_project.R
import com.example.final_project.databinding.FragmentHomeBinding
import com.example.final_project.presentation.MainActivity
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.screen.profile.ProfileFragment
import com.example.final_project.presentation.screen.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()

    override fun bind() {
        (activity as? MainActivity)?.showBottomNavigationBar()
    }

    override fun bindListeners() {
    }

    override fun bindObserves() {
    }

}
