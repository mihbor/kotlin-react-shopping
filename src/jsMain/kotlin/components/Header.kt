package components

import react.RProps
import react.dom.div
import react.dom.img
import react.functionalComponent
import react.router.dom.routeLink

val header = functionalComponent<RProps> {
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
    }
  }
}