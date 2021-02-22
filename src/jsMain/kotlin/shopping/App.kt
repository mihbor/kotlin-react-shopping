package shopping

import dev.gitlive.firebase.decode
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import react.*
import react.redux.useDispatch
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch
import redux.WrapperAction
import shopping.components.header
import shopping.model.User
import shopping.redux.UserEvent
import shopping.redux.UserSignedIn
import shopping.redux.UserSignedOut

val scope = MainScope()

val app = functionalComponent<RProps> {

  val dispatch = useDispatch<UserEvent, WrapperAction>()

  fun signOut() {
    dispatch(UserSignedOut())
  }

  fun signIn(user: User) {
    dispatch(UserSignedIn(user))
  }

  useEffect {
    scope.launch {
      authStateChanged.collect {
        it?.let {
          createUserProfile(it.toUser())
            .onSnapshot({ snapshot ->
              val user: User? = decode(snapshot.data())
              user?.let { signIn(user) } ?: signOut()
            }, {console.log(it.message)})
        } ?: signOut()
      }
    }
  }

  browserRouter {
    child(header) {}
    switch {
      route("/", exact = true) {
        child(shopping.pages.homePage) { }
      }
      route("/shop") {
        child(shopping.pages.shopPage) { }
      }
      route("/login") {
        child(shopping.pages.loginPage) { }
      }
    }
  }
}