import react.RProps
import react.dom.div
import react.dom.h1
import react.dom.span
import react.functionalComponent

@JsExport
val homepage = functionalComponent<RProps> {
    div(classes = "homepage") {
        div(classes = "directory-menu") {
            div(classes = "menu-item") {
                div(classes = "content") {
                    h1(classes = "title") { +"HATS" }
                    span(classes = "subtitle") { +"SHOP NOW" }
                }
            }
            div(classes = "menu-item") {
                div(classes = "content") {
                    h1(classes = "title") { +"JACKETS" }
                    span(classes = "subtitle") { +"SHOP NOW" }
                }
            }
            div(classes = "menu-item") {
                div(classes = "content") {
                    h1(classes = "title") { +"SNEAKERS" }
                    span(classes = "subtitle") { +"SHOP NOW" }
                }
            }
            div(classes = "menu-item") {
                div(classes = "content") {
                    h1(classes = "title") { +"WOMENS" }
                    span(classes = "subtitle") { +"SHOP NOW" }
                }
            }
            div(classes = "menu-item") {
                div(classes = "content") {
                    h1(classes = "title") { +"MENS" }
                    span(classes = "subtitle") { +"SHOP NOW" }
                }
            }
        }
    }

}