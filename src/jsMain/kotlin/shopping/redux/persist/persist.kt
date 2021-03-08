package shopping.redux.persist

import kotlinext.js.Object
import kotlinext.js.assign
import kotlinext.js.js
import redux.*

fun PersistConfig(): PersistConfig = js("{}")

fun PersistConfig(
  key: String,
  storage: Any,
  whitelist: Array<String>? = null,
  blacklist: Array<String>? = null,
  serialize: Any? = false,
  deserialize: Any? = false
) =
  PersistConfig().apply {
    this.key = key
    this.storage = storage
    this.whitelist = whitelist
    this.blacklist = blacklist
    this.serialize = serialize
    this.deserialize = deserialize
  }

fun <S> persistEnhancer(): Enhancer<S, Action, Action, RAction, WrapperAction> = { next ->
  { reducer, initialState ->
    fun wrapperReducer(reducer: Reducer<S, RAction>): Reducer<S, WrapperAction> {
      return { state, action ->
        if (action.type.startsWith("@@redux")) reducer(state, action as RAction)
        else reducer(state, action.action)
      }
    }

    val store = (next.unsafeCast<StoreCreator<S, WrapperAction, WrapperAction>>())(wrapperReducer(reducer), initialState)
    assign(Object.assign(js {}, store)) {
      dispatch = { action: RAction ->
        val result = store.dispatch(js {
          type = action::class.simpleName
          this.action = action
        }.unsafeCast<WrapperAction>())
        result
      }
      replaceReducer = { nextReducer: Reducer<S, RAction> ->
        store.replaceReducer(wrapperReducer(nextReducer))
      }
    }.unsafeCast<Store<S, RAction, WrapperAction>>()
  }
}