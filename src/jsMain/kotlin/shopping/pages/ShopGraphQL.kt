package shopping.pages

import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import react.router.dom.route
import react.router.dom.useRouteMatch
import shopping.components.graphql.collectionGraphQL
import shopping.components.graphql.collectionsOverviewGraphQL

val shopPageGraphQL = functionalComponent<RProps> {

  val match = useRouteMatch<RProps>()!!

  div(classes = "shop-page") {
    route(match.path, exact = true) {
      child(collectionsOverviewGraphQL)
    }
    route("${match.path}/:collectionName") {
      child(collectionGraphQL)
    }
  }
}