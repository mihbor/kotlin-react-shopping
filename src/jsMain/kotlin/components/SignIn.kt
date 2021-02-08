package components

import kotlinx.html.InputType.*
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onSubmitFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.RProps
import react.dom.*
import react.functionalComponent
import react.useState

val signIn = functionalComponent<RProps> {
  val (state, setState) = useState(emptyMap<String, String>())

  val handleSubmit: (Event) -> Unit = {
    it.preventDefault()
    setState(emptyMap())
  }

  div(classes="sign-in") {
    h2 { +"I already have an account" }
    span { +"Sign in with email and password" }

    form {
      attrs {
        onSubmitFunction=handleSubmit
      }

      input(name="email", type=email) {
        attrs {
          required=true
          value=state["email"]?:""
          onChangeFunction={setState(state + Pair("email", (it.target as HTMLInputElement).value))}
        }
      }
      label { +"EMAIL" }
      input(name="password", type=password) {
        attrs {
          required=true
          value=state["password"]?:""
          onChangeFunction={setState(state + Pair("password", (it.target as HTMLInputElement).value))}
        }
      }
      label { +"PASSWORD" }
      input(type=submit) {
        attrs {
          value="Submit Form"
        }
      }
    }
  }
}