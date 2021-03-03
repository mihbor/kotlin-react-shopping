package shopping.redux

import kotlinx.serialization.Serializable
import react.redux.useSelector
import redux.*
import shopping.model.CartState
import shopping.model.Item
import shopping.model.User

@Serializable
data class State(val user: User? = null, val cart: CartState = CartState())

val combinedReducers = combineReducers<State, RAction>(
  mapOf(
    State::user to ::userEventHandler,
    State::cart to ::cartCommandHandler
  ).mapKeys { it.key.name }
)

val middlewares = compose(applyMiddleware(logger), rEnhancer())

val store = createStore(combinedReducers, State(), middlewares)

fun getUser() = useSelector<State, User?>{ it.user }

fun getCartItems() = useSelector<State, Map<Item, Int>>{ it.cart.items }

fun isCartVisible() = useSelector<State, Boolean>{ it.cart.visible }
