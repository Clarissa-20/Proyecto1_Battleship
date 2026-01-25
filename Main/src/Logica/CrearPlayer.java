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
public class CrearPlayer extends JFrame{
    
    public CrearPlayer(){
        setTitle("Battleship - CrearPlayer");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20));
        
        ClaseFondo cf = new ClaseFondo("img aqui");
        cf.setLayout(new BorderLayout(20, 20));
        
        JLabel titulo = new JLabel("CREAR PLAYER", SwingConstants.CENTER);
        titulo.setFont(new Font("Bodoni Bd BT", Font.BOLD, 50));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        cf.add(titulo, BorderLayout.NORTH);
        
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        
        JTextField txtUser = new JTextField(15);
        txtUser.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        txtUser.setBackground(Color.BLACK);
        txtUser.setForeground(Color.WHITE);
        
        JPasswordField txtPass = new JPasswordField(15);
        txtPass.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        txtPass.setBackground(Color.BLACK);
        txtPass.setForeground(Color.WHITE);
        
        panel.add(estiloEtiqueta("Usuario(debe ser unico):"));
        panel.add(txtUser);
        panel.add(estiloEtiqueta("Contraseña(exactamente 5 caracteres):"));
        panel.add(txtPass);
        cf.add(panel, BorderLayout.CENTER);
        
        JPanel panelbtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelbtn.setOpaque(false);
        cf.add(panelbtn, BorderLayout.SOUTH);
        
        JButton btnCrear = estiloBotones("CREAR");
        JButton btnVolver = estiloBotones("VOLVER");
        panelbtn.add(btnCrear);
        panelbtn.add(btnVolver);
        btnCrear.addActionListener(e -> manejarCrearPlayer(txtUser.getText(), new String(txtPass.getPassword())));
        btnVolver.addActionListener(e -> volverMenuInicio());
        
        this.add(cf);
    }
    
    private JLabel estiloEtiqueta(String texto){
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(new Color(124, 252, 0));
        etiqueta.setFont(new Font("Bodoni Bd BT", Font.BOLD, 30));
        return etiqueta;
    }
    
    private JButton estiloBotones(String texto){
        JButton btn = new JButton(texto);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        btn.setPreferredSize(new Dimension(250, 50));
        btn.setBorder(BorderFactory.createLineBorder(new Color(124, 252, 0) ,5));
        return btn;
    }
    
    private void manejarCrearPlayer(String user, String pass){
        
    }
    
    private void volverMenuInicio(){
        MenuInicio mi = new MenuInicio();
        mi.setVisible(true);
        this.dispose();
    }
}
