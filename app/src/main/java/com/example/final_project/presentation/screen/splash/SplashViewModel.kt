package com.example.final_project.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.domain.usecase.datastore.ReadDataStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val readDataStoreUseCase: ReadDataStoreUseCase
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<SplashUIEvent>()
    val uiEvent: SharedFlow<SplashUIEvent> get() = _uiEvent

    init {
        readSession()
    }

    private fun readSession() {
        viewModelScope.launch {
            readDataStoreUseCase().collect {
                if (it.isEmpty()) {
                    _uiEvent.emit(SplashUIEvent.NavigateToLogin)
                } else {
                    _uiEvent.emit(SplashUIEvent.NavigateToMain)
                }
            }
        }
    }

    sealed interface SplashUIEvent {
        object NavigateToLogin : SplashUIEvent
        object NavigateToMain : SplashUIEvent
    }
}