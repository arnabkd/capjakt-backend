package no.capjakt

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import no.capjakt.plugins.configureHTTP
import no.capjakt.plugins.configureMonitoring
import no.capjakt.plugins.configureRouting
import no.capjakt.plugins.configureSerialization

fun main() {
  val port = (System.getenv("PORT") ?: "8080").toInt()
  embeddedServer(Netty, port = port, host = "0.0.0.0") {
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureHTTP()
  }.start(wait = true)
}
