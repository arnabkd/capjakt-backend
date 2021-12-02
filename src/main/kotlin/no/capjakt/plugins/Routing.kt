package no.capjakt.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import no.capjakt.giftcards.Gifter
import java.util.UUID

fun Application.configureRouting() {
  val gifterList = listOf(
    Gifter(1, "Arnab", "Hvor vokste du opp", "Porsgrunn"),
  )


  routing {
    get("/") {
      call.respond(gifterList)
    }
  }
}
