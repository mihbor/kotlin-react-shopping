package shopping.components.redux

import react.RProps
import react.child
import react.functionalComponent
import shopping.components.collectionsOverview
import shopping.redux.getCollections

val collectionsOverviewRedux = functionalComponent<RProps> {
  val cols = getCollections()
  child(collectionsOverview) {
    attrs.collections = cols.values
  }
}