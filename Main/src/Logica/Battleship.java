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
    
    private ArrayList<Player> listaPlayers;
    private Player playerActual; //player que inicio la partida, que por default es el player1
    private String dificultad; //EASY, NORMAL, EXPERT, GENIUS
    private String modoJuego; //TUTO, ARCADE
    
    public Battleship(){
        this.listaPlayers = new ArrayList<>();
        this.playerActual = null;
        this.dificultad = "NORMAL";
        this.modoJuego = "TUTORIAL";
    }
    
    public boolean crearPlayer(String user, String pass){
        if(buscarPlayer(user)==null){
            Player nuevo = new Player(user, pass);
            listaPlayers.add(nuevo);
            return true;
        }
        return false;
    }
    
    public Player buscarPlayer(String user){
        for(Player p: listaPlayers){
            if(p.getUsername().equals(user)){
                return p;
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
    
    public ArrayList getListaPlayers(){
        return listaPlayers;
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
