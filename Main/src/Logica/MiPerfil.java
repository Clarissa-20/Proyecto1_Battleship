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
public class MiPerfil extends JFrame{
    private Player playerActual;
    private Battleship sistema;
    
    public MiPerfil(Battleship sistema, Player playerActual){
        this.sistema = sistema;
        this.playerActual = playerActual;
        
        setTitle("Battleship - Mi Cuenta");
        setSize(800, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        initComponents();
        this.setVisible(true);
    }
    
    private void initComponents(){
        ClaseFondo cf = new ClaseFondo("/img/MiPerfilFondo.png");
        cf.setLayout(new BorderLayout(20, 20));
        
        JLabel titulo = new JLabel("MI CUENTA", SwingConstants.CENTER);
        titulo.setFont(new Font("Bodoni Bd BT", Font.BOLD, 40));
        titulo.setForeground(new Color(255, 204, 51));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 200, 60, 200));
        panelBotones.setOpaque(false);
        
        JButton btnVerInfo = crearBoton("VER MIS DATOS");
        JButton btnCambiarContra = crearBoton("MODIFICAR MIS DATOS");
        JButton btnCerrarCuenta = crearBoton("ELIMINAR CUENTA");
        JButton btnVolver = crearBoton("VOLVER");
        
        panelBotones.add(btnVerInfo);
        panelBotones.add(btnCambiarContra);
        panelBotones.add(btnCerrarCuenta);
        panelBotones.add(btnVolver);
        
        btnVerInfo.addActionListener(e -> VerMisDatos());
        btnCambiarContra.addActionListener(e -> ModificarMisDatos());
        btnCerrarCuenta.addActionListener(e -> EliminarCuenta());
        btnVolver.addActionListener(e -> Volver());
        
        cf.add(titulo, BorderLayout.NORTH);
        cf.add(panelBotones, BorderLayout.CENTER);
        
        this.add(cf);
    }
    
    private JButton crearBoton(String texto){
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(250, 50));
        
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 204, 51), 5),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        return btn;
    }
    
    /*private JButton btnEliminar(String texto){
        JButton btn = new JButton(texto);
        btn.setFont(btnFont);
        btn.setBackground(Color.RED.darker());
        btn.setForeground(colorTexto);
        btn.setFocusPainted(false);
        return btn;
    }*/
    
    private void VerMisDatos(){
        VerDatos info = new VerDatos(playerActual);
        info.setVisible(true);
        this.dispose();
    }
    
    private void ModificarMisDatos(){
        ModificarDatos contra = new ModificarDatos(sistema, playerActual);
        contra.setVisible(true);
        this.dispose();
    }
    
    private void EliminarCuenta(){
        EliminarCuenta cerrar = new EliminarCuenta(sistema, playerActual);
        cerrar.setVisible(true);
        this.dispose();
    }
    
    private void Volver(){
        MenuPrincipal mp = new MenuPrincipal(playerActual, sistema);
        mp.setVisible(true);
        this.dispose();
    }
}
