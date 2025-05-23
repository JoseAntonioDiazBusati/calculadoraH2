package org.example.datos.bd
import java.sql.*

/**
 * Singleton que gestiona la conexion a la base de datos en H2
 * También proporciona los métodos para obtener una conexión y cerrarla.
 */

object DataBase {
    private const val URL = "jdbc:h2:~/log/logCalculadora"
    private const val USERNAME = "sa"
    private const val PASSWORD = ""

    /**
     * La funcion getconnection() establece la conexion a la base de datos
     * @return una instancia de conexion exitosa o null si ocurrió algun error.
     */

    fun getConnection(): Connection? {
        return try {
            DriverManager.getConnection(URL, USERNAME, PASSWORD)
        } catch (e: SQLException) {
            print("Error: ${e.message}")
            null
        }
    }
    /**
     * Cierra la conexion a la base de datos en H2
     * @param la conexion que se desea cerrar puede ser 'null'
     */
    fun closeConnection(conn: Connection?) {
        try {
            conn?.close()
        } catch (e: SQLException) {
            print("Error: ${e.message}")
        }
    }
}