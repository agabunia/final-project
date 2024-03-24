package com.example.final_project.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.domain.local.usecase.datastore.clear.ClearDataStoreUseCase
import com.example.final_project.presentation.event.profile.ProfileEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val clearDataStoreUseCase: ClearDataStoreUseCase
) : ViewModel() {


    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> get() = _uiEvent

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.LogOut -> logout()
            is ProfileEvent.Terms -> navigateToTerms()
        }
    }

    private fun logout() {
        viewModelScope.launch {
            clearDataStoreUseCase()
            _uiEvent.emit(UiEvent.NavigateToLogin)
        }
    }

    private fun navigateToTerms() {
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.NavigateToTerms)
        }
    }


    sealed interface UiEvent {
        object NavigateToLogin : UiEvent
        object NavigateToTerms: UiEvent
    }

}