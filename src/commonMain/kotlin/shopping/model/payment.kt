package shopping.model

import kotlinx.serialization.Serializable

@Serializable
data class Payment(val token: String, val currency: String, val amount: Int)