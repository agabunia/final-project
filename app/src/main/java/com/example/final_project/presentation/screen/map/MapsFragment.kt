package com.example.final_project.presentation.screen.map

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.final_project.R
import com.example.final_project.databinding.FragmentMapsBinding
import com.example.final_project.presentation.base.BaseFragment
import com.example.final_project.presentation.event.maps.MapsEvents
import com.example.final_project.presentation.extensions.showSnackBar
import com.example.final_project.presentation.state.maps.MapsState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapsFragment : BaseFragment<FragmentMapsBinding>(FragmentMapsBinding::inflate),
    OnMapReadyCallback,
    OnSearchButton {

    private val viewModel: MapsViewModel by viewModels()

    private lateinit var mMap: GoogleMap

    override fun bind() {
        googleMapsSetup()
    }

    override fun bindListeners() {
        onLocationButtonClicked()
    }

    override fun bindObserves() {
        observeUserLocation()
        observePlaceLocation()
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
    }

    override fun locationSearchButtonListener(location: String) {
        Log.d("MapsFragment", "Location query: $location")
        viewModel.onEvent(MapsEvents.GetPlaceLocation(location))
    }

    private fun observeUserLocation() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userLocation.collect {
                    handleMapsState(it)
                }
            }
        }
    }

    private fun observePlaceLocation() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.placeLocation.collect {
                    handleMapsState(it)
                }
            }
        }
    }

    private fun handleMapsState(state: MapsState) {
        state.let { mapsState ->
            mapsState.userLocation?.let { userLocation ->
                val userLatLng = LatLng(userLocation.latitude, userLocation.longitude)
                mMap.addMarker(MarkerOptions().position(userLatLng).title("User Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLatLng))
            }
            mapsState.placeLocation?.let { placeLocation ->
                Log.d("MapsFragment", "Place location: $placeLocation")
                val placeLatLng = LatLng(placeLocation.latitude, placeLocation.longitude)
                Log.d("MapsFragment", "Place LatLng: $placeLatLng")
                mMap.addMarker(MarkerOptions().position(placeLatLng).title("Place Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(placeLatLng))
            }
            mapsState.errorMessage?.let { errorMessage ->
                binding.root.showSnackBar(errorMessage)
            }
        }
    }

    private fun googleMapsSetup() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun onLocationButtonClicked() {
        binding.btnSave.setOnClickListener {
            viewModel.onEvent(MapsEvents.GetUserLocation)
        }
    }
}


//import android.Manifest
//import android.content.pm.PackageManager
//import android.location.Location
//import android.util.Log
//import androidx.core.app.ActivityCompat
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.Lifecycle
//import androidx.lifecycle.lifecycleScope
//import androidx.lifecycle.repeatOnLifecycle
//import com.example.final_project.R
//import com.example.final_project.databinding.FragmentMapsBinding
//import com.example.final_project.presentation.base.BaseFragment
//import com.example.final_project.presentation.event.maps.MapsEvents
//import com.example.final_project.presentation.extensions.showSnackBar
//import com.example.final_project.presentation.state.maps.MapsState
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationServices
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.MarkerOptions
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.launch
//
//@AndroidEntryPoint
//class MapsFragment : BaseFragment<FragmentMapsBinding>(FragmentMapsBinding::inflate),
//    OnMapReadyCallback,
//    OnSearchButton {
//
//    private var map: GoogleMap? = null
//    private var mapReady: Boolean = false
//    private lateinit var currentLocation: Location
//    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
//
//    private val latitude = arguments?.getInt("latitude")?.toDouble()
//    private val longitude = arguments?.getInt("longitude")?.toDouble()
//
//
//    override fun bind() {
//        fusedLocationProviderClient =
//            LocationServices.getFusedLocationProviderClient(requireContext())
//        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
//
//        if (latitude != null && longitude != null) {
//            moveMapToCurrentLocation(latitude, longitude)
//        }
//    }
//
//    override fun bindListeners() {
//        binding.btnSave.setOnClickListener {
//            getCurrentLocationAndMoveMap()
//        }
//    }
//
//    override fun bindObserves() {
//    }
//
//    override fun onMapReady(googleMap: GoogleMap) {
//        map = googleMap
//        mapReady = true
//        getCurrentLocationAndMoveMap()
//    }
//
//    private fun getCurrentLocationAndMoveMap() {
//        if (ActivityCompat.checkSelfPermission(
//                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                REQUEST_LOCATION_PERMISSION
//            )
//            return
//        }
//
//        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
//            if (location != null) {
//                currentLocation = location
//                moveMapToCurrentLocation(currentLocation.latitude, currentLocation.longitude)
//            }
//        }
//    }
//
//    companion object {
//        private const val REQUEST_LOCATION_PERMISSION = 100
//    }
//
//    private fun moveMapToCurrentLocation(latitude: Double, longitude: Double) {
//        val latLng = LatLng(latitude, longitude)
//        val markerOptions = MarkerOptions().position(latLng).title("CurrentLocation")
//        map?.clear()
//        map?.addMarker(markerOptions)
//        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
//    }
//
//    override fun locationSearchButtonListener(location: String) {
//
//    }
//
//    private val viewModel: MapsViewModel by viewModels()
//
//    private lateinit var mMap: GoogleMap
//
//    override fun bind() {
//        googleMapsSetup()
//    }
//
//    override fun bindListeners() {
//        onSearchButtonClicked()
//        onLocationButtonClicked()
//    }
//
//    override fun bindObserves() {
//        observeUserLocation()
//        observePlaceLocation()
//    }
//
//    override fun onMapReady(p0: GoogleMap) {
//        mMap = p0
//    }
//
//    override fun locationSearchButtonListener(location: String) {
//        Log.d("MapsFragment", "Location query: $location")
//        viewModel.onEvent(MapsEvents.GetPlaceLocation(location))
//    }
//
//    private fun googleMapsSetup() {
//        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
//        mapFragment?.getMapAsync(this)
//    }
//
//    private fun onSearchButtonClicked() {
//    }
//
//    private fun onLocationButtonClicked() {
//        binding.btnSave.setOnClickListener {
//            viewModel.onEvent(MapsEvents.GetUserLocation)
//        }
//    }
//
//    private fun observeUserLocation() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.userLocation.collect {
//                    handleMapsState(it)
//                }
//            }
//        }
//    }
//
//    private fun observePlaceLocation() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.placeLocation.collect {
//                    handleMapsState(it)
//                }
//            }
//        }
//    }
//
//    private fun handleMapsState(state: MapsState) {
//        state.let { mapsState ->
//            mapsState.userLocation?.let { userLocation ->
//                val userLatLng = LatLng(userLocation.latitude, userLocation.longitude)
//                mMap.addMarker(MarkerOptions().position(userLatLng).title("User Location"))
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLatLng))
//            }
//            mapsState.placeLocation?.let { placeLocation ->
//                Log.d("MapsFragment", "Place location: $placeLocation")
//                val placeLatLng = LatLng(placeLocation.latitude, placeLocation.longitude)
//                Log.d("MapsFragment", "Place LatLng: $placeLatLng")
//                mMap.addMarker(MarkerOptions().position(placeLatLng).title("Place Location"))
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(placeLatLng))
//            }
//            mapsState.errorMessage?.let { errorMessage ->
//                binding.root.showSnackBar(errorMessage)
//            }
//        }
//    }
//}


