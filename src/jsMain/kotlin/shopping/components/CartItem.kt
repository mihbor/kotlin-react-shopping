package shopping.components

import react.dom.div
import react.dom.img
import react.dom.span
import react.functionalComponent

external interface CartItemProps : ItemProps {
  var quantity: Int
}

val cartItem = functionalComponent<CartItemProps> {
  div(classes="cart-item") {
    img(src=it.item.imageUrl, alt=it.item.name) {  }
    div(classes="item-details") {
      span(classes="name") { +it.item.name }
      span(classes="price") { +"${it.quantity} x £${it.item.price}" }
    }
  }
}