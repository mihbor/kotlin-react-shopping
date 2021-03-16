package shopping.components

import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.display
import kotlinx.css.flexDirection
import react.RProps
import react.child
import react.functionalComponent
import shopping.redux.getCollections
import styled.css
import styled.styledDiv as div

val collectionsOverview = functionalComponent<RProps> {
  val collections = getCollections()
  div {
    css {
      display = Display.flex
      flexDirection = FlexDirection.column
    }
    collections.values.map { collection ->
      child(collectionPreview) {
        key = "${collection.id}"
        attrs {
          title = collection.title
          items = collection.items
        }
      }
    }
  }
}