package shopping.model

import kotlinx.serialization.Serializable

@Serializable
data class User(val id: String, val displayName: String?, val email: String?, val createdAt: Double)
