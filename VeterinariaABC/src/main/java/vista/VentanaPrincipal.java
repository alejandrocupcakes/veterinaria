package vista;

import controlador.Coordinador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;

public class VentanaPrincipal extends JFrame implements ActionListener {
    Coordinador miCoordinador;
    JPanel miPanel; 
    JLabel lblTitulo;
    JLabel lblFondo;
    JButton btnGestionarPersonas;
    JButton btnGestionarMascotas;
    
    public VentanaPrincipal () {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 470);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        iniciarComponentes();
        setResizable(false);
    }
    
private void iniciarComponentes() {
    miPanel = new JPanel();
    lblTitulo =  new JLabel();
    lblFondo = new JLabel();
    btnGestionarPersonas = new JButton();
    btnGestionarMascotas = new JButton();
    
    miPanel.setLayout(null);
    miPanel.setBounds(0, 0, 600, 470);
    getContentPane().add(miPanel);
    
    lblTitulo.setText("SISTEMA VETERINARIA ABC");
    lblTitulo.setBounds(0, 16, 600, 25);
    lblTitulo.setFont(new java.awt.Font("Arial", 1, 24));
    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    miPanel.add(lblTitulo);
    
    btnGestionarPersonas.setText("Gestionar Personas");
    btnGestionarPersonas.setBounds(25, 360, 250, 64);
    btnGestionarPersonas.addActionListener(this);
    miPanel.add(btnGestionarPersonas);
    
    btnGestionarMascotas.setText("Gestionar Mascotas");
    btnGestionarMascotas.setBounds(310, 360, 250, 64);
    btnGestionarMascotas.addActionListener(this);
    miPanel.add(btnGestionarMascotas);
    
    lblFondo.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondo.png")));
    lblFondo.setBounds(0, 56, 600, 400);
    miPanel.add(lblFondo);
}
    
    public void setCoordinador (Coordinador coordinador) {
        this.miCoordinador = coordinador;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGestionarPersonas) {
            miCoordinador.mostrarVentanaGestionarPersonas();
        } else if (e.getSource() == btnGestionarMascotas) {
            miCoordinador.mostrarVentanaGestionarMascotas();
        }
    }
}
