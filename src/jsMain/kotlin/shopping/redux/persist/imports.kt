@file:JsModule("redux-persist")
@file:JsNonModule
package shopping.redux.persist

import redux.RAction
import redux.Reducer
import redux.Store
import kotlin.js.Promise

external fun <S, A, R> persistStore(
  store: Store<S, A, R>,
  options: PersistorOptions? = definedExternally,
  callback: (() -> Unit)? = definedExternally
): Persistor

external fun <S, A> persistReducer(
  config: PersistConfig,
  reducer: Reducer<S, A>
) : Reducer<S, A>

external interface PersistorOptions {
  var enhancer: Any?
  var manualPersist: Boolean?
}

external class Persistor {
  fun pause()
  fun persist()
  fun purge(): Promise<Any>
  fun flush(): Promise<Any>
  fun dispatch(action: RAction): RAction
  fun getState(): PersistorState
  fun subscribe(callback: () -> Any): () -> Any
}
external interface PersistorState {
  var registry: Array<String>
  var bootstrapped: Boolean
}
external interface PersistConfig {
  var version: Number?
  var storage: Any
  var key: String
  var keyPrefix: String? // @TODO remove in v6
  var blacklist: Array<String>?
  var whitelist: Array<String>?
  var transforms: Any?
  var throttle: Number?
  var migrate: Any?
  var stateReconciler: Any?
  var getStoredState: Any? // used for migrations
  var debug: Boolean?
  var serialize: Any?
  var deserialize: Any?
  var timeout: Number?
  var writeFailHandler: Any?
}