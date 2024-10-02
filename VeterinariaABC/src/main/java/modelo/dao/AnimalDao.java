package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import javax.swing.JOptionPane;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
public class AnimalDao {
    public String registrarAnimal(AnimalVO miAnimal)throws SQLException{
    	String resultado = "";
    	if(!conectar().equals("conectado")) {
    		return "error";
    	}
        return null;
    }

    private Object conectar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class AnimalVO {

        public AnimalVO() {
        }
    }
}
