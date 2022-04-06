package sfart.semanticfield.hervedarritchon.fr.infrastructure.scrapper.robert

import kotlinx.serialization.Serializable

@Serializable
data class RobertResponse(
    var httpStatusCode: Int = 0,
    var httpStatusMessage: String = "Not Found",
    var key: String = "inconnu",
    var gender: String = "indéfini",
    var nature: String = "inconnue",
    var depth: Int = 1,
)
