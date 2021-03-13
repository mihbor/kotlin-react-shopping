package shopping.components

import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.css.Align.center
import kotlinx.css.Cursor.pointer
import kotlinx.css.Display.flex
import kotlinx.css.JustifyContent.flexEnd
import kotlinx.css.JustifyContent.spaceBetween
import kotlinx.html.js.onClickFunction
import react.RProps
import react.child
import react.dom.img
import react.functionalComponent
import react.router.dom.routeLink
import shopping.firebaseAuth
import shopping.redux.getUser
import shopping.redux.isCartVisible
import shopping.scope
import styled.StyleSheet
import styled.css
import styled.styledDiv as div

val header = functionalComponent<RProps> {
  val user = getUser()
  val isCartVisible = isCartVisible()
  div {
    css { +HeaderStyles.header }
    routeLink(to = "/", className = "Header-logoContainer") {
      img(alt = "crown", src = "/static/images/crown.svg", classes = "logo") { }
    }
    div {
      css { +HeaderStyles.options }
      routeLink(to = "/shop", className = "Header-option") { +"SHOP" }
      routeLink(to = "/contact", className = "Header-option") { +"CONTACT" }
      user?.let {
        div {
          css { +HeaderStyles.option }
          attrs {
            onClickFunction = {
              scope.launch {
                firebaseAuth.signOut()
              }
            }
          }
          +"SIGN OUT ${it.displayName}"
        }
      } ?: routeLink(to = "/login", className = "Header-option") { +"SIGN IN" }
      child(cartIcon) { }
    }
    if (isCartVisible) {
      child(cartDropdown) { }
    }
  }
}

object HeaderStyles : StyleSheet("Header", isStatic = true) {
  val header by css {
    width = LinearDimension("100%")
    height = LinearDimension("70px")
    display = flex
    justifyContent = spaceBetween
    marginBottom = LinearDimension("25px")
  }
  val logoContainer by css {
    height = LinearDimension("100%")
    width = LinearDimension("70%")
    padding = "25px"
  }
  val options by css {
    width = LinearDimension("50%")
    height = LinearDimension("100%")
    display = flex
    alignItems = center
    justifyContent = flexEnd
  }
  val option by css {
    padding = "10px 15px"
    cursor = pointer
  }
}