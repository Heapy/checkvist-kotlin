package io.heapy.checkvist.api

/**
 * Basic configuration
 *
 * @author Ruslan Ibragimov
 * @since 1.0.0
 */
interface Configuration {
    /**
     * Base URL of checkvist api
     */
    val baseUrl: String

    /**
     * Credentials to access api
     */
    val credentials: Credentials
}


data class DefaultConfiguration(
    override val baseUrl: String,
    override val credentials: Credentials
) : Configuration
