package shopping.redux

import react.redux.useSelector
import redux.RAction
import shopping.model.CartState
import shopping.model.Item
import shopping.model.State

open class CartCommand: RAction

class ShowCart: CartCommand() {
  override fun toString() = this::class.simpleName!!
}
class HideCart: CartCommand() {
  override fun toString() = this::class.simpleName!!
}
data class AddToCart(val item: Item): CartCommand()
data class ClearFromCart(val item: Item): CartCommand()
data class RemoveFromCart(val item: Item): CartCommand()

fun cartCommandHandler(state: CartState = CartState(), action: RAction) = when(action) {
  is AddToCart -> state + action.item
  is RemoveFromCart -> state - action.item
  is ClearFromCart -> state.clear(action.item)
  is ShowCart -> CartState(true, state.items)
  is HideCart -> CartState(false, state.items)
  else -> state
}

fun getCartItems() = useSelector<State, Map<Item, Int>>{ it.cart.items }

fun isCartVisible() = useSelector<State, Boolean>{ it.cart.visible }