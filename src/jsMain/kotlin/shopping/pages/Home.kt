package shopping.pages

import kotlinx.css.Align.center
import kotlinx.css.Display.flex
import kotlinx.css.FlexDirection.column
import kotlinx.css.alignItems
import kotlinx.css.display
import kotlinx.css.flexDirection
import kotlinx.css.padding
import react.RProps
import react.child
import react.functionalComponent
import shopping.components.directory
import styled.css
import styled.styledDiv as div

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