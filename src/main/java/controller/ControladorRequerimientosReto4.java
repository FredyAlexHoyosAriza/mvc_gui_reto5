package controller;

import model.vo.LideresMayorSalario;
import model.vo.LideresProyectosEmblematicos;
import model.vo.MaterialRankeadoImportado;

import model.dao.LideresMayorSalarioDao;
import model.dao.LideresProyectosEmblematicosDao;
import model.dao.MaterialRankeadoImportadoDao;

// import view.VistaRequerimientosReto4;

import java.sql.SQLException;
import java.util.ArrayList;

import view.Requirement1InGUI;
import view.Requirement3InGUI;
import view.Requirement4InGUI;

// Clases para GUI
import view.RequirementsMenuInGUI;
import javax.swing.JButton;
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
    private RequirementsMenuInGUI requirementsMenuInGUI;
    private Requirement1InGUI requerimiento1gui;
    private Requirement3InGUI requerimiento3gui;
    private Requirement4InGUI requerimiento4gui;

    public ControladorRequerimientosReto4(){        
        // Aqui se instancian todos los DAO que se utilicen
        this.lideresMayorSalarioDao = new LideresMayorSalarioDao();
        this.lideresProyectosEmblematicosDao = new LideresProyectosEmblematicosDao();
        this.materialRankeadoImportadoDao = new MaterialRankeadoImportadoDao();
        this.requirementsMenuInGUI = new RequirementsMenuInGUI();
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

    public void start() {
        // Ahora el controlador tendra el inicio de la aplicacion
        this.requirementsMenuInGUI.startGUI();
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
                    this.requerimiento1gui = new Requirement1InGUI(queryHighestSalaryLeaders(), this); // Este controlador

                } catch (Exception exc) {
                    System.err.println("Error de cierre en BD durante requerimiento 1!" + exc.getMessage());
                }
                // VistaRequerimientosReto4.requerimiento1();
                break;

            case "requerimiento3":
                try {
                    this.requerimiento3gui = new Requirement3InGUI(queryFlagshipProjectLeaders(), this); // Este controlador

                } catch (Exception exc) {
                    System.err.println("Error de cierre en BD durante requerimiento 3!" + exc.getMessage());
                }
                // VistaRequerimientosReto4.requerimiento3();
                break;

            case "requerimiento4":
                try {
                    this.requerimiento4gui = new Requirement4InGUI(queryImportedMaterialRanking(), this); // Este controlador

                } catch (Exception exc) {
                    System.err.println("Error de cierre en BD durante requerimiento 4!" + exc.getMessage());
                }
                // VistaRequerimientosReto4.requerimiento4();
                break;
        
            default:
                
                break;
        }
    }

    

}
