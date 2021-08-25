package model.vo;

public class MaterialConstruccion {

    // Attributes
    private Integer IdMaterialConstruccion;
    private String nombreMaterial;
    private String importado;
    private Integer precioUnidad;


    // Constructor    
    public MaterialConstruccion() {}

   // Getters and Setters

    public Integer getIdMaterialConstruccion() {
        return IdMaterialConstruccion;
    }


    public void setIdMaterialConstruccion(Integer idMaterialConstruccion) {
        IdMaterialConstruccion = idMaterialConstruccion;
    }


    public String getNombreMaterial() {
        return nombreMaterial;
    }


    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }


    public String getImportado() {
        return importado;
    }


    public void setImportado(String importado) {
        this.importado = importado;
    }


    public Integer getPrecioUnidad() {
        return precioUnidad;
    }


    public void setPrecioUnidad(Integer precioUnidad) {
        this.precioUnidad = precioUnidad;
    }
    
}
