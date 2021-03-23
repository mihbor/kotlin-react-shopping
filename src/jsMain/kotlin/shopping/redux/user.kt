package shopping.redux

import dev.gitlive.firebase.decode
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import react.redux.useSelector
import redux.RAction
import redux.WrapperAction
import shopping.authStateChanged
import shopping.db.createUserProfile
import shopping.model.State
import shopping.model.User
import shopping.scope
import shopping.toUser

interface UserEvent: RAction

data class UserSignedIn(val user: User): UserEvent

class UserSignedOut: UserEvent {
  override fun toString() = this::class.simpleName!!
}

fun userEventHandler(state: User? = null, action: RAction) = when (action) {
  is UserSignedIn -> action.user
  is UserSignedOut -> null
  else -> state
}

fun subscribeToAuthStateChanged(dispatch: (UserEvent) -> WrapperAction) {

  fun signOut() {
    dispatch(UserSignedOut())
  }

  fun signIn(user: User) {
    dispatch(UserSignedIn(user))
  }

  scope.launch {
    authStateChanged.collect {
      it?.let {
        createUserProfile(it.toUser())
          .onSnapshot({ snapshot ->
            decode<User?>(snapshot.data()) ?.let { signIn(it) } ?: signOut()
          }, { console.log(it)} )
      } ?: signOut()
    }
  }
}

fun getUser() = useSelector<State, User?>{ it.user }