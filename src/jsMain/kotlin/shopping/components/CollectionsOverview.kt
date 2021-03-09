package shopping.components

import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import shopping.redux.getCollections

val collectionsOverview = functionalComponent<RProps> { props ->
  val collections = getCollections()
  div(classes="collections-overview") {
    collections.map {
      child(collectionPreview) {
        key = "${it.id}"
        attrs {
          title = it.title
          items = it.items
        }
      }
    }
  }
}