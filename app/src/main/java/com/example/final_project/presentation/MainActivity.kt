package com.example.final_project.presentation

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.final_project.R
import com.example.final_project.databinding.ActivityMainBinding
import com.example.final_project.databinding.FragmentProductDetailedBinding
import com.example.final_project.presentation.screen.home.HomeFragmentDirections
import com.example.final_project.presentation.screen.product.ProductDetailedFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigation()
        readPushToken()
        navigateFromPushNotification()
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

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermission.launch(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
        }
    }

    private fun readPushToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                d("Fetching FCM registration token failed", task.exception.toString())
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            d("firebaseToken", token)
        })
    }

    private fun navigateFromPushNotification() {
//        val redirect = intent.getStringExtra("product_id")
//        if (redirect != null) {
//            d("receivedProductId", redirect)
//        }
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.fragmentContainerView, ProductDetailedFragment())
//            commit()
//        }
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