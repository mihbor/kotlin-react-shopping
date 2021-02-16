import components.header

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
//  val (user, setUser) = useState(null as User?)

//  useEffect(dependencies = listOf()) {
//    scope.launch {
//      authStateChanged.collect {
//        it?.let{
//          createUserProfile(it)
//            .snapshots.collect { snapshot ->
//              setUser(snapshot.data())
//              console.log(user)
//            }
//        }
//      }
//    }
//  }

  browserRouter {
    child(header) {
//      attrs.user = user
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