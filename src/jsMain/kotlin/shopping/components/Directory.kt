package shopping.components

import kotlinx.css.*
import kotlinx.css.Display.flex
import kotlinx.css.FlexWrap.wrap
import kotlinx.css.JustifyContent.spaceBetween
import react.RProps
import react.child
import react.functionalComponent
import react.redux.useDispatch
import react.useEffect
import redux.WrapperAction
import shopping.redux.*
import styled.css
import styled.styledDiv as div

val directory = functionalComponent<RProps> {

  val directory = getSections()
  val isFetching = sectionsFetching()
  val isLoaded = !directory.isNullOrEmpty()
  val dispatch = useDispatch<DirectoryEvent, WrapperAction>()

  useEffect(emptyList()) {
    if (!isLoaded && !isFetching) {
      fetchSections(dispatch)
    }
  }
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