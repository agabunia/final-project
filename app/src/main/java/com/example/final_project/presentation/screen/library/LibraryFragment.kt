package com.example.final_project.presentation.screen.library

import androidx.fragment.app.Fragment
import com.example.final_project.R
import com.example.final_project.databinding.FragmentLibraryBinding
import com.example.final_project.presentation.MainActivity
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.screen.profile.ProfileFragment
import com.example.final_project.presentation.screen.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LibraryFragment : BaseFragment<FragmentLibraryBinding>(FragmentLibraryBinding::inflate) {
    override fun bind() {
        (activity as? MainActivity)?.showBottomNavigationBar()
    }

    override fun bindListeners() {
    }

    override fun bindObserves() {
    }

}