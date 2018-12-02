package io.heapy.checkvist.api

/**
 * Represent authentication methods.
 *
 * @author Ruslan Ibragimov
 * @since 1.0.0
 */
sealed class Credentials

data class BasicAuthenticationCredentials(
    val username: String,
    val secret: String
) : Credentials()

data class TokenAuthenticationCredentials(
    val token: String
) : Credentials()

object NoAuthenticationCredentials : Credentials()
