package com.example.final_project.domain.usecase.validators

class PasswordValidatorUseCase {
    operator fun invoke(password: String): Boolean = password.isNotBlank()
}
