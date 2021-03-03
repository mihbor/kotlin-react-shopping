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

val menuItem = functionalComponent<Section> { props ->
  val history = useHistory()
  div {
    attrs {
      onClickFunction = { history.push(props.title) }
    }
    css {
      classes = mutableListOf("menu-item", props.size)
    }
    div {
      css {
        classes = mutableListOf("background-image")
        backgroundImage = Image("url(${props.image})")
      }
    }
    div {
      css {
        classes = mutableListOf("content")
      }
      h1(classes = "title") { +props.title.toUpperCase() }
      span(classes = "subtitle") { +"SHOP NOW" }
    }
  }
}
