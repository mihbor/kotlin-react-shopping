package shopping.redux

import redux.RAction
import shopping.model.CartState
import shopping.model.Item

open class CartCommand: RAction

class ShowCart: CartCommand() {
  override fun toString() = this::class.simpleName!!
}
class HideCart: CartCommand() {
  override fun toString() = this::class.simpleName!!
}

data class AddItemToCart(val item: Item): CartCommand()

fun cartCommandHandler(state: CartState = CartState(), action: RAction) = when(action) {
  is AddItemToCart -> state + action.item
  is ShowCart -> CartState(true, state.items)
  is HideCart -> CartState(false, state.items)
  else -> state
}