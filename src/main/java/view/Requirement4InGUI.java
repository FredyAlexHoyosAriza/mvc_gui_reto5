package view;

//import controller.ControladorRequerimientosReto4;
import model.vo.MaterialRankeadoImportado;
import java.util.ArrayList;

// Clases para GUI
import javax.swing.JFrame; // Clase para crear ventanas
import javax.swing.JScrollPane;
import javax.swing.JList;

// Interfaces para GUI
// Puesto que el MVC implica una interfaz, con la mernor cantidad
// de logico posible, las interfaces ActionListerner y ActionEvent,
// no se inplementan aqui, sino en el controlador

/********** GUI **********/

public class Requirement4InGUI extends JFrame {

    // Attribute
    // Interface attributes
    private JList<String> impMattRanking; // Imported Materil Ranking

    // Constructor
    public Requirement4InGUI() {}

    // Method
    public void callGUIRequirment4(ArrayList<MaterialRankeadoImportado> impMatt) {        
        // Titulo
        setTitle("*** Productos importados ***");

        // Por defecto la ventana permanece oculta y abierta, por ello se debe establecer que esta se cierre
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Aqui usamos una JList para mostrar la lista de mensajes
        impMattRanking = new JList<String>(this.formatImpMattRanking(impMatt));

        // Incorporar tabla:
        // El Scroll solo aparece si en la ventana no cabe la tabla
        JScrollPane sp = new JScrollPane(impMattRanking);
        super.getContentPane().add(sp); // Se adiciona al panel al JFrame
        // Propiedades de la ventana
        super.setSize(600, 100);
        super.setLocationRelativeTo(null); // NO fijar la ventana a ninguna ubicacion particular en la pantalla
        super.setVisible(true);

    }

    public String[] formatImpMattRanking(ArrayList<MaterialRankeadoImportado> impMatt) {
        // Se crea el nuevo contenedor de los registross
        String[] registers = new String[impMatt.size()];
        
        // Se obtienen los registros del contenedor anterior tipo ArrayList
        for (int i = 0; i < registers.length; i++) {
            // Para cada lider, se guardan sus atributos en una fila de register
            registers[i] = impMatt.get(i).getAttributesMix();
        }

        // Se retornan los registro en formato de arreglo Clasico
        return registers;
    }


}
