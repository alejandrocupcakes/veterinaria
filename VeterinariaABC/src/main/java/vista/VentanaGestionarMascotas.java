package vista;

import controlador.Coordinador;
import modelo.vo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class VentanaGestionarMascotas extends JFrame implements ActionListener {  
    Coordinador miCoordinador;
    JPanel miPanel; 
    JLabel lblTitulo;
    JLabel lblDuenoID;
    JLabel lblRaza;
    JLabel lblNombre;
    JLabel lblSexo;
    JButton btnRegistrar;
    JButton btnConsultar;
    JButton btnActualizar;
    JButton btnEliminar;
    JButton btnConsultarLista;
    JTextField txtDuenoID;
    JTextField txtRaza;
    JTextField txtNombre;
    JTextField txtSexo;
    JSeparator separador;
    JTextArea listaTxt;
    JScrollPane listaScroll;
    
    public VentanaGestionarMascotas () {
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
        lblDuenoID = new JLabel();
        lblRaza = new JLabel();
        lblNombre = new JLabel();
        lblSexo = new JLabel();
        btnRegistrar = new JButton();
        btnConsultar = new JButton();
        btnActualizar = new JButton();
        btnEliminar = new JButton();
        btnConsultarLista = new JButton();
        txtDuenoID = new JTextField();
        txtRaza = new JTextField();
        txtNombre = new JTextField();
        txtSexo = new JTextField();
        separador = new JSeparator();
        listaTxt = new JTextArea();
        listaScroll = new JScrollPane(listaTxt);
        
        miPanel.setLayout(null);
        miPanel.setBounds(0, 0, 500, 450);
        getContentPane().add(miPanel);
        
        lblTitulo.setText("GESTIONAR MASCOTAS");
        lblTitulo.setBounds(0, 16, 450, 25);
        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        miPanel.add(lblTitulo);
        
        lblDuenoID.setText("id dueño:");
        lblDuenoID.setBounds(24, 54, 70, 25);
        miPanel.add(lblDuenoID);
        
        txtDuenoID.setBounds(80, 53, 110, 27);
        miPanel.add(txtDuenoID);
        
        lblRaza.setText("Raza:");
        lblRaza.setBounds(250, 54, 60, 25);
        miPanel.add(lblRaza);
        
        lblSexo.setText("Sexo:");
        lblSexo.setBounds(250, 90, 60, 25);
        miPanel.add(lblSexo);
        
        txtRaza.setBounds(300, 53, 110, 27);
        miPanel.add(txtRaza);
        
        txtNombre.setBounds(80, 90, 110, 27);
        miPanel.add(txtNombre);

        txtSexo.setBounds(300, 90, 110, 27);
        miPanel.add(txtSexo);
        
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
    
    public void listarMascotas(ArrayList<MascotaVo> lista) {
        if (lista == null) {
            listaTxt.setText("No hay mascotas registradas.");
        } else {
            String listaRes = "";
            for (int i = 0; i < lista.size(); i++) {
                listaRes += "("+lista.get(i).getPropietario()+") "+lista.get(i) + "\n";
            }
            listaTxt.setText(listaRes);
        }
    }
    
    public void mostrar (String idPropietario, String nombre) {
        MascotaVo mascota = this.miCoordinador.consultarMascota(idPropietario, nombre);
        if (mascota != null) {
            String propietario = "No tiene dueño";
            PersonaVo propietarioData = this.miCoordinador.consultarPersona(idPropietario, false);
            if (propietarioData != null) {
                propietario = propietarioData.getNombre();
            }
            txtRaza.setText(mascota.getRaza());
            txtNombre.setText(mascota.getNombre());
            txtSexo.setText(mascota.getSexo());
            listaTxt.setText("("+propietario+") "+mascota.toString());
        } else {
            listaTxt.setText("La mascota no está registrada.");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            if (this.miCoordinador.registrarMascota(txtDuenoID.getText(), txtNombre.getText(), txtRaza.getText(), txtSexo.getText()))
                mostrar(txtDuenoID.getText(), txtNombre.getText());
        } else if (e.getSource() == btnConsultarLista) {
            this.miCoordinador.listarMascotas();
        } else if (e.getSource() == btnConsultar) {
            mostrar(txtDuenoID.getText(), txtNombre.getText());
        } else if (e.getSource() == btnEliminar) {
            if (this.miCoordinador.eliminarMascota(txtDuenoID.getText(), txtNombre.getText())) {
                listaTxt.setText("Se ha quitado la mascota del registro");
            } else {
                listaTxt.setText("La mascota no está registrada.");
            }
        } else if (e.getSource() == btnActualizar) {
            this.miCoordinador.editarMascota(txtDuenoID.getText(), txtNombre.getText(), txtRaza.getText(), txtSexo.getText());
            mostrar(txtDuenoID.getText(), txtNombre.getText());
        }
    }
}
