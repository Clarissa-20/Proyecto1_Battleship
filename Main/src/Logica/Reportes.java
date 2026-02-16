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
public class Reportes extends JFrame {

    private Player playerActual;
    private Battleship sistema;

    public Reportes(Player playerActual, Battleship sistema) {
        this.playerActual = playerActual;
        this.sistema = sistema;

        if (this.sistema == null) {
            JOptionPane.showMessageDialog(null, "Error crítico: El sistema no nulo.");
            return;
        }

        setTitle("Battleship - Reportes");
        setSize(900, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ClaseFondo cf = new ClaseFondo("/img/ReportesFondo.png");
        cf.setLayout(new BorderLayout(20, 20));

        JLabel titulo = new JLabel("REPORTES", SwingConstants.CENTER);
        titulo.setFont(new Font("Bodoni Bd BT", Font.BOLD, 50));
        titulo.setForeground(new Color(255, 204, 51));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        cf.add(titulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridLayout(2, 1, 10, 10));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 70, 20, 70));

        cf.add(panelCentral, BorderLayout.CENTER);

        JButton btnVolver = estiloBotones("VOLVER");
        btnVolver.addActionListener(e -> Volver());
        JPanel panelSur = new JPanel();
        panelSur.setOpaque(false);
        panelSur.add(btnVolver);
        panelSur.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        cf.add(panelSur, BorderLayout.SOUTH);

        panelCentral.add(crearSeccionReporte("MIS ÚLTIMO 10 JUEGOS", obtenerTextoLogs()));
        panelCentral.add(crearSeccionReporte("RANKING DE JUGADORES", obtenerTextoRanking()));

        add(cf);
    }

    private JPanel crearSeccionReporte(String titulo, String contenido) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Bodoni Bd BT", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo, BorderLayout.NORTH);

        JTextArea areaTexto = new JTextArea(contenido);
        areaTexto.setEditable(false);
        areaTexto.setBackground(new Color(30, 30, 30));
        areaTexto.setForeground(new Color(124, 252, 0));
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private String obtenerTextoLogs() {
        StringBuilder sb = new StringBuilder();
        String[] logs = playerActual.getLogsPartidas();

        int contadorVisual = 1;
        for (int i = logs.length - 1; i >= 0; i--) {
            if (logs[i] != null && !logs[i].isEmpty()) {
                sb.append(contadorVisual + "- " + logs[i] + "\n");
                contadorVisual++;
            }
        }

        if (sb.length() == 0) {
            return "No hay partidas registradas.";
        }
        return sb.toString();
    }
 
    private String obtenerTextoRanking() {
        if (this.sistema == null) {
            return "Cargando datos...";
        }

        Player[] ranking = new Player[sistema.getContadorPlayers()];
        System.arraycopy(sistema.getListaPlayers(), 0, ranking, 0, sistema.getContadorPlayers());

        for (int i = 0; i < ranking.length - 1; i++) {
            for (int j = 0; j < ranking.length - i - 1; j++) {
                if (ranking[j].getPuntos() < ranking[j + 1].getPuntos()) {
                    Player temp = ranking[j];
                    ranking[j] = ranking[j + 1];
                    ranking[j + 1] = temp;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s | %-15s | %-10s\n", "POS", "USUARIO", "PUNTOS"));
        sb.append("------------------------------------------\n");
        for (int i = 0; i < ranking.length; i++) {
            sb.append(String.format("%-5d | %-15s | %-10d\n",
                    (i + 1), ranking[i].getUsername(), ranking[i].getPuntos()));
        }
        return sb.toString();
    }

    private JButton estiloBotones(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        btn.setPreferredSize(new Dimension(150, 50));
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 3));
        return btn;
    }

    private void Volver() {
        MenuPrincipal mp = new MenuPrincipal(this.playerActual, this.sistema);
        mp.setVisible(true);
        this.dispose();
    }

}
