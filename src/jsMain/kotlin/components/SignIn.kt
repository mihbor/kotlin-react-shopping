package components

import kotlinx.html.InputType.*
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

      formInput {
        attrs {
          type=email
          name="email"
          required=true
          value=state["email"]
          label="email"
          onChangeFunction={setState(state + Pair("email", (it.target as HTMLInputElement).value))}
        }
      }
      formInput {
        attrs {
          type=password
          name="password"
          required=true
          value=state["password"]
          label="password"
          onChangeFunction={setState(state + Pair("password", (it.target as HTMLInputElement).value))}
        }
      }
      input {
        attrs {
          type=submit
          value="Submit Form"
        }
      }
    }
  }
}