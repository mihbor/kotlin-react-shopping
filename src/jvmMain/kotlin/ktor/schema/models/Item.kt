package com.expediagroup.graphql.examples.server.ktor.schema.models

import com.expediagroup.graphql.generator.scalars.ID
import java.util.*

data class Item(
    val id: ID,
    val name: String,
    val price: Int,
    val imageUrl: String? = null,
    val collection: Collection? = null
)
