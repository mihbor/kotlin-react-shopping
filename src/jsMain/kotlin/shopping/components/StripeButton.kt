package shopping.components

import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import react.RProps
import react.functionalComponent
import shopping.API
import shopping.formatPrice
import shopping.model.Payment
import shopping.scope
import shopping.stripe.ReactStripeCheckout

external interface StripeButtonProps : RProps {
  var price: Int
}

@Serializable
data class Token(val id: String)

val stripeButton = functionalComponent<StripeButtonProps> { props ->
  fun onToken(token: Token) {
    console.log(JSON.stringify(token))
    scope.launch {
      API.payment(Payment(token.id, "GBP", props.price))
    }
  }
  child(ReactStripeCheckout::class) {
    attrs {
      amount = props.price
      billingAddress = true
      currency = "GBP"
      description = "Your total is £${props.price.formatPrice}"
      image = "https://svgshare.com/i/CUz.svg"
      label = "Pay Now"
      name = "Crown Clothing"
      panelLabel = "Pay Now"
      shippingAddress = true
      stripeKey = "pk_test_3jdBMhmIHImroAjXe3q1N0Fq"
      token = ::onToken
    }
  }
}