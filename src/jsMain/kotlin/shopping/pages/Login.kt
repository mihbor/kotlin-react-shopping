package shopping.pages

import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import shopping.components.signIn
import shopping.components.signUp

val loginPage = functionalComponent<RProps> {
  div(classes="login") {
    child(signIn)
    child(signUp)
  }
}