package proyecto;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
	private JTextField txtIngreseSuTelfono;
	private JTextField txtIngreseSuDireccin;

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
		btnRegistrar.setBounds(359, 372, 125, 38);
		add(btnRegistrar);
		
		JLabel imagen_izq_sup = new JLabel("");
		imagen_izq_sup.setHorizontalAlignment(SwingConstants.CENTER);
		imagen_izq_sup.setIcon(new ImageIcon("imagenes/logo.png"));
		imagen_izq_sup.setBounds(546, 61, 240, 175);
		add(imagen_izq_sup);
		
		JLabel imagen_izq_inf = new JLabel("New label");
		imagen_izq_inf.setBounds(546, 0, 240, 491);
		imagen_izq_inf.setIcon(new ImageIcon("imagenes/city.png"));
		add(imagen_izq_inf);
		
		JLabel lblRegistroDeUsuario = new JLabel("REGISTRO DE USUARIO");
		lblRegistroDeUsuario.setFont(new Font("Roboto", Font.BOLD, 28));
		lblRegistroDeUsuario.setBounds(20, 11, 348, 50);
		add(lblRegistroDeUsuario);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Roboto", Font.BOLD, 18));
		lblNombre.setBounds(20, 81, 99, 20);
		add(lblNombre);
		
		txtUsuario = new JTextField();
		txtUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtUsuario.getText().equals("INGRESE SU NOMBRE COMPLETO")) {
					txtUsuario.setForeground(new Color(0, 0, 0));
					txtUsuario.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtUsuario.getText().equals("")) {
					txtUsuario.setForeground(new Color(192, 192, 192));
					txtUsuario.setText("INGRESE SU NOMBRE COMPLETO");
				}
			}
		});
		txtUsuario.setText("INGRESE SU NOMBRE COMPLETO");
		txtUsuario.setForeground(Color.LIGHT_GRAY);
		txtUsuario.setFont(new Font("Roboto", Font.BOLD, 13));
		txtUsuario.setColumns(10);
		txtUsuario.setBorder(null);
		txtUsuario.setBounds(20, 112, 348, 26);
		add(txtUsuario);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(20, 149, 348, 2);
		add(separator);
		
		passContra1 = new JPasswordField();
		passContra1.setText("********");
		passContra1.setForeground(Color.LIGHT_GRAY);
		passContra1.setFont(new Font("Roboto", Font.BOLD, 13));
		passContra1.setBorder(null);
		passContra1.setBounds(20, 200, 348, 26);
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
		separator_1.setBounds(20, 234, 348, 2);
		add(separator_1);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Roboto", Font.BOLD, 18));
		lblContrasea.setBounds(20, 169, 99, 20);
		add(lblContrasea);
		
		passContra2 = new JPasswordField();
		passContra2.setText("********");
		passContra2.setForeground(Color.LIGHT_GRAY);
		passContra2.setFont(new Font("Roboto", Font.BOLD, 13));
		passContra2.setBorder(null);
		passContra2.setBounds(20, 290, 348, 20);
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
				if(String.valueOf(passContra2.getPassword()).equals("")) {
					passContra2.setForeground(new Color(192, 192, 192));
					passContra2.setText("********");
				}
			}
		});
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBounds(20, 321, 348, 2);
		add(separator_1_1);
		
		JLabel lblConfirmeSuContrasea = new JLabel("Confirme su contraseña");
		lblConfirmeSuContrasea.setFont(new Font("Roboto", Font.BOLD, 18));
		lblConfirmeSuContrasea.setBounds(20, 259, 216, 20);
		add(lblConfirmeSuContrasea);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_InicioSesion pantalla = new GUI_InicioSesion();
				JComponent thisPantalla = (JComponent) e.getSource();
				
				pantalla.getContentPane();
				pantalla.setVisible(true);
				pantalla.revalidate();
				pantalla.repaint();
				pantalla.getContentPane().setLayout(null);
				pantalla.setLocationRelativeTo(null);
				thisPantalla.setVisible(false);
				
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Roboto", Font.BOLD, 18));
		btnCancelar.setBackground(new Color(19, 45, 70));
		btnCancelar.setBounds(359, 421, 125, 38);
		add(btnCancelar);
		
		JLabel lblTelfono = new JLabel("Teléfono");
		lblTelfono.setFont(new Font("Roboto", Font.BOLD, 18));
		lblTelfono.setBounds(20, 334, 99, 20);
		add(lblTelfono);
		
		txtIngreseSuTelfono = new JTextField();
		txtIngreseSuTelfono.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtIngreseSuTelfono.getText().equals("INGRESE SU TELËFONO")) {
					txtIngreseSuTelfono.setForeground(new Color(0, 0, 0));
					txtIngreseSuTelfono.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtIngreseSuTelfono.getText().equals("")) {
					txtIngreseSuTelfono.setForeground(Color.LIGHT_GRAY);
					txtIngreseSuTelfono.setText("INGRESE SU TELËFONO");
				}
			}
		});
		txtIngreseSuTelfono.setText("INGRESE SU TELËFONO");
		txtIngreseSuTelfono.setForeground(Color.LIGHT_GRAY);
		txtIngreseSuTelfono.setFont(new Font("Roboto", Font.BOLD, 13));
		txtIngreseSuTelfono.setColumns(10);
		txtIngreseSuTelfono.setBorder(null);
		txtIngreseSuTelfono.setBounds(20, 365, 162, 20);
		add(txtIngreseSuTelfono);
		
		JLabel lblTelfono_1 = new JLabel("Dirección");
		lblTelfono_1.setFont(new Font("Roboto", Font.BOLD, 18));
		lblTelfono_1.setBounds(20, 409, 99, 20);
		add(lblTelfono_1);
		
		txtIngreseSuDireccin = new JTextField();
		txtIngreseSuDireccin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtIngreseSuDireccin.getText().equals("INGRESE SU DIRECCIÓN")) {
					txtIngreseSuDireccin.setForeground(new Color(0, 0, 0));
					txtIngreseSuDireccin.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtIngreseSuDireccin.getText().equals("")) {
					txtIngreseSuDireccin.setForeground(Color.LIGHT_GRAY);
					txtIngreseSuDireccin.setText("INGRESE SU DIRECCIÓN");
				}
			}
		});
		txtIngreseSuDireccin.setText("INGRESE SU DIRECCIÓN");
		txtIngreseSuDireccin.setForeground(Color.LIGHT_GRAY);
		txtIngreseSuDireccin.setFont(new Font("Roboto", Font.BOLD, 13));
		txtIngreseSuDireccin.setColumns(10);
		txtIngreseSuDireccin.setBorder(null);
		txtIngreseSuDireccin.setBounds(20, 440, 216, 28);
		add(txtIngreseSuDireccin);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setForeground(Color.BLACK);
		separator_1_1_1.setBounds(20, 396, 162, 2);
		add(separator_1_1_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(20, 478, 216, 2);
		add(separator_2);
		
		//Boton registrar
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexion enviaDatos = new conexion();
				String[] usuarioPart = txtUsuario.getText().split(" ");
				String nombre = txtUsuario.getText();
				String usuario = "";
				String tel = txtIngreseSuTelfono.getText();
				String direccion = txtIngreseSuDireccin.getText();
				if(usuarioPart.length == 4) {
					 usuario = usuarioPart[0].substring(0,1).toUpperCase() + usuarioPart[2].toUpperCase() + tel.substring(3,4) + tel.substring(8,9);
				} else {
					usuario = usuarioPart[0].substring(0,1).toUpperCase() + usuarioPart[1].toUpperCase() + tel.substring(3,4) + tel.substring(8,9);
				}
				String contraseña = String.valueOf(passContra1.getPassword());
				String contraseñaConfirm = String.valueOf(passContra2.getPassword());	
				enviaDatos.addNuevoUsuario(usuario, contraseña, contraseñaConfirm, tel, nombre, direccion);
				JOptionPane.showMessageDialog(null, "Su usuario es: " + usuario +"\n¡Ahora está listo para ingresar!");
				GUI_InicioSesion pantalla = new GUI_InicioSesion();
				JComponent thisPantalla = (JComponent) e.getSource();
				
				pantalla.getContentPane();
				pantalla.setVisible(true);
				pantalla.revalidate();
				pantalla.repaint();
				pantalla.getContentPane().setLayout(null);
				pantalla.setLocationRelativeTo(null);
				thisPantalla.setVisible(false);
				
			}
		});
		
	}
}