package shopping.pages

import kotlinx.coroutines.launch
import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import react.redux.useDispatch
import react.router.dom.RouteResultMatch
import react.router.dom.route
import react.router.dom.useRouteMatch
import react.useEffect
import redux.WrapperAction
import shopping.components.collectionsOverview
import shopping.db.getCollections
import shopping.redux.PopulateCollections
import shopping.scope

val shopPage = functionalComponent<RProps> {
  val dispatch = useDispatch<PopulateCollections, WrapperAction>()
  useEffect(emptyList()) {
    scope.launch {
      dispatch(PopulateCollections(getCollections()))
    }
  }

  val match = useRouteMatch<RProps>() as RouteResultMatch

  div(classes = "shop-page") {
    route(path=match.path, exact = true) {
      child(collectionsOverview)
    }
    route(path="${match.path}/:collectionName") {
      child(collection)
    }
  }
}