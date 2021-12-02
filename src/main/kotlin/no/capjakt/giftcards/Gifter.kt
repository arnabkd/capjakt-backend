package no.capjakt.giftcards

import kotlinx.serialization.Serializable

@Serializable
data class Gifter(
  val id: Int,
  val name: String,
  val question: String,
  val answer: String,
  val ticketsLeft: Int,
)

@Serializable
data class GifterWithoutAnswer(
  val id: Int,
  val name: String,
  val question: String,
  val ticketsLeft: Int
)
