@file:JsModule("react-stripe-checkout")
@file:JsNonModule
package shopping.stripe

import react.*

external interface Props : RProps {
  var amount: Int
  var billingAddress: Boolean
  var currency: String
  var description: String
  var image: String
  var label: String
  var name: String
  var panelLabel: String
  var shippingAddress: Boolean
  var stripeKey: String
  var token: (token: Any) -> Unit
}

@JsName("default")
external class ReactStripeCheckout : Component<Props, RState> {
  override fun render(): dynamic
}
