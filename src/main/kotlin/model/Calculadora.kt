package org.example.model

data class Calculadora (
    val numero1: Double,
    val numero2: Double,
    val operador: Operador,
    val resultado: Double
    ) {
        override fun toString(): String {
            return "%.2f %s %.2f = %.2f".format(numero1, operador.simbolo, numero2, resultado)
        }
}