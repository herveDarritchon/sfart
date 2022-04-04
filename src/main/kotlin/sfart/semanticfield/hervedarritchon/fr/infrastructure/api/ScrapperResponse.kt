package sfart.semanticfield.hervedarritchon.fr.infrastructure.api

import kotlinx.serialization.Serializable

@Serializable
data class ScrapperResponse(
    val httpStatusCode: Int,
    val httpStatusMessage: String,
    val key: String,
    val keywords: List<String>,
    val depth: Int,
)
