package org.example.service

import org.example.datos.RepoLog
import org.example.model.Calculadora

class LogService(private val repoLog: RepoLog): IServicioLog {
    override fun registrarEntradaLog(msj: String) {
        repoLog.registrarEntrada(msj)
    }

    override fun registrarEntradaLog(calculadora: Calculadora) {
        repoLog.registrarEntrada(calculadora)
    }

    override fun getInfoUltimoLog(): List<String> {
        return repoLog.getUltimoLog()
    }

    override fun crearNuevoLog() {
        repoLog.crearNuevoLog()
    }

    override fun crearRutaLog(ruta: String): Boolean {
        repoLog.ruta = ruta
        require(ruta.isNotEmpty()){"No puedes crear una ruta vacía"}
        return repoLog.crearRutaLog()
    }
}