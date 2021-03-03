package shopping.model

import kotlinx.serialization.Serializable

typealias Cart = Map<Item, Int>

typealias CartEntry = Map.Entry<Item, Int>
val CartEntry.item: Item
  get() = this.key
val CartEntry.quantity: Int
  get() = this.value

fun Cart.total() = entries.fold(0) { sum, entry ->
  sum + entry.item.price * entry.quantity
}
@Serializable
data class CartState(
  val visible: Boolean = false,
  val items: Cart = emptyMap()
) {
  fun clear(item: Item) = CartState(visible, items - item)
  operator fun plus(item: Item) = CartState(visible, items.plus(item to (items.getOrElse(item){0} + 1)))
  operator fun minus(item: Item) =
    items.get(item)
      ?.let { quantity ->
        if (quantity > 1) CartState(visible, items + (item to (quantity - 1)))
        else clear(item)
      } ?: this

}
