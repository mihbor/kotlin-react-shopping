package shopping.redux

import react.redux.useSelector
import redux.RAction

open class CartCommand: RAction

data class SetCartVisibility(val visible: Boolean): CartCommand()

fun cartCommandHandler(state: Boolean = false, action: RAction) = when(action) {
  is SetCartVisibility -> action.visible
  else -> state
}

fun isCartVisible() = useSelector<State, Boolean>{it.cartVisible}