/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Battleship {

    public static final int SIZE = 8;
    public static final String PA = "PA", AZ = "AZ", SM = "SM", DT = "DT";

    private Player[] listaPlayers;
    private int contadorPlayers;
    private Player playerActual;
    private Player rival;
    private String dificultad;
    private String modoJuego;

    private String[][] tableroP1, tableroP2;
    private String[][] ataquesP1, ataquesP2;

    public Battleship() {
        this.listaPlayers = new Player[100];
        this.contadorPlayers = 0;
        this.playerActual = null;
        this.dificultad = "NORMAL";
        this.modoJuego = "TUTORIAL";
        this.tableroP1 = new String[SIZE][SIZE];
        this.tableroP2 = new String[SIZE][SIZE];
        this.ataquesP1 = new String[SIZE][SIZE];
        this.ataquesP2 = new String[SIZE][SIZE];
        limpiarTableros();
    }

    public void limpiarTableros() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tableroP1[i][j] = "~";
                tableroP2[i][j] = "~";
                ataquesP1[i][j] = "~";
                ataquesP2[i][j] = "~";
            }
        }
    }

    //metodo recursivo 1 para validar espacion
    private boolean esEspacioLimpioRecursivo(String[][] tablero, int f, int c, int largo, boolean hor, int index) {
        if (index == largo) {
            return true;
        }
        if (f < 0 || f >= SIZE || c < 0 || c >= SIZE) {
            return false;
        }
        if (!tablero[f][c].equals("~")) {
            return false;
        }

        return hor ? esEspacioLimpioRecursivo(tablero, f, c + 1, largo, hor, index + 1)
                : esEspacioLimpioRecursivo(tablero, f + 1, c, largo, hor, index + 1);
    }

    public String intentarColocarBarco(Player p, String tipo, int f, int c, boolean horizontal) {
        String[][] tablero = (p.getUsername().equals(this.playerActual.getUsername())) ? tableroP1 : tableroP2;

        int largo = obtenerLargoBarco(tipo);

        if (yaTieneEseBarco(tablero, tipo)) {
            if (!(dificultad.equals("EASY") && tipo.equals(DT) && contarTipo(tablero, DT) < 2)) {
                return "ERROR: YA COLOCASTE UN " + tipo;
            }
        }

        if (!esEspacioLimpioRecursivo(tablero, f, c, largo, horizontal, 0)) {
            return "ERROR: ESPACIO OCUPADO O FUERA DE LÍMITES.";
        }

        for (int i = 0; i < largo; i++) {
            if (horizontal) {
                tablero[f][c + i] = tipo;
            } else {
                tablero[f + i][c] = tipo;
            }
        }
        return "BARCO " + tipo + " COLOCADO.";
    }

    private int contarTipo(String[][] tablero, String tipo) {
        int count = 0;
        for (String[] fila : tablero) {
            for (String celda : fila) {
                if (celda.equals(tipo)) {
                    count++;
                }
            }
        }
        return count / obtenerLargoBarco(tipo);
    }

    private boolean yaTieneEseBarco(String[][] tablero, String tipo) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tablero[i][j].equals(tipo)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String disparar(int numAtacante, int fila, int col) {
        String[][] objetivo = (numAtacante == 1) ? tableroP2 : tableroP1;
        String[][] registro = (numAtacante == 1) ? ataquesP1 : ataquesP2;

        if (!registro[fila][col].equals("~")) {
            return "YA DISPARASTE AQUÍ";
        }

        if (objetivo[fila][col].equals("~") || objetivo[fila][col].equals("F")) {
            registro[fila][col] = "F";
            limpiarMarcasAgua(objetivo);
            limpiarMarcasAgua(registro);
            regenerarTablero(numAtacante == 1 ? 2 : 1);
            return "AGUA";
        } else {
            String barcoGolpeado = objetivo[fila][col];
            registro[fila][col] = "X";
            objetivo[fila][col] = "X";

            if (!quedanPiezasDe(objetivo, barcoGolpeado)) {
                return "HUNDIDO:" + barcoGolpeado;
            }
            return "IMPACTO";
        }
    }

    private void limpiarMarcasAgua(String[][] m) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (m[i][j].equals("F")) {
                    m[i][j] = "~";
                }
            }
        }
    }

    //metodi recursivo 2 para buscar piezas vivas
    private boolean quedanPiezasDe(String[][] tablero, String tipo) {
        return buscarPiezaRecursiva(tablero, tipo, 0, 0);
    }

    private boolean buscarPiezaRecursiva(String[][] tablero, String tipo, int f, int c) {
        if (f == SIZE) {
            return false;
        }
        if (tablero[f][c].equals(tipo)) {
            return true;
        }
        int sigF = (c == SIZE - 1) ? f + 1 : f;
        int sigC = (c == SIZE - 1) ? 0 : c + 1;
        return buscarPiezaRecursiva(tablero, tipo, sigF, sigC);
    }

    public void regenerarTablero(int numJugador) {
        String[][] tablero = (numJugador == 1) ? tableroP1 : tableroP2;
        String[] tipos = {PA, AZ, SM, DT};
        Random rand = new Random();
        ArrayList<String> vivos = new ArrayList<>();

        for (String t : tipos) {
            if (yaTieneEseBarco(tablero, t)) {
                int cant = (dificultad.equals("EASY") && t.equals(DT)) ? contarTipo(tablero, DT) : 1;
                for (int k = 0; k < cant; k++) {
                    vivos.add(t);
                }
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!tablero[i][j].equals("X")) {
                    tablero[i][j] = "~";
                }
            }
        }

        for (String tipo : vivos) {
            boolean puesto = false;
            while (!puesto) {
                int f = rand.nextInt(SIZE), c = rand.nextInt(SIZE), largo = obtenerLargoBarco(tipo);
                boolean hor = rand.nextBoolean();
                if (validarPosicionRegen(tablero, f, c, largo, hor)) {
                    for (int i = 0; i < largo; i++) {
                        if (hor) {
                            tablero[f][c + i] = tipo;
                        } else {
                            tablero[f + i][c] = tipo;
                        }
                    }
                    puesto = true;
                }
            }
        }
    }

    private boolean validarPosicionRegen(String[][] tab, int f, int c, int l, boolean h) {
        if (h && (c + l > SIZE)) {
            return false;
        }
        if (!h && (f + l > SIZE)) {
            return false;
        }
        for (int i = 0; i < l; i++) {
            int rf = h ? f : f + i;
            int rc = h ? c + i : c;
            if (!tab[rf][rc].equals("~")) {
                return false;
            }
        }
        return true;
    }

    private int obtenerLargoBarco(String tipo) {
        return switch (tipo.toUpperCase()) {
            case PA ->
                5;
            case AZ ->
                4;
            case SM ->
                3;
            case DT ->
                2;
            default ->
                0;
        };
    }

    public boolean hayGanador(int numRival) {
        String[][] tableroRival = (numRival == 1) ? tableroP1 : tableroP2;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String celda = tableroRival[i][j];
                if (!celda.equals("~") && !celda.equals("X") && !celda.equals("F")) {
                    return false;
                }
            }
        }
        return true;
    }

    public Player getPlayerActual() {
        return playerActual;
    }

    public void setPlayerActual(Player p) {
        this.playerActual = p;
    }

    public Player[] getListaPlayers() {
        return listaPlayers;
    }

    public int getContadorPlayers() {
        return contadorPlayers;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getModoJuego() {
        return modoJuego;
    }

    public void setModoJuego(String modoJuego) {
        this.modoJuego = modoJuego;
    }

    public Player getRival() {
        return rival;
    }

    public void setRival(Player rival) {
        this.rival = rival;
    }

    public String[][] getTableroP1() {
        return tableroP1;
    }

    public String[][] getTableroP2() {
        return tableroP2;
    }

    public Player buscarPlayer(String user) {
        for (int i = 0; i < contadorPlayers; i++) {
            if (listaPlayers[i].getUsername().equals(user)) {
                return listaPlayers[i];
            }
        }
        return null;
    }

    public boolean crearPlayer(String user, String pass) {
        if (buscarPlayer(user) == null && contadorPlayers < listaPlayers.length) {
            listaPlayers[contadorPlayers] = new Player(user, pass);
            contadorPlayers++;
            return true;
        }
        return false;
    }

    public Player logIn(String user, String pass) {
        Player p = buscarPlayer(user);
        if (p != null && p.getPassword().equals(pass)) {
            this.playerActual = p;
            return p;
        }
        return null;
    }

    public boolean cambiarUser(String userActual, String passConfirmar, String nuevoUser) {
        Player p = buscarPlayer(userActual);
        if (p != null && p.getPassword().equals(passConfirmar)) {
            if (buscarPlayer(nuevoUser) == null) {
                p.setUsername(nuevoUser);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El nuevo username ya esta en uso.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta. No se puede cambiar el nombre.");
        }
        return false;
    }

    public boolean cambiarPassword(String userActual, String passVieja, String passNueva) {
        Player p = buscarPlayer(userActual);
        if (p != null && p.getPassword().equals(passVieja)) {
            p.setPassword(passNueva);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "La contraseña actual no es correcta.");
        }
        return false;
    }

    public boolean eliminarCuenta(String username, String password) {
        for (int i = 0; i < contadorPlayers; i++) {
            if (listaPlayers[i].getUsername().equals(username)
                    && listaPlayers[i].getPassword().equals(password)) {

                for (int j = i; j < contadorPlayers - 1; j++) {
                    listaPlayers[j] = listaPlayers[j + 1];
                }

                listaPlayers[contadorPlayers - 1] = null;
                contadorPlayers--; 
                return true;
            }
        }
        return false; 
    }

    public int obtenerCantBarcos() {
        return switch (dificultad) {
            case "EASY" ->
                5;
            case "EXPERT" ->
                2;
            case "GENIUS" ->
                1;
            default ->
                4;
        };
    }

}
