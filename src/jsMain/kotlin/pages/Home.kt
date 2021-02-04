package pages

import components.directory
import react.RProps
import react.child
import react.functionalComponent

@JsExport
val homePage = functionalComponent<RProps> {
    child(directory) { }
}