/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author HP
 */
public class EliminarCuenta extends JFrame {

    private Player playerActual; 
    private Battleship sistema;
    private JPasswordField txtConfirmarPassword;

    public EliminarCuenta(Player playerActual, Battleship sistema) {
        this.playerActual = playerActual;
        this.sistema = sistema;

        setTitle("Battleship - Eliminar Cuenta - " + playerActual.getUsername());
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        ClaseFondo cp = new ClaseFondo("/img/MiPerfilFondo.png");
        cp.setLayout(new BorderLayout());
        cp.setBorder(BorderFactory.createEmptyBorder(90, 90, 90, 90));

        JPanel panel = new JPanel(new GridLayout(2, 2, 20, 50));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 5), "ELIMINAR CUENTA",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Bodoni Bd BT", Font.BOLD, 30), new Color(255, 204, 51)));

        txtConfirmarPassword = new JPasswordField(5);
        JButton btnEliminar = new JButton("ELIMINAR CUENTA");
        btnEliminar.setBackground(Color.BLACK);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        btnEliminar.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 5));

        panel.add(crearLabel("CONFIRMAR CONTRASEÑA:"));
        panel.add(txtConfirmarPassword);
        panel.add(new JLabel(""));
        panel.add(btnEliminar);

        btnEliminar.addActionListener(e -> manejarEliminarCuenta());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 5));
        btnVolver.setBackground(Color.BLACK);
        btnVolver.setPreferredSize(new Dimension(250, 50));
        btnVolver.addActionListener(e -> vtnVolver());

        JPanel panelbtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelbtn.setOpaque(false);
        panelbtn.add(btnVolver);
        cp.add(panelbtn, BorderLayout.SOUTH);

        cp.add(panel, BorderLayout.CENTER);
        this.setContentPane(cp);
    }

    private JLabel crearLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        return label;
    }
    
    private void manejarEliminarCuenta() {
        String confirmarPassword = new String(txtConfirmarPassword.getPassword());

        if (confirmarPassword.isEmpty()) {
            DecoMensajes.mostrarMensaje(this, "Por favor, ingrese su contraseña.", "BATTLESHIP");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar su cuenta? Esta acción es irreversible.",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmar == JOptionPane.YES_OPTION) {
            boolean exito = sistema.eliminarCuenta(playerActual.getUsername(), confirmarPassword);

            if (exito) {
                DecoMensajes.mostrarMensaje(this, "Cuenta eliminada con éxito.", "BATTLESHIP");
                MenuInicio mi = new MenuInicio(sistema);
                mi.setVisible(true);
                this.dispose();
            } else {
                DecoMensajes.mostrarMensaje(this, "Contraseña incorrecta. No se pudo eliminar la cuenta.", "BATTLESHIP");
                txtConfirmarPassword.setText("");
            }
        }
    }

    private void vtnVolver() {
        MiPerfil mp = new MiPerfil(playerActual, sistema);
        mp.setVisible(true);
        this.dispose();
    }

}
