package com.example.final_project.domain.usecase.registration

import com.example.final_project.domain.repository.registration.RegistrationRepository
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(private val registrationRepository: RegistrationRepository) {
    suspend operator fun invoke(email: String, password: String) =
        registrationRepository.registration(email = email, password = password)
}
