package model.dao;

import util.JDBCUtilities;
import model.vo.MaterialConstruccion;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MaterialConstruccionDao {

    public ArrayList<MaterialConstruccion> findAllMaterials() throws SQLException {

        // Se prepara el contenedor de la respuesta
        ArrayList<MaterialConstruccion> answer = new ArrayList<>();

        // Se prepara el contenerdor de la conexion
        Connection conexion = null;

        try { // Se da manejo a la excepcion lanzada en getConnection

            conexion = JDBCUtilities.getConnection(); // aqui se da la conexion

            String consulta = "SELECT * FROM MaterialConstruccion";

            //System.out.println(consulta); // Verificar la consulta
            PreparedStatement readyStatement= conexion.prepareStatement(consulta);
            // Aqui la consulta en el String fue convertida en un objeto

            // Ahora ejecutemos la consulta
            ResultSet resultSet =  readyStatement.executeQuery();

            while (resultSet.next()) { 

                MaterialConstruccion material = new MaterialConstruccion();
                // Se asignan atributos de un registro (fila) a atributos del VO
                material.setIdMaterialConstruccion(resultSet.getInt(1));//"ID_MaterialConstruccion"
                // El indice que se le pasa a getInt debe conincidir con la columna deseada de la entidad
                material.setNombreMaterial(resultSet.getString(2));//"Nombre_Material"
                material.setImportado(resultSet.getString(3));//"Importado"
                material.setPrecioUnidad(resultSet.getInt(4));//"Precio_Unidad"
                answer.add(material);
            }

            resultSet.close();
            readyStatement.close();

        } catch (SQLException e) { // objeto error
            System.err.println("Error consultando la entidad material en la BD: " + e);
            // Se reporta el error y continua el flujo de codigo
        } finally {
            // Si se logro la conexion con la BD esta deber ser cerrada
            // Se logro siempre que conexion tenga un valor distinto de null
            if (conexion != null) {
                conexion.close();
            }

        }
        // Se retorna la respuesta obtenida de la interaccion con la BD
        return answer;
    }

    public ArrayList<MaterialConstruccion> insertMaterial(MaterialConstruccion newMaterial) throws SQLException {

        // Se prepara el contenerdor de la conexion
        Connection conexion = null;

        // Se intenta ingresar un nuevo material en la BD
        try { // Se da manejo a la excepcion lanzada en getConnection

            conexion = JDBCUtilities.getConnection(); // aqui se da la conexion

            String consulta =   "INSERT INTO MaterialConstruccion " +
                                "(Nombre_Material, Importado, Precio_Unidad) " +
                                "VALUES (?, ?, ?)";

            //System.out.println(consulta); // Verificar la consulta

            // Contruir objeto que realiza consulta
            PreparedStatement readyStatement= conexion.prepareStatement(consulta);
            readyStatement.setString(1, newMaterial.getNombreMaterial());
            readyStatement.setString(2, newMaterial.getImportado());
            readyStatement.setInt(3, newMaterial.getPrecioUnidad());

            // Se realiza la insercion
            readyStatement.executeUpdate(); // success could be used

            readyStatement.close();

        } catch (SQLException e) { // objeto error
            System.err.println("Error insertando registro en entidad MaterialConstruccion!: " + e);
            // Se reporta el error y continua el flujo de codigo
        } finally {
            // Si se logro la conexion con la BD esta deber ser cerrada
            // Se logro siempre que conexion tenga un valor distinto de null
            if (conexion != null) {
                conexion.close();
            }

        }
        // Se retorna la respuesta obtenida de la interaccion con la BD
        return findAllMaterials();
    }

    public int updateMaterial(MaterialConstruccion exchMaterial) throws SQLException {

        // Se prepara el contenerdor de la conexion
        Connection conexion = null;
        int success = 0;

        // Se intenta actualizar un material en la BD
        try { // Se da manejo a la excepcion lanzada en getConnection

            conexion = JDBCUtilities.getConnection(); // aqui se da la conexion

            String consulta =   "UPDATE MaterialConstruccion SET " +
                                "Nombre_Material = ?, Importado = ?, Precio_Unidad = ? " +
                                "WHERE ID_MaterialConstruccion = ?";

            //System.out.println(consulta); // Verificar la consulta

            // Contruir objeto que realiza consulta
            PreparedStatement readyStatement= conexion.prepareStatement(consulta);
            readyStatement.setString(1, exchMaterial.getNombreMaterial());
            readyStatement.setString(2, exchMaterial.getImportado());
            readyStatement.setInt(3, exchMaterial.getPrecioUnidad());
            readyStatement.setInt(4, exchMaterial.getIdMaterialConstruccion());

            // Se realiza la insercion
            success = readyStatement.executeUpdate();

            readyStatement.close();

        } catch (SQLException e) { // objeto error
            System.err.println("Error actualizando registro en entidad MaterialConstruccion!: " + e);
            // Se reporta el error y continua el flujo de codigo
        } finally {
            // Si se logro la conexion con la BD esta deber ser cerrada
            // Se logro siempre que conexion tenga un valor distinto de null
            if (conexion != null) {
                conexion.close();
            }

        }
        // Si el registro a actualizar se encontro en BD succes = 1
        return success;
    }

    public int deleteMaterial(MaterialConstruccion discardedMaterial) throws SQLException {

        // Se prepara el contenerdor de la conexion
        Connection conexion = null;
        int success = 0;

        // Se intenta actualizar un material en la BD
        try { // Se da manejo a la excepcion lanzada en getConnection

            conexion = JDBCUtilities.getConnection(); // aqui se da la conexion

            String consulta =   "DELETE FROM MaterialConstruccion " +
                                "WHERE ID_MaterialConstruccion = ?";

            //System.out.println(consulta); // Verificar la consulta

            // Contruir objeto que realiza consulta
            PreparedStatement readyStatement= conexion.prepareStatement(consulta);
            readyStatement.setInt(1, discardedMaterial.getIdMaterialConstruccion());

            // Se realiza la insercion
            success = readyStatement.executeUpdate();

            readyStatement.close();

        } catch (SQLException e) { // objeto error
            System.err.println("Error eliminando registro en entidad MaterialConstruccion!: " + e);
            // Se reporta el error y continua el flujo de codigo
        } finally {
            // Si se logro la conexion con la BD esta deber ser cerrada
            // Se logro siempre que conexion tenga un valor distinto de null
            if (conexion != null) {
                conexion.close();
            }

        }
        // Si el registro a eliminar se encontro en BD succes = 1
        return success;
    }

}
