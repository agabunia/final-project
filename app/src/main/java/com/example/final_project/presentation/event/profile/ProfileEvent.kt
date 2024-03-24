package com.example.final_project.presentation.event.profile

sealed class ProfileEvent {
    object LogOut: ProfileEvent()
    object Terms: ProfileEvent()
}