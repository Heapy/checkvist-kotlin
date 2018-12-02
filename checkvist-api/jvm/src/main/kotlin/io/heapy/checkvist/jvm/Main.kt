package io.heapy.checkvist.jvm

import io.heapy.checkvist.api.DefaultCheckvist
import io.heapy.checkvist.api.DefaultConfiguration
import io.heapy.checkvist.api.TokenAuthenticationCredentials
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.features.json.JsonFeature
import kotlinx.coroutines.runBlocking

/**
 * Main to play with API.
 *
 * @author Ruslan Ibragimov
 * @since 1.0.0
 */
fun main(args: Array<String>) = runBlocking {
    val client = HttpClient(Apache) {
        install(JsonFeature) {
            serializer = JacksonSerializer {
                configureCheckvist()
            }
        }
    }

    val token = TokenAuthenticationCredentials("token")

    val configuration = DefaultConfiguration(
        baseUrl = "https://checkvist.com",
        credentials = token
    )

    val checkvist = DefaultCheckvist(configuration, client)

    println(checkvist.user(token))
    println(checkvist.checklists(
        archived = false,
        skipStats = false,
        order = "id:asc",
        credentials = token
    ))
    println(checkvist.checklist(42, token))
    println(checkvist.tasks(42, true, "", token))

    Unit
}
