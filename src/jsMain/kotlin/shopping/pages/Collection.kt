package shopping.pages

import react.RProps
import react.dom.div
import react.dom.h2
import react.functionalComponent
import react.router.dom.useRouteMatch
import shopping.propsToMap
import shopping.redux.getCollection

val collection = functionalComponent<RProps> {
  val match = useRouteMatch<RProps>()
  val collection = propsToMap(match!!.params)["collectionName"]?.let(::getCollection)
  div(classes="collection-page") {
    collection?.let {
      h2 {
        +"${collection.title} PAGE"
      }
    }
  }
}