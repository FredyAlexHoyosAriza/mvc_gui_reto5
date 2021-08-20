package model.dao;

import util.JDBCUtilities;
import model.vo.LideresProyectosEmblematicos;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LideresProyectosEmblematicosDao {

    public ArrayList<LideresProyectosEmblematicos> findFlagshipProjectLeaders() throws SQLException {

        // Se prepara el contenedor de la respuesta
        ArrayList<LideresProyectosEmblematicos> answer = new ArrayList<>();

        // Se prepara el contenerdor de la conexion
        Connection conexion = null;

        try { // Se da manejo a la excepcion lanzada en getConnection

            conexion = JDBCUtilities.getConnection(); // aqui se da la conexion

            String consulta = "SELECT ID_Lider, ID_Proyecto, ID_Tipo " +
                              "FROM Proyecto " +
                              "WHERE (ID_Proyecto >= 180 AND ID_Proyecto < 195) " +
                              "GROUP BY ID_Lider " +
                              "ORDER BY ID_Lider ASC";
            
            //System.out.println(consulta); // Verificar la consulta
            PreparedStatement readyStatement= conexion.prepareStatement(consulta);
            // Aqui la consulta en el String fue convertida en un objeto

            // Ahora ejecutemos la consulta
            ResultSet resultSet =  readyStatement.executeQuery();

            while (resultSet.next()) { 

                LideresProyectosEmblematicos lider = new LideresProyectosEmblematicos();
                // Se asignan atributos de un registro (fila) a atributos del VO
                lider.setIdLider(resultSet.getInt("ID_Lider")); // getInt se usa para cargar un campo entero de resultSet
                // El String que se le pasa a getInt debe conincidir con el atributo correspondiente de la entidad
                lider.setIdProyecto(resultSet.getInt("ID_Proyecto"));
                lider.setIdTipo(resultSet.getInt("ID_Tipo"));
                answer.add(lider);
            }

            resultSet.close();
            readyStatement.close();

        } catch (SQLException e) { // objeto error
            System.err.println("Error consultando la entidad Proyecto en la BD: " + e);
            // Se reporta el error y continua el flujo de codigo
        } finally {
            // Si se logro la conexion con la BD esta deber ser cerrada;
            // se logro siempre que conexion tenga un valor distinto de null
            if (conexion != null) {
                conexion.close();
            }

        }
        // Se retorna la respuesta obtenida de la interaccion con la BD
        return answer;
    }
}
