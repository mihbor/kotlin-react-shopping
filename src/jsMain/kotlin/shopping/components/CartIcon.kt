package shopping.components

import kotlinx.html.js.onClickFunction
import react.RProps
import react.dom.div
import react.dom.img
import react.dom.span
import react.functionalComponent
import react.redux.useDispatch
import redux.WrapperAction
import shopping.redux.*

val cartIcon = functionalComponent<RProps> {
  val dispatch = useDispatch<CartCommand, WrapperAction>()
  val isCartVisible = isCartVisible()
  val cartItems = getCartItems()

  div(classes="cart-icon") {
    attrs {
      onClickFunction={dispatch(if(isCartVisible) HideCart() else ShowCart())}
    }
    img(alt = "shopping bag", src = "/static/images/shopping-bag.svg", classes = "shopping-icon") { }
    span(classes="item-count") {+"${cartItems.values.sum()}"}
  }
}