package com.example.final_project.presentation.screen.map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.domain.usecase.maps.GetPlaceLocationUseCase
import com.example.final_project.domain.usecase.maps.UserLocationPlaceUseCase
import com.example.final_project.presentation.event.maps.MapsEvents
import com.example.final_project.presentation.state.maps.MapsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val userLocationPlaceUseCase: UserLocationPlaceUseCase,
    private val getPlaceLocationUseCase: GetPlaceLocationUseCase
) : ViewModel() {

    private val _userLocation = MutableStateFlow(MapsState())
    val userLocation: SharedFlow<MapsState> get() = _userLocation

    private val _placeLocation = MutableStateFlow(MapsState())
    val placeLocation: SharedFlow<MapsState> get() = _placeLocation

    fun onEvent(event: MapsEvents) {
        when (event) {
            is MapsEvents.GetUserLocation -> getUserLocation()
            is MapsEvents.GetPlaceLocation -> getPlaceLocation(event.placeName)
        }
    }

    private fun getUserLocation() {
        viewModelScope.launch {
            val location = userLocationPlaceUseCase()
            _userLocation.value = MapsState(userLocation = location)
        }
    }

    private fun getPlaceLocation(placeName: String) {
        Log.d("com.example.final_project.presentation.screen.map.MapsViewModel", "Getting location for place: $placeName")
        viewModelScope.launch {
            val placeLocation = getPlaceLocationUseCase(placeName)
            _placeLocation.value = MapsState(placeLocation = placeLocation)
            Log.d("com.example.final_project.presentation.screen.map.MapsViewModel", "Getting location for place: $placeLocation")
        }
    }
}