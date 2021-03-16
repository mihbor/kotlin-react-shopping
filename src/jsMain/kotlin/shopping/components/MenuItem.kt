package shopping.components

import kotlinx.css.*
import kotlinx.css.Color.Companion.white
import kotlinx.css.Cursor.pointer
import kotlinx.css.FlexBasis.Companion.auto
import kotlinx.css.FlexDirection.column
import kotlinx.css.FontWeight.Companion.lighter
import kotlinx.css.Overflow.hidden
import kotlinx.css.Position.absolute
import kotlinx.css.properties.*
import kotlinx.html.js.onClickFunction
import react.RProps
import react.dom.h1
import react.functionalComponent
import react.router.dom.useHistory
import shopping.model.Section
import styled.StyleSheet
import styled.css
import styled.styledDiv as div
import styled.styledSpan as span

external interface SectionProps : RProps {
  var section: Section
}

val menuItem = functionalComponent<SectionProps> { props ->
  val section = props.section
  val history = useHistory()
  div {
    attrs {
      onClickFunction = { history.push("shop/${section.title}") }
    }
    css {
      +MenuItemStyles.menuItem
      height = LinearDimension(if(section.isLarge) "380px" else "240px")
    }
    div {
      css {
        backgroundImage = Image("url(${section.image})")
        +MenuItemStyles.background
      }
    }
    div {
      css { +MenuItemStyles.content }
      h1(classes = "title") { +section.title.toUpperCase() }
      span {
        css { +MenuItemStyles.subtitle }
        +"SHOP NOW"
      }
    }
  }
}

object MenuItemStyles : StyleSheet("MenuItem", isStatic = true) {
  val background by css {
    width = LinearDimension("100%")
    height = LinearDimension("100%")
    backgroundPosition = "center"
    backgroundSize = "cover"
  }
  val menuItem by css {
    minWidth = LinearDimension("30%")
    display = Display.flex
    flex(1.0, 1.0, auto)
    alignItems = Align.center
    justifyContent = JustifyContent.center
    border = "1px solid black"
    margin = "0 7.5px 15px"
    overflow = hidden

    firstChild { marginRight = LinearDimension("7.5px") }
    lastChild { marginLeft = LinearDimension("7.5px") }
    hover {
      cursor = pointer
      children {
        firstChild { // background
          transform { scale(1.1) }
          transition("transform", 6.s, cubicBezier(0.25, 0.45, 0.45, 0.95))
        }
        lastChild { // content
          opacity = 0.9
        }
      }
    }
  }
  val content by css {
    position = absolute
    height = LinearDimension("90px")
    padding = "0 25px"
    display = Display.flex
    flexDirection = column
    alignItems = Align.center
    justifyContent = JustifyContent.center
    border = "1px solid black"
    backgroundColor = white
    opacity = 0.7
  }
  val subtitle by css {
    fontWeight = lighter
    fontSize = LinearDimension("16px")
  }
}
