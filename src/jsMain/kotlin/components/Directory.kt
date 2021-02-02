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
    Section(1, "hats", "https://i.ibb.co/cvpntL1/hats.png"),
    Section(2, "jackets", "https://i.ibb.co/px2tCc3/jackets.png"),
    Section(3, "sneakers", "https://i.ibb.co/0jqHpnp/sneakers.png"),
    Section(4, "womens", "https://i.ibb.co/GCCdy8t/womens.png", "large"),
    Section(5, "mens", "https://i.ibb.co/R70vBrQ/men.png", "large")
  ) }

  div(classes = "directory-menu") {
    state.map {
      child(menuItem, it)
    }
  }
}