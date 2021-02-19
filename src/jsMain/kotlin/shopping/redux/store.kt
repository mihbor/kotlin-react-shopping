package shopping.redux

import redux.createStore
import redux.rEnhancer

val store = createStore(::userEventHandler, null, rEnhancer())
//val store = createStore(::userEventHandler, JSON.parse(JSON.stringify(State())), rEnhancer())
