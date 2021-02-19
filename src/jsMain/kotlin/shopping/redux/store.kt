package shopping.redux

import redux.createStore
import redux.rEnhancer

val store = createStore(::userEventHandler, null, rEnhancer())
