package shopping.redux

import kotlinx.serialization.Serializable
import redux.*
import shopping.model.User

@Serializable
data class State(val user: User? = null, val cartVisible: Boolean = false)

val combinedReducers = combineReducers<State, RAction>(
  mapOf(
    State::user to ::userEventHandler,
    State::cartVisible to ::cartCommandHandler
  ).mapKeys { it.key.name }
)

val loggerMiddleware:Middleware<State, RAction, WrapperAction, RAction, WrapperAction> = { middleware ->
  { next ->
    { action ->
      console.log("prev state: ${JSON.stringify(middleware.getState())}")
      console.log("action: $action")
      val result = next(action)
      console.log("next state: ${JSON.stringify(middleware.getState())}")
      result
    }
  }
}

val middlewares = compose(applyMiddleware(loggerMiddleware), rEnhancer())

val store = createStore(combinedReducers, JSON.parse(JSON.stringify(State())), middlewares)
