package shopping.redux

import redux.*
import shopping.devMode
import shopping.json
import shopping.model.DirectoryState
import shopping.model.State
import shopping.redux.persist.*
import shopping.redux.persist.storage.Storage

val combinedReducers = combineReducers<State, RAction>(
  mapOf(
    State::user to ::userEventHandler,
    State::cart to ::cartCommandHandler,
    State::directory to ::directoryHandler
  ).mapKeys { it.key.name }
)

val middlewares = if(devMode) compose(applyMiddleware(logger), persistEnhancer()) else persistEnhancer()

val persistConfig = PersistConfig(
  key = "root",
  storage = Storage(State::class, json),
  blacklist = arrayOf(State::user.name), // this only works with nullable fields
  // for non-nullable fields have to set it to default rather than null
  transforms = arrayOf(replace(State::directory.name, DirectoryState()))
)

val persistReducer = persistReducer(persistConfig, combinedReducers)

val store = createStore(persistReducer, State(), middlewares)

val persistor = persistStore(store)
