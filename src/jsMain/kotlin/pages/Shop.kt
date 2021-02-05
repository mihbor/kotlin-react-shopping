package pages

import components.collectionPreview
import getCollections
import model.*

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.*
import react.dom.div

private val scope = MainScope()

@JsExport
val shopPage = functionalComponent<RProps> {
  val (state, setState) = useState {
    emptyList<Collection>()
  }
  useEffect(dependencies = listOf()) {
    scope.launch {
      setState(getCollections())
    }
  }

  div(classes = "shop-page") {
    state.map {
      child(collectionPreview) {
        key = "${it.id}"
        attrs {
          title = it.title
          items = it.items
        }
      }
    }
  }

}