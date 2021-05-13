package shopping.components.redux

import kotlinx.css.Image
import kotlinx.css.backgroundImage
import kotlinx.html.js.onClickFunction
import react.RProps
import react.dom.div
import react.dom.span
import react.functionalComponent
import react.redux.useDispatch
import redux.WrapperAction
import shopping.components.button
import shopping.formatPrice
import shopping.model.Item
import shopping.redux.AddToCart
import shopping.redux.CartCommand
import styled.css
import styled.styledDiv

external interface ItemProps : RProps {
  var item: Item
}

val collectionItem = functionalComponent<ItemProps> { props ->
  val item = props.item
  val dispatch = useDispatch<CartCommand, WrapperAction>()

  div(classes = "collection-item") {
    styledDiv {
      css {
        classes = mutableListOf("image")
        backgroundImage = Image("url(${item.imageUrl})")
      }
    }
    div(classes = "collection-footer") {
      span(classes = "name") { +item.name }
      span(classes = "price") { +"£${item.price.formatPrice}" }
    }
    button {
      attrs {
        invertColors=true
        label="add to cart"
        onClickFunction={ dispatch(AddToCart(item)) }
      }
    }
  }
}