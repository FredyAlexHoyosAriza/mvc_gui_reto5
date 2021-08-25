package view;

import controller.ControladorRequerimientosReto4;
import model.vo.LideresMayorSalario;
import model.vo.LideresProyectosEmblematicos;
import model.vo.MaterialRankeadoImportado;
import java.util.ArrayList;
import java.sql.SQLException;

// Clases para GUI
import javax.swing.JFrame; // Clase para crear ventanas
import javax.swing.JPanel; // Clase para crear paneless
import javax.swing.plaf.FontUIResource;
import javax.swing.JButton; // Clase para crear botones
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;
// Interfaces para GUI
// Puesto que el MVC implica una interfaz, con la mernor cantidad
// de logico posible, las interfaces ActionListerner y ActionEvent,
// no se inplementan aqui, sino en el controlador

/********** GUI **********/

public class RequirementsMenuInGUI extends JFrame {

    // Attribute    
    public static final ControladorRequerimientosReto4 controlador = new ControladorRequerimientosReto4();

    // Interface attributes
    private JButton btnRequerimiento1;
    private JButton btnRequerimiento3;
    private JButton btnRequerimiento4;
    private JButton btnCRUD_Materiales;

    // Metodo que genere la ventana, se tienen como opciones:
    // 1) A partir del constructor
    // 2) Usando un metodo especifico

    public void startGUI() {
        // Este es el punto de entrada de la app.

        // Titulo
        setTitle("Requirements menu start");

        // Por defecto la ventana permanece oculta y abierta, por ello se debe establecer que esta se cierre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Al cerrar mata todos los procesos

        // Nota: Swing hereda de AWT, y a su vez AWT consume lo que obtiene del sistema operativo (SO), por
        // lo cual la vista de la aplicacion cambia dependiendo del SO

        // Crear o instanciar los componentes
        final int FONT_SIZE = 18;

        // Nota: para iconos -> pagina flaticon

        ImageIcon icono = new ImageIcon("img/money.png");
        Image img = icono.getImage();
        icono = new ImageIcon(img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        // Al constructor JButton se envia una imagen
        btnRequerimiento1 = new JButton(icono);
        btnRequerimiento1.setText("Highest salary leaders");
        // Al crear los botones, tambien se debe establecer, quien los puede escuchar, por ello,        
        // se enlaza btnrequerimiento1 con el controlador
        btnRequerimiento1.addActionListener(controlador);
        // Ademas se debe establecer que dice cada uno
        btnRequerimiento1.setActionCommand("requerimiento1");
        // Tambien se pueden establecer las fuentes
        btnRequerimiento1.setFont(new FontUIResource("Dialog", Font.PLAIN, FONT_SIZE));

        icono = new ImageIcon("img/leader.png");
        img = icono.getImage();
        icono = new ImageIcon(img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        btnRequerimiento3 = new JButton(icono);
        btnRequerimiento3.setText("Flagship project leaders");
        btnRequerimiento3.addActionListener(controlador);
        btnRequerimiento3.setActionCommand("requerimiento3");
        btnRequerimiento3.setFont(new FontUIResource("Dialog", Font.PLAIN, FONT_SIZE));

        icono = new ImageIcon("img/materiales.png");
        img = icono.getImage();
        icono = new ImageIcon(img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        btnRequerimiento4 = new JButton(icono);
        btnRequerimiento4.setText("Imported material ranking");
        btnRequerimiento4.addActionListener(controlador);
        btnRequerimiento4.setActionCommand("requerimiento4");
        btnRequerimiento4.setFont(new FontUIResource("Dialog", Font.PLAIN, FONT_SIZE));

        icono = new ImageIcon("img/edicion_BD.png");
        img = icono.getImage();
        icono = new ImageIcon(img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        btnCRUD_Materiales = new JButton(icono);
        btnCRUD_Materiales.setText("Materials CRUD");
        btnCRUD_Materiales.addActionListener(controlador);
        btnCRUD_Materiales.setActionCommand("crudMateriales");
        btnCRUD_Materiales.setFont(new FontUIResource("Dialog", Font.PLAIN, FONT_SIZE));

        // Añadir componentes a componentes intermedios o contenedores

        // Nota: Es un poco mas facil organizar los componenes en el contenedor intermedio
        // JPanel que en un contenedor JFrame, por ello los botones se añaden a un JPanel

        // Contenedor intermdio tipo JPanel, estos por lo general van en un contenedor (JFrame)
        JPanel panel = new JPanel();
        // Se ajustan los botones al tamaño de la ventana, en disposicion: 2 filas por 2 columnas
        panel.setLayout(new GridLayout(2, 2));
        // panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        // Agregar componentes al panel
        panel.add(btnRequerimiento1);
        panel.add(btnRequerimiento3);
        panel.add(btnRequerimiento4);
        panel.add(btnCRUD_Materiales);

        // Ahora se pasa del componete intermedio (tipo JPanel) a la ventana (tipo JFrame)
        getContentPane().add(panel); // Se agrega el panel a la ventana
        
        // Establecer las demás propiedades del frame; Algunas propiedades es mejor establecerlas luego de
        // añadir los componentes, p. ej. el tamaño, la visibilidad, etc.
        setSize(600, 150);
        setLocationRelativeTo(null); // posicion relativa de la ventana con respecto a la pantalla
        setVisible(true); // Ahora la ventana puede verse

    }



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
