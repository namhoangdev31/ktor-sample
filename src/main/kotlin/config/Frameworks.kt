package com.example.config


import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import com.example.dao.UserDao
import com.example.dao.UserDaoImpl
import com.example.repositories.AuthRepository
import com.example.repositories.AuthRepositoryImpl
import com.example.service.AuthService

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(listOf(authModule(environment)))
    }
}

fun authModule(applicationEnvironment: ApplicationEnvironment) = module {
    single<ApplicationEnvironment> { applicationEnvironment }
    single<UserDao> { UserDaoImpl() }
    single<AuthService> { AuthService(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}