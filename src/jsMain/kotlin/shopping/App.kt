package shopping

import dev.gitlive.firebase.decode
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import react.*
import react.redux.provider
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch
import shopping.components.header
import shopping.model.User
import shopping.redux.store

val scope = MainScope()

@JsExport
val app = functionalComponent<RProps> {
  val (user, setUser) = useState(null as User?)

  fun logout() {
    setUser(null)
    console.log("Logged out")
  }

  useEffect(dependencies = listOf()) {
    scope.launch {
      authStateChanged.collect {
        it?.let{
          createUserProfile(it.toUser())
            .onSnapshot ({snapshot ->
              val decodedUser: User? = decode(snapshot.data())
              decodedUser?.let {
                setUser(decodedUser)
                console.log("User ${decodedUser.email} display name ${decodedUser.displayName}")
              } ?: logout()
            }, console::log)
        } ?: logout()
      }
    }
  }

  provider(store) {
    browserRouter {
      child(header) {
        attrs.user = user
      }
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