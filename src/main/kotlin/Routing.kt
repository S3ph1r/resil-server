import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// Per ora, useremo una semplice mappa in memoria per "salvare" gli utenti.
val userStorage = mutableMapOf<String, RegistrationRequest>()

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Resil Server is alive!")
        }

        route("/auth") {
            post("/register") {
                try {
                    val request = call.receive<RegistrationRequest>()

                    println("Received registration for: ${request.phoneNumber}")
                    println("Identity Key starts with: ${request.identityPublicKey.take(10)}...")
                    println("User has ${request.preKeys.size} one-time pre-keys.")

                    userStorage[request.phoneNumber] = request

                    call.respond(HttpStatusCode.OK, "User registered successfully.")

                } catch (e: Exception) {
                    println("Error during registration: ${e.message}")
                    call.respond(HttpStatusCode.BadRequest, "Invalid request format.")
                }
            }
        }
    }
}
