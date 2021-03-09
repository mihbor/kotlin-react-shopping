package shopping.model

import kotlinx.serialization.Serializable

@Serializable
data class State(val user: User? = null, val cart: CartState = CartState(), val directory: DirectoryState = DirectoryState())