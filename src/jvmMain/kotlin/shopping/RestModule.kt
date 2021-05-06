package shopping

import com.stripe.Stripe
import com.stripe.model.Charge
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import shopping.model.Payment

fun Application.restModule() {
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
    post("/payment") {
      Stripe.apiKey = System.getenv("stripe_secret_key")

      val payment = call.receive<Payment>()

      Charge.create(mapOf("amount" to payment.amount, "currency" to payment.currency, "source" to payment.token))

      call.response.status(HttpStatusCode.OK)
    }
    static("/static") {
      resources("static")
    }
    static {
      resource("/kotlin-react-shopping.js")
      resource("/{...}", "index.html")
    }
  }
}