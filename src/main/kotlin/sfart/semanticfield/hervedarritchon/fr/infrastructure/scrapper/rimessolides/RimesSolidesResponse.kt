package sfart.semanticfield.hervedarritchon.fr.infrastructure.scrapper.RimesSolides

import kotlinx.serialization.Serializable

@Serializable
data class RimesSolidesResponse(
    val httpStatusCode: Int,
    val httpStatusMessage: String,
    val key: String,
    val keywords: List<String>,
    val depth: Int,
)
