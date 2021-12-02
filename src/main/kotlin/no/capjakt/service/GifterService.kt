package no.capjakt.service

import no.capjakt.giftcards.Gifter

object GifterService {
  private val gifterList = mutableListOf(
    Gifter(1, "Arnab", "Hvor vokste du opp", "Porsgrunn", 5),
  )

  fun allGifters() = gifterList

  fun findGifter(id: Int) = gifterList.find { it.id == id }

  fun takeOneticket(id: Int) =  findGifter(id)!!.run {
    val updated = this.copy(ticketsLeft =  this.ticketsLeft - 1)

    gifterList.remove(this)
    gifterList.add(updated)
  }
}
