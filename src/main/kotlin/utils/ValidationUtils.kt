package com.example.utils

import io.ktor.server.plugins.requestvalidation.*

fun validateEmail(email: String?): ValidationResult {
    return when {
        email.isNullOrBlank() -> ValidationResult.Invalid("Email cannot be null or blank")
        !email.contains("@") -> ValidationResult.Invalid("Invalid email format")
        else -> ValidationResult.Valid
    }
}

fun validateUsername(username: String?): ValidationResult {
    return when {
        username.isNullOrBlank() -> ValidationResult.Invalid("Username cannot be null or blank")
        username.length < 3 -> ValidationResult.Invalid("Username must be at least 3 characters long")
        else -> ValidationResult.Valid
    }
}

fun validatePassword(password: String?): ValidationResult {
    return when {
        password.isNullOrBlank() -> ValidationResult.Invalid("Password cannot be null or blank")
        password.length < 8 -> ValidationResult.Invalid("Password must be at least 8 characters long")
        else -> ValidationResult.Valid
    }
}

