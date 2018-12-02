package io.heapy.checkvist.api.model

/**
 * TODO.
 *
 * @author Ruslan Ibragimov
 * @since TODO
 */
data class User(
    val email: String,
    val emailMd5: String,
    val id: Long,
    val pro: Boolean,
    val username: String
)
