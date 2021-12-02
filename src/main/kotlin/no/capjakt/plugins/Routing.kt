package no.capjakt.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.features.NotFoundException
import io.ktor.response.*
import io.ktor.request.*
import no.capjakt.giftcards.AnswerBody
import no.capjakt.giftcards.Gifter
import no.capjakt.service.GifterService
import java.util.UUID

fun Application.configureRouting() {
  // TODO: lagre dette i en database og lese ut

  routing {
    get("/") {
      call.respond(GifterService.allGifters())
    }

    post("/answer") {
      val input = call.receive<AnswerBody>()
      val gifter = GifterService.findGifter(input.id)
        ?: return@post call.respond(
          HttpStatusCode.NotFound,
          "No gifter with id: ${input.id} found"
        )

      if (gifter.ticketsLeft == 0) {
        call.response.status(HttpStatusCode.BadRequest)
        call.respond("Ikke flere billetter igjen på ${gifter.name}")
      } else if (gifter.answer != input.answer) {
        call.response.status(HttpStatusCode.BadRequest)
        call.respond("Feil svar")
      } else {
        GifterService.takeOneticket(gifter.id)
        call.respond("Du fikk 1 billett!")
      }
    }
  }
}
