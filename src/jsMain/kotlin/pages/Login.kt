package pages

import components.signIn
import react.RProps
import react.child
import react.dom.div
import react.functionalComponent

@JsExport
val loginPage = functionalComponent<RProps> {
  div(classes="login") {
    child(signIn) { }
  }
}