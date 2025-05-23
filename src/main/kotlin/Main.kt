package org.example

import org.example.app.CalculadoraManager
import org.example.datos.RepoLog
import org.example.datos.dao.RegistroDAO
import org.example.service.CalculadoraService
import org.example.service.LogService
import org.example.service.RegistroService
import org.example.ui.Consola
import org.example.utils.GestorFicheros


fun main(args: Array<String>) {
    val consola = Consola()
    val gestorFicheros = GestorFicheros()
    val repoLog = RepoLog(gestorFicheros)
    val servicioLog = LogService(repoLog)
    val calculadora = CalculadoraService()
    val registroDao = RegistroDAO()
    val regitro = RegistroService(registroDao)
    val controlador = CalculadoraManager(consola, calculadora, servicioLog,regitro)

    controlador.iniciar(args)
}