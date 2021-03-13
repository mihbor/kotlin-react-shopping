package shopping

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
  embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
    install(ContentNegotiation) {
      json()
    }
    install(CORS) {
      method(HttpMethod.Get)
      method(HttpMethod.Post)
      method(HttpMethod.Delete)
      anyHost()
    }
    install(Compression) {
      gzip()
    }
    routing {
      get(collectionsPath) {
        call.respond(collectionsData)
      }
      get(sectionsPath) {
        call.respond(sectionsData)
      }
      static("/static") {
        resources("static")
      }
      static {
        resource("/kotlin-react-shopping.js")
        resource("/{...}", "index.html")
      }
    }
  }.start(wait = true)
}