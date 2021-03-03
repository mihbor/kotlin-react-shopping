package shopping.components

import kotlinx.html.ButtonType
import kotlinx.html.js.onClickFunction
import react.RProps
import react.child
import react.dom.div
import react.dom.span
import react.functionalComponent
import react.redux.useDispatch
import react.router.dom.useHistory
import redux.RAction
import shopping.redux.CartCommand
import shopping.redux.HideCart
import shopping.redux.getCartItems

val cartDropdown = functionalComponent<RProps> {
  val history = useHistory()
  val dispatch = useDispatch<CartCommand, RAction>()
  val cartItems = getCartItems()
  div(classes="cart-dropdown") {
    if(cartItems.isEmpty()) span(classes="empty-message") { +"Your cart is empty" }
    else {
      div(classes = "cart-items") {
        cartItems.map {
          child(cartItem) {
            attrs {
              item = it.key
              quantity = it.value
            }
          }
        }
      }
      button {
        attrs {
          type = ButtonType.submit
          label = "GO TO CHECKOUT"
          onClickFunction={
            history.push("/checkout")
            dispatch(HideCart())
          }
        }
      }
    }
  }
}