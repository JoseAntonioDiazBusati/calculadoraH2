package org.example.model

import java.time.LocalDateTime

data class Registro(
    val nombreRegistro: String,
    val resultado: Double,
    val fechaRegistro: LocalDateTime
)
