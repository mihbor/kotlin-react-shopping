package shopping.pages

import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import react.redux.useDispatch
import react.router.dom.route
import react.router.dom.useRouteMatch
import react.useEffect
import redux.WrapperAction
import shopping.components.redux.collectionRedux
import shopping.components.redux.collectionsOverviewRedux
import shopping.components.withSpinner
import shopping.redux.DirectoryEvent
import shopping.redux.collectionsFetching
import shopping.redux.collectionsPresent
import shopping.redux.fetchCollectionsRest


val shopPageRest = functionalComponent<RProps> {

  val isFetching = collectionsFetching()
  val isLoaded = collectionsPresent()
  val dispatch = useDispatch<DirectoryEvent, WrapperAction>()

  useEffect(emptyList()) {
    if (!isLoaded && !isFetching) {
      fetchCollectionsRest(dispatch)
    }
  }

  val match = useRouteMatch<RProps>()!!

  div(classes = "shop-page") {
    route(match.path, exact = true) {
      withSpinner(!isLoaded) {
        child(collectionsOverviewRedux)
      }
    }
    route("${match.path}/:collectionName") {
      withSpinner(!isLoaded) {
        child(collectionRedux)
      }
    }
  }
}