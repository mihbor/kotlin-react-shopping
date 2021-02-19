package shopping

import dev.gitlive.firebase.decode
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import react.*
import react.redux.rConnect
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
interface AppProps : RProps {
  var signIn: (User) -> Unit
  var signOut: () -> Unit
}

val app = rConnect<UserEvent, WrapperAction, RProps, AppProps>({ dispatch, _ ->
  signIn = { dispatch(UserSignedIn(it)) }
  signOut = {
    dispatch(UserSignedOut())
    console.log("Logged out")
  }
})(
  App::class.js.unsafeCast<RClass<RProps>>()
)

class App : RComponent<AppProps, RState>() {

  override fun componentDidMount() {
    scope.launch {
      authStateChanged.collect {
        it?.let {
          createUserProfile(it.toUser())
            .onSnapshot({ snapshot ->
              val decodedUser: User? = decode(snapshot.data())
              decodedUser?.let {
                props.signIn(decodedUser)
                console.log("User ${decodedUser.email} display name ${decodedUser.displayName}")
              } ?: props.signOut()
            }, console::log)
        } ?: props.signOut()
      }
    }
  }

  override fun RBuilder.render() {

    browserRouter {
      header {}
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
}