/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 *
 * @author HP
 */
public class CrearPlayer extends JFrame {
    private Battleship sistema;
    private JPasswordField txtPass;
    private JTextField txtUser;
    private JLabel reqLongitud, reqMayus, reqMinus, reqNumero, reqEspecial;
    private final int ANCHO_COMPONENTE = 400;

    public CrearPlayer(Battleship sistema) {
        this.sistema = sistema;

        setTitle("Battleship - Crear Player");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ClaseFondo cf = new ClaseFondo("/img/CrearPlayerFondo.png");
        cf.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("CREAR PLAYER", SwingConstants.CENTER);
        titulo.setFont(new Font("Bodoni Bd BT", Font.BOLD, 50));
        titulo.setForeground(new Color(255, 204, 51));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        cf.add(titulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setOpaque(false);
        
        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.setOpaque(false);

        formulario.add(estiloEtiqueta("Usuario (único):"));
        txtUser = new JTextField();
        estiloCampoTexto(txtUser);
        formulario.add(txtUser);

        formulario.add(Box.createRigidArea(new Dimension(0, 15)));

        formulario.add(estiloEtiqueta("Contraseña:"));
        txtPass = (JPasswordField) estiloCampoTexto(new JPasswordField());
        formulario.add(txtPass);

        formulario.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel panelCajita = new JPanel(new GridLayout(5, 1, 0, 2));
        panelCajita.setBackground(new Color(20, 20, 20)); 
        panelCajita.setPreferredSize(new Dimension(ANCHO_COMPONENTE, 110));
        panelCajita.setMaximumSize(new Dimension(ANCHO_COMPONENTE, 110));
        panelCajita.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 204, 51), 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

        reqLongitud = estiloReq("- Mínimo 5 caracteres");
        reqMayus = estiloReq("- Al menos una mayúscula");
        reqMinus = estiloReq("- Al menos una minúscula");
        reqNumero = estiloReq("- Al menos un número");
        reqEspecial = estiloReq("- Un carácter especial");

        panelCajita.add(reqLongitud);
        panelCajita.add(reqMayus);
        panelCajita.add(reqMinus);
        panelCajita.add(reqNumero);
        panelCajita.add(reqEspecial);
        
        panelCajita.setAlignmentX(Component.LEFT_ALIGNMENT);
        formulario.add(panelCajita);

        panelCentral.add(formulario);
        cf.add(panelCentral, BorderLayout.CENTER);

        JPanel panelbtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        panelbtn.setOpaque(false);
        JButton btnCrear = estiloBotones("CREAR");
        JButton btnVolver = estiloBotones("VOLVER");
        panelbtn.add(btnCrear);
        panelbtn.add(btnVolver);
        cf.add(panelbtn, BorderLayout.SOUTH);

        txtPass.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validar(); }
            public void removeUpdate(DocumentEvent e) { validar(); }
            public void changedUpdate(DocumentEvent e) { validar(); }
        });

        btnCrear.addActionListener(e -> manejarCrearPlayer(txtUser.getText(), new String(txtPass.getPassword())));
        btnVolver.addActionListener(e -> volverMenuInicio());

        this.setContentPane(cf);
    }

    private JComponent estiloCampoTexto(JTextField campo) {
        campo.setFont(new Font("Bodoni Bd BT", Font.PLAIN, 18));
        campo.setBackground(Color.BLACK);
        campo.setForeground(Color.WHITE);
        campo.setPreferredSize(new Dimension(ANCHO_COMPONENTE, 35));
        campo.setMaximumSize(new Dimension(ANCHO_COMPONENTE, 35));
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
        if (campo instanceof JTextField) {
            ((JTextField) campo).setCaretColor(Color.WHITE);
        }
        return campo;
    }

    private JLabel estiloEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(new Color(255, 204, 51));
        etiqueta.setFont(new Font("Bodoni Bd BT", Font.BOLD, 22));
        etiqueta.setAlignmentX(Component.LEFT_ALIGNMENT);
        etiqueta.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        return etiqueta;
    }

    private JLabel estiloReq(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(new Color(255, 80, 80));
        return label;
    }

    private void validar() {
        String pass = new String(txtPass.getPassword());
        actualizarColor(reqLongitud, pass.length() >= 5);
        actualizarColor(reqMayus, pass.matches(".*[A-Z].*"));
        actualizarColor(reqMinus, pass.matches(".*[a-z].*"));
        actualizarColor(reqNumero, pass.matches(".*[0-9].*"));
        actualizarColor(reqEspecial, pass.matches(".*[!@#$%^&+=.].*"));
    }

    private void actualizarColor(JLabel label, boolean cumple) {
        label.setForeground(cumple ? new Color(50, 255, 50) : new Color(255, 80, 80));
    }

    private JButton estiloBotones(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        btn.setPreferredSize(new Dimension(180, 45));
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 2));
        btn.setFocusPainted(false);
        return btn;
    }

    private void manejarCrearPlayer(String user, String pass) {
        try {
            validarPasswordEstructura(pass);
            if (sistema.crearPlayer(user, pass)) {
                Player nuevo = sistema.logIn(user, pass);
                new MenuPrincipal(nuevo, sistema).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "El usuario ya existe.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "La contraseña no cumple los requisitos.");
        }
    }

    private void validarPasswordEstructura(String password) throws Exception {
        final String PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{5,}$";
        if (password == null || !password.matches(PATTERN)) throw new Exception();
    }

    private void volverMenuInicio() {
        new MenuInicio(sistema).setVisible(true);
        this.dispose();
    }
}
