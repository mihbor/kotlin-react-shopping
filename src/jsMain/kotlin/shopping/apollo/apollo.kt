package apollo

import apollo.react.QueryHookOptions
import kotlinext.js.jsObject

fun <TVariables> QueryOptions(query: Any) = jsObject<QueryOptions<TVariables>> {
  this.query = query
}

fun <TData> QueryHookOptions(variablesProvider: dynamic.() -> Unit) = jsObject<QueryHookOptions<TData, dynamic>> {
  val variables = js("{}")
  variablesProvider.invoke(variables)
  this.variables = variables
}