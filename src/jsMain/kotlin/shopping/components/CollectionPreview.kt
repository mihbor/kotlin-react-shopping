package shopping.components

import kotlinx.css.*
import kotlinx.css.Display.flex
import kotlinx.css.FlexDirection.column
import kotlinx.css.FontWeight.Companion.normal
import react.RProps
import react.child
import react.functionalComponent
import shopping.model.Item
import styled.css
import styled.styledDiv as div
import styled.styledH1 as h1

external interface CollectionPreviewProps : RProps {
  var title: String
  var items: List<Item>
}

val collectionPreview = functionalComponent<CollectionPreviewProps> { props ->
  div {
    css {
      display = flex
      flexDirection = column
      marginBottom = LinearDimension("30px")
    }
    h1 {
      css {
        fontWeight = normal
        fontSize = LinearDimension("28px")
        marginBottom = LinearDimension("25px")
      }
      +props.title
    }
    div {
      css {
        display = Display.grid
        gridTemplateColumns = GridTemplateColumns("1fr 1fr 1fr 1fr")
        gap = Gap("10px")
      }
      props.items.take(4).map { item ->
        child(collectionItem) {
          key = "${item.id}"
          attrs.item = item
        }
      }
    }
  }
}