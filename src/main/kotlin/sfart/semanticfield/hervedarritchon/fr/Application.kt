package sfart.semanticfield.hervedarritchon.fr

import io.ktor.server.application.Application
import sfart.semanticfield.hervedarritchon.fr.plugins.configureHTTP
import sfart.semanticfield.hervedarritchon.fr.plugins.configureMonitoring
import sfart.semanticfield.hervedarritchon.fr.plugins.configureRouting
import sfart.semanticfield.hervedarritchon.fr.plugins.configureSerialization

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
}
