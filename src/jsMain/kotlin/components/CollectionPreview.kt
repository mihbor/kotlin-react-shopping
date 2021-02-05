package components

import model.*
import react.RProps
import react.dom.h1
import react.dom.div
import react.functionalComponent

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
        div {
          key = "${it.id}"
          +it.name
        }
      }
    }
  }
}