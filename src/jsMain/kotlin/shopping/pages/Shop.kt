package shopping.pages

import kotlinx.coroutines.launch
import react.*
import react.dom.div
import shopping.components.collectionPreview
import shopping.getCollections
import shopping.model.Collection
import shopping.scope

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