package shopping.redux

import redux.RAction
import shopping.model.User

open class UserEvent: RAction
data class UserSignedIn(val user: User): UserEvent()
class UserSignedOut: UserEvent() {
  override fun toString(): String {
    return "UserSignedOut"
  }
}

fun userEventHandler(state: User? = null, action: RAction): User? = when (action) {
  is UserSignedIn -> action.user
  is UserSignedOut -> null
  else -> state
}

//@Serializable
//data class State(val user: User?)
//
//fun <S, R> combineReducers(reducers: Map<KProperty1<S, R>, Reducer<*, RAction>>): Reducer<S, RAction> {
//  return combineReducers(reducers.mapKeys { it.key.name })
//}
//
//fun combinedReducers() = combineReducers(
//  mapOf(
//    State::user to ::userReducer
//  )
//)