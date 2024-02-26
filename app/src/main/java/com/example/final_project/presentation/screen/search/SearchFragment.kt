package com.example.final_project.presentation.screen.search

import androidx.fragment.app.Fragment
import com.example.final_project.R
import com.example.final_project.databinding.FragmentSearchBinding
import com.example.final_project.presentation.MainActivity
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.screen.account.AccountFragment
import com.example.final_project.presentation.screen.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    override fun bind() {
        (activity as? MainActivity)?.showBottomNavigationBar()
    }

    override fun bindListeners() {
        setDrawerNavigationListener()
    }

    override fun bindObserves() {
    }

    private fun setDrawerNavigationListener() {
        binding.drawerMenu.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.accountFragment -> replaceFragment(AccountFragment())
                R.id.settingsFragment -> replaceFragment(SettingsFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }

}