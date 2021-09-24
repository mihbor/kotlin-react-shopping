package shopping

import react.RProps
import react.child
import react.functionalComponent
import react.redux.useDispatch
import react.router.dom.browserRouter
import react.router.dom.redirect
import react.router.dom.route
import react.router.dom.switch
import react.useEffect
import redux.RAction
import redux.WrapperAction
import shopping.components.redux.header
import shopping.pages.checkout
import shopping.pages.homePage
import shopping.pages.shopPageGraphQL
import shopping.pages.shopPageRest
import shopping.redux.getUser
import shopping.redux.subscribeToAuthStateChanged

const val useGraphQL = true

val app = functionalComponent<RProps> {

  val dispatch = useDispatch<RAction, WrapperAction>()
  val user = getUser()

  useEffect(emptyList()) {
    subscribeToAuthStateChanged(dispatch)
  }

  browserRouter {
    child(header) { }
    switch {
      route("/", exact = true) {
        child(homePage)
      }
      route("/shop") {
        child(if (useGraphQL) shopPageGraphQL else shopPageRest) { }
      }
      route("/checkout", exact = true) {
        child(checkout) { }
      }
      route("/login", exact = true) {
        user?.let{ redirect(to="/") } ?: child(shopping.pages.loginPage) { }
      }
    }
  }
}