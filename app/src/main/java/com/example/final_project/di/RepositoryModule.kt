package com.example.final_project.di

import com.example.final_project.data.remote.repository.login.LoginRepositoryImpl
import com.example.final_project.data.remote.repository.registration.RegistrationRepositoryImpl
import com.example.final_project.domain.repository.login.LoginRepository
import com.example.final_project.domain.repository.registration.RegistrationRepository
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

}
