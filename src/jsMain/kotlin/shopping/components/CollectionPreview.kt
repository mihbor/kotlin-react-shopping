package shopping.components

import react.RProps
import react.child
import react.dom.div
import react.dom.h1
import react.functionalComponent
import shopping.model.Item

external interface CollectionPreviewProps : RProps {
  var title: String
  var items: List<Item>
}

val collectionPreview = functionalComponent<CollectionPreviewProps> { props ->
  div(classes = "collection-preview"){
    h1(classes = "title") { +props.title }
    div(classes = "preview") {
      props.items.take(4).map { item ->
        child(collectionItem) {
          key = "${item.id}"
          attrs.item = item
        }
      }
    }
  }
}