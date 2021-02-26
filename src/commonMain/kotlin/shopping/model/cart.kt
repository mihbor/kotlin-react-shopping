package shopping.model

import kotlinx.serialization.Serializable

@Serializable
data class CartState(
  val visible: Boolean = false,
  val items: Map<Item, Int> = emptyMap()
) {
  operator fun plus(item: Item) = CartState(visible, items.plus(item to (items.getOrElse(item){0} + 1)))
  operator fun minus(item: Item) =
    items.getOrElse(item){ throw NoSuchElementException(item.toString()) }
      .let { count ->
        if (count > 1) CartState(visible, items + (item to (count - 1)))
        else CartState(visible, items - item)
      }

}
