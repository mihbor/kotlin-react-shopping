package shopping.components

import kotlinx.html.ButtonType
import react.RProps
import react.dom.div
import react.functionalComponent

val cartDropdown = functionalComponent<RProps> {
  div(classes="cart-dropdown") {
    div(classes="cart-items") {

    }
    button {
      attrs {
        type= ButtonType.submit
        label="GO TO CHECKOUT"
      }
    }
  }
}