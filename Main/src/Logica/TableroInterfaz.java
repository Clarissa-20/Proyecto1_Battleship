/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author HP
 */
public class TableroInterfaz extends JPanel {

    private int numTablero;
    private Battleship sistema;

    public TableroInterfaz(int numTablero, Battleship sistema) {
        this.numTablero = numTablero;
        this.sistema = sistema;

        this.setPreferredSize(new Dimension(420, 420));

        this.setBorder(BorderFactory.createLineBorder(new Color(101, 67, 33), 6));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cellSize = getWidth() / Battleship.SIZE;
                int fila = e.getY() / cellSize;
                int col = e.getX() / cellSize;

                if (fila >= 0 && fila < Battleship.SIZE && col >= 0 && col < Battleship.SIZE) {
                    comunicarClicAJuego(fila, col);
                }
            }
        });
    }

    private void comunicarClicAJuego(int f, int c) {

        Container parent = getParent();
        while (parent != null && !(parent instanceof Juego)) {
            parent = parent.getParent();
        }

        if (parent instanceof Juego ventanaJuego) {

            ventanaJuego.gestionarClicTablero(f, c, numTablero);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int cellSize = getWidth() / Battleship.SIZE;
        String[][] matriz = (numTablero == 1) ? sistema.getTableroP1() : sistema.getTableroP2();

        Container parent = getParent();
        while (parent != null && !(parent instanceof Juego)) {
            parent = parent.getParent();
        }

        String nombreDuenio = "";
        if (parent instanceof Juego v) {
            nombreDuenio = (numTablero == 1) ? v.getLblNombreP1().getText() : v.getLblNombreP2().getText();
        }

        for (int i = 0; i < Battleship.SIZE; i++) {
            for (int j = 0; j < Battleship.SIZE; j++) {
                Color fondoBase = (numTablero == 1) ? new Color(20, 50, 90) : new Color(90, 20, 20);
                g2.setColor(fondoBase);
                g2.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);

                g2.setColor(new Color(255, 255, 255, 30));
                g2.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);

                String contenido = matriz[i][j];

                if (sistema.getModoJuego().equalsIgnoreCase("TUTORIAL")) {
                    dibujarSimbolo(g2, contenido, j * cellSize, i * cellSize, cellSize);
                } else {
                    boolean esMiTablero = sistema.getPlayerActual().getUsername().equals(nombreDuenio);
                    if (esMiTablero || contenido.equals("X") || contenido.equals("F")) {
                        dibujarSimbolo(g2, contenido, j * cellSize, i * cellSize, cellSize);
                    }
                }
            }
        }
    }

    private void dibujarSimbolo(Graphics2D g2, String texto, int x, int y, int size) {
        if (texto.equals("~")) {
            return;
        }

        g2.setFont(new Font("Arial Black", Font.BOLD, 18));

        switch (texto) {
            case "X": 
                g2.setColor(Color.RED);
                break;
            case "F": 
                g2.setColor(Color.WHITE);
                break;
            default: 
                g2.setColor(Color.YELLOW);
                break;
        }

        FontMetrics fm = g2.getFontMetrics();
        int tx = x + (size - fm.stringWidth(texto)) / 2;
        int ty = y + (size + fm.getAscent()) / 2 - 4;
        g2.drawString(texto, tx, ty);
    }
}
