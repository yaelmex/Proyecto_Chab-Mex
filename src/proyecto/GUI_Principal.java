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
import javax.swing.SwingUtilities;
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
	GUI_Inversiones p = new GUI_Inversiones();
	GUI_Operaciones o = new GUI_Operaciones();
	GUI_Cuenta c = new GUI_Cuenta();
	
	

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
		
		JPanel fondo = new JPanel(); // Color blanco
		fondo.setBackground(new Color(255, 255, 255)); 
		fondo.setBounds(0, 0, 981, 599);
		contentPane.add(fondo);
		fondo.setLayout(null);
		
		JPanel menu = new JPanel(); // color rosa
		menu.setBackground(new Color(19, 45, 70));
		menu.setBounds(0, 0, 196, 588);
		fondo.add(menu);
		menu.setLayout(null);
		
		JPanel Principal = new JPanel(); // Color verde
		Principal.setBackground(new Color(255, 255, 255));
		Principal.setBounds(196, 116, 785, 472);
		fondo.add(Principal);
		Principal.setLayout(null);
		
		JLabel lblBienvenidoAItz = new JLabel("¡BIENVENIDO A ITZÁ INVERSIONES, EL LUGAR EN DONDE IMPULSAMOS\r\n");
		lblBienvenidoAItz.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenidoAItz.setForeground(new Color(0, 0, 0));
		lblBienvenidoAItz.setFont(new Font("Roboto", Font.BOLD, 20));
		lblBienvenidoAItz.setBounds(24, 21, 665, 57);
		Principal.add(lblBienvenidoAItz);
		
		JLabel lblTPatrimonio = new JLabel("TÚ PATRIMONIO!");
		lblTPatrimonio.setHorizontalAlignment(SwingConstants.CENTER);
		lblTPatrimonio.setForeground(new Color(0, 0, 0));
		lblTPatrimonio.setFont(new Font("Roboto", Font.BOLD, 20));
		lblTPatrimonio.setBounds(24, 68, 665, 57);
		Principal.add(lblTPatrimonio);
		
		JLabel lblEsteEsEl = new JLabel("ESTA ES LA PANTALLA PRINCIPAL, POR FAVOR ELIJA SUS OPCIONES \r\n");
		lblEsteEsEl.setHorizontalAlignment(SwingConstants.LEFT);
		lblEsteEsEl.setForeground(Color.BLACK);
		lblEsteEsEl.setFont(new Font("Roboto", Font.BOLD, 20));
		lblEsteEsEl.setBounds(24, 149, 720, 57);
		Principal.add(lblEsteEsEl);
		
		JLabel lblEnLaBarra = new JLabel("EN LA BARRA LATERAL.");
		lblEnLaBarra.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnLaBarra.setForeground(Color.BLACK);
		lblEnLaBarra.setFont(new Font("Roboto", Font.BOLD, 20));
		lblEnLaBarra.setBounds(24, 199, 720, 57);
		Principal.add(lblEnLaBarra);
		
		JLabel lblFecha = new JLabel("Fecha: []");
		lblFecha.setEnabled(false);
		lblFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblFecha.setForeground(Color.BLACK);
		lblFecha.setFont(new Font("Roboto", Font.BOLD, 20));
		lblFecha.setBounds(24, 363, 280, 43);
		Principal.add(lblFecha);
		Calendar calendario = Calendar.getInstance();
		String year = String.valueOf( calendario.get(Calendar.YEAR));
		String dia =  String.valueOf(calendario.get(Calendar.DATE));
		String mes = String.valueOf(calendario.get(Calendar.MONTH)+1);
		String fecha = dia+"/"+mes+"/"+year;
		lblFecha.setText("Fecha: " + fecha);
		
		JLabel lblHora = new JLabel("Hora: ");
		lblHora.setHorizontalAlignment(SwingConstants.LEFT);
		lblHora.setForeground(Color.BLACK);
		lblHora.setFont(new Font("Roboto", Font.BOLD, 20));
		lblHora.setEnabled(false);
		lblHora.setBounds(24, 418, 280, 43);
		Principal.add(lblHora);
		
		JLabel lbllogo = new JLabel("");
		lbllogo.setIcon(new ImageIcon("imagenes/logo.png"));
		lbllogo.setHorizontalAlignment(SwingConstants.LEFT);
		lbllogo.setForeground(Color.BLACK);
		lbllogo.setFont(new Font("Roboto", Font.BOLD, 20));
		lbllogo.setBounds(544, 261, 200, 200);
		Principal.add(lbllogo);
		
		JLabel lblInversiones = new JLabel("Invertir");
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
			public void mouseClicked(MouseEvent event) {
				if(Principal.isShowing()) {
					cambiarPantallas(Principal, p);
				} else if(c.isShowing()) {
					cambiarPantallas(c, p);
				} else {
					cambiarPantallas(o, p);
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

		
		JLabel lblCuenta = new JLabel("Mis Inversiones");
		lblCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCuenta.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCuenta.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Principal.isShowing()) {
					cambiarPantallas(Principal, c);
				} else if(p.isShowing()) {
					cambiarPantallas(p, c);
				} else {
					cambiarPantallas(o, c);
				}
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
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Principal.isShowing()) {
					cambiarPantallas(Principal, o);
				} else if(c.isShowing()) {
					cambiarPantallas(c, o);
				} else {
					cambiarPantallas(p, o);
				}
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
		
		
		
		JLabel lblPrincipal = new JLabel("Principal");
		lblPrincipal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblPrincipal.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblPrincipal.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(p.isShowing()) {
					cambiarPantallas(p, Principal);
				} else if(c.isShowing()) {
					cambiarPantallas(c, Principal);
				} else {
					cambiarPantallas(o, Principal);
				}
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
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Roboto", Font.BOLD, 20));
		lblNombre.setBounds(10, 28, 259, 29);
		barra.add(lblNombre);
		lblNombre.setText("¡Hola! " + GUI_InicioSesion.user);
		
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
	
	public void cambiarPantallas(JPanel p1, JPanel p2) {
		
		if(p1.isShowing() == true) {
		 	contentPane.add(p2);
            p2.setVisible(true);
            p2.repaint();
            p2.revalidate();
		}
		else{
			p2.setVisible(false);
			p1.setVisible(true);
            p1.repaint();
            p1.revalidate();
		}
	}
}