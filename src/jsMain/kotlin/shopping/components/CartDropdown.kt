package shopping.components

import kotlinx.html.ButtonType
import react.RProps
import react.dom.br
import react.dom.div
import react.functionalComponent
import react.redux.useSelector
import shopping.model.Item
import shopping.redux.State

val cartDropdown = functionalComponent<RProps> {
  val cartItems = useSelector<State, Map<Item, Int>>{it.cart.items}
  div(classes="cart-dropdown") {
    div(classes="cart-items") {
      cartItems.map {
        +"${it.key.name}: ${it.value}"
        br { }
      }
    }
    button {
      attrs {
        type= ButtonType.submit
        label="GO TO CHECKOUT"
      }
    }
  }
}