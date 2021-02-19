package shopping.components

import kotlinx.coroutines.launch
import kotlinx.html.ButtonType
import kotlinx.html.InputType.email
import kotlinx.html.InputType.password
import kotlinx.html.js.onSubmitFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.RProps
import react.dom.div
import react.dom.form
import react.dom.h2
import react.dom.span
import react.functionalComponent
import react.useState
import shopping.firebaseAuth
import shopping.scope

//import signInWithGoogle

val signIn = functionalComponent<RProps> {
  val (state, setState) = useState(emptyMap<String, String>())

  val handleSubmit: (Event) -> Unit = {
    it.preventDefault()
    scope.launch {
      firebaseAuth.signInWithEmailAndPassword(state["email"]!!, state["password"]!!)
      setState(emptyMap())
      console.log("Submitted sign in")
    }
  }

  div(classes="sign-in") {
    h2(classes = "title")  { +"I already have an account" }
    span { +"Sign in with email and password" }

    form {
      attrs {
        name="sign-in"
        onSubmitFunction=handleSubmit
      }

      formInput {
        attrs {
          type=email
          name="email"
          required=true
          value=state["email"]
          label="email"
          onChangeFunction={setState(state + ("email" to (it.target as HTMLInputElement).value))}
        }
      }
      formInput {
        attrs {
          type=password
          name="password"
          required=true
          value=state["password"]
          label="password"
          onChangeFunction={setState(state + ("password" to (it.target as HTMLInputElement).value))}
        }
      }
      button {
        attrs {
          type=ButtonType.submit
          label="Sign In"
        }
      }
//      button {
//        attrs {
//          onClickFunction={signInWithGoogle()}
//          label="Sign In With Google"
//        }
//      }
    }
  }
}


