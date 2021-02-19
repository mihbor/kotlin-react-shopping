package shopping.redux

import redux.createStore
import redux.rEnhancer

val store = createStore(combinedReducers(), State(null), rEnhancer())
