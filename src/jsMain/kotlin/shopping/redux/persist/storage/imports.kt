@file:JsModule("redux-persist/lib/storage")
@file:JsNonModule
package shopping.redux.persist.storage

import kotlin.js.Promise

@JsName("default")
external object storage {
  fun getItem(key: String, callback: ((String) -> Any)? = definedExternally): Promise<String>
  fun setItem(key: String, item: String, callback: (() -> Any)? = definedExternally): Promise<Unit>
  fun removeItem(key: String, callback: (() -> Any)? = definedExternally): Promise<Unit>
}
