package shopping.redux.persist

import kotlinext.js.Object
import kotlinext.js.assign
import kotlinext.js.js
import kotlinext.js.jsObject
import redux.*

private fun PersistConfig(): PersistConfig = jsObject()
private fun TransformConfig(): TransformConfig = jsObject()

fun PersistConfig(
  key: String,
  storage: Any,
  /** whitelist and blacklist can only be used to exclude nullable fields, for non-nullable fields use the [replace] transform */
  whitelist: Array<String>? = null,
  blacklist: Array<String>? = null,
  serialize: Any? = false,
  deserialize: Any? = false,
  transforms: Array<Transform>? = null
) = PersistConfig().apply {
  this.key = key
  this.storage = storage
  this.whitelist = whitelist
  this.blacklist = blacklist
  this.serialize = serialize
  this.deserialize = deserialize
  this.transforms = transforms
}

fun TransformConfig(
  whitelist: Array<String>? = null,
  blacklist: Array<String>? = null
) = TransformConfig().apply {
  this.whitelist = whitelist
  this.blacklist = blacklist
}

fun replace(key: String, value: Any) = createTransform(
  inbound = { _, _, _ -> value },
  config = TransformConfig(whitelist = arrayOf(key))
)

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