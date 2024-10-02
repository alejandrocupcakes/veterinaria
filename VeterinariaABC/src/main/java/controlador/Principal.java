package controlador;

import vista.*;
import modelo.vo.*;
import modelo.dao.*;
import modelo.*;
import modelo.procesos.*;
import controlador.Coordinador;

public class Principal {
    public static void main(String[] args) {
        VentanaPrincipal miVentanaPrincipal = new VentanaPrincipal();
        VentanaGestionarMascotas miVentanaGestionarMascotas = new VentanaGestionarMascotas();
        VentanaGestionarPersonas miVentanaGestionarPersonas = new VentanaGestionarPersonas();
        
        Coordinador miCoordinador = new Coordinador();
        
        ProcesosMascota misProcesosMascota = new ProcesosMascota();
        ProcesosPersona misProcesosPersona = new ProcesosPersona();
        
        miVentanaPrincipal.setCoordinador(miCoordinador);
        miVentanaGestionarMascotas.setCoordinador(miCoordinador);
        miVentanaGestionarPersonas.setCoordinador(miCoordinador);
        misProcesosMascota.setCoordinador(miCoordinador);
        misProcesosPersona.setCoordinador(miCoordinador);
        
        miCoordinador.setVentanaPrincipal(miVentanaPrincipal);
        miCoordinador.setVentanaGestionarMascotas(miVentanaGestionarMascotas);
        miCoordinador.setVentanaGestionarPersonas(miVentanaGestionarPersonas);
        miCoordinador.setProcesosMascota(misProcesosMascota);
        miCoordinador.setProcesosPersona(misProcesosPersona);
        
        miVentanaPrincipal.setVisible(true);
    }
}
