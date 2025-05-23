package org.example.datos

import org.example.model.Calculadora

interface IRepoLog {
    var ruta: String?
    var logActual: String?

    fun crearRutaLog(): Boolean
    fun crearNuevoLog(): String
    fun getContenidoUltimoLog(): List<String>
    fun registrarEntrada(msj: String)
    fun registrarEntrada(calculadora: Calculadora)
}