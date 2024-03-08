package com.example.final_project.domain.usecase.validators

class PasswordRepeatValidatorUseCase {
    operator fun invoke(password: String, repeatPassword: String): Boolean {
        return password == repeatPassword
    }
}
