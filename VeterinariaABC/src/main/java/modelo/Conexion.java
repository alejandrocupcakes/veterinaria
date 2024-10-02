package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
    Connection conn=null;
 
    public Conexion(){
        String nombreBd="veterinariaABC";
    String usuario="root";
        String port = "3306";
    String password="";
    String url="jdbc:mysql://localhost:"+port+"/"+nombreBd+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
      
            conn=DriverManager.getConnection(url,usuario,password);
        }
        catch (ClassNotFoundException e) {
            System.out.println("ocurre una ClassNotFoundException : "+e.getMessage());
            System.exit(0);
        } catch (SQLException e) {
            System.out.println("ocurre una SQLException: "+e.getMessage());
            System.out.println("Verifique que Mysql est� encendido...");
            JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    public Connection getConnection(){
        return conn;
    }
    public void desconectar(){
        conn=null;
    }
}