package com.example.final_project.presentation.screen.home

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.data.common.Resource
import com.example.final_project.domain.usecase.access_token.AccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accessTokenUseCase: AccessTokenUseCase
) : ViewModel() {

     fun getAccessToken() {
        d("testTestService","function is called")
        viewModelScope.launch {
            accessTokenUseCase().collect{
                d("testTestService", "usecase is invoked: $it")
            }
        }
    }

}