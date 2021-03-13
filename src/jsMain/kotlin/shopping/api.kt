package shopping

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.browser.window
import shopping.model.Collection
import shopping.model.Section

val origin = window.location.origin // only needed until https://youtrack.jetbrains.com/issue/KTOR-453 is resolved

val devMode = true
val endpoint = if (devMode) origin.replace(":8081", ":8080") else origin

val jsonClient = HttpClient {
  install(JsonFeature) { serializer = KotlinxSerializer() }
}

object API {
  suspend fun getCollections(): List<Collection> {
    console.log("getting $endpoint + $collectionsPath")
    return jsonClient.get(endpoint + collectionsPath)
  }

  suspend fun getSections(): List<Section> {
    console.log("getting $endpoint + $sectionsPath")
    return jsonClient.get(endpoint + sectionsPath)
  }
}