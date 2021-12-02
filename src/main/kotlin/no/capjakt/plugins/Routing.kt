package no.capjakt.plugins

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import no.capjakt.giftcards.AnswerBody
import no.capjakt.service.GifterService

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
