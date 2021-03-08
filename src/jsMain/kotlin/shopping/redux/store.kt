package shopping.redux

import redux.*
import shopping.model.State
import shopping.redux.persist.PersistConfig
import shopping.redux.persist.persistEnhancer
import shopping.redux.persist.persistReducer
import shopping.redux.persist.persistStore
import shopping.redux.persist.storage.Storage

val combinedReducers = combineReducers<State, RAction>(
  mapOf(
    State::user to ::userEventHandler,
    State::cart to ::cartCommandHandler
  ).mapKeys { it.key.name }
)

val middlewares = compose(applyMiddleware(logger), persistEnhancer())

val persistConfig = PersistConfig(key="root", storage=Storage(State::class), whitelist=arrayOf(State::cart.name))

val persistReducer = persistReducer(persistConfig, combinedReducers)

val store = createStore(persistReducer, State(), middlewares)

val persistor = persistStore(store)
