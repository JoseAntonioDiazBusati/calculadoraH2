package org.example.model

enum class Operador (val simbolo: Char, val simbolos: List<Char>) {
    SUMA('+', listOf('+')),
    RESTA('-', listOf('-')),
    MULTIPLICACION('x', listOf('*', 'x')),
    DIVISION('/', listOf(':', '/'));

    companion object {
        fun tenerOperador(operador: Char?) = operador?.let { op -> entries.find { op in it.simbolos } }
    }
}