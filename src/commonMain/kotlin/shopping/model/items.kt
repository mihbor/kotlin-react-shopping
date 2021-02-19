package shopping.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(val id: Int, val name: String, val imageUrl: String, val price: Int)

@Serializable
data class Collection(val id: Int, val title: String, val routeName: String, val items: Array<Item>)
