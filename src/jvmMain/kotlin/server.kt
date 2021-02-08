import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.respondHtml
import io.ktor.http.*
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.serialization.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.response.*
import kotlinx.html.*

fun HTML.index() {
  head {
    title("Shopping in React Kotlin")
    link(rel="stylesheet", href="/static/styles.css")
    link(rel="preconnect", href="https://fonts.gstatic.com")
    link(rel="stylesheet", href="https://fonts.googleapis.com/css2?family=Open+Sans+Condensed:wght@300&display=swap")
  }
  body {
    div {
      id = "root"
      +"Page loading..."
    }
    script(src = "/static/output.js") {}
  }
}

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
      get("/") {
        call.respondHtml(HttpStatusCode.OK, HTML::index)
      }
      get("/shop") {
        call.respondHtml(HttpStatusCode.OK, HTML::index)
      }
      get("/login") {
        call.respondHtml(HttpStatusCode.OK, HTML::index)
      }
      get(collectionsPath) {
        call.respond(collectionsData)
      }
      static("/static") {
        resources()
      }
    }
  }.start(wait = true)
}