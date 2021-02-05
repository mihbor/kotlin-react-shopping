import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window
import react.child
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch

fun main() {
  window.onload = {
    document.getElementById("root")
      ?.also { it.innerHTML = "" }
      ?.also {
        render(it) {
          browserRouter {
            switch {
              route("/", exact = true) {
                child(pages.homePage) { }
              }
              route("/shop") {
                child(pages.shopPage) { }
              }
            }
          }
        }
      }
  }
}
