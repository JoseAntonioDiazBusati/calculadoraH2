package org.example.app

import org.example.model.Operador
import org.example.service.CalculadoraService
import org.example.service.IServicioLog
import org.example.ui.IConsola

class CalculadoraManager(
    private val consola: IConsola,
    private val calculadora: CalculadoraService,
    private val gestorFicheros: IServicioLog
) {

    companion object {
        private const val RUTA_POR_DEFECTO = "./log"
    }

    fun iniciar(args: Array<String>) {
        if (!procesarArgumentos(args)) return

        mostrarInfo(gestorFicheros.getInfoUltimoLog())

        gestorFicheros.crearNuevoLog()

        if (args.size == 4) ejecutarCalculoConArgumentos(args)

        consola.pausa("Pulsa ENTER para iniciar la calculadora...")
        consola.limpiarPantalla()

        bucleCalculosUsuario()
    }

    private fun realizarCalculo(num1: Double, operador: Operador, num2: Double) {
        val calculo = calculadora.realizarCalculo(num1, operador, num2)
        consola.mostrar(calculo.toString())
        gestorFicheros.registrarEntradaLog(calculo)
    }

    private fun bucleCalculosUsuario() {
        do {
            try {
                val numero1 = consola.pedirDouble("Introduce el primer número: ") ?: throw Exception("El primer número no es válido!")
                val simbolo = consola.pedirInfo("Introduce el operador (+, -, x, /): ").firstOrNull()
                val operador = Operador.tenerOperador(simbolo) ?: throw Exception("El operador no es válido!")
                val numero2 = consola.pedirDouble("Introduce el segundo número: ") ?: throw Exception("El segundo número no es válido!")

                realizarCalculo(numero1, operador, numero2)
            } catch (e: Exception) {
                val mensaje = e.message ?: "Se ha producido un error!"
                consola.mostrarError(mensaje)
                gestorFicheros.registrarEntradaLog(mensaje)
            }
        } while (consola.preguntar())

        consola.limpiarPantalla()
    }

    private fun mostrarInfo(lineas: List<String>) {
        if (lineas.isEmpty()) {
            consola.mostrar("No existe información de un log previo!")
        } else {
            consola.mostrar("Contenido del log más reciente:")
            lineas.forEach { consola.mostrar(it) }
        }
    }

    private fun ejecutarCalculoConArgumentos(args: Array<String>) {
        val numero1 = args[1].replace(',', '.').toDoubleOrNull()
        val operador = Operador.tenerOperador(args[2].firstOrNull())
        val numero2 = args[3].replace(',', '.').toDoubleOrNull()

        if (numero1 == null || operador == null || numero2 == null) {
            val msg = "Error en los argumentos: operación no válida."
            consola.mostrarError(msg)
            gestorFicheros.registrarEntradaLog(msg)
        } else {
            realizarCalculo(numero1, operador, numero2)
        }
    }

    private fun procesarArgumentos(args: Array<String>): Boolean {
        val ruta = when (args.size) {
            0 -> RUTA_POR_DEFECTO
            1, 4 -> args[0]
            else -> {
                consola.mostrarError("Número de argumentos inválido. Esperado: 0, 1 o 4.")
                return false
            }
        }
        if (gestorFicheros.crearRutaLog(ruta)) {
            consola.mostrar("Ruta $ruta creada")
        }
        return true
    }
}