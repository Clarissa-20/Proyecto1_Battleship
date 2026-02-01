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
public class VerDatos extends JFrame{
    private Player playerActual;
    private Battleship sistema;

    public VerDatos(Player playerActual) {
        super("Battleship - Información de la Cuenta - " + playerActual.getUsername());

        this.playerActual = playerActual;
        
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        ClaseFondo cp = new ClaseFondo("/img/MiPerfilFondo.png");
        cp.setLayout(new BorderLayout());
        cp.setBorder(BorderFactory.createEmptyBorder(90, 90, 90, 90));

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(90, 90, 90, 90));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK, 5), "MI INFORMACIÓN",
            TitledBorder.LEFT, TitledBorder.TOP, new Font("Bodoni Bd BT", Font.BOLD, 30), new Color(255, 204, 51)));

        //user
        JLabel nombre = new JLabel("USUARIO:");
        nombre.setFont(new Font("Bodoni Bd BT", Font.BOLD, 25));
        nombre.setForeground(Color.WHITE);
        nombre.setPreferredSize(new Dimension(100, 30));

        JTextField txtUsername = new JTextField(playerActual.getUsername());
        txtUsername.setEditable(false);
        txtUsername.setFont(new Font("Bodoni Bd BT", Font.BOLD, 25));
        txtUsername.setBackground(Color.BLACK);
        txtUsername.setForeground(Color.WHITE);
        txtUsername.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

        //puntos
        JLabel puntos = new JLabel("PUNTOS:");
        puntos.setFont(new Font("Bodoni Bd BT", Font.BOLD, 25));
        puntos.setForeground(Color.WHITE);
        puntos.setPreferredSize(new Dimension(100, 30));
        
        JLabel txtPuntos = new JLabel(String.valueOf(playerActual.getPuntos()));
        txtPuntos.setFont(new Font("Bodoni Bd BT", Font.BOLD, 25));
        txtPuntos.setForeground(Color.WHITE);
        txtPuntos.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

        
        //fecha ingreso
        JLabel registro = new JLabel("FECHA DE INGRESO:");
        registro.setFont(new Font("Bodoni Bd BT", Font.BOLD, 25));
        registro.setForeground(Color.WHITE);
        registro.setPreferredSize(new Dimension(100, 30));
        
        JLabel txtRegistro = new JLabel(playerActual.getFechaIngreso().toString());
        txtRegistro.setFont(new Font("Bodoni Bd BT", Font.BOLD, 25));
        txtRegistro.setForeground(Color.WHITE);
        txtRegistro.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        
        //estado user
        JLabel estado = new JLabel("ESTADO DE CUENTA:");
        estado.setFont(new Font("Bodoni Bd BT", Font.BOLD, 25));
        estado.setForeground(Color.WHITE);
        
        String estadoTexto = playerActual.isActivo() ? "ACTIVO" : "INACTIVO";
        //Color color = playerActual.isActivo() ? Color.GREEN : Color.RED;

        JLabel txtEstado = new JLabel(estadoTexto);
        txtEstado.setFont(new Font("Bodoni Bd BT", Font.BOLD, 25));
        txtEstado.setForeground(Color.WHITE);
        txtEstado.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        
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
        cp.add(panelbtn, BorderLayout.SOUTH);
        
        panel.add(nombre);
        panel.add(txtUsername);
        panel.add(puntos);
        panel.add(txtPuntos);
        panel.add(registro);
        panel.add(txtRegistro);
        panel.add(estado);
        panel.add(txtEstado);

        cp.add(panel, BorderLayout.CENTER);
        this.setContentPane(cp);
    }
    
    private void vtnVolver(){
        MiPerfil mp = new MiPerfil(sistema, playerActual);
        mp.setVisible(true);
        this.dispose();
    }
    
}
