package com.expediagroup.graphql.examples.server.ktor.schema

import com.expediagroup.graphql.examples.server.ktor.schema.models.Collection
import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query

class CollectionQueryService : Query {
    suspend fun collections() = Collection.data
    suspend fun collection(id: ID) = Collection.data.filter { it.id == id }.firstOrNull()
    suspend fun collectionsByTitle(title: String) = Collection.data.filter { it.title == title }
}
