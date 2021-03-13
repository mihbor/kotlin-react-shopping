@file:JsModule("redux-persist/integration/react")
@file:JsNonModule

package shopping.redux.persist.react

import react.PureComponent
import react.RProps
import react.RState


external interface Props: RProps {
  var onBeforeLift: dynamic
  var children: dynamic
  var loading: dynamic
  var persistor: dynamic
}

external interface State: RState {
  var bootstrapped: Boolean
}

external class PersistGate : PureComponent<Props, State> {
  override fun render(): dynamic
  override fun componentDidMount()
  override fun componentWillUnmount()
}
