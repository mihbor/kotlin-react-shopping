package shopping.pages

import kotlinx.html.js.onClickFunction
import react.RProps
import react.child
import react.dom.br
import react.dom.div
import react.dom.img
import react.dom.span
import react.functionalComponent
import react.redux.useDispatch
import redux.RAction
import shopping.components.stripeButton
import shopping.formatPrice
import shopping.model.item
import shopping.model.quantity
import shopping.model.total
import shopping.redux.*

val checkout = functionalComponent<RProps> {
  val cartItems = getCartItems()
  val total = cartItems.total()
  val dispatch = useDispatch<CartCommand, RAction>()
  div(classes="checkout-page") {
    div(classes="checkout-header") {
      listOf("Product", "Description", "Quantity", "Price", "Remove").map {
        div(classes = "header-block") {
          span { +it }
        }
      }
    }
    cartItems.map {
      val item = it.item
      div(classes="checkout-item") {
        div(classes="image-container") {
          img(src=item.imageUrl, alt=item.name) { }
        }
        span(classes="name") { +item.name }
        span(classes="quantity") {
          div(classes="arrow") {
            attrs.onClickFunction={ _ -> dispatch(RemoveFromCart(item)) }
            +"❮"
          }
          span(classes="value") { +"${it.quantity}" }
          div(classes="arrow") {
            attrs.onClickFunction={ _ -> dispatch(AddToCart(item)) }
            +"❯"
          }
        }
        span(classes="price") { +"£${item.price.formatPrice}" }
        div(classes="remove-button") {
          attrs.onClickFunction={ _ -> dispatch(ClearFromCart(item)) }
          +"✕"
        }
      }
    }
    div(classes="total") {
      +"TOTAL: £${total.formatPrice}"
    }
    child(stripeButton) {
      attrs.price=total
    }
    div(classes="test-warning") {
      +"*Please use the following test credit card for payments*"
      br {}
      +"4242 4242 4242 4242 - Exp any future date, CVC any 3 digits"
    }
  }
}