package components

import kotlinx.css.Image
import kotlinx.css.backgroundImage
import react.RProps
import react.dom.div
import react.dom.span
import react.functionalComponent
import styled.css
import styled.styledDiv

external interface ItemProps : RProps {
  var name: String
  var price: Int
  var imageUrl: String
}

@JsExport
val collectionItem = functionalComponent<ItemProps> {
  div(classes = "collection-item") {
    styledDiv {
      css {
        classes = mutableListOf("image")
        backgroundImage = Image("url(${it.imageUrl})")
      }
    }
    div(classes = "collection-footer") {
      span(classes = "name") { +it.name }
      span(classes = "price") { +"£${it.price}" }
    }
  }
}