import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.http.content.resources
import io.ktor.http.content.static
import kotlinx.html.*

fun HTML.index() {
  head {
    title("Hello from Ktor!")
    link(rel="stylesheet", href="/static/styles.css")
    link(rel="preconnect", href="https://fonts.gstatic.com")
    link(rel="stylesheet", href="https://fonts.googleapis.com/css2?family=Open+Sans+Condensed:wght@300&display=swap")
  }
  body {
    div {
      id = "root"
      +"Hello from Ktor"
    }
    script(src = "/static/output.js") {}
  }
}

fun main() {
  embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
    routing {
      get("/") {
        call.respondHtml(HttpStatusCode.OK, HTML::index)
      }
      static("/static") {
        resources()
      }
    }
  }.start(wait = true)
}