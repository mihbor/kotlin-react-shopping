package shopping.pages

import react.RProps
import react.child
import react.dom.div
import react.dom.h2
import react.functionalComponent
import shopping.components.redux.collectionItem
import shopping.model.Collection

external interface CollectionProps : RProps {
  var collection: Collection?
}
val collection = functionalComponent<CollectionProps> { props ->
  console.log("collection: ${props.collection}")
  div(classes="collection-page") {
    props.collection?.let {
      h2(classes="title") { +it.title }
      div(classes="items") {
        it.items.map { item ->
          child(collectionItem) {
            key = "${item.id}"
            attrs.item = item
          }
        }
      }
    }
  }
}