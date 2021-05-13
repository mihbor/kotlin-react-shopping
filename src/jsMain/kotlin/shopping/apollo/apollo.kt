package apollo

import apollo.react.QueryHookOptions

fun <TVariables> QueryOptions(query: Any) = (js("{}") as QueryOptions<TVariables>).apply {
  this.query = query
}

fun <TData> QueryHookOptions(variablesProvider: dynamic.() -> Unit) = (js("{}") as QueryHookOptions<TData, dynamic>).apply {
  val variables = js("{}")
  variablesProvider.invoke(variables)
  this.variables = variables
}