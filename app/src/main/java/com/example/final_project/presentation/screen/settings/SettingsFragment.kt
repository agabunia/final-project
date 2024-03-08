package com.example.final_project.presentation.screen.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.final_project.R
import com.example.final_project.databinding.FragmentSettingsBinding
import com.example.final_project.presentation.MainActivity
import com.example.final_project.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    override fun bind() {
        (activity as? MainActivity)?.showBottomNavigationBar()
    }

    override fun bindListeners() {
    }

    override fun bindObserves() {
    }

}