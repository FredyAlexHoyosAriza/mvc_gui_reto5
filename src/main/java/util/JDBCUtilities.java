package util;

import java.io.File; // Permite averiguar si una BD esta vacia (al no existir la BD, se crea una vacia),
                     // nos asistiria en el proceso de saber si la BD previamente no existia.

// Paquetes para asistir la interaccion con la BD
import java.sql.DriverManager; // paquete para manipular conexiones
import java.sql.Connection; // paquete para establecer conexiones
import java.sql.SQLException; // manejador de excepciones de conexiones a la BD (falla de conexion)

public class JDBCUtilities {

    //Atributos de clase para gestión de conexión con la base de datos      
    private static final String UBICACION_BD = "ProyectosConstruccion.db"; // ruta relativa

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:" + UBICACION_BD;
        return DriverManager.getConnection(url);
    }

    public static boolean estaVacia(){
        File archivo = new File(JDBCUtilities.UBICACION_BD);
        return archivo.length() == 0;
    }

}
