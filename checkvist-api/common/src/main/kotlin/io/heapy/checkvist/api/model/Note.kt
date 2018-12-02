package io.heapy.checkvist.api.model

/**
 * TODO.
 *
 * @author Ruslan Ibragimov
 * @since TODO
 */
data class Note(
    val id: Long,
    val comment: String,
    val taskId: Long,
    val userId: Long,
    val username: String,
    val updatedAt: String,
    val createdAt: String
)
