package shopping.components

import kotlinx.css.Image
import kotlinx.css.backgroundImage
import kotlinx.html.js.onClickFunction
import react.dom.h1
import react.dom.span
import react.functionalComponent
import react.router.dom.useHistory
import styled.css
import styled.styledDiv as div

val menuItem = functionalComponent<SectionProps> { props ->
  val section = props.section
  val history = useHistory()
  div {
    attrs {
      onClickFunction = { history.push("shop/${section.title}") }
    }
    css {
      classes = mutableListOf("menu-item", section.size)
    }
    div {
      css {
        classes = mutableListOf("background-image")
        backgroundImage = Image("url(${section.image})")
      }
    }
    div {
      css {
        classes = mutableListOf("content")
      }
      h1(classes = "title") { +section.title.toUpperCase() }
      span(classes = "subtitle") { +"SHOP NOW" }
    }
  }
}
