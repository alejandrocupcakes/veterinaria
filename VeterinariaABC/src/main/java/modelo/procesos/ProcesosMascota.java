package modelo.procesos;

import javax.swing.JOptionPane;

import java.util.ArrayList;

import modelo.dao.*;
import modelo.vo.MascotaVo;

public class ProcesosMascota extends ProcesosGenerales {
    public boolean registrar (String propietario, String nombre, String raza, String sexo) {
        if (nombre.length() < 1){
            JOptionPane.showMessageDialog(null, "Se debe ingresar el nombre del animal", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (raza.length() < 1){
            JOptionPane.showMessageDialog(null, "Se debe ingresar la raza del animal", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (sexo.length() < 1){
            JOptionPane.showMessageDialog(null, "Se debe ingresar el genero del animal", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        MascotaDao mascotaDao = new MascotaDao();
        return mascotaDao.registrar(propietario, nombre, raza, sexo);
    }
    
    public MascotaVo consultar (String idPropietario, String nombre) {
        if (nombre.length() < 1) {
            JOptionPane.showMessageDialog(null, "Se debe ingresar el nombre de la mascota", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        MascotaDao mascotaDao = new MascotaDao();
        return mascotaDao.consultar(idPropietario, nombre);
    }
    
    public ArrayList<MascotaVo> listarMascotas() {
        MascotaDao mascotaDao = new MascotaDao();
        return mascotaDao.listar(this.miCoordinador);
    }
    
    public boolean eliminarMascota (String idPropietario, String nombre) {
        if (nombre.length() < 1) {
            return false;
        }
        MascotaDao mascotaDao = new MascotaDao();
        return mascotaDao.eliminar(idPropietario, nombre);
    }
    
    public void actualizar(String propietario, String nombre, String raza, String sexo) {
        if (nombre.length() < 1){
            JOptionPane.showMessageDialog(null, "Se debe ingresar el nombre del animal", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (raza.length() < 1){
            JOptionPane.showMessageDialog(null, "Se debe ingresar la raza del animal", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (sexo.length() < 1){
            JOptionPane.showMessageDialog(null, "Se debe ingresar el genero del animal", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        MascotaDao mascotaDao = new MascotaDao();
        mascotaDao.actualizar(propietario, nombre, raza, sexo);
    }
}
