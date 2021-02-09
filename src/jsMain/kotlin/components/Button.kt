package components

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
  button(classes="button") {
    attrs {
      props.type?.let{type = it}
    }
    +props.label
  }
}

fun RBuilder.button(handler: ButtonProps.() -> Unit) = child(button) {
  this.attrs(handler)
}