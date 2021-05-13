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
import shopping.graphql.fetchSectionsGraphQL
import shopping.redux.DirectoryEvent
import shopping.redux.fetchSectionsRest
import shopping.redux.getSections
import shopping.redux.sectionsFetching
import shopping.useGraphQL
import styled.css
import styled.styledDiv as div

val directory = functionalComponent<RProps> {

  val sections = getSections()
  val isFetching = sectionsFetching()
  val isLoaded = !sections.isNullOrEmpty()
  val dispatch = useDispatch<DirectoryEvent, WrapperAction>()
  if (useGraphQL) {
    if (!isLoaded && !isFetching) {
      fetchSectionsGraphQL(dispatch)
    }
  } else {
    useEffect(emptyList()) {
      if (!isLoaded && !isFetching) {
        fetchSectionsRest(dispatch)
      }
    }
  }
  div {
    css {
      width = LinearDimension("100%")
      display = flex
      flexWrap = wrap
      justifyContent = spaceBetween
    }
    sections.map {
      child(menuItem) {
        attrs.section = it
      }
    }
  }
}