package view;

import controller.ControladorRequerimientosReto4;
import model.vo.LideresMayorSalario;
import java.util.ArrayList;

// Clases para GUI
import javax.swing.JFrame; // Clase para crear ventanas
import javax.swing.JScrollPane;
import javax.swing.JTable;
// import java.awt.GridLayout;

// Interfaces para GUI
// Puesto que el MVC implica una interfaz, con la mernor cantidad
// de logica posible, las interfaces ActionListerner y ActionEvent,
// no se inplementan aqui, sino en el controlador

/********** GUI **********/

public class Requirement1InGUI extends JFrame {

    // Attribute
    private ControladorRequerimientosReto4 controlador;

    // Interface attributes
    private JTable jtBestSalaries;
    // Constructor
    public Requirement1InGUI(ControladorRequerimientosReto4 controlador) {

        this.controlador = controlador;
        // // Se establece el diseño de cuadricula usando un metodo de la super clase JFrame
        // super.setLayout(new GridLayout());
    }

    // Method
    public void callGUIRequirment1(ArrayList<LideresMayorSalario> leaders) {
        // Titulo
        setTitle("*** Lideres con mayor salario ***");

        // Por defecto la ventana permanece oculta y abierta, por ello se debe establecer que esta se cierre
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Las JTable requiren 2 arreglos que deben ser clasicos, uno es el encabezado y el otro contiene los
        // registros. A continuacion se preparan los encabezados
        String[] columnHeaders = {"ID_Lider", "Nombre", "Primer_Apellido"};
        jtBestSalaries = new JTable(this.formatBestSalaries(leaders, columnHeaders.length), columnHeaders);
        // jtBestSalaries.setLayout(new GridLayout(1,1));

        // Incorporar tabla:
        // El Scroll solo aparece si en la ventana no cabe la tabla
        JScrollPane sp = new JScrollPane(jtBestSalaries);
        super.getContentPane().add(sp); // Se adiciona al Scroll al JFrame

        // Propiedades de la ventana
        super.setSize(400, 100);
        super.setLocationRelativeTo(null);
        super.setVisible(true);



    }

    public String[][] formatBestSalaries(ArrayList<LideresMayorSalario> leaders, int columnsNumber) {
        // Se crea el nuevo contenedor de los registross
        String[][] registers = new String[leaders.size()][columnsNumber];
        
        // Se obtienen los registros del contenedor anterior tipo ArrayList
        for (int i = 0; i < registers.length; i++) {
            // Para cada lider, se guardan sus atributos en una fila de register
            registers[i][0] = Integer.toString(leaders.get(i).getIdLider()); //String.valueOf(leaders.get(i).getIdLider())
            registers[i][1] = leaders.get(i).getNombre();
            registers[i][2] = leaders.get(i).getPrimerApellido();
        }

        // Se retornan los registro en formato de arreglo Clasico
        return registers;
    }


}
