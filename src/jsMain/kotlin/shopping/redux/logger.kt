package shopping.redux

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import redux.Middleware
import redux.RAction
import redux.WrapperAction
import shopping.model.State

val json = Json {
    isLenient = true
    ignoreUnknownKeys = true
    allowSpecialFloatingPointValues = true
    useArrayPolymorphism = true
    allowStructuredMapKeys = true
}
inline fun <reified S> stringify(state: S) = json.encodeToString(state)

val logger: Middleware<State, RAction, WrapperAction, RAction, WrapperAction> = { middleware ->
    { next ->
        { action ->
            console.log("prev state: ${stringify(middleware.getState())}")
            console.log("action: $action")
            val result = next(action)
            console.log("next state: ${stringify(middleware.getState())}")
            result
        }
    }
}