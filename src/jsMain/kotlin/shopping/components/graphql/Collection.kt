package shopping.components.graphql

import apollo.QueryHookOptions
import apollo.react.useQuery
import kotlinx.serialization.json.decodeFromDynamic
import react.RProps
import react.child
import react.functionalComponent
import react.router.dom.useRouteMatch
import shopping.components.spinner
import shopping.graphql.collectionByTitleQuery
import shopping.json
import shopping.model.Collection
import shopping.pages.collection
import shopping.propsToMap

val collectionGraphQL = functionalComponent<RProps> {
  val match = useRouteMatch<RProps>()!!
  val collectionName = propsToMap(match.params)["collectionName"]!!
  val result = useQuery<dynamic, Any?>(collectionByTitleQuery, QueryHookOptions{title = collectionName})
  console.log("result data: ${JSON.stringify(result.data)}")
  if (result.loading) {
    child(spinner)
  } else if (result.data.collectionByTitle != null) {
    child(collection) {
      attrs.collection = json.decodeFromDynamic<Collection>(result.data.collectionByTitle)
    }
  }
}