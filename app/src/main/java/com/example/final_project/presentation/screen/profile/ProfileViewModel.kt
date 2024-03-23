package com.example.final_project.presentation.screen.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {
    private val _name = MutableStateFlow("Default Name")
    val name: StateFlow<String> = _name

    private val _phone = MutableStateFlow("Default Phone")
    val phone: StateFlow<String> = _phone

    fun setNameAndPhone(name: String, phone: String) {
        _name.value = name
        _phone.value = phone
    }
}