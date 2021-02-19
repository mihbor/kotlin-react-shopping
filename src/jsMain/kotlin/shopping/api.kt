package shopping

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.browser.window
import shopping.model.Collection

val endpoint = window.location.origin // only needed until https://github.com/ktorio/ktor/issues/1695 is resolved

val jsonClient = HttpClient {
  install(JsonFeature) { serializer = KotlinxSerializer() }
}

suspend fun getCollections() : List<Collection> {
  return jsonClient.get(endpoint + collectionsPath)
}