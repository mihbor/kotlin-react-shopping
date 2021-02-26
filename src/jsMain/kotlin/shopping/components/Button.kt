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
  var invertColors: Boolean?
}

@JsExport
val button = functionalComponent<ButtonProps> { props ->
  button(type = props.type, classes="button${if(props.invertColors ?: false) " inverted" else ""}") {
    +props.label
  }
}

fun RBuilder.button(handler: ButtonProps.() -> Unit) = child(button) {
  this.attrs(handler)
}