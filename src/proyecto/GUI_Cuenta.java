package proyecto;

import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_Cuenta extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public GUI_Cuenta() {
		setBackground(new Color(255, 255, 255));
		setBounds(new Rectangle(196, 116, 785, 472));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mis inversiones");
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 10, 202, 37);
		add(lblNewLabel);
		
		JLabel lblMenMis = new JLabel("Menú / Mis inversiones");
		lblMenMis.setFont(new Font("Roboto", Font.BOLD, 15));
		lblMenMis.setBounds(10, 42, 166, 29);
		add(lblMenMis);
		
		JButton btnNewButton = new JButton("Mis inversiones");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_VerInversiones pantalla = new GUI_VerInversiones();
				removeAll();
				repaint();
				revalidate();
				add(pantalla);
				repaint();
				revalidate();
			}
		});
		btnNewButton.setBounds(71, 85, 166, 109);
		add(btnNewButton);
		
		JLabel lblRevisarInversiones = new JLabel("Inversiones activas");
		lblRevisarInversiones.setHorizontalAlignment(SwingConstants.CENTER);
		lblRevisarInversiones.setFont(new Font("Roboto", Font.BOLD, 15));
		lblRevisarInversiones.setBounds(71, 200, 166, 29);
		add(lblRevisarInversiones);
		
		JButton btnConfiguraciones = new JButton("Configuraciones");
		btnConfiguraciones.setBounds(71, 268, 166, 109);
		add(btnConfiguraciones);
		
		JLabel lblConfiguracion = new JLabel("Configuración");
		lblConfiguracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfiguracion.setFont(new Font("Roboto", Font.BOLD, 15));
		lblConfiguracion.setBounds(71, 383, 166, 29);
		add(lblConfiguracion);
		
		JButton btnHistorialDeInversiones = new JButton("Historial de inversiones");
		btnHistorialDeInversiones.setBounds(315, 85, 166, 109);
		add(btnHistorialDeInversiones);
		
		JLabel lblHistorialDeInversiones = new JLabel("Historial de Inversiones");
		lblHistorialDeInversiones.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialDeInversiones.setFont(new Font("Roboto", Font.BOLD, 15));
		lblHistorialDeInversiones.setBounds(309, 200, 183, 29);
		add(lblHistorialDeInversiones);
		
		JButton btnConfTarjetas = new JButton("Conf. Tarjetas");
		btnConfTarjetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConfTarjetas.setBounds(548, 85, 166, 109);
		add(btnConfTarjetas);
		
		JLabel lblTarjetas = new JLabel("Cuentas y tarjetas");
		lblTarjetas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTarjetas.setFont(new Font("Roboto", Font.BOLD, 15));
		lblTarjetas.setBounds(548, 200, 166, 29);
		add(lblTarjetas);

	}
}
