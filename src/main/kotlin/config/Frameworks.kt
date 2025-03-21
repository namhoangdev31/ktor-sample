package com.example.config


import com.example.controllers.AuthController
import com.example.controllers.UserController
import com.example.dao.RegionDao
import com.example.dao.RegionDaoImpl
import com.example.dao.UserDao
import com.example.dao.UserDaoImpl
import com.example.repositories.AuthRepository
import com.example.repositories.AuthRepositoryImpl
import com.example.repositories.UserRepository
import com.example.repositories.UserRepositoryImpl
import com.example.service.AuthService
import com.example.services.UserService
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(
            listOf(
                configModule(environment),
                appDaoModule(),
                authModule(),
                userModule()
            )
        )
    }
}

fun authModule() = module {
    single<AuthService> { AuthService(get()) }
    single<AuthController> { AuthController() }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}

fun userModule() = module {
    single<UserService> { UserService() }
    single<UserController> { UserController() }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
}

fun appDaoModule() = module {
    single<UserDao> { UserDaoImpl() }
    single<RegionDao> { RegionDaoImpl() }
}

fun configModule(applicationEnvironment: ApplicationEnvironment) = module {
    single<ApplicationEnvironment> { applicationEnvironment }
}
