package shopping.redux

import kotlinx.serialization.Serializable
import react.redux.useSelector
import redux.*
import shopping.model.CartState
import shopping.model.User

@Serializable
data class State(val user: User? = null, val cart: CartState = CartState())

val combinedReducers = combineReducers<State, RAction>(
  mapOf(
    State::user to ::userEventHandler,
    State::cart to ::cartCommandHandler
  ).mapKeys { it.key.name }
)

val loggerMiddleware:Middleware<State, RAction, WrapperAction, RAction, WrapperAction> = { middleware ->
  { next ->
    { action ->
      console.log("prev state: ${middleware.getState()}")
      console.log("action: $action")
      val result = next(action)
      console.log("next state: ${middleware.getState()}")
      result
    }
  }
}

val middlewares = compose(applyMiddleware(loggerMiddleware), rEnhancer())

val store = createStore(combinedReducers, State(), middlewares)

fun getUser() = useSelector<State, User?>{ it.user }

fun isCartVisible() = useSelector<State, Boolean>{ it.cart.visible }