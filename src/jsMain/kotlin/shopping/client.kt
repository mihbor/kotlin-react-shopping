package shopping

import kotlinx.browser.document
import kotlinx.browser.window
import react.dom.render
import react.redux.provider
import shopping.redux.store

fun main() {
  window.onload = {
    document.getElementById("root")
      ?.also { it.innerHTML = "" }
      ?.also {
        render(it) {
          provider(store) {
            app {}
          }
        }
      }
  }
}
