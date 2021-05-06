package shopping.graphql.model

import com.expediagroup.graphql.generator.scalars.ID

data class Collection(
    val id: ID,
    val title: String,
    val items: List<Item> = listOf()
)
