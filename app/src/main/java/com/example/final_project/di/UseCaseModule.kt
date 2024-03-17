package com.example.final_project.di

import com.example.final_project.domain.repository.datastore.DataStoreRepository
import com.example.final_project.domain.repository.login.LoginRepository
import com.example.final_project.domain.repository.registration.RegistrationRepository
import com.example.final_project.domain.usecase.datastore.clear.ClearDataStoreUseCase
import com.example.final_project.domain.usecase.datastore.authorization.ReadDataStoreUseCase
import com.example.final_project.domain.usecase.datastore.authorization.SaveDataStoreUseCase
import com.example.final_project.domain.usecase.login.LoginUseCase
import com.example.final_project.domain.usecase.registration.RegistrationUseCase
import com.example.final_project.domain.usecase.validators.EmailValidatorUseCase
import com.example.final_project.domain.usecase.validators.PasswordRepeatValidatorUseCase
import com.example.final_project.domain.usecase.validators.PasswordValidatorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideRegistrationUseCase(
        registrationRepository: RegistrationRepository
    ): RegistrationUseCase {
        return RegistrationUseCase(registrationRepository = registrationRepository)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(
        loginRepository: LoginRepository
    ): LoginUseCase {
        return LoginUseCase(loginRepository = loginRepository)
    }

    @Singleton
    @Provides
    fun provideEmailValidatorUseCase(): EmailValidatorUseCase {
        return EmailValidatorUseCase()
    }

    @Singleton
    @Provides
    fun providePasswordValidatorUseCase(): PasswordValidatorUseCase {
        return PasswordValidatorUseCase()
    }

    @Singleton
    @Provides
    fun providePasswordReenterValidatorUseCase(): PasswordRepeatValidatorUseCase {
        return PasswordRepeatValidatorUseCase()
    }

    @Singleton
    @Provides
    fun provideSaveDataStoreUseCase(
        dataStoreRepository: DataStoreRepository
    ): SaveDataStoreUseCase {
        return SaveDataStoreUseCase(dataStoreRepository = dataStoreRepository)
    }

    @Singleton
    @Provides
    fun provideReadDataStoreUseCase(
        dataStoreRepository: DataStoreRepository
    ): ReadDataStoreUseCase {
        return ReadDataStoreUseCase(dataStoreRepository = dataStoreRepository)
    }

    @Singleton
    @Provides
    fun provideClearDataStoreUseCase(
        dataStoreRepository: DataStoreRepository
    ): ClearDataStoreUseCase {
        return ClearDataStoreUseCase(dataStoreRepository = dataStoreRepository)
    }

}
