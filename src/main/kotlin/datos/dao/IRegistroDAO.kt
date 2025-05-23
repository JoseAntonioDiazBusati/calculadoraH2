package org.example.datos.dao

import org.example.model.Registro
import java.time.LocalDateTime


interface IRegistroDAO {
    fun crearRegistro()
    fun guardarRegistro(nombreRegistro: String, resultado: Double, fechaRegistro: LocalDateTime)
    fun mostrarRegistro(): List<Registro>
}