package com.example.config

import io.github.flaxoos.ktor.server.plugins.ratelimiter.*
import io.github.flaxoos.ktor.server.plugins.ratelimiter.implementations.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlin.time.Duration.Companion.seconds

fun Application.configureAdministration() {
    routing {
        route("/") {
            install(RateLimiting) {
                rateLimiter {
                    type = TokenBucket::class
                    capacity = 100
                    rate = 10.seconds
                }
            }
        }
    }
//    install(TaskScheduling) {
//        // Choose task manager config based on your chosen task manager dependencies
//        redis { // <-- given no name, this will be the default manager
//            connectionPoolInitialSize = 1
//            host = "host"
//            port = 6379
//            username = "my_username"
//            password = "my_password"
//            connectionAcquisitionTimeoutMs = 1_000
//            lockExpirationMs = 60_000
//        }
//        jdbc("my jdbc manager") { // <-- given a name, a manager can be explicitly selected for a task
//            database = org.jetbrains.exposed.sql.Database.connect(
//                url = "jdbc:postgresql://host:port",
//                driver = "org.postgresql.Driver",
//                user = "my_username",
//                password = "my_password"
//            ).also {
//                transaction { SchemaUtils.create(DefaultTaskLockTable) }
//            }
//        }
//        mongoDb("my mongodb manager") {
//            databaseName = "test"
//            client = MongoClient.create("mongodb://host:port")
//        }
//
//        task { // if no taskManagerName is provided, the task would be assigned to the default manager
//            name = "My task"
//            task = { taskExecutionTime ->
//                log.info("My task is running: $taskExecutionTime")
//            }
//            kronSchedule = {
//                hours {
//                    from(0).every(12)
//                }
//                minutes {
//                    from(10).every(30)
//                }
//            }
//            concurrency = 2
//        }
//
//        task(taskManagerName = "my jdbc manager") {
//            name = "My Jdbc task"
//            // rest of task config
//        }
//    }
//    install(ShutDownUrl.ApplicationCallPlugin) {
//        // The URL that will be intercepted (you can also use the application.conf's ktor.deployment.shutdown.url key)
//        shutDownUrl = "/ktor/application/shutdown"
//        // A function that will be executed to get the exit code of the process
//        exitCodeSupplier = { 0 } // ApplicationCall.() -> Int
//    }
}
