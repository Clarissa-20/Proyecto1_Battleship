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
    private final Color COLOR_PRIMARIO = new Color(150, 0, 0);
    private final Color COLOR_TEXTO = Color.WHITE;
    private final Font FONT_LABEL = new Font("Bodoni Bd BT", Font.BOLD, 20);
    
    public ModificarDatos(Battleship sistema, Player playerActual){
        this.sistema = sistema;
        this.playerActual = playerActual;
        setTitle("Battleship - Modificar Datos");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        ClaseFondo cf = new ClaseFondo("/img/.png");
        cf.setLayout(new BorderLayout());
        cf.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_TEXTO, 5), "MODIFICAR DATOS",
            TitledBorder.LEFT, TitledBorder.TOP, FONT_LABEL.deriveFont(Font.BOLD, 25), COLOR_TEXTO));
        
        txtviejoUser = new JTextField(5);
        txtnuevoUser = new JTextField(5);
        JButton btnCambiarUser = new JButton("CAMBIAR USERNAME");
        
        txtviejaPass = new JPasswordField(5);
        txtnuevaPass = new JPasswordField(5);
        JButton btnCambiarPass = new JButton("CAMBIAR CONTRASEÑA");
        
        btnCambiarPass.setBackground(COLOR_PRIMARIO);
        btnCambiarPass.setForeground(COLOR_TEXTO);
        btnCambiarPass.setFont(FONT_LABEL.deriveFont(Font.BOLD));
        
        btnCambiarUser.setBackground(COLOR_PRIMARIO);
        btnCambiarUser.setForeground(COLOR_TEXTO);
        btnCambiarUser.setFont(FONT_LABEL.deriveFont(Font.BOLD));
        
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
        btnVolver.setFont(FONT_LABEL);
        btnVolver.setForeground(COLOR_TEXTO);
        btnVolver.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        btnVolver.setBackground(new Color(153, 0, 0));
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
        label.setFont(FONT_LABEL);
        label.setForeground(COLOR_TEXTO);
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
