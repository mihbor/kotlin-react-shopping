package shopping.components

import kotlinext.js.jsObject
import react.*

external interface ErrorState : RState {
  var hasErrored: Boolean
}

fun ErrorState.init() {
  hasErrored = false
}

class ErrorBoundary : RComponent<RProps, ErrorState>() {
  companion object : RStatics<RProps, ErrorState, ErrorBoundary, Nothing>(ErrorBoundary::class) {
    init {
      getDerivedStateFromError = {
        jsObject {
          hasErrored = true
        }
      }
    }
  }

  override fun componentDidCatch(error: Throwable, info: RErrorInfo) {
    console.log(error)
  }

  override fun RBuilder.render() {

  }

}