package shopping.pages

import react.RProps
import react.child
import react.functionalComponent
import shopping.components.directory

@JsExport
val homePage = functionalComponent<RProps> {
  child(directory) { }
}