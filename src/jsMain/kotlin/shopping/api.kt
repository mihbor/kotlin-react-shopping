package shopping

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.browser.window
import shopping.model.Collection
import shopping.model.Payment
import shopping.model.Section

val origin = window.location.origin // only needed until https://youtrack.jetbrains.com/issue/KTOR-453 is resolved

val devMode = true
// if we are running front end in webpack server but want to hit the ktor server
val endpoint = if (devMode) origin.replace(":8081", ":8080") else origin

val http = HttpClient {
  install(JsonFeature) { serializer = KotlinxSerializer() }
}

object API {
  suspend fun getCollections(): List<Collection> {
    console.log("getting $endpoint + $collectionsPath")
    return http.get(endpoint + collectionsPath)
  }

  suspend fun getSections(): List<Section> {
    console.log("getting $endpoint + $sectionsPath")
    return http.get(endpoint + sectionsPath)
  }

  suspend fun payment(payment: Payment) {
    val response: HttpResponse = http.post(endpoint + paymentPath) {
      contentType(ContentType.Application.Json)
      body = payment
    }
    console.log(response.readText())
  }
}