package org.example.utils

import java.io.File

class GestorFicheros: IUtilFicheros {
    override fun crearRuta(ruta: String): Boolean {
        val dir = File(ruta)
        return if (!dir.exists()) dir.mkdirs() else false
    }

    override fun listarFicheros(ruta: String): List<File> = File(ruta).listFiles()?.toList() ?: emptyList()

    override fun leerFichero(path: String): List<String> = File(path).readLines()

    override fun escribirLinea(path: String, linea: String) {
        File(path).appendText(linea)
    }

    override fun crearFichero(path: String) {
        File(path).createNewFile()
    }
}