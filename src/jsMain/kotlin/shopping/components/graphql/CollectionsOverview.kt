package shopping.components.graphql

import apollo.react.useQuery
import kotlinx.serialization.json.decodeFromDynamic
import react.RProps
import react.child
import react.functionalComponent
import shopping.components.collectionsOverview
import shopping.components.spinner
import shopping.graphql.collectionsQuery
import shopping.json
import shopping.model.Collection

val collectionsOverviewGraphQL = functionalComponent<RProps> {
  val result = useQuery<dynamic, Any?>(collectionsQuery)
  if (result.loading) {
    child(spinner)
  } else if (result.data != null) {
    child(collectionsOverview) {
      attrs.collections = json.decodeFromDynamic<List<Collection>>(result.data.collections)
    }
  }
}