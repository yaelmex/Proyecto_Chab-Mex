package proyecto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument.Content;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.CardLayout;

public class GUI_Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Flatlaf decoración
		FlatLightLaf.setup();
		UIManager.put("Button.arc", 999);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Principal frame = new GUI_Principal();
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
	public GUI_Principal() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 995, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(255, 255, 255));
		fondo.setBounds(0, 0, 981, 599);
		contentPane.add(fondo);
		fondo.setLayout(null);
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(19, 45, 70));
		menu.setBounds(0, 0, 196, 588);
		fondo.add(menu);
		menu.setLayout(null);
		
		JPanel content = new JPanel();
		content.setBackground(new Color(1, 195, 142));
		content.setBounds(196, 116, 785, 472);
		fondo.add(content);
		content.setLayout(null);
		
		JLabel lblInversiones = new JLabel("Mis Inversiones");
		lblInversiones.setBorder(null);
		lblInversiones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblInversiones.setHorizontalAlignment(SwingConstants.CENTER);
		lblInversiones.setForeground(Color.WHITE);
		lblInversiones.setFont(new Font("Roboto", Font.BOLD, 20));
		lblInversiones.setBounds(0, 208, 196, 29);
		menu.add(lblInversiones);
		lblInversiones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblInversiones.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblInversiones.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				prueba p = new prueba();
				 if (content.isVisible()) {
	                    content.setVisible(false);
	                    contentPane.add(p);
	                    p.setVisible(true);
	                    p.repaint();
	                    p.revalidate();
	                } else { // Si el panel 2 está visible, ocúltalo y muestra el panel 1
	                	p.setVisible(false);
	                    content.setVisible(true);
	                    content.repaint();
	                    content.revalidate();
	                }
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Menú");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Roboto", Font.BOLD, 20));
		lblNewLabel_1.setBounds(0, 57, 196, 29);
		menu.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(30, 97, 138, 11);
		menu.add(separator);

		
		JLabel lblCuenta = new JLabel("Mi cuenta");
		lblCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCuenta.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCuenta.setForeground(Color.WHITE);
			}
		});
		lblCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuenta.setForeground(Color.WHITE);
		lblCuenta.setFont(new Font("Roboto", Font.BOLD, 20));
		lblCuenta.setBounds(0, 280, 196, 29);
		menu.add(lblCuenta);
		
		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOperaciones.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOperaciones.setForeground(Color.WHITE);
			}
		});
		lblOperaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblOperaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperaciones.setForeground(Color.WHITE);
		lblOperaciones.setFont(new Font("Roboto", Font.BOLD, 20));
		lblOperaciones.setBounds(0, 350, 196, 29);
		menu.add(lblOperaciones);
		
		JLabel lblSalir = new JLabel("Salir");
		lblSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSalir.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblSalir.setForeground(Color.WHITE);
			}
		});
		lblSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalir.setForeground(Color.WHITE);
		lblSalir.setFont(new Font("Roboto", Font.BOLD, 20));
		lblSalir.setBounds(0, 428, 196, 29);
		menu.add(lblSalir);
		
		JLabel lblfecha = new JLabel("[fecha]");
		lblfecha.setEnabled(false);
		lblfecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblfecha.setForeground(Color.WHITE);
		lblfecha.setFont(new Font("Roboto", Font.BOLD, 20));
		lblfecha.setBounds(0, 513, 196, 29);
		menu.add(lblfecha);
		Calendar calendario = Calendar.getInstance();
		String year = String.valueOf( calendario.get(Calendar.YEAR));
		String dia =  String.valueOf(calendario.get(Calendar.DATE));
		String mes = String.valueOf(calendario.get(Calendar.MONTH)+1);
		String fecha = dia+"/"+mes+"/"+year;
		lblfecha.setText(fecha);
		
		JLabel lblPrincipal = new JLabel("Principal");
		lblPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblPrincipal.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblPrincipal.setForeground(Color.WHITE);
			}
		});
		lblPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrincipal.setForeground(Color.WHITE);
		lblPrincipal.setFont(new Font("Roboto", Font.BOLD, 20));
		lblPrincipal.setBorder(null);
		lblPrincipal.setBounds(0, 138, 196, 29);
		menu.add(lblPrincipal);
		
		
		JPanel barra = new JPanel();
		barra.setBackground(new Color(19, 45, 70));
		barra.setBounds(196, 50, 785, 68);
		fondo.add(barra);
		barra.setLayout(null);
		
		JLabel lblNombre = new JLabel("¡Hola! [nombre del usuario]");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Roboto", Font.BOLD, 20));
		lblNombre.setBounds(10, 28, 259, 29);
		barra.add(lblNombre);
		
		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdioma.setForeground(Color.WHITE);
		lblIdioma.setFont(new Font("Roboto", Font.BOLD, 20));
		lblIdioma.setBounds(533, 28, 118, 29);
		barra.add(lblIdioma);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Roboto", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Español", "Maya"}));
		comboBox.setBounds(661, 27, 114, 29);
		barra.add(comboBox);
		

		
		JLabel lblNewLabel = new JLabel("¡Impulsando tú patrimonio! ");
		lblNewLabel.setIcon(new ImageIcon("imagenes/favicon.png"));
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		lblNewLabel.setBounds(208, 11, 273, 28);
		fondo.add(lblNewLabel);
	}
}
