package shopping.graphql

import apollo.QueryOptions
import graphql.gql
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.json.decodeFromDynamic
import redux.WrapperAction
import shopping.apolloClient
import shopping.json
import shopping.model.Collection
import shopping.model.Section
import shopping.redux.*
import shopping.scope


val sectionsQuery = gql("""{
  sections {
    id title image isLarge
  }
}""")

fun fetchSectionsGraphQL(dispatch: (DirectoryEvent) -> WrapperAction) {
  scope.launch {
    dispatch(SectionsFetchStarted())
    val result = apolloClient.query<dynamic, Any?>(QueryOptions(sectionsQuery)).await()
    result.run {
      if (!loading) {
        if (data != null) {
          val sections = json.decodeFromDynamic<List<Section>>(data.sections)
          dispatch(SectionsFetchSucceeded(sections))
        }
        if (!errors.isNullOrEmpty()) {
          dispatch(SectionsFetchFailed(errors!!.first()))
          window.alert(errors!!.first().message!!)
          errors!!.forEach {
            console.log("GrapqhQL error: ${JSON.stringify(it)}")
          }
        }
      }
    }
  }
}

val collectionsQuery = gql("""{
  collections {
    id title routeName
    items {
      id name price imageUrl
    }
  }
}""")
val collectionByTitleQuery = gql("""
  query collection(${"$"}title: String!) {
    collectionByTitle(title: ${"$"}title) {
      id title routeName
      items {
        id name price imageUrl
      }
    }
  }
""")

fun fetchCollectionsGraphQL(dispatch: (DirectoryEvent) -> WrapperAction) {
  scope.launch {
    dispatch(CollectionsFetchStarted())
    val result = apolloClient.query<dynamic, Any?>(QueryOptions(collectionsQuery)).await()
    result.run {
      if (!loading) {
        if (data != null) {
          val collections = json.decodeFromDynamic<List<Collection>>(data.collections)
          dispatch(CollectionsFetchSucceeded(collections))
        }
        if (!errors.isNullOrEmpty()) {
          dispatch(CollectionsFetchFailed(errors!!.first()))
          window.alert(errors!!.first().message!!)
          errors!!.forEach {
            console.log("GrapqhQL error: ${JSON.stringify(it)}")
          }
        }
      }
    }
  }
}