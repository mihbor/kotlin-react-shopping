package shopping.components

import kotlinx.html.ButtonType
import react.RBuilder
import react.RProps
import react.child
import react.dom.button
import react.functionalComponent

external interface ButtonProps : RProps {
  var type: ButtonType?
  var label: String
}

@JsExport
val button = functionalComponent<ButtonProps> { props ->
  button(type = props.type, classes="button") {
    +props.label
  }
}

fun RBuilder.button(handler: ButtonProps.() -> Unit) = child(button) {
  this.attrs(handler)
}