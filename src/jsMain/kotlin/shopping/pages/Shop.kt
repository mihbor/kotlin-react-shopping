package shopping.pages

import kotlinx.browser.window
import kotlinx.coroutines.launch
import react.*
import react.dom.div
import react.redux.useDispatch
import react.router.dom.RouteResultMatch
import react.router.dom.route
import react.router.dom.useRouteMatch
import redux.WrapperAction
import shopping.API
import shopping.components.collectionsOverview
import shopping.components.withSpinner
import shopping.redux.*
import shopping.scope

val shopPage = functionalComponent<RProps> {

  val isFetching = collectionsFetching()
  val isLoaded = collectionsPresent()
  val dispatch = useDispatch<DirectoryEvent, WrapperAction>()

  useEffect(emptyList()) {
    if (!isLoaded && !isFetching) {
      scope.launch {
        dispatch(CollectionsFetchStarted())
        try {
          dispatch(CollectionsFetchSucceeded(API.getCollections()))
        } catch (e: Exception) {
          dispatch(CollectionsFetchFailed(e))
          window.alert(e.message!!)
        }
      }
    }
  }

  val match = useRouteMatch<RProps>() as RouteResultMatch

  div(classes = "shop-page") {
    route(path=match.path, exact = true) {
      withSpinner(!isLoaded) {
        child(collectionsOverview)
      }
    }
    route(path="${match.path}/:collectionName") {
      withSpinner(!isLoaded) {
        child(collection)
      }
    }
  }
}