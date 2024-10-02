package modelo.vo;

public class MascotaVo extends AnimalVo {
    private String nombre;
    private String propietario;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    
    @Override
    public String toString() {
        return "nombre=" + nombre + ", raza=" + this.getRaza() + ", genero=" + this.getSexo();
    }
}
