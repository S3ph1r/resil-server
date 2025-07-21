import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    // Avvia il server Netty sulla porta 8080, accessibile da ovunque ("0.0.0.0")
    // Render userà la porta 10000, ma Ktor si adatta automaticamente.
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

// Il modulo principale dell'applicazione, chiamato quando il server parte.
fun Application.module() {
    // Chiama le funzioni di configurazione che abbiamo definito negli altri file.
    configureSerialization()
    configureRouting()
}
