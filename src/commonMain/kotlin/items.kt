data class Item(val id: Int, val name: String, val imageUrl: String, val price: Number)

data class Collection(val id: Int, val title: String, val routeName: String, val items: List<Item>)
