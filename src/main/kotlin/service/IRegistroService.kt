package org.example.service

import org.example.model.Registro
import java.time.LocalDateTime

interface IRegistroService {
    fun crearRegistro()
    fun guardarRegistro(nombreRegistro: String, resultado: Double, fechaRegistro: LocalDateTime)
    fun mostrarRegistro(): List<Registro>
}