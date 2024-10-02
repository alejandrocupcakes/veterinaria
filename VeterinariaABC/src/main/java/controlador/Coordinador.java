package controlador;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.procesos.*;
import modelo.vo.*;
import vista.*;

public class Coordinador {
    private VentanaPrincipal miVentanaPrincipal;
    private VentanaGestionarMascotas miVentanaGestionarMascotas;
    private VentanaGestionarPersonas miVentanaGestionarPersonas;
    private ProcesosMascota misProcesosMascota;
    private ProcesosPersona misProcesosPersona;
    
    public void setProcesosMascota(ProcesosMascota procesosMascota) {
        this.misProcesosMascota = procesosMascota;
    }
    
    public void setProcesosPersona(ProcesosPersona procesosPersona) {
        this.misProcesosPersona = procesosPersona;
    }
    
    public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
        this.miVentanaPrincipal = ventanaPrincipal;
    }
    
    public void setVentanaGestionarMascotas(VentanaGestionarMascotas ventanaGestionarMascotas) {
        this.miVentanaGestionarMascotas = ventanaGestionarMascotas;
    }
    
    public void setVentanaGestionarPersonas(VentanaGestionarPersonas ventanaGestionarPersonas) {
        this.miVentanaGestionarPersonas = ventanaGestionarPersonas;
    }
    
    public void mostrarVentanaGestionarMascotas() {
        this.miVentanaGestionarMascotas.setVisible(true);
    }
    
    public void mostrarVentanaGestionarPersonas() {
        this.miVentanaGestionarPersonas.setVisible(true);
    }
    
    public boolean registarPersona(String documento, String telefono, String nombre) {
        return this.misProcesosPersona.registrar(documento, telefono, nombre);
    }
    
    public boolean registrarMascota(String propietario, String nombre, String raza, String sexo) {
        return this.misProcesosMascota.registrar(propietario, nombre, raza, sexo);
    }
    
    public void editarPersona(String documento, String telefono, String nombre) {
        this.misProcesosPersona.actualizar(documento, nombre, telefono);
        this.miVentanaGestionarPersonas.mostrar(documento);
    }
    
    public void editarMascota(String propietario, String nombre, String raza, String sexo) {
        this.misProcesosMascota.actualizar(propietario, nombre, raza, sexo);
    }
    
    public boolean eliminarPersona(String documento) {
        return this.misProcesosPersona.eliminarPersona(documento);
    }
    
    public boolean eliminarMascota (String idPropietario, String nombre) {
        return this.misProcesosMascota.eliminarMascota(idPropietario, nombre);
    } 
    
    public void listarPersonas() {
        ArrayList<PersonaVo> lista = this.misProcesosPersona.listarPersonas();
        this.miVentanaGestionarPersonas.listarPersonas(lista);
    }
    
    public void listarMascotas() {
        ArrayList<MascotaVo> lista = this.misProcesosMascota.listarMascotas();
        this.miVentanaGestionarMascotas.listarMascotas(lista);
    }
    
    public PersonaVo consultarPersona(String documento) {
        return this.misProcesosPersona.consultar(documento);
    }
    
    public PersonaVo consultarPersona(String documento, boolean mensaje) {
        return this.misProcesosPersona.consultar(documento, mensaje);
    }
    
    public MascotaVo consultarMascota (String idPropietario, String nombre) {
        return this.misProcesosMascota.consultar(idPropietario, nombre);
    }
}
