package controller;

import model.vo.LideresMayorSalario;
import model.vo.LideresProyectosEmblematicos;
import model.vo.MaterialRankeadoImportado;
import model.vo.MaterialConstruccion;

import model.dao.LideresMayorSalarioDao;
import model.dao.LideresProyectosEmblematicosDao;
import model.dao.MaterialRankeadoImportadoDao;
import model.dao.MaterialConstruccionDao;

// import view.VistaRequerimientosReto4;

import java.sql.SQLException;
import java.util.ArrayList;

import view.Requirement1InGUI;
import view.Requirement3InGUI;
import view.Requirement4InGUI;
import view.MaterialsCRUD_GUI;

// Clases para GUI
import view.RequirementsMenuInGUI;
import javax.swing.JButton;
import javax.swing.JOptionPane;

// Interfaces para GUI
import java.awt.event.ActionListener; // Manejo de eventos
import java.awt.event.ActionEvent; // Evento relacionado a un boton
// Con las interfaces ActionListener y ActionEvent se le da la capacidad
// al controlador de que escuche lo que ocurre con los botones; para ello
// se debe hacer sobreescritura de algnos metodos abstractos en la
// interface ActionListener

public class ControladorRequerimientosReto4 implements ActionListener {

    // Attributes    
    private final LideresMayorSalarioDao lideresMayorSalarioDao;
    private final LideresProyectosEmblematicosDao lideresProyectosEmblematicosDao;
    private final MaterialRankeadoImportadoDao materialRankeadoImportadoDao;
    private final MaterialConstruccionDao materialDao;
    private RequirementsMenuInGUI requirementsMenuInGUI;
    private Requirement1InGUI requerimiento1gui;
    private Requirement3InGUI requerimiento3gui;
    private Requirement4InGUI requerimiento4gui;
    private MaterialsCRUD_GUI materialsCrud;

    public ControladorRequerimientosReto4(){        
        // Aqui se instancian todos los DAO que se utilicen
        this.lideresMayorSalarioDao = new LideresMayorSalarioDao();
        this.lideresProyectosEmblematicosDao = new LideresProyectosEmblematicosDao();
        this.materialRankeadoImportadoDao = new MaterialRankeadoImportadoDao();
        this.materialDao = new MaterialConstruccionDao();
        this.requirementsMenuInGUI = new RequirementsMenuInGUI();

        requerimiento1gui = new Requirement1InGUI(this);
        requerimiento3gui = new Requirement3InGUI();
        requerimiento4gui = new Requirement4InGUI();
        materialsCrud = new MaterialsCRUD_GUI(this);
    }

    public ArrayList<LideresMayorSalario> queryHighestSalaryLeaders() throws SQLException {
        return this.lideresMayorSalarioDao.findBestSalaries();
    }

    public ArrayList<LideresProyectosEmblematicos> queryFlagshipProjectLeaders() throws SQLException {
        return this.lideresProyectosEmblematicosDao.findFlagshipProjectLeaders();
    }

    public ArrayList<MaterialRankeadoImportado> queryImportedMaterialRanking() throws SQLException {
        return this.materialRankeadoImportadoDao.assembleImportedMaterialMsg();
    }

    public ArrayList<MaterialConstruccion> queryAllMaterials() throws SQLException {
        return this.materialDao.findAllMaterials();
    }

    public void start() {
        // Ahora el controlador tendra el inicio de la aplicacion
        this.requirementsMenuInGUI.startGUI();
    }

    private MaterialConstruccion readJtableRegister(MaterialConstruccion material) {

        /* int[] slctnColumns = materialsCrud.getJtConstructionMaterial().getSelectedColumns(); */
                
        // Extraer la informacion del formulario y envolverla en el VO correspondiente

        // Extraer registro seleccionado del JTable. Solo se toma la primera fila seleccionada:
        // es decir slctnRows[0]
        int[] slctnRows = materialsCrud.getJtConstructionMaterial().getSelectedRows();

        // Se copian los campos en el registro seleccionado del JTable al VO MaterialConstruccion
        material.setIdMaterialConstruccion(Integer.parseInt(
            (String) materialsCrud.getJtConstructionMaterial().getValueAt(slctnRows[0], 0))
        );
        material.setNombreMaterial(
            (String) materialsCrud.getJtConstructionMaterial().getValueAt(slctnRows[0], 1)
        );
        material.setImportado(
            (String) materialsCrud.getJtConstructionMaterial().getValueAt(slctnRows[0], 2)
        );
        material.setPrecioUnidad(Integer.parseInt(
            (String) materialsCrud.getJtConstructionMaterial().getValueAt(slctnRows[0], 3))
        );

        return material;
    }

    // Sobre-escritura de metodos abstractos de la interface ActionListener

