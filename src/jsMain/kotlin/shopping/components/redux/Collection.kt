package shopping.components.redux

import react.RProps
import react.child
import react.functionalComponent
import react.router.dom.useRouteMatch
import shopping.pages.collection
import shopping.propsToMap
import shopping.redux.getCollection

val collectionRedux = functionalComponent<RProps> {
  val match = useRouteMatch<RProps>()!!
  val collectionName = propsToMap(match.params)["collectionName"]!!
  val col = collectionName.let(::getCollection)
  child(collection) {
    attrs.collection = col
  }
}