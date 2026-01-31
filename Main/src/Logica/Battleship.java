/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Battleship {
    
    private Player[] listaPlayers;
    private int contadorPlayers;
    private Player playerActual; //player que inicio la partida, que por default es el player1
    private String dificultad; //EASY, NORMAL, EXPERT, GENIUS
    private String modoJuego; //TUTO, ARCADE
    private String[][] tableroP1;
    private String[][] tableroP2;
    
    public Battleship(){
        this.listaPlayers = new Player[100];
        this.playerActual = null;
        this.dificultad = "NORMAL";
        this.modoJuego = "TUTORIAL";
        this.tableroP1 = new String[8][8];
        this.tableroP2 = new String[8][8];
        
        limpiarTableros();
    }
    
    private void limpiarTableros(){ //
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
                tableroP1[i][j] = "~";
                tableroP2[i][j] = "~";
            }
        }
    }
    
    public boolean crearPlayer(String user, String pass){ //
        if(buscarPlayer(user)==null && contadorPlayers<listaPlayers.length){
            listaPlayers[contadorPlayers] = new Player(user, pass);
            contadorPlayers++;
            return true;
        }
        return false;
    }
    
    /*public Player buscarPlayer(String user){ 
        for(Player p: listaPlayers){
            if(p.getUsername().equals(user)){
                return p;
            }
        }
        return null;
    }*/
    
    public Player buscarPlayer(String user) {
        for (int i = 0; i < contadorPlayers; i++) {
            if (listaPlayers[i].getUsername().equals(user)) {
                return listaPlayers[i];
            }
        }
        return null;
    }
    
    public Player logIn(String user, String pass){
        Player p = buscarPlayer(user);
        if( p!=null && p.getPassword().equals(pass)){
            this.playerActual = p;
            return p;
        }
        return null;
    }
    
    public void Salir(){
        this.playerActual = null;
    }
    
    public Player getPlayerActual(){
        return playerActual;
    }
    
    public Player[] getListaPlayers(){
        return listaPlayers;
    }
    
    public int getContadorPlayers(){
        return contadorPlayers;
    }
    
    public String getDificultad(){
        return dificultad;
    }
    
    public void setDificultad(String dificultad){
        this.dificultad = dificultad;
    }
    
    public String getModoJuego(){
        return modoJuego;
    }
    
    public void setModoJuego(String modoJuego){
        this.modoJuego = modoJuego;
    }
}
