package com.example.exceptions

class NotFoundException(message: String = "Resource not found") : RuntimeException(message)
