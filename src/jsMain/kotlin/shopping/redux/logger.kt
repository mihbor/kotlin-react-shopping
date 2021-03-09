package shopping.redux

import kotlinx.serialization.encodeToString
import redux.Middleware
import redux.RAction
import redux.WrapperAction
import shopping.json
import shopping.model.State

inline fun <reified S> stringify(state: S) = json.encodeToString(state)

val logger: Middleware<State, RAction, WrapperAction, RAction, WrapperAction> = { middleware ->
    { next ->
        { action ->
            console.log("prev user: ${stringify(middleware.getState().user)}")
            console.log("prev cart: ${stringify(middleware.getState().cart)}")
            console.log("action: $action")
            val result = next(action)
            console.log("next user: ${stringify(middleware.getState().user)}")
            console.log("next cart: ${stringify(middleware.getState().cart)}")
            result
        }
    }
}