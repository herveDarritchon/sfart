@file:OptIn(KtorExperimentalLocationsAPI::class)

package sfart.semanticfield.hervedarritchon.fr.infrastructure.api

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.http.content.resources
import io.ktor.server.http.content.static
import io.ktor.server.locations.KtorExperimentalLocationsAPI
import io.ktor.server.locations.get
import io.ktor.server.plugins.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import sfart.semanticfield.hervedarritchon.fr.infrastructure.scrapper.RimesSolides.RimesSolidesScrapperEngine
import sfart.semanticfield.hervedarritchon.fr.infrastructure.scrapper.robert.RobertScrapperEngine
import sfart.semanticfield.hervedarritchon.fr.plugins.AuthenticationException
import sfart.semanticfield.hervedarritchon.fr.plugins.AuthorizationException

fun Application.applicationRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get<MyLocation> {
            call.respondText("Location: name=${it.name}, arg1=${it.arg1}, arg2=${it.arg2}")
        }
        // Register nested routes
        get<Type.Edit> {
            call.respondText("Inside $it")
        }
        get<Type.List> {
            call.respondText("Inside $it")
        }
        get<RimesSolidesScrapeLocation> { scrape ->
            val semanticField = RimesSolidesScrapperEngine().extract(scrape.word, scrape.depth)
            //SemanticField(key = semanticField.key, keywords = semanticField.keywords, depth = semanticField.depth)
            call.respond(semanticField)
        }
        get<RobertScrapeLocation> { scrape ->
            val semanticField = RobertScrapperEngine().extract(scrape.word, scrape.depth)
            //SemanticField(key = semanticField.key, keywords = semanticField.keywords, depth = semanticField.depth)
            call.respond(semanticField)
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
        install(StatusPages) {
            exception<AuthenticationException> { call, _ ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { call, _ ->
                call.respond(HttpStatusCode.Forbidden)
            }

        }
    }
}
