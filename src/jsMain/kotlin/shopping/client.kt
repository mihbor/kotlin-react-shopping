package shopping

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

fun main() {
  window.onload = {
    document.getElementById("root")
      ?.also { it.innerHTML = "" }
      ?.also {
        render(it) {
          provider(store) {
            child(PersistGate::class) {
              attrs.persistor=persistor
              child(app) {}
            }
          }
        }
      }
  }
}
