package shopping

import apollo.*
import apollo.react.ApolloProvider
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import react.child
import react.dom.render
import react.redux.provider
import shopping.redux.persist.react.PersistGate
import shopping.redux.persistor
import shopping.redux.store

val scope = MainScope()

val httpLink = createHttpLink(
  (js("{}") as HttpLink.Options).apply { uri = "http://localhost:8080/graphql" }
)

val apolloCache = InMemoryCache()

val apolloClient = ApolloClient(
  (js("{}") as ApolloClientOptions<NormalizedCacheObject>).apply {
    link = httpLink
    cache = apolloCache
  }
)

fun main() {
  window.onload = {
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
}
