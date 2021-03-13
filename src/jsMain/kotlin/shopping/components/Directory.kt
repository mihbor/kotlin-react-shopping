package shopping.components

import kotlinx.css.*
import kotlinx.css.Display.flex
import kotlinx.css.FlexWrap.wrap
import kotlinx.css.JustifyContent.spaceBetween
import react.RProps
import react.child
import react.functionalComponent
import shopping.model.Section
import shopping.redux.getSections
import styled.css
import styled.styledDiv as div

external interface SectionProps : RProps {
  var section: Section
}

val directory = functionalComponent<RProps> {

  val directory = getSections()

  div {
    css {
      width = LinearDimension("100%")
      display = flex
      flexWrap = wrap
      justifyContent = spaceBetween
    }
    directory.map {
      child(menuItem) {
        attrs.section = it
      }
    }
  }
}