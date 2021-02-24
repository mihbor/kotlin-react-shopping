package shopping.redux

import react.redux.useSelector
import redux.RAction
import shopping.model.User

open class UserEvent: RAction

data class UserSignedIn(val user: User): UserEvent()

class UserSignedOut: UserEvent() {
  override fun toString() = UserSignedOut::class.simpleName!!
}

fun userEventHandler(state: User? = null, action: RAction) = when (action) {
  is UserSignedIn -> action.user
  is UserSignedOut -> null
  else -> state
}

fun getUser() = useSelector<State, User?>{it.user}