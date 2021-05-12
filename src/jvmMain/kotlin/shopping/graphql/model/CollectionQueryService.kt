package shopping.graphql.model

import com.expediagroup.graphql.server.operations.Query
import shopping.collectionsData

class CollectionQueryService : Query {
    suspend fun collections() = collectionsData
    suspend fun collection(id: Int) = collections().firstOrNull { it.id == id }
    suspend fun collectionsByTitle(title: String) = collections().filter { it.title == title }
}

