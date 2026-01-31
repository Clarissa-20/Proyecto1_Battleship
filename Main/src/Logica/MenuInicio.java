/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author HP
 */
public class MenuInicio extends JFrame {
    private Battleship sistema;

    public MenuInicio(Battleship sistema){
        this.sistema = sistema;
        setTitle("Battleship - Menú Inicio");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20));

        ClaseFondo cf = new ClaseFondo("/img/MenuInicioFondo.png");
        cf.setLayout(new BorderLayout(20, 20));

        JLabel titulo = new JLabel("BATTLESHIP", SwingConstants.CENTER);
        titulo.setFont(new Font("Bodoni Bd BT", Font.BOLD, 50));
        titulo.setForeground(new Color(255, 204, 51));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        cf.add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 200, 60, 200));
        panelBotones.setOpaque(false);

        JButton btnLogIn = estiloBotones("LOGIN");
        JButton btnCrearPlayer = estiloBotones("CREAR PLAYER");
        JButton btnSalir = estiloBotones("SALIR");

        panelBotones.add(btnLogIn);
        panelBotones.add(btnCrearPlayer);
        panelBotones.add(btnSalir);

        cf.add(panelBotones, BorderLayout.CENTER);
        this.add(cf);

        btnLogIn.addActionListener(e -> mostrarLogIn());
        btnCrearPlayer.addActionListener(e -> mostrarCrearPlayer());
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private JButton estiloBotones(String texto) {
        JButton btn = new JButton(texto);
        Color color = new Color(255, 204, 51);
        btn.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.BLACK);
        btn.setPreferredSize(new Dimension(205, 50));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 5),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        return btn;
    }

    private void mostrarLogIn() {
        LogIn lg = new LogIn(sistema);
        lg.setVisible(true);
        this.dispose();
    }
    
    private void mostrarCrearPlayer() {
        CrearPlayer cp = new CrearPlayer(sistema);
        cp.setVisible(true);
        this.dispose();
    }
}
