package no.capjakt.giftcards

import kotlinx.serialization.Serializable

@Serializable
data class AnswerBody(
  val id: Int,
  val answer: String,
)
