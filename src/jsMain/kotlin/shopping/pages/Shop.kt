package shopping.pages

import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import react.router.dom.RouteResultMatch
import react.router.dom.route
import react.router.dom.useRouteMatch
import shopping.components.collectionsOverview

val shopPage = functionalComponent<RProps> {
  val match = useRouteMatch<RProps>() as RouteResultMatch

  div(classes = "shop-page") {
    route(path=match.path, exact = true) {
      child(collectionsOverview) {}
    }
    route(path="${match.path}/:collectionName") {
      child(collection) {}
    }
  }
}