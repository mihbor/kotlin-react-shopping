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
import shopping.components.header
import shopping.pages.checkout
import shopping.pages.homePage
import shopping.pages.shopPage
import shopping.redux.getUser
import shopping.redux.subscribeToAuthStateChanged

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
        child(homePage) { }
      }
      route("/shop") {
        child(shopPage) { }
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