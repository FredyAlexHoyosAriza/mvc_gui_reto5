package model.vo;

public class LideresProyectosEmblematicos {

    // Attributes
    private Integer idLider; // los nombres pueden ser diferentes que en la BD
    private Integer idProyecto; 
    private Integer idTipo;
 
    
    // Constructor
    public LideresProyectosEmblematicos() {}

    // Getters and Setters
    
    public Integer getIdLider() {
        return idLider;
    }

    public void setIdLider(Integer idLider) {
        this.idLider = idLider;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

}
