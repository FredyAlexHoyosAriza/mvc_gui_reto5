package view;

import controller.ControladorRequerimientosReto4;
import model.vo.MaterialConstruccion;
import java.util.ArrayList;

import javax.swing.JButton;
// Clases para GUI
import javax.swing.JFrame; // Clase para crear ventanas
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

// import org.w3c.dom.ranges.Range;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;

// Interfaces para GUI
// Puesto que el MVC implica una interfaz, con la mernor cantidad
// de logico posible, las interfaces ActionListerner y ActionEvent,
// no se inplementan aqui, sino en el controlador

/********** GUI **********/

public class MaterialsCRUD_GUI extends JFrame {

    // Attributes
    
    // Interface attributes
    private JTable jtConstructionMaterial; // Lider de proyecto enblematico
    private JPanel panelEditMaterial;
    // private JPanel jpBtn;

    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;

    // Text fields
    // No se requiere txtIdMaterial, ya que esta se genera automatica/ cuando se adiciona
    private JTextField txtMaterialName;
    private JTextField txtImported;
    private JTextField txtUnitPrice;

    // Text labels
    private JLabel lblMaterialName;
    private JLabel lblImported;
    private JLabel lblUnitPrice;

    // Constructor
    public MaterialsCRUD_GUI(ControladorRequerimientosReto4 controlador) {

        // Propiedades de la ventana que se agregan al inicio

        // GridLayout(numberOfRows, numberOfColumns, horizontalSeparation, verticalSeparation)
        super.setLayout(new GridLayout(2, 1, 0, 10));

        // Titulo
        setTitle("*** Gestión Materiales Construccion ***");

        // Por defecto la ventana permanece oculta y abierta, por ello se debe establecer que esta se cierre
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        final int FONT_SIZE = 18;

        // Inicializar botones
        btnAdd = new JButton("Add"); // Texto que aparece en el boton
        btnAdd.addActionListener(controlador); // Ahora el controlador escucha la presion del boton
        btnAdd.setActionCommand("agregarMaterial"); // Mensaje enviado al presionar boton
        btnAdd.setFont(new FontUIResource("Dialog", Font.PLAIN, FONT_SIZE));

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(controlador);
        btnDelete.setActionCommand("eliminarMaterial");
        btnDelete.setFont(new FontUIResource("Dialog", Font.PLAIN, FONT_SIZE));

        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(controlador);
        btnUpdate.setActionCommand("actualizarMaterial");
        btnUpdate.setFont(new FontUIResource("Dialog", Font.PLAIN, FONT_SIZE));

        txtMaterialName = new JTextField();
        txtImported = new JTextField();
        txtUnitPrice = new JTextField();

        txtMaterialName.setFont(new FontUIResource("Dialog", Font.PLAIN, FONT_SIZE));
        txtImported.setFont(new FontUIResource("Dialog", Font.PLAIN, FONT_SIZE));
        txtUnitPrice.setFont(new FontUIResource("Dialog", Font.PLAIN, FONT_SIZE));
        
        // Nota: al igual que los JButtons, los JLabels tambien pueden llevar imagenes
        lblMaterialName = new JLabel("Material name: ", SwingConstants.RIGHT); // texto alineado a la derecha
        lblImported = new JLabel("Imported: ", SwingConstants.RIGHT);
        lblUnitPrice = new JLabel("Unit price: ", SwingConstants.RIGHT);

        lblMaterialName.setFont(new FontUIResource("Dialog", Font.PLAIN, FONT_SIZE));
        lblImported.setFont(new FontUIResource("Dialog", Font.PLAIN, FONT_SIZE));
        lblUnitPrice.setFont(new FontUIResource("Dialog", Font.PLAIN, FONT_SIZE));

        panelEditMaterial = new JPanel(new GridLayout(3, 3));
        panelEditMaterial.setBorder(new TitledBorder("Registrar nuevo material"));

        panelEditMaterial.add(btnAdd);
        panelEditMaterial.add(lblMaterialName);
        panelEditMaterial.add(txtMaterialName);
        panelEditMaterial.add(btnDelete);
        panelEditMaterial.add(lblImported);
        panelEditMaterial.add(txtImported);
        panelEditMaterial.add(btnUpdate);
        panelEditMaterial.add(lblUnitPrice);
        panelEditMaterial.add(txtUnitPrice);

        // panelEditMaterial.setBounds(10, 10, 680, 380);

    }

    // Method
    public void callMaterialsGUI(ArrayList<MaterialConstruccion> materials) {

        // Las JTable requiren 2 arreglos que deben ser clasicos, uno es el encabezado y el otro contiene los
        // registros. A continuacion se preparan los encabezados
        String[] columnHeaders = {"ID_Material_Construccion", "Nombre_Material", "Importado", "Precio_Unidad"};
        jtConstructionMaterial = new JTable(this.formatJtConstructionMaterial(materials, columnHeaders.length), columnHeaders);

        // Incorporar tabla:
        // El Scroll solo aparece si en la ventana no cabe la tabla
        JScrollPane sp = new JScrollPane(jtConstructionMaterial);

        JPanel panel = new JPanel(new GridLayout());
        panel.setBorder(new TitledBorder("Materiales de construccion"));
        panel.add(sp);
        
        // Nota a futuro: El metodo super.repaint(), borra el look previo.

        // Si existian peneles previos en la ventana se remueven con el metodo removeAll,
        // de no hacerlo, se mostraran junto con los que se agreguen a continuacion.
        super.getContentPane().removeAll();
        super.getContentPane().add(panel); // Incorporar tabla MaterialConstruccion
        // super.getContentPane().add(jpBtn);
        super.getContentPane().add(panelEditMaterial); // Incorporar formulario
        // super.getContentPane().add(sp);
        
        // Propiedades de la ventana que se agregan al final
        super.setSize(700, 600);
        super.setLocationRelativeTo(null);
        super.setVisible(true);

    }

    public String[][] formatJtConstructionMaterial(ArrayList<MaterialConstruccion> materials, int columnsNumber) {
        // Se crea el nuevo contenedor de los registross
        String[][] registers = new String[materials.size()][columnsNumber];
        
        // Se obtienen los registros del contenedor anterior tipo ArrayList
        for (int i = 0; i < registers.length; i++) {
            // Para cada lider, se guardan sus atributos en una fila de registers
            registers[i][0] = Integer.toString(materials.get(i).getIdMaterialConstruccion());
            registers[i][1] = materials.get(i).getNombreMaterial();
            registers[i][2] = materials.get(i).getImportado();
            registers[i][3] = Integer.toString(materials.get(i).getPrecioUnidad());
        }

        // Se retornan los registro en formato de arreglo Clasico
        return registers;
    }

    public JTable getJtConstructionMaterial() {
        return jtConstructionMaterial;
    }

    public void setJtConstructionMaterial(JTable jtConstructionMaterial) {
        this.jtConstructionMaterial = jtConstructionMaterial;
    }

    public JTextField getTxtMaterialName() {
        return txtMaterialName;
    }

    public void setTxtMaterialName(JTextField txtMaterialName) {
        this.txtMaterialName = txtMaterialName;
    }

    public JTextField getTxtImported() {
        return txtImported;
    }

    public void setTxtImported(JTextField txtImported) {
        this.txtImported = txtImported;
    }

    public JTextField getTxtUnitPrice() {
        return txtUnitPrice;
    }

    public void setTxtUnitPrice(JTextField txtUnitPrice) {
        this.txtUnitPrice = txtUnitPrice;
    }

}
