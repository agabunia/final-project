package com.example.final_project.presentation.screen.favorites

import com.example.final_project.databinding.FragmentFavoritesBinding
import com.example.final_project.presentation.MainActivity
import com.example.final_project.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {
    override fun bind() {
        (activity as? MainActivity)?.showBottomNavigationBar()
    }

    override fun bindListeners() {
    }

    override fun bindObserves() {
    }

}