package com.example.config

import com.example.table.inventory_management.RegionTable
import com.example.table.user.RoleTable
import com.example.table.user.UserAccountTable
import com.example.table.user.UserDetailTable
import com.ucasoft.ktor.simpleMemoryCache.*
import com.ucasoft.ktor.simpleRedisCache.*
import dev.inmo.krontab.builder.*
import freemarker.cache.*
import io.github.flaxoos.ktor.server.plugins.ratelimiter.*
import io.github.flaxoos.ktor.server.plugins.ratelimiter.implementations.*
import io.github.flaxoos.ktor.server.plugins.taskscheduling.*
import io.github.flaxoos.ktor.server.plugins.taskscheduling.managers.lock.database.*
import io.github.flaxoos.ktor.server.plugins.taskscheduling.managers.lock.redis.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.resources.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.engine.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.cachingheaders.*
import io.ktor.server.plugins.callid.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.csrf.*
import io.ktor.server.plugins.doublereceive.*
import io.ktor.server.plugins.httpsredirect.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.sse.*
import io.ktor.server.webjars.*
import io.ktor.server.websocket.*
import io.ktor.sse.*
import io.ktor.websocket.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection
import org.slf4j.event.*

fun Application.configureDatabases() {
    DatabaseFactory.init(environment)
    DatabaseAuthFactory.init(environment)
}


/**
 * Makes a connection to a Postgres database.
 *
 * In order to connect to your running Postgres process,
 * please specify the following parameters in your configuration file:
 * - postgres.url -- Url of your running database process.
 * - postgres.user -- Username for database connection
 * - postgres.password -- Password for database connection
 *
 * If you don't have a database process running yet, you may need to [download]((https://www.postgresql.org/download/))
 * and install Postgres and follow the instructions [here](https://postgresapp.com/).
 * Then, you would be able to edit your url,  which is usually "jdbc:postgresql://host:port/database", as well as
 * user and password values.
 *
 *
 * @param embedded -- if [true] defaults to an embedded database for tests that runs locally in the same process.
 * In this case you don't have to provide any parameters in configuration file, and you don't have to run a process.
 *
 * @return [Connection] that represent connection to the database. Please, don't forget to close this connection when
 * your application shuts down by calling [Connection.close]
 * */

object DatabaseFactory {
    lateinit var dbMain: Database

    fun init(environment: ApplicationEnvironment) {
        dbMain = Database.connect(
            url = environment.config.property("postgres.url").getString(),
            driver = environment.config.property("postgres.driver").getString(),
            user = environment.config.property("postgres.user.username").getString(),
            password = environment.config.property("postgres.password.passwordktor").getString()
        )
        transaction(dbMain) {
            SchemaUtils.createMissingTablesAndColumns(RegionTable)
        }
    }
}

object DatabaseAuthFactory {
    lateinit var dbAuth: Database

    fun init(environment: ApplicationEnvironment) {
        dbAuth = Database.connect(
            url = "jdbc:postgresql://${environment.config.property("postgres.host").getString()}:${environment.config.property("postgres.port").getString()}/${environment.config.property("postgres.database").getString()}?prepareThreshold=0",
            driver = environment.config.property("postgres.driver").getString(),
            user = environment.config.property("postgres.user.usernameauth").getString(),
            password = environment.config.property("postgres.password.passwordauth").getString()
        )
        transaction(dbAuth) {
            SchemaUtils.createMissingTablesAndColumns(UserAccountTable)
            SchemaUtils.createMissingTablesAndColumns(RoleTable)
            SchemaUtils.createMissingTablesAndColumns(UserDetailTable)
        }
    }
}

/**
 * Executes a suspended transaction on the specified database.
 */
suspend fun <T> dbQuery(db: Database, block: suspend () -> T): T =
    newSuspendedTransaction(db = db) { block() }

suspend fun <T> asyncDbQuery(db: Database, block: suspend () -> T): T =
    suspendedTransactionAsync(db = db) { block() }.await()


//    install(Kafka) {
//        schemaRegistryUrl = "my.schemaRegistryUrl"
//        val myTopic = TopicName.named("my-topic")
//        topic(myTopic) {
//            partitions = 1
//            replicas = 1
//            configs {
//                messageTimestampType = MessageTimestampType.CreateTime
//            }
//        }
//        common { // <-- Define common properties
//            bootstrapServers = listOf("my-kafka")
//            retries = 1
//            clientId = "my-client-id"
//        }
//        admin { } // <-- Creates an admin client
//        producer { // <-- Creates a producer
//            clientId = "my-client-id"
//        }
//        consumer { // <-- Creates a consumer
//            groupId = "my-group-id"
//            clientId = "my-client-id-override" //<-- Override common properties
//        }
//        consumerConfig {
//            consumerRecordHandler(myTopic) { record ->
//                // Do something with record
//            }
//        }
//        registerSchemas {
//            using { // <-- optionally provide a client, by default CIO is used
//                HttpClient()
//            }
//            // MyRecord::class at myTopic // <-- Will register schema upon startup
//        }
//    }

//val dbUrl = environment.config.property("postgres.url").getString()
//    val dbUser = environment.config.property("postgres.user.username").getString()
//    val dbPassword = environment.config.property("postgres.password.passwordktor").getString()
//
//    val flyway = Flyway.configure()
//        .dataSource(dbUrl, dbUser, dbPassword)
//        .locations("classpath:db/migration")
//        .load()
//
//    flyway.migrate()
//
//    val dbAuthUrl = "jdbc:postgresql://${environment.config.property("postgres.host").getString()}:${environment.config.property("postgres.port").getString()}/${environment.config.property("postgres.database").getString()}?prepareThreshold=0"
//    val dbAuthUser = environment.config.property("postgres.user.usernameauth").getString()
//    val dbAuthPassword = environment.config.property("postgres.password.passwordauth").getString()
//
//    val flywayAuth = Flyway.configure()
//        .dataSource(dbAuthUrl, dbAuthUser, dbAuthPassword)
//        .locations("classpath:db/migration")
//        .load()
//
//    flywayAuth.migrate()