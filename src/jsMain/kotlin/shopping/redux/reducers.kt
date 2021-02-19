package shopping.redux

import kotlinx.serialization.Serializable
import redux.RAction
import redux.Reducer
import redux.combineReducers
import shopping.model.User
import kotlin.reflect.KProperty1

class UserLoggedIn(val user: User): RAction
class UserLoggedOut: RAction

fun userReducer(state: User? = null, action: RAction): User? = when (action) {
  is UserLoggedIn -> action.user
  is UserLoggedOut -> null
  else -> state
}

@Serializable
data class State(val user: User?)

fun <S, R> combineReducers(reducers: Map<KProperty1<S, R>, Reducer<*, RAction>>): Reducer<S, RAction> {
  return combineReducers(reducers.mapKeys { it.key.name })
}

fun combinedReducers() = combineReducers(
  mapOf(
    State::user to ::userReducer
  )
)