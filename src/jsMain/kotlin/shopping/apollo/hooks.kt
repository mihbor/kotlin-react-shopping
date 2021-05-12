package apollo.react

import graphql.DocumentNode

fun <TData, TVariables> useLazyQuery(query: DocumentNode, options: LazyQueryHookOptions<TData, TVariables>) : Pair<(QueryLazyOptions<TVariables>?) -> Unit, QueryResult<TData, TVariables>> {
  val result = useLazyQueryJs(query, options)
  return Pair(result[0], result[1])
}
fun <TData, TVariables> useLazyQuery(query: DocumentNode) : Pair<(QueryLazyOptions<TVariables>?) -> Unit, QueryResult<TData, TVariables>> {
  val result = useLazyQueryJs<TData, TVariables>(query)
  return Pair(result[0], result[1])
}
