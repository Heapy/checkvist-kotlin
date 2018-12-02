package io.heapy.checkvist.api.model

/**
 * TODO.
 *
 * @author Ruslan Ibragimov
 * @since TODO
 */
data class Checklist(
    val id: Long,
    val name: String,
    val public: Boolean,
    val updatedAt: String,
    val tags: Map<String, Boolean>,
    val tagsAsText: String,
    val taskCount: Int,
    val taskCompleted: Int,
    val readOnly: Boolean,
    val archived: Boolean
)
