import components.header
import dev.gitlive.firebase.decode

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import react.*
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch

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
          createUserProfile(it)
            .onSnapshot ({snapshot ->
              val decodedUser: User? = decode(snapshot.data())
              decodedUser?.let {
                setUser(decodedUser)
                console.log("User ${decodedUser.displayName}")
              } ?: logout()
            }, console::log)
        } ?: logout()
      }
    }
  }

  browserRouter {
    child(header) {
      attrs.user = user
    }
    switch {
      route("/", exact = true) {
        child(pages.homePage) { }
      }
      route("/shop") {
        child(pages.shopPage) { }
      }
      route("/login") {
        child(pages.loginPage) { }
      }
    }
  }
}