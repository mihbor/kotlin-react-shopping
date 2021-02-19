package shopping.components

import kotlinx.coroutines.launch
import kotlinx.html.js.onClickFunction
import react.RProps
import react.dom.div
import react.dom.img
import react.functionalComponent
import react.router.dom.routeLink
import shopping.firebaseAuth
import shopping.model.User
import shopping.scope

external interface HeaderProps : RProps {
  var user: User?
}

val header = functionalComponent<HeaderProps> { props ->
  div(classes="header") {
    routeLink(to="/", className="logo-container") {
      img(alt="crown", src="/static/images/crown.svg", classes="logo") { }
    }
    div(classes="options") {
      routeLink(to="/shop", className="option") {
        +"SHOP"
      }
      routeLink(to="/contact", className="option") {
        +"CONTACT"
      }
      props.user?.let {
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
      } ?: routeLink(to="/login", className="option") {
        +"SIGN IN"
      }
    }
  }
}