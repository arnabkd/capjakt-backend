package no.capjakt.plugins

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.CallLogging
import io.ktor.request.path
import org.slf4j.event.Level

fun Application.configureMonitoring() {
  install(CallLogging) {
    level = Level.INFO
    filter { call -> call.request.path().startsWith("/") }
  }

  val jdbcUrl = System.getenv("JDBC_DATABASE_URL")
  log.debug("jdbc url is $jdbcUrl")

  val dbUrl = System.getenv("DATABASE_URL")
  log.debug("db url is $dbUrl")
}
