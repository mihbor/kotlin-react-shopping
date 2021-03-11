package shopping.pages

import react.RProps
import react.child
import react.dom.div
import react.dom.h2
import react.functionalComponent
import react.router.dom.useRouteMatch
import shopping.components.collectionItem
import shopping.propsToMap
import shopping.redux.getCollection

val collection = functionalComponent<RProps> {
  val match = useRouteMatch<RProps>()
  val collection = propsToMap(match!!.params)["collectionName"]?.let(::getCollection)
  div(classes="collection-page") {
    collection?.let {
      h2(classes="title") { +collection.title }
      div(classes="items") {
        collection.items.map { item ->
          child(collectionItem) {
            key = "${item.id}"
            attrs.item = item
          }
        }
      }
    }
  }
}