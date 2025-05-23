package org.example.service

import org.example.datos.dao.RegistroDAO
import org.example.model.Registro
import java.time.LocalDateTime

class RegistroService(private val registroDAO: RegistroDAO): IRegistroService {
    override fun crearRegistro() {
        return registroDAO.crearRegistro()
    }

    override fun guardarRegistro(
        nombreRegistro: String,
        resultado: Double,
        fechaRegistro: LocalDateTime
    ) {
        require(nombreRegistro.isNotBlank()){"El nombre no puede estar vacio"}
        return registroDAO.guardarRegistro(nombreRegistro,resultado,fechaRegistro)
    }

    override fun mostrarRegistro(): List<Registro> {
        return registroDAO.mostrarRegistro()
    }
}