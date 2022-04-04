@file:OptIn(KtorExperimentalLocationsAPI::class)

package sfart.semanticfield.hervedarritchon.fr.infrastructure.api

import io.ktor.server.locations.KtorExperimentalLocationsAPI
import io.ktor.server.locations.Location

@Location("/location/{name}")
class MyLocation(val name: String, val arg1: Int = 42, val arg2: String = "default")

@Location("/type/{name}")
data class Type(val name: String) {
    @Location("/edit")
    data class Edit(val type: Type)

    @Location("/list/{page}")
    data class List(val type: Type, val page: Int)
}

@Location("/scrape-it/{word}")
data class ScrapeLocation(val word: String, val depth: Int = 1)