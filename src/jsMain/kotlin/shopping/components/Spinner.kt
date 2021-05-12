package shopping.components

import react.*
import react.dom.div

val spinner = functionalComponent<RProps> {
  div("spinner-overlay") {
    div("spinner-container") { }
  }
}

fun RBuilder.withSpinner(isLoading : Boolean, children: RBuilder.() -> Unit): ReactElement? =
  if (isLoading) {
    child(spinner)
  } else {
    buildElements(children)
  }