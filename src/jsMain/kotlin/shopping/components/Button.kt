package shopping.components

import kotlinx.css.*
import kotlinx.css.Color.Companion.black
import kotlinx.css.Color.Companion.white
import kotlinx.css.Cursor.pointer
import kotlinx.css.Display.flex
import kotlinx.css.FontWeight.Companion.bolder
import kotlinx.css.JustifyContent.center
import kotlinx.css.TextTransform.uppercase
import kotlinx.css.properties.LineHeight
import kotlinx.html.ButtonType
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import shopping.components.ButtonStyles.blackOnWhite
import shopping.components.ButtonStyles.whiteOnBlack
import styled.StyleSheet
import styled.css
import styled.styledButton as button

external interface ButtonProps : RProps {
  var type: ButtonType?
  var label: String
  var invertColors: Boolean?
}

val button = functionalComponent<ButtonProps> { props ->
  button(type = props.type) {
    css {
      +ButtonStyles.button
      if (props.invertColors ?: false) {
        +blackOnWhite
        hover { +whiteOnBlack }
      } else {
        +whiteOnBlack
        hover { +blackOnWhite}
      }
    }
    +props.label
  }
}

fun RBuilder.button(handler: ButtonProps.() -> Unit) = child(button) {
  this.attrs(handler)
}

object ButtonStyles : StyleSheet("Button", isStatic = true) {
  val button by css {
    minWidth = LinearDimension("165px")
    width = LinearDimension("auto")
    height = LinearDimension("50px")
    letterSpacing = LinearDimension("0.5px")
    lineHeight = LineHeight("50px")
    padding = "0 35px 0 35px"
    fontSize = LinearDimension("15px")
    textTransform = uppercase
    fontWeight = bolder
    cursor = pointer
    display = flex
    justifyContent = center
  }
  val blackOnWhite by css {
    backgroundColor = white
    color = black
    border = "1px solid black"
  }
  val whiteOnBlack by css {
    backgroundColor = black
    color = white
    border = "none"
  }
}