package shopping.components

import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.display
import kotlinx.css.flexDirection
import react.RProps
import react.child
import react.functionalComponent
import shopping.model.Collection
import styled.css
import styled.styledDiv as div

external interface CollectionsOverviewProps : RProps {
  var collections: kotlin.collections.Collection<Collection>
}
val collectionsOverview = functionalComponent<CollectionsOverviewProps> { props ->
  div {
    css {
      display = Display.flex
      flexDirection = FlexDirection.column
    }
    props.collections.map { collection ->
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