//import android.Manifest
//import android.content.pm.PackageManager
//import android.location.Location
//import androidx.core.app.ActivityCompat
//import com.example.final_project.R
//import com.example.final_project.databinding.FragmentMapsBinding
//import com.example.final_project.presentation.base.BaseFragment
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationServices
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.MarkerOptions
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class MapsFragment : BaseFragment<FragmentMapsBinding>(FragmentMapsBinding::inflate),
//    OnMapReadyCallback {
//    private var map: GoogleMap? = null
//    private var mapReady: Boolean = false
//    private lateinit var currentLocation: Location
//    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
//
//    private val latitude = arguments?.getInt("latitude")?.toDouble()
//    private val longitude = arguments?.getInt("longitude")?.toDouble()
//
//
//    override fun bind() {
//        fusedLocationProviderClient =
//            LocationServices.getFusedLocationProviderClient(requireContext())
//        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
//
//        if (latitude != null && longitude != null) {
//            moveMapToCurrentLocation(latitude, longitude)
//        }
//    }
//
//    override fun bindListeners() {
//        binding.btnSave.setOnClickListener {
//            getCurrentLocationAndMoveMap()
//        }
//    }
//
//    override fun bindObserves() {
//    }
//
//    override fun onMapReady(googleMap: GoogleMap) {
//        map = googleMap
//        mapReady = true
//        getCurrentLocationAndMoveMap()
//    }
//
//    private fun getCurrentLocationAndMoveMap() {
//        if (ActivityCompat.checkSelfPermission(
//                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                REQUEST_LOCATION_PERMISSION
//            )
//            return
//        }
//
//        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
//            if (location != null) {
//                currentLocation = location
//                moveMapToCurrentLocation(currentLocation.latitude, currentLocation.longitude)
//            }
//        }
//    }
//
//    companion object {
//        private const val REQUEST_LOCATION_PERMISSION = 100
//    }
//
//    private fun moveMapToCurrentLocation(latitude: Double, longitude: Double) {
//        val latLng = LatLng(latitude, longitude)
//        val markerOptions = MarkerOptions().position(latLng).title("CurrentLocation")
//        map?.clear()
//        map?.addMarker(markerOptions)
//        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
//    }
//
//}