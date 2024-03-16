package com.example.final_project.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.final_project.R
import com.example.final_project.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigation()
    }

    fun hideBottomNavigationBar() {
        binding.bottomNavigation.visibility = View.GONE
    }

    fun showBottomNavigationBar() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun setNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(
            navController
        )
    }


    // ეს გასასწორებელია რომ სპლეშში, რეგისტრაციაში და ლოგინში არ გამოჩნდეს. რატომღაც მგონია რომ
    // ინდივიდუალურად უნდა გავუწეროთ ფრაგმენტებს
//    private fun setDrawerNavigationListener() {
//        binding.drawerMenu.setNavigationItemSelectedListener {
//            true
//        }
//    }


    //  შემდეგი სქრიუნიდან გადავცემ წინა სქრინს არგუმენტს
//    fun <T> Fragment.navigationResult(key: String) =
//        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
//
//    fun <T> Fragment.setNavigationResult(key: String, result: T) {
//        findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
//    }


}