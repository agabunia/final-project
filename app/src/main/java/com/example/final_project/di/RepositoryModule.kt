package com.example.final_project.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.final_project.data.common.HandleResponse
import com.example.final_project.data.local.repository.datastore.DataStoreRepositoryImpl
import com.example.final_project.data.remote.repository.access_token.AccessTokenRepositoryImpl
import com.example.final_project.data.remote.repository.login.LoginRepositoryImpl
import com.example.final_project.data.remote.repository.maps.PlaceLocationRepositoryImpl
import com.example.final_project.data.remote.repository.maps.UserLocationRepositoryImpl
import com.example.final_project.data.remote.repository.registration.RegistrationRepositoryImpl
import com.example.final_project.data.remote.service.access_token.AccessTokenService
import com.example.final_project.domain.repository.access_token.AccessTokenRepository
import com.example.final_project.domain.repository.datastore.DataStoreRepository
import com.example.final_project.domain.repository.login.LoginRepository
import com.example.final_project.domain.repository.maps.PlaceLocationRepository
import com.example.final_project.domain.repository.maps.UserLocationRepository
import com.example.final_project.domain.repository.registration.RegistrationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(impl: LoginRepositoryImpl): LoginRepository = impl

    @Provides
    @Singleton
    fun provideRegistrationRepository(impl: RegistrationRepositoryImpl): RegistrationRepository =
        impl

    @Provides
    @Singleton
    fun provideDataStoreRepository(dataStore: DataStore<Preferences>): DataStoreRepository {
        return DataStoreRepositoryImpl(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideAccessTokenRepository(
        handleResponse: HandleResponse,
        accessTokenService: AccessTokenService
    ): AccessTokenRepository {
        return AccessTokenRepositoryImpl(
            handleResponse = handleResponse,
            accessTokenService = accessTokenService
        )
    }

    @Provides
    @Singleton
    fun providePlaceLocationRepository(
        context: Context,
        placesClient: PlacesClient
    ): PlaceLocationRepository {
        return PlaceLocationRepositoryImpl(
            context = context,
            placesClient = placesClient
        )
    }

    @Provides
    @Singleton
    fun provideUserLocationRepository(
        context: Context,
        locationProvider: FusedLocationProviderClient
    ): UserLocationRepository {
        return UserLocationRepositoryImpl(
            context = context,
            locationProvider = locationProvider
        )
    }

}
