package proyecto;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JPasswordField passContra1;
	private JPasswordField passContra2;
	GUI_InicioSesion pantalla = new GUI_InicioSesion();

	/**
	 * Create the panel.
	 */
	public RegistrarUsuario() {
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 786, 491);
		setLayout(null);
		
		JButton btnRegistrar = new JButton("Registrarse");
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setBackground(new Color(19, 45, 70));
		btnRegistrar.setFont(new Font("Roboto", Font.BOLD, 18));
		btnRegistrar.setBounds(200, 419, 149, 50);
		add(btnRegistrar);
		
		JLabel imagen_izq_sup = new JLabel("");
		imagen_izq_sup.setHorizontalAlignment(SwingConstants.CENTER);
		imagen_izq_sup.setIcon(new ImageIcon("imagenes/logo.png"));
		imagen_izq_sup.setBounds(510, 61, 276, 175);
		add(imagen_izq_sup);
		
		JLabel imagen_izq_inf = new JLabel("New label");
		imagen_izq_inf.setBounds(510, 0, 276, 491);
		imagen_izq_inf.setIcon(new ImageIcon("imagenes/city.png"));
		add(imagen_izq_inf);
		
		JLabel Logo = new JLabel("Itzá Inversiones");
		Logo.setFont(new Font("Roboto", Font.BOLD, 25));
		Logo.setIcon(new ImageIcon("imagenes/favicon.png"));
		Logo.setBounds(10, 20, 276, 60);
		add(Logo);
		
		JLabel lblRegistroDeUsuario = new JLabel("REGISTRO DE USUARIO");
		lblRegistroDeUsuario.setFont(new Font("Roboto", Font.BOLD, 28));
		lblRegistroDeUsuario.setBounds(20, 91, 348, 50);
		add(lblRegistroDeUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Roboto", Font.BOLD, 18));
		lblUsuario.setBounds(20, 169, 99, 20);
		add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtUsuario.getText().equals("INGRESE SU USUARIO")) {
					txtUsuario.setForeground(new Color(0, 0, 0));
					txtUsuario.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtUsuario.getText().equals("")) {
					txtUsuario.setForeground(new Color(192, 192, 192));
					txtUsuario.setText("INGRESE SU USUARIO");
				}
			}
		});
		txtUsuario.setText("INGRESE SU USUARIO");
		txtUsuario.setForeground(Color.LIGHT_GRAY);
		txtUsuario.setFont(new Font("Roboto", Font.BOLD, 13));
		txtUsuario.setColumns(10);
		txtUsuario.setBorder(null);
		txtUsuario.setBounds(20, 200, 348, 26);
		add(txtUsuario);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(20, 232, 348, 2);
		add(separator);
		
		passContra1 = new JPasswordField();
		passContra1.setText("********");
		passContra1.setForeground(Color.LIGHT_GRAY);
		passContra1.setFont(new Font("Roboto", Font.BOLD, 13));
		passContra1.setBorder(null);
		passContra1.setBounds(20, 292, 348, 26);
		add(passContra1);
		passContra1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(passContra1.getPassword()).equals("********")) {
					passContra1.setForeground(new Color(0, 0, 0));
					passContra1.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(String.valueOf(passContra1.getPassword()).equals("")) {
					passContra1.setForeground(new Color(192, 192, 192));
					passContra1.setText("********");
				}
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(20, 324, 348, 2);
		add(separator_1);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Roboto", Font.BOLD, 18));
		lblContrasea.setBounds(20, 266, 99, 20);
		add(lblContrasea);
		
		passContra2 = new JPasswordField();
		passContra2.setText("********");
		passContra2.setForeground(Color.LIGHT_GRAY);
		passContra2.setFont(new Font("Roboto", Font.BOLD, 13));
		passContra2.setBorder(null);
		passContra2.setBounds(20, 365, 348, 26);
		add(passContra2);
		passContra2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(passContra2.getPassword()).equals("********")) {
					passContra2.setForeground(new Color(0, 0, 0));
					passContra2.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(String.valueOf(passContra1.getPassword()).equals("")) {
					passContra2.setForeground(new Color(192, 192, 192));
					passContra2.setText("********");
				}
			}
		});
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBounds(20, 397, 348, 2);
		add(separator_1_1);
		
		JLabel lblConfirmeSuContrasea = new JLabel("Confirme su contraseña");
		lblConfirmeSuContrasea.setFont(new Font("Roboto", Font.BOLD, 18));
		lblConfirmeSuContrasea.setBounds(20, 339, 216, 20);
		add(lblConfirmeSuContrasea);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_InicioSesion pantalla = new GUI_InicioSesion();
				JComponent thisPantalla = (JComponent) e.getSource();
				thisPantalla.setVisible(false);
				pantalla.getContentPane();
				pantalla.setVisible(true);
				pantalla.revalidate();
				pantalla.repaint();
				pantalla.getContentPane().setLayout(null);
				pantalla.setLocationRelativeTo(null);
				
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Roboto", Font.BOLD, 18));
		btnCancelar.setBackground(new Color(19, 45, 70));
		btnCancelar.setBounds(21, 419, 149, 50);
		add(btnCancelar);
		
		//Boton registrar
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexion enviaDatos = new conexion();
				enviaDatos.addNuevoUsuario(txtUsuario.getText(), passContra1.getText(), TOOL_TIP_TEXT_KEY);
				
			}
		});
		
	}
}
