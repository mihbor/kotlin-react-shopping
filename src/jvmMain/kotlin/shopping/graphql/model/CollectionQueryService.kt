package shopping.graphql.model

import com.expediagroup.graphql.server.operations.Query
import shopping.collectionsData
import shopping.sectionsData

class CollectionQueryService : Query {
    suspend fun collections() = collectionsData
    suspend fun collection(id: Int) = collections().firstOrNull { it.id == id }
    suspend fun collectionByTitle(title: String) = collections().firstOrNull { it.routeName == title }
    suspend fun sections() = sectionsData
}

