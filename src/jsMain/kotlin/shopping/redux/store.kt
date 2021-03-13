package shopping.redux

import redux.*
import shopping.devMode
import shopping.json
import shopping.model.State
import shopping.redux.persist.PersistConfig
import shopping.redux.persist.persistEnhancer
import shopping.redux.persist.persistReducer
import shopping.redux.persist.persistStore
import shopping.redux.persist.storage.Storage

val combinedReducers = combineReducers<State, RAction>(
  mapOf(
    State::user to ::userEventHandler,
    State::cart to ::cartCommandHandler,
    State::directory to ::directoryHandler
  ).mapKeys { it.key.name }
)

val middlewares = if(devMode) compose(applyMiddleware(logger), persistEnhancer()) else persistEnhancer()

val persistConfig = PersistConfig(key="root", storage=Storage(State::class, json), blacklist=arrayOf(State::user.name))

val persistReducer = persistReducer(persistConfig, combinedReducers)

val store = createStore(persistReducer, State(), middlewares)

val persistor = persistStore(store)
