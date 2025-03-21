package com.example.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.csrf.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import kotlinx.serialization.Serializable

fun Application.configureSecurity() {
    // Please read the jwt property from the config file if you are using EngineMain
    val jwtAudience = "jwt-audience"
    val jwtDomain = "https://jwt-provider-domain/"
    val jwtRealm = "ktor sample app"
    val jwtSecret = "secret"
    authentication {
        jwt {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtDomain)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
//    authentication {
//        oauth("auth-oauth-google") {
//            urlProvider = { "http://localhost:8080/callback" }
//            providerLookup = {
//                OAuthServerSettings.OAuth2ServerSettings(
//                    name = "google",
//                    authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
//                    accessTokenUrl = "https://accounts.google.com/o/oauth2/token",
//                    requestMethod = HttpMethod.Post,
//                    clientId = System.getenv("GOOGLE_CLIENT_ID"),
//                    clientSecret = System.getenv("GOOGLE_CLIENT_SECRET"),
//                    defaultScopes = listOf("https://www.googleapis.com/auth/userinfo.profile")
//                )
//            }
//            client = HttpClient(Apache)
//        }
//    }
    install(CSRF) {
        allowOrigin("http://localhost:8080")
        originMatchesHost()
//        checkHeader("X-CSRF-Token")
    }
    install(Sessions) {
        cookie<MySession>("MY_SESSION") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    intercept(ApplicationCallPipeline.Call) {
        if (!call.request.uri.startsWith("/public") && !call.request.uri.startsWith("/v1/auth") &&
            call.request.httpMethod in listOf(HttpMethod.Post, HttpMethod.Put, HttpMethod.Delete )) {
            val headerToken = call.request.headers["X-CSRF-Token"]
            val session = call.sessions.get<UserSession>()
            if (headerToken == null || session == null) {
                call.respond(HttpStatusCode.Forbidden, "Missing or invalid CSRF token")
                finish() // Dừng pipeline nếu token không hợp lệ
            }
        }
    }

}

class UserSession(accessToken: String)

@Serializable
data class MySession(val count: Int = 0)
//routing {
////        authenticate("auth-oauth-google") {
////            get("auth") {
////                call.respondRedirect("/callback")
////            }
////
////            get("/callback") {
////                val principal: OAuthAccessTokenResponse.OAuth2? = call.authentication.principal()
////                call.sessions.set(UserSession(principal?.accessToken.toString()))
////                call.respondRedirect("/hello")
////            }
////        }
//    get("/session/increment") {
//        val session = call.sessions.get<MySession>() ?: MySession()
//        call.sessions.set(session.copy(count = session.count + 1))
//        call.respondText("Counter is ${session.count}. Refresh to increment.")
//    }
//    route("/callback") {
//        install(DoubleReceive)
//        install(LineWebhook) {
//            channelSecret = environment.config.property("line.webhook.channelSecret").getString()
//        }
//        post {
//            call.respond(HttpStatusCode.OK)
//        }
//    }
//}
