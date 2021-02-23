package shopping.redux

import redux.*
import shopping.model.User

val loggerMiddleware:Middleware<User?, RAction, WrapperAction, RAction, WrapperAction> = { middleware ->
  { next ->
    { action ->
      console.log("prev state: ${middleware.getState()}")
      console.log("action: $action")
      val result = next(action)
      console.log("next state: ${middleware.getState()}")
      result
    }
  }
}

val middlewares = compose(applyMiddleware(loggerMiddleware), rEnhancer())

val store = createStore(::userEventHandler, null, middlewares)


//val store = createStore(::userEventHandler, JSON.parse(JSON.stringify(State())), rEnhancer())
