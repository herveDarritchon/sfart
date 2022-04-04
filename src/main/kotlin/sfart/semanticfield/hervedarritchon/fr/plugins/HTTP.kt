package sfart.semanticfield.hervedarritchon.fr.plugins

import io.ktor.http.CacheControl
import io.ktor.http.ContentType
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.http.content.CachingOptions
import io.ktor.server.plugins.CachingHeaders
import io.ktor.server.plugins.Compression
import io.ktor.server.plugins.ConditionalHeaders
import io.ktor.server.plugins.DefaultHeaders
import io.ktor.server.plugins.deflate
import io.ktor.server.plugins.gzip
import io.ktor.server.plugins.minimumSize
import java.time.ZonedDateTime

fun Application.configureHTTP() {

    install(CachingHeaders) {
        options { outgoingContent ->
            when (outgoingContent.contentType?.withoutParameters()) {
                ContentType.Text.CSS -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 24 * 60 * 60), ZonedDateTime.now().plusDays(2))
                else -> null
            }
        }
    }

    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }
    install(ConditionalHeaders)
    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }
}
