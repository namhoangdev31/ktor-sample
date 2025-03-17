package com.example.config

import com.example.dao.UserDao
import com.example.dao.UserDaoImpl
import com.example.repositories.AuthRepository
import com.example.repositories.AuthRepositoryImpl
import com.example.service.AuthService
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(authModule)
    }
}

val authModule = module {
    single<UserDao> { UserDaoImpl() }
    single<AuthService> { AuthService(get<ApplicationEnvironment>()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}
