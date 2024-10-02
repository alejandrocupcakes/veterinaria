package vista;

import controlador.Coordinador;
import modelo.vo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class VentanaGestionarPersonas extends JFrame implements ActionListener {
    Coordinador miCoordinador;
    JPanel miPanel; 
    JLabel lblTitulo;
    JLabel lblDocumento;
    JLabel lblTelefono;
    JLabel lblNombre;
    JButton btnRegistrar;
    JButton btnConsultar;
    JButton btnActualizar;
    JButton btnEliminar;
    JButton btnConsultarLista;
    JTextField txtDocumento;
    JTextField txtTelefono;
    JTextField txtNombre;
    JSeparator separador;
    JTextArea listaTxt;
    JScrollPane listaScroll;
    
    public VentanaGestionarPersonas() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(450, 450);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        iniciarComponentes();
        setResizable(false);
    }

    private void iniciarComponentes () {
        miPanel = new JPanel();
        lblTitulo = new JLabel();
        lblDocumento = new JLabel();
        lblTelefono = new JLabel();
        lblNombre = new JLabel();
        btnRegistrar = new JButton();
        btnConsultar = new JButton();
        btnActualizar = new JButton();
        btnEliminar = new JButton();
        btnConsultarLista = new JButton();
        txtDocumento = new JTextField();
        txtTelefono = new JTextField();
        txtNombre = new JTextField();
        separador = new JSeparator();
        listaTxt = new JTextArea();
        listaScroll = new JScrollPane(listaTxt);
        
        miPanel.setLayout(null);
        miPanel.setBounds(0, 0, 500, 450);
        getContentPane().add(miPanel);
        
        lblTitulo.setText("GESTIONAR PERSONAS");
        lblTitulo.setBounds(0, 16, 450, 25);
        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        miPanel.add(lblTitulo);
        
        lblDocumento.setText("Documento:");
        lblDocumento.setBounds(8, 54, 70, 25);
        miPanel.add(lblDocumento);
        
        txtDocumento.setBounds(80, 53, 110, 27);
        miPanel.add(txtDocumento);
        
        lblTelefono.setText("Telefono:");
        lblTelefono.setBounds(230, 54, 60, 25);
        miPanel.add(lblTelefono);
        
        txtTelefono.setBounds(300, 53, 110, 27);
        miPanel.add(txtTelefono);
        
        txtNombre.setBounds(80, 90, 330, 27);
        miPanel.add(txtNombre);
        
        lblNombre.setText("Nombre:");
        lblNombre.setBounds(28, 88, 70, 25);
        miPanel.add(lblNombre);
        
        separador.setOrientation(SwingConstants.HORIZONTAL);
        separador.setBounds(36, 140, 370, 25);
        miPanel.add(separador);
        
        btnRegistrar.setText("Registrar");
        btnRegistrar.setBounds(36, 150, 180, 35);
        btnRegistrar.addActionListener(this);
        miPanel.add(btnRegistrar);
        
        btnConsultar.setText("Consultar");
        btnConsultar.setBounds(225, 150, 180, 35);
        btnConsultar.addActionListener(this);
        miPanel.add(btnConsultar);
        
        btnActualizar.setText("Actualizar");
        btnActualizar.setBounds(36, 190, 180, 35);
        btnActualizar.addActionListener(this);
        miPanel.add(btnActualizar);
        
        btnEliminar.setText("Eliminar");
        btnEliminar.setBounds(225, 190, 180, 35);
        btnEliminar.addActionListener(this);
        miPanel.add(btnEliminar);
        
        btnConsultarLista.setText("Consultar lista");
        btnConsultarLista.setBounds(36, 232, 370, 35);
        btnConsultarLista.addActionListener(this);
        miPanel.add(btnConsultarLista);
        
        listaScroll.setBounds(36, 280, 370, 120);
        miPanel.add(listaScroll);
        
    }
    
    public void setCoordinador (Coordinador coordinador) {
        this.miCoordinador = coordinador;
    }
    
    public void listarPersonas(ArrayList<PersonaVo> lista) {
        if (lista == null) {
            listaTxt.setText("No hay personas registradas.");
        } else {
            String listaRes = "";
            for (int i = 0; i < lista.size(); i++) {
                listaRes += lista.get(i) + "\n";
            }
            listaTxt.setText(listaRes);
        }
    }
    
    public void mostrar (String codigo) {
        PersonaVo persona = this.miCoordinador.consultarPersona(codigo);
        if (persona == null) {
            listaTxt.setText("La persona no está registrada.");
        } else {
            listaTxt.setText(persona.toString());
            txtDocumento.setText(persona.getDocumento());
            txtTelefono.setText(persona.getTelefono());
            txtNombre.setText(persona.getNombre());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            if (this.miCoordinador.registarPersona(txtDocumento.getText(), txtTelefono.getText(), txtNombre.getText()))
                mostrar(txtDocumento.getText());
        } else if (e.getSource() == btnEliminar) {
            if (this.miCoordinador.eliminarPersona(txtDocumento.getText())) {
                listaTxt.setText("Se ha quitado la persona del registro");
            } else {
                listaTxt.setText("La persona no está registrada.");
            }
        } else if (e.getSource() == btnConsultarLista) {
            this.miCoordinador.listarPersonas();
        } else if (e.getSource() == btnConsultar) {
            mostrar(txtDocumento.getText());
        } else if (e.getSource() == btnActualizar) {
            this.miCoordinador.editarPersona(txtDocumento.getText(), txtTelefono.getText(), txtNombre.getText());
        }
    }
}