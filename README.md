# Ktor Sample Application

This project is structured to resemble a Spring Boot and Laravel-like architecture using **Ktor** with **Thymeleaf** for templating.

## 🚀 Features

| Name                                                                   | Description                                                                        |
|------------------------------------------------------------------------|------------------------------------------------------------------------------------|
| **Routing**                                                            | Structured routing similar to Laravel                                             |
| **JWT Authentication**                                                 | Secured with JWT similar to Spring Security                                        |
| **Request Validation (DTO)**                                           | Ensures request data integrity like Spring Boot's `@Valid`                         |
| **Exception Handling**                                                 | Global error handling similar to Spring Boot                                       |
| **Repository + DAO Pattern**                                           | Structured data access similar to Spring Data JPA                                  |
| **Flyway Database Migration**                                          | Manages database schema migrations                                                 |
| **Thymeleaf Views**                                                    | Dynamic views like Laravel's Blade                                                 |
| **Task Scheduler**                                                     | Handles scheduled background tasks                                                 |
| **Middleware**                                                         | Intercepts requests similar to Spring Interceptors                                 |
| **Swagger + OpenAPI**                                                  | API documentation built-in                                                        |
| **CORS, Compression, and Logging**                                     | Essential configurations for production readiness                                  |
| **PostgreSQL Integration**                                             | Database configuration using PostgreSQL                                            |

## 📁 Project Structure

```plaintext
ktor-app/
│── src/
│   ├── application/
│   │   ├── modules.kt            # Configures Ktor modules
│   │   ├── Routing.kt            # Defines application routes
│   │   ├── Middleware.kt         # Middleware configurations
│   │   ├── ExceptionHandler.kt   # Global exception handling setup
│   ├── controllers/
│   │   ├── UserController.kt     # Controller managing User operations
│   ├── services/
│   │   ├── UserService.kt        # Business logic for User
│   ├── repositories/
│   │   ├── UserRepository.kt     # Data access via repository pattern
│   ├── dao/
│   │   ├── UserDAO.kt            # DAO layer for direct database queries
│   ├── models/
│   │   ├── User.kt               # User data model
│   │   ├── BaseResponse.kt       # Standard response wrapper
│   ├── dto/
│   │   ├── UserAccountDTO.kt            # DTO for input validation
│   ├── security/
│   │   ├── JwtConfig.kt          # JWT configuration for security
│   ├── database/
│   │   ├── FlywayMigration.kt    # Flyway database migrations
│   ├── scheduler/
│   │   ├── TaskScheduler.kt      # Scheduled background tasks
│   ├── views/                    # Thymeleaf views
│   │   ├── layouts/
│   │   │   ├── base.html         # Main layout template
│   │   ├── user.html             # CRUD User page
│   ├── Main.kt                   # Main entry point for the application
│── resources/
│   ├── application.conf          # Application configurations
│── build.gradle.kts              # Build configuration file
│── settings.gradle.kts
│── gradle.properties
│── README.md
```

## ⚙️ Building & Running

### Common Tasks
| Task                          | Description                                                           |
|-------------------------------|-----------------------------------------------------------------------|
| `./gradlew test`              | Run the test suite                                                     |
| `./gradlew build`             | Build the entire project                                              |
| `./gradlew run`               | Run the server locally                                                 |
| `buildFatJar`                 | Package the server as an executable JAR                               |
| `buildImage`                  | Create a Docker image for the application                             |
| `publishImageToLocalRegistry` | Publish the Docker image locally                                      |
| `runDocker`                   | Run the application via the Docker image                              |

### Starting the Server
```bash
./gradlew run
```

**Expected Output:**
```plaintext
2025-03-12 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2025-03-12 14:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8080
```

## 🛠️ Configuration (application.conf)
```hocon
ktor {
    deployment {
        port = 8080
        watch = [ ktor-app ]
    }
    application {
        modules = [ application.modules ]
    }
}

jwt.secret = "your-secret-key"
jwt.issuer = "ktor.io"
jwt.audience = "ktor-users"
```

## 🔒 Security (JWT Example)
```kotlin
package security

object JwtConfig {
    const val secret = "your-secret-key"
    const val issuer = "ktor.io"
    const val audience = "ktor-users"
}
```

## 📚 API Documentation (Swagger)
- Access Swagger UI at: [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)

## 🚀 Features to Explore Further
- **Rate Limiting**: Configure limits on API requests.
- **Caching (Redis + Memory)**: Optimize data retrieval.
- **Kafka + WebSocket**: For real-time processing.
- **Validation Layer**: Implement advanced data validation.

## 🙌 Contributing
- Fork the repository.
- Create your feature branch (`git checkout -b feature/my-feature`).
- Commit your changes (`git commit -am 'Add new feature'`).
- Push to the branch (`git push origin feature/my-feature`).
- Create a new Pull Request.

## 📄 License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

---

Happy Coding with **Ktor + Thymeleaf**! 🚀
