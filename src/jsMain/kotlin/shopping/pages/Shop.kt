package shopping.pages

import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import react.redux.useDispatch
import react.router.dom.RouteResultMatch
import react.router.dom.route
import react.router.dom.useRouteMatch
import redux.WrapperAction
import shopping.components.collectionsOverview
import shopping.components.withSpinner
import shopping.redux.DirectoryEvent
import shopping.redux.collectionsFetching
import shopping.redux.collectionsPresent
import shopping.redux.fetchCollectionsGraphQL

val shopPage = functionalComponent<RProps> {

  val isFetching = collectionsFetching()
  val isLoaded = collectionsPresent()
  val dispatch = useDispatch<DirectoryEvent, WrapperAction>()

//  useEffect(emptyList()) {
//    if (!isLoaded && !isFetching) {
//      fetchCollectionsRest(dispatch)
//    }
//  }
  if (!isLoaded && !isFetching) {
    fetchCollectionsGraphQL(dispatch)
  }

  val match = useRouteMatch<RProps>() as RouteResultMatch

  div(classes = "shop-page") {
    route(path = match.path, exact = true) {
      withSpinner(!isLoaded) {
        child(collectionsOverview)
      }
    }
    route(path = "${match.path}/:collectionName") {
      withSpinner(!isLoaded) {
        child(collection)
      }
    }
  }
}