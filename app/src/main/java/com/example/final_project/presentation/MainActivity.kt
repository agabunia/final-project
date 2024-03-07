package com.example.final_project.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.final_project.R
import com.example.final_project.databinding.ActivityMainBinding
import com.example.final_project.presentation.screen.home.HomeFragment
import com.example.final_project.presentation.screen.library.LibraryFragment
import com.example.final_project.presentation.screen.profile.ProfileFragment
import com.example.final_project.presentation.screen.search.SearchFragment
import com.example.final_project.presentation.screen.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavigationListener()
        setDrawerNavigationListener()
    }

    fun hideBottomNavigationBar() {
        binding.bottomNavigation.visibility = View.GONE
    }

    fun showBottomNavigationBar() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    // ესეც გასასწორებელია ალბათ. სპლეშში რეგისტრაცია და ლოგინში არჩანს მაგრამ არაა
    // კარგად დაჰენდლილი.
    private fun bottomNavigationListener() {
        binding.bottomNavigation.selectedItemId = R.id.homeFragment
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.searchFragment -> replaceFragment(SearchFragment())
                R.id.homeFragment -> replaceFragment(HomeFragment())
                R.id.libraryFragment -> replaceFragment(LibraryFragment())
                R.id.profileFragment -> replaceFragment(ProfileFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }


    // ეს გასასწორებელია რომ სპლეშში, რეგისტრაციაში და ლოგინში არ გამოჩნდეს. რატომღაც მგონია რომ
    // ინდივიდუალურად უნდა გავუწეროთ ფრაგმენტებს
    private fun setDrawerNavigationListener() {
        binding.drawerMenu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.settingsFragment -> replaceFragment(SettingsFragment())
            }
            true
        }
    }

}