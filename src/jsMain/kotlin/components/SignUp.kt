package components

import firebaseAuth
import createUserProfile
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.html.ButtonType
import kotlinx.html.InputType.*
import kotlinx.html.js.onSubmitFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.RProps
import react.dom.*
import react.functionalComponent
import react.useState

@JsExport
val signUp = functionalComponent<RProps> {
  val (state, setState) = useState(emptyMap<String, String>())

  fun handleChange(name: String) : (Event) -> Unit = { event ->
//    val name = (event.target as HTMLInputElement).name // doesn't work for some reason
    val value = (event.target as HTMLInputElement).value

    setState(state + (name to value))
  }
  val handleSubmit: (Event) -> Unit = {
    it.preventDefault()
    console.log("Submitted sing up")
    if (state["password"] != state["passwordConfirm"]) {
      window.alert("Passwords don't match")
      console.log("Passwords don't match")
    } else {
      MainScope().launch {
        val user = firebaseAuth.createUserWithEmailAndPassword(state["email"]!!, state["password"]!!).user
        user?.let{
          createUserProfile(it)
          setState(emptyMap())
        }
      }
    }
  }

  div(classes = "sign-up") {
    h2(classes = "title") {
      +"I do not have an account"
    }
    span {
      +"Sign up with your email and password"
    }
    form(classes = "sign-up-form") {
      attrs {
        name="sign-up"
        onSubmitFunction = handleSubmit
      }
      formInput {
        attrs {
          type = email
          name = "email"
          value = state["email"]
          label = "Email"
          required = true
          onChangeFunction = handleChange("email")
        }
      }
      formInput {
        attrs {
          type = text
          name = "displayName"
          value = state["displayName"]
          label = "Display Name"
          required = true
          onChangeFunction = handleChange("displayName")
        }
      }
      formInput {
        attrs {
          type = password
          name = "password"
          value = state["password"]
          label = "Password"
          required = true
          onChangeFunction = handleChange("password")
        }
      }
      formInput {
        attrs {
          type = password
          name = "passwordConfirm"
          value = state["passwordConfirm"]
          label = "Confirm Password"
          required = true
          onChangeFunction = handleChange("passwordConfirm")
        }
      }
      button {
        attrs {
          type = ButtonType.submit
          label = "SIGN UP"
        }
      }
    }
  }
}