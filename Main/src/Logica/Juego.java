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
public class Juego extends JFrame {

    private Battleship sistema;
    private Player dueñoSesion;
    private TableroInterfaz panelP1, panelP2;
    private JLabel infoPartida, lblTurno, lblModo, lblDificultad;
    private JLabel lblNombreP1, lblNombreP2;

    private JPanel statusP1, statusP2;
    private JComboBox<String> comboCod, comboOri;
    private JButton btnConfirmarFlota;

    private boolean faseColocacion = true;
    private int jugadorColocando = 1;
    private int barcosP1 = 0, barcosP2 = 0;

    public Juego(Battleship sistema) {
        this.sistema = sistema;
        this.dueñoSesion = sistema.getPlayerActual();
        configurarVentana();
        inicializarComponentes();
        refrescarPantalla();
    }

    private void configurarVentana() {
        setTitle("BATTLESHIP - ESTADO DE GUERRA");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        ClaseFondo fondo = new ClaseFondo("/img/JuegoFondo.png");
        fondo.setLayout(new BorderLayout(10, 10));

        JPanel norte = new JPanel(new GridLayout(2, 1));
        norte.setOpaque(false);
        JPanel filaInfo = new JPanel(new GridLayout(1, 3));
        filaInfo.setOpaque(false);

        lblTurno = crearLabelInfo("FASE DE PREPARACIÓN");
        lblModo = crearLabelInfo("MODO: " + sistema.getModoJuego());
        lblDificultad = crearLabelInfo("DIFICULTAD: " + sistema.getDificultad());

        filaInfo.add(lblTurno);
        filaInfo.add(lblModo);
        filaInfo.add(lblDificultad);

        infoPartida = new JLabel("JUGADOR 1: COLOQUE SUS BARCOS (IZQUIERDA)", SwingConstants.CENTER);
        infoPartida.setFont(new Font("Arial Black", Font.BOLD, 18));
        infoPartida.setForeground(Color.YELLOW);
        norte.add(filaInfo);
        norte.add(infoPartida);

        JPanel centroContenedor = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        centroContenedor.setOpaque(false);

        JPanel bloqueP1 = new JPanel(new BorderLayout());
        bloqueP1.setOpaque(false);
        lblNombreP1 = new JLabel(sistema.getPlayerActual().getUsername(), SwingConstants.CENTER);
        lblNombreP1.setForeground(Color.WHITE);
        lblNombreP1.setFont(new Font("Arial", Font.BOLD, 20));
        panelP1 = new TableroInterfaz(1, sistema);
        bloqueP1.add(lblNombreP1, BorderLayout.NORTH);
        bloqueP1.add(panelP1, BorderLayout.CENTER);

        JPanel bloqueP2 = new JPanel(new BorderLayout());
        bloqueP2.setOpaque(false);
        lblNombreP2 = new JLabel(sistema.getRival().getUsername(), SwingConstants.CENTER);
        lblNombreP2.setForeground(Color.WHITE);
        lblNombreP2.setFont(new Font("Arial", Font.BOLD, 20));
        panelP2 = new TableroInterfaz(2, sistema);
        bloqueP2.add(lblNombreP2, BorderLayout.NORTH);
        bloqueP2.add(panelP2, BorderLayout.CENTER);

        centroContenedor.add(bloqueP1);
        centroContenedor.add(bloqueP2);

        JPanel sur = new JPanel(new BorderLayout());
        sur.setOpaque(false);
        sur.setPreferredSize(new Dimension(0, 200));

        statusP1 = crearPanelStatus("BARCOS P1", new Color(10, 30, 50));
        statusP2 = crearPanelStatus("BARCOS P2", new Color(50, 10, 10));

        JPanel controles = new JPanel();
        controles.setLayout(new BoxLayout(controles, BoxLayout.Y_AXIS));
        controles.setOpaque(false);
        controles.setPreferredSize(new Dimension(250, 0));

        comboCod = new JComboBox<>(new String[]{"PA", "AZ", "SM", "DT"});
        comboOri = new JComboBox<>(new String[]{"Horizontal", "Vertical"});
        btnConfirmarFlota = new JButton("CONFIRMAR FLOTA");
        JButton btnRetirar = new JButton("RETIRAR");

        btnRetirar.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Deseas retirarte? Esto contará como derrota.",
                    "Confirmar Retirada", JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                Player perdedor = sistema.getPlayerActual();
                Player p1 = sistema.buscarPlayer(lblNombreP1.getText());
                Player p2 = sistema.getRival();

                Player ganador = (perdedor.getUsername().equals(p1.getUsername())) ? p2 : p1;

                finalizarPartida(ganador, perdedor, true);
            }
        });

        controles.add(new JLabel("SELECCIONAR BARCO:"));
        controles.add(comboCod);
        controles.add(new JLabel("ORIENTACIÓN:"));
        controles.add(comboOri);
        controles.add(Box.createVerticalStrut(10));
        controles.add(btnConfirmarFlota);
        controles.add(Box.createVerticalStrut(10));
        controles.add(btnRetirar);

        btnConfirmarFlota.addActionListener(e -> manejarConfirmacionFlota());

        sur.add(statusP1, BorderLayout.WEST);
        sur.add(controles, BorderLayout.CENTER);
        sur.add(statusP2, BorderLayout.EAST);

        fondo.add(norte, BorderLayout.NORTH);
        fondo.add(centroContenedor, BorderLayout.CENTER);
        fondo.add(sur, BorderLayout.SOUTH);
        this.setContentPane(fondo);
    }

    public void gestionarClicTablero(int f, int c, int tableroClickeado) {
        if (faseColocacion) {
            if (jugadorColocando == 1 && tableroClickeado == 2) {
                setMensaje(lblNombreP1.getText() + ": COLOCA EN TU LADO (IZQUIERDA)");
                return;
            }
            if (jugadorColocando == 2 && tableroClickeado == 1) {
                setMensaje(lblNombreP2.getText() + ": COLOCA EN TU LADO (DERECHA)");
                return;
            }

            colocarBarco(f, c);

        } else {
            boolean esTurnoP1 = sistema.getPlayerActual().getUsername().equals(lblNombreP1.getText());

            if (esTurnoP1) {
                if (tableroClickeado == 1) {
                    setMensaje("¡" + lblNombreP1.getText() + ", ATACA EL TABLERO RIVAL (DERECHA)!");
                    return;
                }
                ejecutarAtaque(f, c);

            } else {
                if (tableroClickeado == 2) {
                    setMensaje("¡" + lblNombreP2.getText() + ", ATACA EL TABLERO RIVAL (IZQUIERDA)!");
                    return;
                }
                ejecutarAtaque(f, c);
            }
        }
    }

    private void colocarBarco(int f, int c) {
        int limite = sistema.obtenerCantBarcos();
        Player pActual = (jugadorColocando == 1) ? sistema.getPlayerActual() : sistema.getRival();

        String res = sistema.intentarColocarBarco(pActual, (String) comboCod.getSelectedItem(), f, c, comboOri.getSelectedIndex() == 0);

        if (res.contains("COLOCADO")) {
            if (jugadorColocando == 1) {
                barcosP1++;
            } else {
                barcosP2++;
            }
            setMensaje(res + " (" + (jugadorColocando == 1 ? barcosP1 : barcosP2) + "/" + limite + ")");
        } else {
            setMensaje(res);
        }
        refrescarPantalla();
    }

    private void ejecutarAtaque(int f, int c) {
        Player p1 = sistema.buscarPlayer(lblNombreP1.getText());
        Player p2 = sistema.getRival();

        int numAtacante = (sistema.getPlayerActual().getUsername().equals(p1.getUsername())) ? 1 : 2;
        int numRival = (numAtacante == 1) ? 2 : 1;

        String resultado = sistema.disparar(numAtacante, f, c);

        if (resultado.equals("AGUA")) {
            if (numAtacante == 1) {
                sistema.setPlayerActual(p2);
            } else {
                sistema.setPlayerActual(p1);
            }

            setMensaje("¡AGUA! TURNO DE: " + sistema.getPlayerActual().getUsername());
        } else {
            setMensaje(resultado + "!SIGUE ATACANDO: " + sistema.getPlayerActual().getUsername());

            if (sistema.hayGanador(numRival)) {
                Player ganador = sistema.getPlayerActual();
                Player perdedor = (numRival == 1) ? p1 : p2;
                String modo = sistema.getModoJuego();

                ganador.agregarPuntos(3);

                String mensajeLog = ganador.getUsername() + " hundió todos los barcos de "
                        + perdedor.getUsername() + " en modo " + modo;

                dueñoSesion.agregarLog(mensajeLog);

                JOptionPane.showMessageDialog(this, "¡VICTORIA PARA " + ganador.getUsername() + "!\n+3 Puntos para el ranking.");

                new MenuPrincipal(dueñoSesion, sistema).setVisible(true);
                this.dispose();
                return;
            }
        }
        refrescarPantalla();
    }

    private void finalizarPartida(Player ganador, Player perdedor, boolean fueRetirada) {
        ganador.agregarPuntos(3);

        String causa = fueRetirada ? " por retirada" : " hundiendo la flota";
        String mensajeLog = ganador.getUsername() + " venció a " + perdedor.getUsername() + causa + " (Modo: " + sistema.getModoJuego() + ")";

        dueñoSesion.agregarLog(mensajeLog);

        JOptionPane.showMessageDialog(this, "¡FIN DE LA PARTIDA!\nGanador: " + ganador.getUsername() + "\nLog: " + mensajeLog);

        new MenuPrincipal(dueñoSesion, sistema).setVisible(true);
        this.dispose();
    }

    private void manejarConfirmacionFlota() {
        int limite = sistema.obtenerCantBarcos();
        if (jugadorColocando == 1) {
            if (barcosP1 < limite) {
                setMensaje(lblNombreP1.getText() + ": TE FALTAN " + (limite - barcosP1) + " BARCOS");
                return;
            }
            jugadorColocando = 2;
            setMensaje(lblNombreP2.getText() + ": COLOQUE SUS BARCOS (DERECHA)");
        } else {
            if (barcosP2 < limite) {
                setMensaje(lblNombreP2.getText() + ": TE FALTAN " + (limite - barcosP2) + " BARCOS");
                return;
            }
            faseColocacion = false;
            btnConfirmarFlota.setVisible(false);
            comboCod.setEnabled(false);
            comboOri.setEnabled(false);

            Player p1Real = sistema.buscarPlayer(lblNombreP1.getText());
            sistema.setPlayerActual(p1Real);
            setMensaje("¡GUERRA! " + sistema.getPlayerActual().getUsername() + " ATACA A LA DERECHA");
        }
        refrescarPantalla();
    }

    private void actualizarStatusVidas() {
        statusP1.removeAll();
        statusP2.removeAll();

        String[] cods = {"PA", "AZ", "SM", "DT"};

        for (String c : cods) {
            int vidas = contarCeldas(sistema.getTableroP1(), c);
            if (vidas > 0) {
                statusP1.add(new JLabel("<html><font color='white'>" + c + " - VIDAS: </font><font color='green'>" + vidas + "</font></html>"));
            }
        }

        for (String c : cods) {
            int vidas = contarCeldas(sistema.getTableroP2(), c);
            if (vidas > 0) {
                statusP2.add(new JLabel("<html><font color='white'>" + c + " - VIDAS: </font><font color='green'>" + vidas + "</font></html>"));
            }
        }

        statusP1.revalidate();
        statusP2.revalidate();
        statusP1.repaint();
        statusP2.repaint();
    }

    private int contarCeldas(String[][] t, String cod) {
        int count = 0;
        for (String[] f : t) {
            for (String celda : f) {
                if (celda.equals(cod)) {
                    count++;
                }
            }
        }
        return count;
    }

    public void refrescarPantalla() {
        lblTurno.setText("TURNO DE: " + sistema.getPlayerActual().getUsername());
        actualizarStatusVidas();
        panelP1.repaint();
        panelP2.repaint();
    }

    private void setMensaje(String msj) {
        infoPartida.setText(msj.toUpperCase());
    }

    private JLabel crearLabelInfo(String t) {
        JLabel l = new JLabel(t, SwingConstants.CENTER);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        return l;
    }

    private JPanel crearPanelStatus(String titulo, Color color) {
        JPanel p = new JPanel(new GridLayout(4, 1));
        p.setPreferredSize(new Dimension(280, 0));
        p.setBackground(color);
        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), titulo, 0, 0, null, Color.WHITE));
        return p;
    }

    public JLabel getLblNombreP1() {
        return lblNombreP1;
    }

    public JLabel getLblNombreP2() {
        return lblNombreP2;
    }

    public boolean isFaseColocacion() {
        return faseColocacion;
    }

    public int getJugadorColocando() {
        return jugadorColocando;
    }
}
