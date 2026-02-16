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
public class Configuracion extends JFrame{
    private Battleship sistema;
    
    public Configuracion(Battleship sistema){
        this.sistema = sistema;
        
        setTitle("Battleship - Configuración");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
         ClaseFondo cf = new ClaseFondo("/img/ConfiguracionFondo.png");
        cf.setLayout(new BorderLayout(10, 10));
        
        JLabel titulo = new JLabel("CONFIGURACIÓN", SwingConstants.CENTER);
        titulo.setFont(new Font("Bodoni Bd BT", Font.BOLD, 40));
        titulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 10, 0));
        titulo.setForeground(new Color(255, 204, 51));
        cf.add(titulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridLayout(2, 1, 10, 10));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        panelCentral.add(crearPanelDificultad());

        panelCentral.add(crearPanelModo());

        cf.add(panelCentral, BorderLayout.CENTER);

        JButton btnVolver = estiloBotones("VOLVER");
        btnVolver.addActionListener(e -> this.dispose());
        JPanel panelSur = new JPanel();
        panelSur.setOpaque(false);
        panelSur.add(btnVolver);
        panelSur.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        cf.add(panelSur, BorderLayout.SOUTH);

        add(cf);
    }
    
    private JPanel crearPanelDificultad() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
    panel.setOpaque(false);
    panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 5), 
            "DIFICULTAD", 0, 0, new Font("Bodoni Bd BT", Font.BOLD, 20), Color.WHITE));

    String[] opciones = {"EASY", "NORMAL", "EXPERT", "GENIUS"};
    JComboBox<String> comboDificultad = new JComboBox<>(opciones);
    
    comboDificultad.setPreferredSize(new Dimension(150, 30));
    comboDificultad.setSelectedItem(sistema.getDificultad()); 
    
    JLabel info = new JLabel("<html>EASY - 5 barcos<br><br>NORMAL - 4 barcos<br><br>EXPERT - 2 barcos<br><br>GENIUS - 1 barco</html>");
    info.setForeground(Color.BLACK);
    info.setFont(new Font("Bodoni Bd BT", Font.ITALIC, 16));

    comboDificultad.addActionListener(e -> {
        sistema.setDificultad((String) comboDificultad.getSelectedItem());
        DecoMensajes.mostrarMensaje(this, "Dificultad cambiada a: " + sistema.getDificultad(), "BATTLESHIP");
    });

    panel.add(comboDificultad);
    panel.add(info);
    return panel;
}

private JPanel crearPanelModo() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
    panel.setOpaque(false);
    panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 5), 
            "MODO DE JUEGO", 0, 0, new Font("Bodoni Bd BT", Font.BOLD, 18), Color.WHITE));

    String[] modos = {"TUTORIAL", "ARCADE"};
    JComboBox<String> comboModo = new JComboBox<>(modos);
    
    comboModo.setPreferredSize(new Dimension(150, 30));
    comboModo.setSelectedItem(sistema.getModoJuego());

    JLabel infoModo = new JLabel("<html>TUTORIAL<br>Muestra barcos rivales.<br><br>ARCADE<br>Esconde barcos rivales.</html>");
    infoModo.setForeground(Color.BLACK);
    infoModo.setFont(new Font("Bodoni Bd BT", Font.ITALIC, 16));

    comboModo.addActionListener(e -> {
        sistema.setModoJuego((String) comboModo.getSelectedItem());
        DecoMensajes.mostrarMensaje(this, "Modo de juego cambiado a: " + sistema.getModoJuego(), "BATTLESHIP");
    });

    panel.add(comboModo);
    panel.add(infoModo);
    return panel;
}

    private JButton estiloBotones(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Bodoni Bd BT", Font.BOLD, 18));
        btn.setPreferredSize(new Dimension(150, 50));
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 5));
        return btn;
    }
    
}
