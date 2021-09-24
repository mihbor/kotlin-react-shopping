package shopping

import apollo.*
import apollo.react.ApolloProvider
import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import org.w3c.dom.Element
import org.w3c.dom.events.Event
import react.child
import react.dom.render
import react.redux.provider
import shopping.redux.persist.react.PersistGate
import shopping.redux.persistor
import shopping.redux.store

val scope = MainScope()

val httpLink = createHttpLink(
  jsObject { uri = "http://localhost:8080/graphql" }
)

val apolloCache = InMemoryCache()

val apolloClient = ApolloClient(
  jsObject<ApolloClientOptions<NormalizedCacheObject>>{
    link = httpLink
    cache = apolloCache
  }
)
//external val self: ServiceWorkerGlobalScope
fun main() {
//  try {
    window.onload = content()
//    window.addEventListener("load", {
//      window.navigator.serviceWorker.register("/kotlin-react-shopping.js")
//        .then { console.log("Service worker registered") }
//        .catch { console.error("Service worker registration failed", it) }
//    })
//  } catch (e: Throwable) {
//    self.addEventListener("install", { console.log("Service worker installed") })
//    self.addEventListener("activate", { console.log("Service worker is now active") })
//  }
}

private fun content(): (Event) -> Element? = {
  document.getElementById("root")
    ?.also { it.innerHTML = "" }
    ?.also {
      render(it) {
        child(ApolloProvider) {
          attrs.client = apolloClient
          provider(store) {
            child(PersistGate::class) {
              attrs.persistor = persistor
              child(app) {}
            }
          }
        }
      }
    }
}
