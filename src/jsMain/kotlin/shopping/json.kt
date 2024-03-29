package shopping

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.RProps

val json = Json {
  isLenient = true
  ignoreUnknownKeys = true
  allowSpecialFloatingPointValues = true
  useArrayPolymorphism = true
  allowStructuredMapKeys = true
  encodeDefaults = false
}

fun propsToMap(props: RProps) = json.decodeFromString<Map<String, String>>(JSON.stringify(props))

val Int.formatPrice
  get() = "${this/100}.${(this%100).toString().padStart(2, '0')}"