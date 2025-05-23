package org.example.ui

interface IConsola {
    fun mostrar(msj: String = "")
    fun mostrarError(msj: String)
    fun pedirInfo(msj: String = ""): String
    fun pedirDouble(msj: String = ""): Double?
    fun pedirEntero(msj: String = ""): Int?
    fun preguntar(msj: String = "¿Deseas intentarlo de nuevo? (s/n): "): Boolean
    fun limpiarPantalla(numSaltos: Int = 20)
    fun pausa(msj: String = "Pulsa Enter para continuar...")
}