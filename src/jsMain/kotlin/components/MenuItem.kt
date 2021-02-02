package components

import kotlinx.css.Image
import kotlinx.css.backgroundImage
import react.dom.h1
import react.dom.span
import react.functionalComponent
import styled.css
import styled.styledDiv as div

@JsExport
val menuItem = functionalComponent<Section> { props ->

  div {
    css {
      classes = mutableListOf("menu-item", props.size)
      backgroundImage = Image("url(${props.image})")
    }
    div {
      css {
        classes = mutableListOf("content")
      }
      h1(classes = "title") { +props.title }
      span(classes = "subtitle") { +"SHOP NOW" }
    }
  }
}
