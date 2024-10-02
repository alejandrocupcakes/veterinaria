package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import controlador.Coordinador;

import modelo.Conexion;
import modelo.vo.MascotaVo;
import modelo.vo.PersonaVo;

public class MascotaDao {
    public boolean registrar (String propietario, String nombre, String raza, String sexo) {
        Conexion miConexion = new Conexion();
        try {
            Statement consulta = miConexion.getConnection().createStatement();
            consulta.executeUpdate("INSERT INTO mascotas (propietario, nombre, raza, sexo) VALUES (\""+propietario+"\", \""+nombre+"\", \""+raza+"\", \""+sexo+"\")");
            JOptionPane.showMessageDialog(null, "Se ha registrado a "+nombre);
            consulta.close();
            miConexion.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo registrar a la mascota", "Error", JOptionPane.ERROR_MESSAGE);
            miConexion.desconectar();
            return false;
        }
    }
    
    public ArrayList<MascotaVo> listar(Coordinador coordinador) {
        ArrayList<MascotaVo> lista = new ArrayList<MascotaVo>();
        Conexion miConexion = new Conexion();
        try {
            Statement consulta = miConexion.getConnection().createStatement();
            PreparedStatement consultar = miConexion.getConnection().prepareStatement("SELECT * FROM mascotas");
            ResultSet res = consultar.executeQuery();
            boolean hay = false;
            while(res.next()){
                hay = true;
                String propietario = "No tiene dueño";
                PersonaVo propietarioData = coordinador.consultarPersona(res.getString("propietario"), false);
                if (propietarioData != null) {
                    propietario = propietarioData.getNombre();
                }
                MascotaVo mascota = new MascotaVo();
                mascota.setPropietario(propietario);
                mascota.setNombre(res.getString("nombre"));
                mascota.setRaza(res.getString("raza"));
                mascota.setSexo(res.getString("sexo"));
                lista.add(mascota);
            }
            consulta.close();
            miConexion.desconectar();
            if (hay)
                return lista;
            else 
                return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            miConexion.desconectar();
            return null;
        }
    }
    
    public MascotaVo consultar (String idPropietario, String nombre) {
        MascotaVo mascotaConsultada = new MascotaVo();
        Conexion miConexion = new Conexion();
        try {
            Statement consulta = miConexion.getConnection().createStatement();
            PreparedStatement consultar = miConexion.getConnection().prepareStatement("SELECT * FROM mascotas WHERE propietario=\""+idPropietario+"\" AND nombre=\""+nombre+"\"");
            ResultSet res = consultar.executeQuery();
            boolean existe = false;
            while(res.next()) {
                existe = true;
                mascotaConsultada.setNombre(res.getString("nombre"));
                mascotaConsultada.setRaza(res.getString("raza"));
                mascotaConsultada.setSexo(res.getString("sexo"));
            }
            consulta.close();
            miConexion.desconectar();
            if (existe)
                return mascotaConsultada;
            else
                return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            miConexion.desconectar();
            return null;
        }
    }
    
    public boolean eliminar (String idPropietario, String nombre) {
        Conexion miConexion = new Conexion();
        try {
            Statement consulta = miConexion.getConnection().createStatement();
            boolean resultado = consulta.executeUpdate("DELETE FROM mascotas WHERE propietario=\""+idPropietario+"\" AND nombre=\""+nombre+"\"") != 0;
            consulta.close();
            miConexion.desconectar();
            return resultado;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            miConexion.desconectar();
            return false;
        }
    }
    
    public void actualizar(String propietario, String nombre, String raza, String sexo) {
        Conexion miConexion = new Conexion();
        try {
            Statement consulta = miConexion.getConnection().createStatement();
            consulta.executeUpdate("UPDATE mascotas SET nombre=\""+nombre+"\", propietario=\""+propietario+"\", raza=\""+raza+"\", sexo=\""+sexo+"\" WHERE propietario=\""+propietario+"\" AND nombre=\""+nombre+"\"");
            JOptionPane.showMessageDialog(null, "Se ha actualizado a "+nombre);
            consulta.close();
            miConexion.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la mascota", "Error", JOptionPane.ERROR_MESSAGE);
            miConexion.desconectar();
        }
    }
}
