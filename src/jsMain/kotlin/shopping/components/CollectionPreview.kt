package shopping.components

import react.RProps
import react.child
import react.dom.div
import react.dom.h1
import react.functionalComponent
import shopping.model.Item

external interface CollectionPreviewProps : RProps {
  var title: String
  var items: Array<Item>
}

@JsExport
val collectionPreview = functionalComponent<CollectionPreviewProps> {
  div(classes = "collection-preview"){
    h1(classes = "title") { +it.title }
    div(classes = "preview") {
      it.items.take(4).map {
        child(collectionItem) {
          key = "${it.id}"
          attrs {
            name = it.name
            price = it.price
            imageUrl = it.imageUrl
          }
        }
      }
    }
  }
}