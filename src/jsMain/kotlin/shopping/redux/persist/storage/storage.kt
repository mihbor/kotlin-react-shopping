package shopping.redux.persist.storage

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.js.Promise
import kotlin.reflect.KClass

@OptIn(InternalSerializationApi::class)
class Storage<S : Any>(val c: KClass<S>, val json: Json) {
  @JsName("getItem")
  fun getItem(key: String): Promise<S?> {
    return storage.getItem(key).then{ json.decodeFromString(c.serializer(), it) }
  }
  @JsName("setItem")
  fun setItem(key: String, item: S): Promise<Unit> {
    return storage.setItem(key, json.encodeToString(c.serializer(), item))
  }
  @JsName("removeItem")
  fun removeItem(key: String): Promise<Unit> {
    return storage.removeItem(key)
  }
}
