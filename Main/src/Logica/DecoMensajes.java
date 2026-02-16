/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
/**
 *
 * @author HP
 */
public class DecoMensajes {
    private static final Color DORADO = new Color(255, 204, 51);
    private static final Color FONDO = Color.BLACK;
    private static final Font FUENTE = new Font("Bodoni Bd BT", Font.BOLD, 16);

    public static void mostrarMensaje(Component padre, String mensaje, String titulo) {
        UIManager.put("OptionPane.background", FONDO);
        UIManager.put("Panel.background", FONDO);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);

        JLabel lbl = new JLabel("<html><div style='text-align: center;'>" + mensaje + "</div></html>");
        lbl.setFont(FUENTE);
        lbl.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBackground(FONDO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DORADO, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.add(lbl);

        JOptionPane.showMessageDialog(padre, panel, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    public static int mostrarConfirmacion(Component padre, String mensaje, String titulo) {
        UIManager.put("OptionPane.background", FONDO);
        UIManager.put("Panel.background", FONDO);

        JLabel lbl = new JLabel(mensaje);
        lbl.setFont(FUENTE);
        lbl.setForeground(Color.WHITE);

        return JOptionPane.showConfirmDialog(padre, lbl, titulo,
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
    }
}
