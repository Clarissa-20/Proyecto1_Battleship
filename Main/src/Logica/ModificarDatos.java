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
public class ModificarDatos extends JFrame{
    private Battleship sistema;
    private Player playerActual;
    private JPasswordField txtviejaPass, txtnuevaPass;
    private JTextField txtviejoUser, txtnuevoUser;
    
    public ModificarDatos(Battleship sistema, Player playerActual){
        this.sistema = sistema;
        this.playerActual = playerActual;
        setTitle("Battleship - Modificar Datos");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        ClaseFondo cf = new ClaseFondo("/img/MiPerfilFondo.png");
        cf.setLayout(new BorderLayout());
        cf.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 90));
        
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK, 5), "MODIFICAR DATOS",
            TitledBorder.LEFT, TitledBorder.TOP, new Font("Bodoni Bd BT", Font.BOLD, 30), new Color(255, 204, 51)));
        
        txtviejoUser = new JTextField(5);
        txtnuevoUser = new JTextField(5);
        JButton btnCambiarUser = new JButton("CAMBIAR USERNAME");
        
        txtviejaPass = new JPasswordField(5);
        txtnuevaPass = new JPasswordField(5);
        JButton btnCambiarPass = new JButton("CAMBIAR CONTRASEÑA");
        
        btnCambiarPass.setBackground(Color.BLACK);
        btnCambiarPass.setForeground(Color.WHITE);
        btnCambiarPass.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 5));
        btnCambiarPass.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        
        btnCambiarUser.setBackground(Color.BLACK);
        btnCambiarUser.setForeground(Color.WHITE);
        btnCambiarUser.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 5));
        btnCambiarUser.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        
        panel.add(crearLabel("USERNAME ACTUAL:"));
        panel.add(txtviejoUser);
        panel.add(crearLabel("NUEVO USERNAME:"));
        panel.add(txtnuevoUser);
        panel.add(new JLabel(""));
        panel.add(btnCambiarUser);
        
        panel.add(crearLabel("CONTRASEÑA ACTUAL:"));
        panel.add(txtviejaPass);
        panel.add(crearLabel("NUEVA CONTRASEÑA:"));
        panel.add(txtnuevaPass);
        panel.add(new JLabel(""));
        panel.add(btnCambiarPass);
        
        /*btnCambiarUser.addActionListener(e -> manejarCambioUser());
        btnCambiarPass.addActionListener(e -> manejarCambioPass());*/
        
        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setFont(new Font("Bodoni Bd BT", Font.BOLD, 25));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 5));
        btnVolver.setBackground(Color.BLACK);
        btnVolver.setPreferredSize(new Dimension(250, 50));
        btnVolver.addActionListener(e -> vtnVolver());
        
        JPanel panelbtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelbtn.setOpaque(false);
        panelbtn.add(btnVolver);
        cf.add(panelbtn, BorderLayout.SOUTH);

        cf.add(panel, BorderLayout.CENTER);
        this.setContentPane(cf);
    }
    
    private JLabel crearLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        return label;
    }
    
    /*private void manejarCambioUser() {
        String viejoUser = new String(txtviejoUser.getName());
        String nuevoUser = new String(txtnuevoUser.getName());

        boolean exito = sistema.cambiarUser(playerActual.getUsername(), viejoUser, nuevoUser);

        if (exito) {
            txtviejoUser.setText("");
            txtnuevoUser.setText("");
            this.dispose();
        } 
    }
    
    private void manejarCambioPass() {
        String viejaPassword = new String(txtviejaPass.getPassword());
        String nuevaPassword = new String(txtnuevaPass.getPassword());

        boolean exito = sistema.cambiarPassword(playerActual.getUsername(), viejaPassword, nuevaPassword);

        if (exito) {
            txtviejaPass.setText("");
            txtnuevaPass.setText("");
            this.dispose();
        } 
    }*/
    
    private void vtnVolver(){
        MiPerfil mp = new MiPerfil(sistema, playerActual);
        mp.setVisible(true);
        this.dispose();
    }
    
}
