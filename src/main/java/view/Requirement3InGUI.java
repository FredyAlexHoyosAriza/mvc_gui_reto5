package view;

import controller.ControladorRequerimientosReto4;
import model.vo.LideresProyectosEmblematicos;
import java.util.ArrayList;

// Clases para GUI
import javax.swing.JFrame; // Clase para crear ventanas
import javax.swing.JScrollPane;
import javax.swing.JTable;

// Interfaces para GUI
// Puesto que el MVC implica una interfaz, con la mernor cantidad
// de logico posible, las interfaces ActionListerner y ActionEvent,
// no se inplementan aqui, sino en el controlador

/********** GUI **********/

public class Requirement3InGUI extends JFrame {

    // Attribute
    // Interface attributes
    private JTable flagshipProjectLdr; // Lider de proyecto enblematico

    // Constructor
    public Requirement3InGUI(ArrayList<LideresProyectosEmblematicos> leaders, ControladorRequerimientosReto4 controlador) {
        // Titulo
        setTitle("*** Lideres proyectos emblemáticos ");

        // Por defecto la ventana permanece oculta y abierta, por ello se debe establecer que esta se cierre
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Las JTable requiren 2 arreglos que deben ser clasicos, uno es el encabezado y el otro contiene los
        // registros. A continuacion se preparan los encabezados
        String[] columnHeaders = {"Id_Lider", "Id_Proyecto", "Id_Tipo"};  
        flagshipProjectLdr = new JTable(this.formatFlagshipProjectLdr(leaders, columnHeaders.length), columnHeaders);

        // Incorporar tabla:
        // El Scroll solo aparece si en la ventana no cabe la tabla
        JScrollPane sp = new JScrollPane(flagshipProjectLdr);
        super.getContentPane().add(sp); // Se adiciona al panel al JFrame

        // Propiedades de la ventana
        super.setSize(400, 300);
        super.setLocationRelativeTo(null);
        super.setVisible(true);



    }

    public Integer[][] formatFlagshipProjectLdr(ArrayList<LideresProyectosEmblematicos> leaders, int columnsNumber) {
        // Se crea el nuevo contenedor de los registross
        Integer[][] registers = new Integer[leaders.size()][columnsNumber];
        
        // Se obtienen los registros del contenedor anterior tipo ArrayList
        for (int i = 0; i < registers.length; i++) {
            // Para cada lider, se guardan sus atributos en una fila de registers
            registers[i][0] = leaders.get(i).getIdLider();
            registers[i][1] = leaders.get(i).getIdProyecto();
            registers[i][2] = leaders.get(i).getIdTipo();
        }

        // Se retornan los registro en formato de arreglo Clasico
        return registers;
    }


}