    // Al inplementar este metodo en la clase controlador, esta ahora tiene
    // la capacidad de escuchar eventos
    @Override
    // Un pocquito de la logica quedo en el formulario, concretamente, que
    // boton se presiona, pero aca se revisa cual fue el boton presionado;
    // cual fue el evento
    public void actionPerformed(ActionEvent e) {
        // Una de las formas para establecer que camino tomar es obtener informacion del evento.
        // Una de estas formas es con un re-cast JButton sobre e.getSource(), ya que se esperan
        // eventos accionados por botones
        String actionString = ((JButton) e.getSource()).getActionCommand();
        // Basicamente se esta escuchando si algun comando de accion fue accionado (boton oprimido)
        // Dependiendo del contenido de actionString, se pueden tomar diferentes camninos

        // Caminos segun el evento ocurrido
        // Se quiere que para cada boton se genere una ventana que muestre la salida
        switch (actionString) {
            case "requerimiento1":
                try {
                    this.requerimiento1gui.callGUIRequirment1(queryHighestSalaryLeaders()); // Este controlador

                } catch (Exception exc) {
                    System.err.println("Error de cierre en BD durante requerimiento 1!" + exc.getMessage());
                }
                // VistaRequerimientosReto4.requerimiento1();
                break;

            case "requerimiento3":
                try {
                    this.requerimiento3gui.callGUIRequirment3(queryFlagshipProjectLeaders()); // Este controlador

                } catch (Exception exc) {
                    System.err.println("Error de cierre en BD durante requerimiento 3!" + exc.getMessage());
                }
                // VistaRequerimientosReto4.requerimiento3();
                break;

            case "requerimiento4":
                try {
                    this.requerimiento4gui.callGUIRequirment4(queryImportedMaterialRanking()); // Este controlador

                } catch (Exception exc) {
                    System.err.println("Error de cierre en BD durante requerimiento 4!" + exc.getMessage());
                }
                // VistaRequerimientosReto4.requerimiento4();
                break;

            case "crudMateriales":
                // materialsCrud.dispose(); // Cerrar instancia de la interfaz materialsCrud
                try {
                    this.materialsCrud.callMaterialsGUI(this.materialDao.findAllMaterials()); //queryAllMaterials()

                } catch (Exception exc) {
                    System.err.println("Error de cierre en BD en acceso a MaterialesConstruccion!: " + exc.getMessage());
                }
                break;

            case "agregarMaterial":
                // Extraer la informacion del formulario y envolverla en el VO correspondiente
                MaterialConstruccion newMaterial = new MaterialConstruccion();
                newMaterial.setNombreMaterial(this.materialsCrud.getTxtMaterialName().getText());
                newMaterial.setImportado(this.materialsCrud.getTxtImported().getText());
                newMaterial.setPrecioUnidad(Integer.parseInt((this.materialsCrud.getTxtUnitPrice().getText())));//Integer.valueOf
                // Intentar insertar el registro obtenido con la interfaz
                try {
                    // this.materialDao.insertMaterial(newMaterial); //returns all Materials
                    this.materialsCrud.callMaterialsGUI(this.materialDao.insertMaterial(newMaterial));

                } catch (Exception exc) {
                    System.err.println("Error de cierre en BD en inserción a MaterialesConstruccion!" + exc.getMessage());
                }
                break;

            case "actualizarMaterial":
 
                // Se crea un VO para almacenar los campos de la fila seleccionada en el JTable
                MaterialConstruccion exchMaterial = new MaterialConstruccion();

                // Se copian los campos en el registro seleccionado del JTable al VO
                exchMaterial = readJtableRegister(exchMaterial);
                // Intentar actualizar en la BD el registro seleccionado del JTable en la interfaz
                try {
                    // Si la actualizacion fue exitosa, los cambios en la tabla tambien estan en BD
                    if (materialDao.updateMaterial(exchMaterial) == 1) {
                        JOptionPane.showMessageDialog(  this.materialsCrud,
                                                        "Actualización exitosa",
                                                        "Exito",
                                                        JOptionPane.INFORMATION_MESSAGE
                                                    );
                    } else {
                        // Si la actualizacion no fue exitosa, los cambios en la tabla no estan en BD (recargar ventana)
                        this.materialsCrud.callMaterialsGUI(this.materialDao.findAllMaterials());
                        JOptionPane.showMessageDialog(  this.materialsCrud,
                                                        "Registro a actualizar no encontrado en BD",
                                                        "Registro no existe en BD",
                                                        JOptionPane.ERROR_MESSAGE
                                                    );
                    }

                } catch (Exception exc) {
                    System.err.println("Error de cierre en BD en actualización a MaterialesConstruccion!" + exc.getMessage());
                }
                break;

            case "eliminarMaterial":

                // Se crea un VO para almacenar los campos de la fila seleccionada en el JTable
                MaterialConstruccion discardedMaterial = new MaterialConstruccion();
                // Se copian los campos en el registro seleccionado del JTable al VO
                discardedMaterial = readJtableRegister(discardedMaterial);

                // Intentar eliminar en le BD el registro seleccionado del JTable en la interfaz
                try {
                    Boolean success = materialDao.deleteMaterial(discardedMaterial) == 1;
                    this.materialsCrud.callMaterialsGUI(this.materialDao.findAllMaterials());
                    if (success) {
                        // Si la eliminacion fue exitosa la BD cambia y por ende el JTable debe cambiar
                        JOptionPane.showMessageDialog(  this.materialsCrud,
                                                        "Eliminación exitosa",
                                                        "Exito",
                                                        JOptionPane.INFORMATION_MESSAGE
                                                    );
                    } else {
                        // Si la eliminacion no fue exitosa el JTable puede o no cambiar, pero la BD no
                        // cambia, por ello, en este caso tambien se debe recargar el Jtable
                        JOptionPane.showMessageDialog(  this.materialsCrud,
                                                        "Registro a eliminar no encontrado en BD",
                                                        "Registro no existe en BD",
                                                        JOptionPane.ERROR_MESSAGE
                                                    );
                    }

                } catch (Exception exc) {
                    System.err.println("Error de cierre en BD en eliminación a MaterialesConstruccion!" + exc.getMessage());
                }
                break;

            default: break;

        }

    }

}
