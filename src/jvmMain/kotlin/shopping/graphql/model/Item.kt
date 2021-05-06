package shopping.graphql.model

import com.expediagroup.graphql.generator.scalars.ID

data class Item(
    val id: ID,
    val name: String,
    val price: Int,
    val imageUrl: String? = null,
    val collection: Collection? = null
)
