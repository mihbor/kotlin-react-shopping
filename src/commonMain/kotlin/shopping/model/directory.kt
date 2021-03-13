package shopping.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(val id: Int, val name: String, val imageUrl: String, val price: Int)

@Serializable
data class Collection(val id: Int, val title: String, val routeName: String, val items: List<Item>)

@Serializable
data class Section(var id: Int, var title: String, var image: String, var isLarge: Boolean = false)

@Serializable
data class DirectoryState(val sections: List<Section> = emptyList(), val collections: Map<String, Collection> = emptyMap())