/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.time.LocalDate;

/**
 *
 * @author HP
 */
public class Player {
    private String username;
    private String password;
    private int puntos;
    private String[] logsPartidas; //este es para el arreglo de las 10 ultimas partidsa
    private int contadorLogs; //saber cuantos lleva
    private boolean activo;
    private LocalDate fechaIngreso;
    
    public Player(String username, String password){
        this.username = username;
        this.password = password;
        this.puntos = 0;
        this.logsPartidas = new String[10];
        this.contadorLogs = 0;
        this.activo = true;
        this.fechaIngreso = LocalDate.now();
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public int getPuntos(){
        return puntos;
    }
    
    public void agregarPuntos(int puntosGanados){
        this.puntos += puntosGanados;
    }
    
    public void agregarLog(String mensaje){
        for(int i = Math.min(contadorLogs, 9); i>0; i--){
            logsPartidas[i] = logsPartidas[i-1];
        }
        logsPartidas[0] = mensaje;
        if(contadorLogs>10){
            contadorLogs++;
        }
    }
    
    public String[] getLogsPartidas(){
        return logsPartidas;
    }
    
    public boolean isActivo(){
        return activo;
    }
    
    public void setActivo(boolean activo){
        this.activo = activo;
    }
    
    public LocalDate getFechaIngreso(){
        return fechaIngreso;
    }
}
