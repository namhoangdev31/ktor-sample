package com.example.utils

import com.example.dto.AuthRequest
import com.example.dto.RegisterRequest
import io.ktor.server.plugins.requestvalidation.*


fun validateRegisterRequest(registerRequest: RegisterRequest): ValidationResult {
    when (val result = validateEmail(registerRequest.email)) {
        is ValidationResult.Invalid -> return result
        is ValidationResult.Valid -> {}
    }

    when (val result = validateUsername(registerRequest.username)) {
        is ValidationResult.Invalid -> return result
        is ValidationResult.Valid -> {}
    }

    when (val result = validatePassword(registerRequest.password)) {
        is ValidationResult.Invalid -> return result
        is ValidationResult.Valid -> {}
    }

    return ValidationResult.Valid
}

fun validateAuthRequest(authRequest: AuthRequest): ValidationResult {
    when (val result = validateUsername(authRequest.username)) {
        is ValidationResult.Invalid -> return result
        is ValidationResult.Valid -> {}
    }

    when (val result = validatePassword(authRequest.password)) {
        is ValidationResult.Invalid -> return result
        is ValidationResult.Valid -> {}
    }

    return ValidationResult.Valid
}
