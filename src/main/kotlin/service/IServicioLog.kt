package org.example.service

import org.example.model.Calculadora

interface IServicioLog {
    fun registrarEntradaLog(msj: String)
    fun registrarEntradaLog(calculadora: Calculadora)
    fun getInfoUltimoLog(): List<String>
    fun crearNuevoLog()
    fun crearRutaLog(ruta: String): Boolean
}