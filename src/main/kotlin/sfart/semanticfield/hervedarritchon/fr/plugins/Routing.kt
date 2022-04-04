@file:OptIn(KtorExperimentalLocationsAPI::class)

package sfart.semanticfield.hervedarritchon.fr.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.locations.KtorExperimentalLocationsAPI
import io.ktor.server.locations.Locations
import io.ktor.server.plugins.AutoHeadResponse
import sfart.semanticfield.hervedarritchon.fr.infrastructure.api.applicationRouting

fun Application.configureRouting() {
    install(AutoHeadResponse)
    install(Locations) {
    }

   applicationRouting()
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
