package shopping.components

import kotlinx.browser.window
import react.RProps
import react.functionalComponent
import shopping.formatPrice
import shopping.stripe.ReactStripeCheckout

external interface StripeButtonProps : RProps {
  var price: Int
}

fun onToken(token: Any) {
  console.log(JSON.stringify(token))
  window.alert(JSON.stringify(token))
}

val stripeButton = functionalComponent<StripeButtonProps> { props ->
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