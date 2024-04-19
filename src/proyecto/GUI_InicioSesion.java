package proyecto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_InicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passContraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatLightLaf.setup();
		UIManager.put("Button.arc", 999);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_InicioSesion frame = new GUI_InicioSesion();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_InicioSesion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/favicon.png"));
		setTitle("Itzá Inversiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(255, 255, 255));
		fondo.setBounds(0, 0, 786, 491);
		contentPane.add(fondo);
		fondo.setLayout(null);
		
		JLabel imagen_izq_sup = new JLabel("");
		imagen_izq_sup.setHorizontalAlignment(SwingConstants.CENTER);
		imagen_izq_sup.setIcon(new ImageIcon("imagenes/logo.png"));
		imagen_izq_sup.setBounds(510, 61, 276, 175);
		fondo.add(imagen_izq_sup);
		
		JLabel imagen_izq_inf = new JLabel("New label");
		imagen_izq_inf.setIcon(new ImageIcon("imagenes/city.png"));
		imagen_izq_inf.setBounds(510, 0, 276, 491);
		fondo.add(imagen_izq_inf);
		
		JLabel Logo = new JLabel("Itzá Inversiones");
		Logo.setFont(new Font("Roboto", Font.BOLD, 25));
		Logo.setIcon(new ImageIcon("imagenes/favicon.png"));
		Logo.setBounds(20, 28, 276, 60);
		fondo.add(Logo);
		
		JLabel lblNewLabel = new JLabel("INICIAR SESIÓN");
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 28));
		lblNewLabel.setBounds(30, 99, 304, 50);
		fondo.add(lblNewLabel);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Roboto", Font.BOLD, 18));
		lblUsuario.setBounds(30, 177, 99, 20);
		fondo.add(lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textUsuario.getText().equals("INGRESE SU USUARIO")) {
					textUsuario.setForeground(new Color(0, 0, 0));
					textUsuario.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textUsuario.getText().equals("")) {
					textUsuario.setForeground(new Color(192, 192, 192));
					textUsuario.setText("INGRESE SU USUARIO");
				}
			}
		});
		textUsuario.setFont(new Font("Roboto", Font.BOLD, 13));
		textUsuario.setBorder(null);
		textUsuario.setForeground(new Color(192, 192, 192));
		textUsuario.setText("INGRESE SU USUARIO");
		textUsuario.setBounds(30, 208, 348, 26);
		fondo.add(textUsuario);
		textUsuario.setColumns(10);
		
		
		JLabel lblCrearNuevo = new JLabel("¿Nuevo en la app? ¡Registrate!");
		lblCrearNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCrearNuevo.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCrearNuevo.setForeground(Color.BLACK);
			}
		});
		lblCrearNuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCrearNuevo.setFont(new Font("Roboto", Font.BOLD, 15));
		lblCrearNuevo.setBounds(257, 408, 213, 20);
		fondo.add(lblCrearNuevo);
		
		JButton btnIngresar = new JButton("INGRESAR");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contraseña = String.valueOf(passContraseña.getPassword());
				String usuario = textUsuario.getText();
			
			}
		});
		btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnIngresar.setForeground(new Color(255, 255, 255));
		btnIngresar.setBackground(new Color(19, 45, 70));
		btnIngresar.setFont(new Font("Roboto", Font.BOLD, 17));
		btnIngresar.setBounds(30, 392, 199, 50);
		fondo.add(btnIngresar);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(30, 240, 348, 2);
		fondo.add(separator);
		
		passContraseña = new JPasswordField();
		passContraseña.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(passContraseña.getPassword()).equals("********")) {
					passContraseña.setForeground(new Color(0, 0, 0));
					passContraseña.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(String.valueOf(passContraseña.getPassword()).equals("")) {
					passContraseña.setForeground(new Color(192, 192, 192));
					passContraseña.setText("********");
				}
			}
		});
		passContraseña.setText("********");
		passContraseña.setForeground(new Color(192, 192, 192));
		passContraseña.setFont(new Font("Roboto", Font.BOLD, 13));
		passContraseña.setBorder(null);
		passContraseña.setBounds(30, 307, 348, 26);
		fondo.add(passContraseña);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(30, 340, 348, 2);
		fondo.add(separator_1);
	}
}
