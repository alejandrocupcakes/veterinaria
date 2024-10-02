package modelo.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Conexion;
import modelo.vo.PersonaVo;

public class PersonaDao {
    public boolean registrar (String documento, String telefono, String nombre) {
        Conexion miConexion = new Conexion();
        try {
            Statement consulta = miConexion.getConnection().createStatement();
            consulta.executeUpdate("INSERT INTO personas (documento,telefono,nombre) VALUES (\""+documento+"\",\""+telefono+"\",\""+nombre+"\")");
            JOptionPane.showMessageDialog(null, "Se ha registrado a "+nombre);
            consulta.close();
            miConexion.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo registrar a la persona", "Error", JOptionPane.ERROR_MESSAGE);
            miConexion.desconectar();
            return false;
        }
    }
    
    public boolean actualizar (String documento, String nombre, String telefono) {
        Conexion miConexion = new Conexion();
        try {
            Statement consulta = miConexion.getConnection().createStatement();
            consulta.executeUpdate("UPDATE personas SET nombre=\""+nombre+"\", telefono=\""+telefono+"\" WHERE documento=\""+documento+"\"");
            JOptionPane.showMessageDialog(null, "Se ha actualizado a "+nombre);
            consulta.close();
            miConexion.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la persona", "Error", JOptionPane.ERROR_MESSAGE);
            miConexion.desconectar();
            return false;
        }
    }
    
    public ArrayList<PersonaVo> listar() {
        ArrayList<PersonaVo> lista = new ArrayList<PersonaVo>();
        Conexion miConexion = new Conexion();
        try {
            Statement consulta = miConexion.getConnection().createStatement();
            PreparedStatement consultar = miConexion.getConnection().prepareStatement("SELECT * FROM personas");
            ResultSet res = consultar.executeQuery();
            boolean hay = false;
            while(res.next()){
                hay = true;
                PersonaVo persona = new PersonaVo();
                persona.setDocumento(res.getString("documento"));
                persona.setNombre(res.getString("nombre"));
                persona.setTelefono(res.getString("telefono"));
                lista.add(persona);
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
    
    public PersonaVo consultar(String documento) {
        PersonaVo personaConsultada = new PersonaVo();
        Conexion miConexion = new Conexion();
        try {
            Statement consulta = miConexion.getConnection().createStatement();
            PreparedStatement consultar = miConexion.getConnection().prepareStatement("SELECT * FROM personas WHERE documento=\""+documento+"\"");
            ResultSet res = consultar.executeQuery();
            boolean existe = false;
            while(res.next()) {
                existe = true;
                personaConsultada.setDocumento(res.getString("documento"));
                personaConsultada.setNombre(res.getString("nombre"));
                personaConsultada.setTelefono(res.getString("telefono"));
            }
            consulta.close();
            miConexion.desconectar();
            if (existe)
                return personaConsultada;
            else
                return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            miConexion.desconectar();
            return null;
        }
    }
    
    public boolean eliminar (String documento) {
        Conexion miConexion = new Conexion();
        try {
            Statement consulta = miConexion.getConnection().createStatement();
            boolean resultado = consulta.executeUpdate("DELETE FROM personas WHERE documento=\""+documento+"\"") != 0;
            consulta.close();
            miConexion.desconectar();
            return resultado;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            miConexion.desconectar();
            return false;
        }
    }
}
