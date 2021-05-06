package shopping.graphql.model

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query
import shopping.collectionsData

class CollectionQueryService : Query {
    suspend fun collections() = collectionsData.map{it.toGraphQL()}
    suspend fun collection(id: ID) = collections().filter { it.id == id }.firstOrNull()
    suspend fun collectionsByTitle(title: String) = collections().filter { it.title == title }
}

fun shopping.model.Collection.toGraphQL() = Collection(ID(id.toString()), title, items.map{it.toGraphQL()})
fun shopping.model.Item.toGraphQL() = Item(ID(id.toString()), name, price, imageUrl)
