package shopping.components

import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import shopping.model.Section
import shopping.redux.getSections

external interface SectionProps : RProps {
  var section: Section
}

val directory = functionalComponent<RProps> {

  val directory = getSections()

  div(classes = "directory-menu") {
    directory.map {
      child(menuItem) {
        attrs.section = it
      }
    }
  }
}