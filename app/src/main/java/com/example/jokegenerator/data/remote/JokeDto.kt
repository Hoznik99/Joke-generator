package com.example.jokegenerator.data.remote

data class JokeDto(
    val error: Boolean,
    val category: String?,
    val type: String?,
    val joke: String?,
    val setup: String?,
    val delivery: String?
) {
    fun asDisplayText(): String {
        return when (type) {
            "single" -> joke ?: ""
            "twopart" -> listOfNotNull(setup, delivery).joinToString("\n\n")
            else -> ""
        }
    }
}
