/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class MenuPrincipal extends JFrame {

    private Player playerActual;
    private Battleship sistema;

    public MenuPrincipal(Player playerActual, Battleship sistema) {
        this.playerActual = playerActual;
        this.sistema = sistema;
        System.out.println("DEBUG MenuPrincipal: sistema guardado correctamente: " + this.sistema);
        
        setTitle("Battleship - Menu Principal");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ClaseFondo cf = new ClaseFondo("/img/MenuPrincipalFondo.png");
        cf.setLayout(new BorderLayout(20, 20));

        JLabel titulo = new JLabel("¡Bienvenido, " + playerActual.getUsername() + "!", SwingConstants.CENTER);
        titulo.setFont(new Font("Bodoni Bd BT", Font.BOLD, 40));
        titulo.setForeground(new Color(255, 204, 51));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        cf.add(titulo, BorderLayout.NORTH);
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 1, 20, 20));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 230, 60, 230));
        panelBotones.setOpaque(false);

        JButton btnJugar = estiloBotones("JUGAR BATTLESHIP");
        JButton btnConfiguracion = estiloBotones("CONFIGURACION");
        JButton btnMiPerfil = estiloBotones("MI PERFIL");
        JButton btnReportes = estiloBotones("REPORTES");
        JButton btnSalir = estiloBotones("SALIR");

        panelBotones.add(btnJugar);
        panelBotones.add(btnConfiguracion);
        panelBotones.add(btnMiPerfil);
        panelBotones.add(btnReportes);
        panelBotones.add(btnSalir);

        cf.add(panelBotones, BorderLayout.CENTER);

        this.add(cf);

        btnJugar.addActionListener(e -> iniciarJuego());
        btnConfiguracion.addActionListener(e -> mostrarConfig());
        btnReportes.addActionListener(e -> mostrarReportes());
        btnMiPerfil.addActionListener(e -> mostrarPerfil());
        btnSalir.addActionListener(e -> manejarSalir());
    }

    private JButton estiloBotones(String texto) {
        JButton btn = new JButton(texto);
        Color color = new Color(255, 204, 51);
        btn.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.BLACK);
        btn.setPreferredSize(new Dimension(250, 50));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 5),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        return btn;
    }

    private void iniciarJuego() {
        if (sistema.getContadorPlayers() >= 2) {
            this.dispose();
            new SelecRival(playerActual, sistema).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Se necesitan al menos dos jugadores registrados para jugar.",
                    "Faltan Oponentes",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarConfig() {
        Configuracion c = new Configuracion(sistema);
        c.setVisible(true);
    }

    private void mostrarReportes() {
        if (this.sistema != null) {
            Reportes r = new Reportes(this.playerActual, this.sistema);
            r.setVisible(true);
            this.dispose();
        } else {
            System.out.println("ERROR: 'this.sistema' es null en MenuPrincipal antes de abrir Reportes");
        }
    }

    private void mostrarPerfil() {
        MiPerfil p = new MiPerfil(this.playerActual, this.sistema);
        p.setVisible(true);
        this.dispose();
    }

    private void manejarSalir() {
        MenuInicio mi = new MenuInicio(sistema);
        mi.setVisible(true);
        this.dispose();
    }
}
