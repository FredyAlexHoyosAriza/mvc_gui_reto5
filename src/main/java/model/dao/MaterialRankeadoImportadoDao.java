package model.dao;

import util.JDBCUtilities;
import model.vo.MaterialRankeadoImportado;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MaterialRankeadoImportadoDao {
    public ArrayList<MaterialRankeadoImportado> assembleImportedMaterialMsg() throws SQLException {

        // Se prepara el contenedor de la respuesta
        ArrayList<MaterialRankeadoImportado> answer = new ArrayList<>();

        // Se prepara el contenerdor de la conexion
        Connection conexion = null;

        try { // Se da manejo a la excepcion lanzada en getConnection

            conexion = JDBCUtilities.getConnection(); // aqui se da la conexion

            String consulta = "SELECT \"El producto de ID \" || mc.ID_MaterialConstruccion || " +
                              "\" de descripción: \" || mc.Nombre_Material || " +
                              "\" de tipo importado(a), tiene un precio de \" || " +
                              "mc.Precio_Unidad AS Productos_Importados " +
                              "FROM MaterialConstruccion mc " +
                              "WHERE (mc.Importado = \"Si\" AND mc.Precio_Unidad >= 2000)";

            // System.out.println(consulta); // Verificar la consulta           
            PreparedStatement readyStatement= conexion.prepareStatement(consulta);
            // Aqui la consulta en el String fue convertida en un objeto

            // Ahora ejecutemos la consulta
            ResultSet resultSet =  readyStatement.executeQuery();

            while (resultSet.next()) { 

                MaterialRankeadoImportado materialConstruccion = new MaterialRankeadoImportado();
                // Se asigna el contenido de un campo calculado a un atributo del VO
                materialConstruccion.setAttributesMix(resultSet.getString("Productos_Importados"));
                answer.add(materialConstruccion);
            }

            resultSet.close();
            readyStatement.close();

        } catch (SQLException e) { // objeto error
            System.err.println("Error consultando la entidad MaterialConstruccion en la BD: " + e);
            // Se reporta el error y continua el flujo de codigo
        } finally {
            // Si se logro la conexion con la BD esta deber ser cerrada;
            // Se logro siempre que conexion tenga un valor distinto de null
            if (conexion != null) {
                conexion.close(); // Este cierre podria generar una excepcion por eso
                                  // el throw en la firma de la funcion
            }

        }
        // Se retorna la respuesta obtenida de la interaccion con la BD
        return answer;
    }
}
