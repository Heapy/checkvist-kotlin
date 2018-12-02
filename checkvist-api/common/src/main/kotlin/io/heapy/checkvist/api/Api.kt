package io.heapy.checkvist.api

import io.heapy.checkvist.api.model.Checklist
import io.heapy.checkvist.api.model.Task
import io.heapy.checkvist.api.model.User
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.HttpHeaders
import java.util.Base64

/**
 * Currently all operation regardless public/private checklist available in this api.
 * In future this will split in different api files for auth|working with checklists
 * Also currently only readonly ops supported
 */
interface Checkvist {
    suspend fun login(): String
    suspend fun refresh(oldToken: String): String

    suspend fun user(credentials: Credentials): User

    suspend fun checklists(
        archived: Boolean,
        skipStats: Boolean,
        order: String, // TODO
        credentials: Credentials
    ): List<Checklist>

    suspend fun checklist(
        checklistId: Long,
        credentials: Credentials
    ): Checklist

    suspend fun tasks(
        checklistId: Long,
        withNotes: Boolean,
        order: String, // TODO
        credentials: Credentials
    ): List<Task>
}

// 2005/02/01 15:15:10 +0000

/**
 *
 */
class DefaultCheckvist(
    private val configuration: Configuration,
    private val client: HttpClient
) : Checkvist {
    override suspend fun login(): String {
        val token = client.post<String>("${configuration.baseUrl}/auth/login.json") {
            when (val credentials = configuration.credentials) {
                is BasicAuthenticationCredentials -> {
                    parameter("username", credentials.username)
                    parameter("remote_key", credentials.secret)
                }
                is TokenAuthenticationCredentials,
                is NoAuthenticationCredentials -> {
                    throw CheckvistApiClientException("Calling login doesn't make sense with token authentication")
                }
            }
        }

        // Token is quoted string, so there we delete quotes
        return token.removeQuotes()
    }

    override suspend fun refresh(oldToken: String): String {
        //
        val token = client.post<String>("${configuration.baseUrl}/auth/refresh_token.json") {
            parameter("old_token", oldToken)
        }

        // Token is quoted string, so there we delete quotes
        return token.removeQuotes()
    }

    /**
     * Removes first, and last char
     */
    internal fun String.removeQuotes(): String {
        return substring(1, length - 1)
    }

    override suspend fun user(credentials: Credentials): User {
        return client.get("${configuration.baseUrl}/auth/curr_user.json") {
            authenticate(credentials)
        }
    }

    override suspend fun checklists(
        archived: Boolean,
        skipStats: Boolean,
        order: String,
        credentials: Credentials
    ): List<Checklist> {
        return client.get("${configuration.baseUrl}/checklists.json") {
            authenticate(credentials)
            if (archived) parameter("archived", archived)
            if (skipStats) parameter("skip_stats", skipStats)
            parameter("order", order)
        }
    }

    override suspend fun checklist(checklistId: Long, credentials: Credentials): Checklist {
        return client.get("${configuration.baseUrl}/checklists/$checklistId.json") {
            authenticate(credentials)
        }
    }

    override suspend fun tasks(
        checklistId: Long,
        withNotes: Boolean,
        order: String,
        credentials: Credentials
    ): List<Task> {
        return client.get("${configuration.baseUrl}/checklists/$checklistId/tasks.json") {
            authenticate(credentials)
        }
    }

    internal fun HttpRequestBuilder.authenticate(credentials: Credentials) {
        when (credentials) {
            is BasicAuthenticationCredentials -> {
                val pair = "${credentials.username}:${credentials.secret}"
                val pairBase64 = Base64.getEncoder()
                    .encodeToString(pair.toByteArray(Charsets.ISO_8859_1))

                header(HttpHeaders.Authorization, "Basic $pairBase64")
            }
            is TokenAuthenticationCredentials -> {
                parameter("token", credentials.token)
            }
        }
    }
}
