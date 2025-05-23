package org.example.service

import org.example.model.Calculadora
import org.example.model.Operador

class CalculadoraService {
    fun realizarOperacion(num1: Double, operador: Operador, num2: Double): Double {
        return when (operador) {
            Operador.SUMA -> num1 + num2
            Operador.RESTA -> num1 - num2
            Operador.MULTIPLICACION -> num1 * num2
            Operador.DIVISION -> num1 / num2
        }
    }
    fun realizarCalculo(num1: Double, operador: Operador, num2: Double): Calculadora {
        return Calculadora(num1, num2, operador, realizarOperacion(num1, operador, num2))

    }
}