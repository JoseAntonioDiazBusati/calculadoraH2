package org.example.datos

import org.example.model.Calculadora
import org.example.utils.IUtilFicheros
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RepoLog(private val fichero: IUtilFicheros): IRepoLog {
    override var ruta: String? = null
    override var logActual: String? = null

    override fun crearRutaLog(): Boolean = ruta?.let { fichero.crearRuta(it) } ?: false

    override fun crearNuevoLog(): String {
        val fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        val nombre = "log$fecha.txt"
        val rutaCompleta = "$ruta/$nombre"
        fichero.crearFichero(rutaCompleta)
        logActual = rutaCompleta
        return nombre
    }

    override fun getUltimoLog(): List<String> {
        val logs = ruta?.let { ficheroLog ->
            fichero.listarFicheros(ficheroLog)
                .filter { it.name.startsWith("log") && it.name.endsWith(".txt") }
                .sortedByDescending { it.name }
                .firstOrNull()
        }
        return logs?.let { fichero.leerFichero(it.path) } ?: emptyList()
    }

    override fun registrarEntrada(msj: String) {
        val linea = "${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))} $msj\n"
        logActual?.let { fichero.escribirLinea(it, linea) }
    }

    override fun registrarEntrada(calculadora: Calculadora) {
        registrarEntrada(calculadora.toString())
    }
}