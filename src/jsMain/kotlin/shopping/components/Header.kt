package shopping.components

import kotlinx.coroutines.launch
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.div
import react.dom.img
import react.redux.useSelector
import react.router.dom.routeLink
import shopping.firebaseAuth
import shopping.model.User
import shopping.scope

val header = functionalComponent<RProps> {
  val user = useSelector<User?, User?>{it}
  div(classes = "header") {
    routeLink(to = "/", className = "logo-container") {
      img(alt = "crown", src = "/static/images/crown.svg", classes = "logo") { }
    }
    div(classes = "options") {
      routeLink(to = "/shop", className = "option") {
        +"SHOP"
      }
      routeLink(to = "/contact", className = "option") {
        +"CONTACT"
      }
      user?.let {
        div(classes = "option") {
          attrs {
            onClickFunction = {
              scope.launch {
                firebaseAuth.signOut()
              }
            }
          }
          +"SIGN OUT ${it.displayName}"
        }
      } ?: routeLink(to = "/login", className = "option") {
        +"SIGN IN"
      }
    }
  }
}