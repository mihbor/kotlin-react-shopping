package components

import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import react.useState

class Section(var id: Int, var title: String, var image: String, var size: String = "normal") : RProps

@JsExport
val directory = functionalComponent<RProps> {
  val (state, setState) = useState { listOf(
    Section(1, "hats", "/static/images/hats.jpeg"),
    Section(2, "jackets", "/static/images/jackets.jpeg"),
    Section(3, "sneakers", "/static/images/sneakers.jpeg"),
    Section(4, "womens", "/static/images/womens.jpeg", "large"),
    Section(5, "mens", "/static/images/mens.jpeg", "large")
  ) }

  div(classes = "directory-menu") {
    state.map {
      child(menuItem, it)
    }
  }
}