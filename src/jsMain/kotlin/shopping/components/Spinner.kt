package shopping.components

import react.*
import react.dom.div

fun RBuilder.withSpinner(isLoading : Boolean, children: RBuilder.() -> Unit): ReactElement? =
  if (isLoading) {
    div("spinner-overlay") {
      div("spinner-container") { }
    }
  } else {
    buildElements(children)
  }