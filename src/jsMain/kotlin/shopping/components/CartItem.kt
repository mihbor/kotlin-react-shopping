package shopping.components

import react.dom.div
import react.dom.img
import react.dom.span
import react.functionalComponent
import shopping.formatPrice

external interface CartItemProps : ItemProps {
  var quantity: Int
}

val cartItem = functionalComponent<CartItemProps> { props ->
  val item = props.item
  div(classes="cart-item") {
    img(src=item.imageUrl, alt=item.name) {  }
    div(classes="item-details") {
      span(classes="name") { +item.name }
      span(classes="price") { +"${props.quantity} x £${item.price.formatPrice}" }
    }
  }
}