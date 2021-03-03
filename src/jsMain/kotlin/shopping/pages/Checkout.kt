package shopping.pages

import kotlinx.html.js.onClickFunction
import react.RProps
import react.dom.div
import react.dom.img
import react.dom.span
import react.functionalComponent
import react.redux.useDispatch
import redux.RAction
import shopping.model.item
import shopping.model.quantity
import shopping.model.total
import shopping.redux.*

val checkout = functionalComponent<RProps> {
  val cartItems = getCartItems()
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
      div(classes="checkout-item") {
        div(classes="image-container") {
          img(src=it.item.imageUrl, alt=it.item.name) { }
        }
        span(classes="name") { +it.item.name }
        span(classes="quantity") {
          div(classes="arrow") {
            attrs.onClickFunction={ _ -> dispatch(RemoveFromCart(it.item)) }
            +"❮"
          }
          span(classes="value") { +"${it.quantity}" }
          div(classes="arrow") {
            attrs.onClickFunction={ _ -> dispatch(AddToCart(it.item)) }
            +"❯"
          }
        }
        span(classes="price") { +"${it.item.price}" }
        div(classes="remove-button") {
          attrs.onClickFunction={ _ -> dispatch(ClearFromCart(it.item)) }
          +"✕"
        }
      }
    }
    div(classes="total") {
      span { +"TOTAL: £${cartItems.total()}"}
    }
  }
}