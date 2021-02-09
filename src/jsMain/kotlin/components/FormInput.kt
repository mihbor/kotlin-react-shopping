package components

import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import react.dom.input
import react.dom.label

external interface FormInputProps : RProps {
  var type: InputType?
  var name: String?
  var value: String?
  var required: Boolean?
  var label: String?
  var onChangeFunction: ((Event) -> Unit)?
}

@JsExport
val formInput = functionalComponent<FormInputProps> { props ->
  div(classes = "group") {
    input(classes = "form-input") {
      attrs {
        props.type?.let{type = it}
        props.name?.let{name = it}
        value = props.value?:""
        props.required?.let{required = it}
        props.onChangeFunction?.let{onChangeFunction=it}
      }
    }
    props.label?.let {
      label(classes = "form-input-label${props.value?.let{if(it.length>0) " shrink" else null}?:""}") {
        +"${props.label}"
      }
    }
  }
}

fun RBuilder.formInput(handler: FormInputProps.() -> Unit) = child(formInput) {
  this.attrs(handler)
}