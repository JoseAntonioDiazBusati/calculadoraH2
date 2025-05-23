package org.example.datos.dao

import org.example.datos.bd.DataBase
import org.example.model.Registro
import org.example.ui.Consola
import java.sql.*
import java.time.LocalDateTime

class RegistroDAO: IRegistroDAO {
    override fun crearRegistro() {
        val sql = """
            CREATE TABLE IF NOT EXISTS REGISTRO (
                id INT PRIMARY KEY AUTO_INCREMENT,
                nombreRegistro VARCHAR(255) NOT NULL,
                resultado DOUBLE NOT NULL,
                fechaRegistro TIMESTAMP NOT NULL
            )   
        """.trimIndent()
        DataBase.getConnection().use { conn ->
            conn?.createStatement().use { stmt ->
                stmt?.execute(sql)
            }
        }
    }


    override fun guardarRegistro(
        nombreRegistro: String,
        resultado: Double,
        fechaRegistro: LocalDateTime
    ) {
        var conn: Connection? = null
        var stmt: PreparedStatement? = null

        try {
            conn = DataBase.getConnection()
            stmt = conn?.prepareStatement("INSERT INTO REGISTRO (nombreRegistro,resultado,fechaRegistro) VALUES (?,?,?,?,?,?)")
            stmt?.setString(1, nombreRegistro)
            stmt?.setDouble(2, resultado)
            stmt?.setTimestamp(3, Timestamp.valueOf(fechaRegistro))
            stmt?.executeUpdate()
        } catch (e: SQLException) {
            Consola().mostrarError("Error al insertar en la tabla.")
        } catch (e: Exception) {
            Consola().mostrarError("Error al ejecutar.")
        } finally {
            stmt?.close()
            conn?.close()
        }
    }

    override fun mostrarRegistro(): List<Registro> {
        val registros = mutableListOf<Registro>()
        var conn: Connection? = null
        var stmt: Statement? = null
        var rs: ResultSet? = null

        try {
            conn = DataBase.getConnection()
            stmt = conn?.createStatement()
            rs = stmt?.executeQuery("SELECT * FROM REGISTRO")

            while (rs?.next() == true) {
                val nombreRegistro = rs.getString("nombreRegistro")
                val resultado = rs.getDouble("resultado")
                val fechaRegistro = rs.getTimestamp("fechaRegistro").toLocalDateTime()

                registros.add(Registro(nombreRegistro,resultado, fechaRegistro))
            }
        } catch (e: SQLException) {
            Consola().mostrarError("Error al mostrar la tabla REGISTRO.")
        } catch (e: Exception) {
            Consola().mostrarError("Error inesperado al mostrar registros.")
        } finally {
            rs?.close()
            stmt?.close()
            conn?.close()
        }

        return registros
    }
}