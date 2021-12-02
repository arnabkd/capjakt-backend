package no.capjakt.plugins

import io.ktor.serialization.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import java.beans.Encoder
import java.util.Base64
import java.util.UUID

fun Application.configureSerialization() {
  install(ContentNegotiation) {
    json()
  }
}
