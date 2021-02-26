package shopping.components

import kotlinx.css.Image
import kotlinx.css.backgroundImage
import kotlinx.html.js.onClickFunction
import react.RProps
import react.dom.div
import react.dom.span
import react.functionalComponent
import react.redux.useDispatch
import redux.WrapperAction
import shopping.model.Item
import shopping.redux.AddItemToCart
import shopping.redux.CartCommand
import styled.css
import styled.styledDiv

external interface ItemProps : RProps {
  var item: Item
}

@JsExport
val collectionItem = functionalComponent<ItemProps> { props ->
  val dispatch = useDispatch<CartCommand, WrapperAction>()

  div(classes = "collection-item") {
    styledDiv {
      css {
        classes = mutableListOf("image")
        backgroundImage = Image("url(${props.item.imageUrl})")
      }
    }
    div(classes = "collection-footer") {
      span(classes = "name") { +props.item.name }
      span(classes = "price") { +"£${props.item.price}" }
    }
    button {
      attrs {
        invertColors=true
        label="add to cart"
        onClickFunction={ dispatch(AddItemToCart(props.item)) }
      }
    }
  }
}