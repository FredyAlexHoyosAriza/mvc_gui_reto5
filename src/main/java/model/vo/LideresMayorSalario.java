package model.vo;

public class LideresMayorSalario {

    // Attributes
    private Integer idLider; // los nombres pueden ser diferentes que en la BD
    private String nombre;
    private String primerApellido;

    // Constructor
    public LideresMayorSalario() {
        // La mecanica que se sigue, es que el constructor se instancie vacio
        // se hace la conexion a la base de datos y si es exitosa y no esta vacia
        // se carga todo, pero no aqui sino despues con los setters
    }

    // Getters y Setters

    public Integer getIdLider() {
        return idLider;
    }

    public void setIdLider(Integer idLider) {
        this.idLider = idLider;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }   

}
