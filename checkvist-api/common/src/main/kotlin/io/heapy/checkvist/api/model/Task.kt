package io.heapy.checkvist.api.model

/**
 * TODO.
 *
 * @author Ruslan Ibragimov
 * @since TODO
 */
data class Task(
    val id: Long,
    val content: String,
    val status: Int,
    val checklistId: Long,
    val parentId: Long,
    val comments_count: Int,
    val position: Int,
    val deleted: Boolean?,
    val tasks: List<Long>,
    val tags: Map<String, Boolean>,
    val tagsAsText: String,
    val updateLine: String,
    val updatedAt: String,
    val due: String?,
    val notes: List<Note>?
)

