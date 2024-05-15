package proyecto;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Configuraciones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtUserName;
	private JTextField txtUserPassword;
	private JLabel lblUsuario;
	private JTextField txtUserCode;
	private JTextField txtPhoneNumber;
	private JTextField txtAdress;
	conexion llenarTabla = new conexion();

	/**
	 * Create the panel.
	 */
	public Configuraciones() {
		
		setBackground(new Color(19, 45, 70));
		setBounds(new Rectangle(0, 0, 785, 472));
		setLayout(null);
		llenarTabla.getUserData(GUI_InicioSesion.user);
		
		JPanel panel = new JPanel();
		panel.setBounds(127, 75, 525, 287);
		add(panel);
		panel.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setText(GUI_InicioSesion.user);
		txtUserName.setEditable(false);
		txtUserName.setBounds(190, 32, 271, 20);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblUsuario.setBounds(20, 35, 68, 14);
		panel.add(lblUsuario);
		
		txtUserPassword = new JTextField();
		txtUserPassword.setText(llenarTabla.DatosUsuario.get(0).toString());
		txtUserPassword.setColumns(10);
		txtUserPassword.setBounds(190, 132, 268, 20);
		panel.add(txtUserPassword);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblContrasena.setBounds(20, 135, 119, 14);
		panel.add(lblContrasena);
		
		txtUserCode = new JTextField();
		txtUserCode.setText(GUI_InicioSesion.user);
		txtUserCode.setEditable(false);
		txtUserCode.setColumns(10);
		txtUserCode.setBounds(190, 79, 271, 20);
		panel.add(txtUserCode);
		
		JLabel UserCode = new JLabel("Código de usuario");
		UserCode.setFont(new Font("Roboto", Font.PLAIN, 15));
		UserCode.setBounds(20, 82, 144, 14);
		panel.add(UserCode);
		
		JLabel lblNumeroDeTelfono = new JLabel("Numero de Teléfono");
		lblNumeroDeTelfono.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblNumeroDeTelfono.setBounds(20, 189, 164, 14);
		panel.add(lblNumeroDeTelfono);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setText(llenarTabla.DatosUsuario.get(1).toString());
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(190, 187, 268, 20);
		panel.add(txtPhoneNumber);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblDomicilio.setBounds(20, 240, 164, 14);
		panel.add(lblDomicilio);
		
		txtAdress = new JTextField();
		txtAdress.setText(llenarTabla.DatosUsuario.get(2).toString());
		txtAdress.setColumns(10);
		txtAdress.setBounds(190, 238, 268, 20);
		panel.add(txtAdress);
		
		JButton btnActualizar = new JButton("Actualizar datos");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTabla.setUserData(GUI_InicioSesion.user, txtPhoneNumber.getText(), txtUserPassword.getText(), txtAdress.getText());
				JOptionPane.showMessageDialog(null, "Información actualizada con éxito");
			}
		});
		btnActualizar.setBounds(272, 385, 221, 57);
		add(btnActualizar);
		
		JLabel lblNewLabel = new JLabel("Datos de Cuenta");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 25));
		lblNewLabel.setBounds(0, 29, 785, 23);
		add(lblNewLabel);
		
		if(GUI_Principal.idioma.equals("Maya")) {
			btnActualizar.setText("Tumbenik u tuunich u k'abaal");
			lblNewLabel.setText("Datos tuunich u k'abaal");
			lblUsuario.setText("Yuumkab");
			lblContrasena.setText("K'a'abet");
			UserCode.setText("Tzol u yuumkab");
			lblNumeroDeTelfono.setText("Tzolaj k'iin");
			lblDomicilio.setText("Tzol u ki");
		} else {
			btnActualizar.setText("Actualizar datos");
			lblNewLabel.setText("Datos de Cuenta");
			lblUsuario.setText("Usuario");
			lblContrasena.setText("Contraseña");
			UserCode.setText("Código de usuario");
			lblNumeroDeTelfono.setText("Numero de Teléfono");
			lblDomicilio.setText("Domicilio");
		}
		
	}
}