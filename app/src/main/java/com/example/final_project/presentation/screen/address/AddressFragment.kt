package com.example.final_project.presentation.screen.address

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.final_project.databinding.FragmentAddressBinding
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.extensions.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressFragment : BaseFragment<FragmentAddressBinding>(FragmentAddressBinding::inflate) {
    override fun bind() {
    }

    override fun bindListeners() {
        binding.addNewAddress.setOnClickListener {
            navigateToMapsFragment()
        }
    }

    override fun bindObserves() {
    }

    private fun navigateToMapsFragment() {
        val action = AddressFragmentDirections.actionAddressFragmentToMapsFragment()
        findNavController().navigate(action)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions -> }


    private fun requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
            }
        }
    }

    private fun showPermissionDeniedMessage() {
        binding.root.showSnackBar("Location permission is required for this App to properly work")
    }

}