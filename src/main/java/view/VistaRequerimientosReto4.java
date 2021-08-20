package view;

import controller.ControladorRequerimientosReto4;
import model.vo.LideresMayorSalario;
import model.vo.LideresProyectosEmblematicos;
import model.vo.MaterialRankeadoImportado;
import java.util.ArrayList;
import java.sql.SQLException;


public class VistaRequerimientosReto4 {
    
    public static final ControladorRequerimientosReto4 controlador = new ControladorRequerimientosReto4();

    public static void requerimiento1() {

        System.out.println("*** Lideres con mayor salario ***");
        try {
            ArrayList<LideresMayorSalario> lideresMayorSalario = controlador.queryHighestSalaryLeaders();

            //Encabezado del resultado
            System.out.println("ID_Lider  Nombre  Primer_Apellido");

            // Se inprime cada uno de los registros obtenidos del llamado en controller
            for (LideresMayorSalario lider : lideresMayorSalario) {
                // Tambien se pudo hacer la salida con un println y concatenando cadenas y numeros
                System.out.printf("      %d  %s   %s  %n",
                                lider.getIdLider(),
                                lider.getNombre(),
                                lider.getPrimerApellido()
                );
            }
        } catch (SQLException e) {
            System.err.println("Ha ocurrido un error!" + e.getMessage());
        }
    }

    public static void requerimiento3() {

        System.out.println("*** Lideres proyectos emblemáticos ***");       

        try{

            ArrayList<LideresProyectosEmblematicos> lideresProyectosEmblematicos = controlador.queryFlagshipProjectLeaders();

            //Encabezado del resultado
            System.out.println("Id_Lider Id_Proyecto Id_Tipo");

            // Se inprime cada uno de los registros obtenidos del llamado en controller
            for (LideresProyectosEmblematicos lider : lideresProyectosEmblematicos) {
                // Tambien se pudo hacer la salida con un println y concatenando cadenas y numeros
                System.out.printf("      %d         %d       %d %n",
                                lider.getIdLider(),
                                lider.getIdProyecto(),
                                lider.getIdTipo()
                );
            }

        } catch (SQLException e) {
            System.err.println("Ha ocurrido un error!"+e.getMessage());
        }

    }

    public static void requerimiento4(){

        System.out.println("*** Productos importados ***");       

        try{

            ArrayList<MaterialRankeadoImportado> materialRankeadoImportado = controlador.queryImportedMaterialRanking();

            //Encabezado del resultado
            // System.out.println("Sin encabezado");
            
            // Se inprime cada uno de los registros obtenidos del llamado en controller

            for (MaterialRankeadoImportado materialImportado : materialRankeadoImportado) {
                // Tambien se pudo hacer la salida con un println y concatenando cadenas y numeros
                System.out.printf("%s %n",
                                materialImportado.getAttributesMix()
                );
            }

        } catch (SQLException e) {
            System.err.println("Ha ocurrido un error!"+e.getMessage());
        }

    }


}
