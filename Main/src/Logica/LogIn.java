/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author HP
 */
public class LogIn extends JFrame{
    private Battleship sistema;
    
    public LogIn(Battleship sistema){
        this.sistema = sistema;
        setTitle("BattleShip - LogIn");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20));
        
        ClaseFondo cf = new ClaseFondo("/img/LogInFondo.png");
        cf.setLayout(new BorderLayout(20, 20));
        
        JLabel titulo = new JLabel("INICIO SESIÓN", SwingConstants.CENTER);
        titulo.setFont(new Font("Bodoni Bd BT", Font.BOLD, 50));
        titulo.setForeground(new Color(255, 204, 51));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        cf.add(titulo, BorderLayout.NORTH);
        
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        
        JTextField txtUser = new JTextField(20);
        txtUser.setBackground(Color.BLACK);
        txtUser.setForeground(Color.WHITE);
        txtUser.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        
        JPasswordField txtPass = new JPasswordField(20);
        txtPass.setBackground(Color.BLACK);
        txtPass.setForeground(Color.WHITE);
        txtPass.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        
        panel.add(etiquetaEstilo("Username:"));
        panel.add(txtUser);
        panel.add(etiquetaEstilo("Password:"));
        panel.add(txtPass);
        cf.add(panel, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.setOpaque(false);
        cf.add(panelBotones, BorderLayout.SOUTH);
        
        JButton btnEntrar = estiloBotones("ENTRAR");
        JButton btnVolver = estiloBotones("VOLVER");
        panelBotones.add(btnEntrar);
        panelBotones.add(btnVolver);
        btnEntrar.addActionListener(e -> manejarLogIn(txtUser.getText(), new String(txtPass.getPassword())));
        btnVolver.addActionListener(e -> volverMenuInicio());
        
        this.add(cf);
    }
    
    private JLabel etiquetaEstilo(String texto){
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(new Color(255, 204, 51));
        etiqueta.setFont(new Font("Bodoni Bd BT", Font.BOLD, 30));
        return etiqueta;
    }
    
    private JButton estiloBotones(String texto){
        JButton btn = new JButton(texto);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        btn.setPreferredSize(new Dimension(250, 50));
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 5));
        return btn;
    }
    
    private void manejarLogIn(String user, String contra) {
        Player playerLogeado = sistema.logIn(user, contra);
        
        if (playerLogeado != null) {
            JOptionPane.showMessageDialog(this, "¡Bienvenido, " + user + "!", "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);
            MenuPrincipal menuPrincipal = new MenuPrincipal(playerLogeado, sistema);
            menuPrincipal.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos o no existe.", "Error de Login", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void volverMenuInicio(){
        MenuInicio mi = new MenuInicio(sistema);
        mi.setVisible(true);
        this.dispose();
    }
}
