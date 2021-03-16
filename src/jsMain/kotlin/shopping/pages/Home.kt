package shopping.pages

import kotlinx.css.*
import kotlinx.css.Align.center
import kotlinx.css.Display.flex
import kotlinx.css.FlexDirection.column
import react.RProps
import react.child
import styled.styledDiv as div
import react.functionalComponent
import shopping.components.directory
import styled.css

val homePage = functionalComponent<RProps> {
  div {
    child(directory)
    css {
      display = flex
      flexDirection = column
      alignItems = center
      padding = "20px 80px"
    }
  }
}