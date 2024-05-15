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
import java.awt.Component;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JSeparator;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class GUI_Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	GUI_Inversiones p = new GUI_Inversiones();
	GUI_Operaciones o = new GUI_Operaciones();
	GUI_Cuenta c = new GUI_Cuenta();
	public static String idioma = "";
	
	

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
		setResizable(false);
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 995, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel menu = new JPanel(); // color rosa
		menu.setBackground(new Color(19, 45, 70));
		menu.setBounds(0, 0, 196, 588);
	    getContentPane().add(menu);
		menu.setLayout(null);
		
		JPanel Principal = new JPanel(); // Color verde
		Principal.setBackground(new Color(255, 255, 255));
		Principal.setBounds(196, 116, 785, 472);
		getContentPane().add(Principal);
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
		lblFecha.setBounds(24, 418, 280, 43);
		Principal.add(lblFecha);
		Calendar calendario = Calendar.getInstance();
		String year = String.valueOf( calendario.get(Calendar.YEAR));
		String dia =  String.valueOf(calendario.get(Calendar.DATE));
		String mes = String.valueOf(calendario.get(Calendar.MONTH)+1);
		String fecha = dia+"/"+mes+"/"+year;
		lblFecha.setText("Fecha: " + fecha);
		
		JLabel lbllogo = new JLabel("");
		lbllogo.setIcon(new ImageIcon("imagenes/logo.png"));
		lbllogo.setHorizontalAlignment(SwingConstants.LEFT);
		lbllogo.setForeground(Color.BLACK);
		lbllogo.setFont(new Font("Roboto", Font.BOLD, 20));
		lbllogo.setBounds(544, 261, 200, 200);
		Principal.add(lbllogo);
		
		JLabel lblIdioma = new JLabel("IDIOMA: ");
		lblIdioma.setBounds(24, 267, 118, 29);
		Principal.add(lblIdioma);
		lblIdioma.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdioma.setForeground(new Color(0, 0, 0));
		lblIdioma.setFont(new Font("Roboto", Font.BOLD, 20));
		
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
				GUI_Inversiones pantalla = new GUI_Inversiones();
				JPanel contenedor = (JPanel) contentPane.getComponentAt(729, 447);
				getContentPane().remove(contenedor);
				contentPane.add(pantalla);
				pantalla.revalidate();
				pantalla.repaint();
			}
		});
		
		JLabel lblMenu = new JLabel("Menú");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setForeground(new Color(255, 255, 255));
		lblMenu.setFont(new Font("Roboto", Font.BOLD, 20));
		lblMenu.setBounds(0, 57, 196, 29);
		menu.add(lblMenu);
		
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
				GUI_Cuenta pantalla = new GUI_Cuenta();
				JPanel contenedor = (JPanel) contentPane.getComponentAt(729, 447);
				getContentPane().remove(contenedor);
				contentPane.add(pantalla);
				pantalla.revalidate();
				pantalla.repaint();
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
				GUI_Operaciones pantalla = new GUI_Operaciones();
				JPanel contenedor = (JPanel) contentPane.getComponentAt(729, 447);
				getContentPane().remove(contenedor);
				contentPane.add(pantalla);
				pantalla.revalidate();
				pantalla.repaint();
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
			@Override
			public void mouseClicked(MouseEvent e) {
				int decision = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas salir?");
				if(decision == 0) {
					JOptionPane.showMessageDialog(null, "Sesión Cerrada exitosamente", "Proceso éxitoso", JOptionPane.INFORMATION_MESSAGE);
					GUI_InicioSesion frame = new GUI_InicioSesion();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Proceso cancelado", "Proceso Cancelado", JOptionPane.INFORMATION_MESSAGE);
				}
				
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
				JPanel contenedor = (JPanel) contentPane.getComponentAt(729, 447);
				getContentPane().remove(contenedor);
				contenedor.setVisible(false);
				getContentPane().add(Principal);
				Principal.setVisible(true);
				Principal.add(lblBienvenidoAItz);
				Principal.add(lblTPatrimonio);
				Principal.add(lblEsteEsEl);
				Principal.add(lblEnLaBarra);
				Principal.add(lblFecha);
				Principal.revalidate();
				Principal.repaint();
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
		getContentPane().add(barra);
		barra.setLayout(null);
		
		JLabel lblNombre = new JLabel("¡Hola! [nombre del usuario]");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Roboto", Font.BOLD, 20));
		lblNombre.setBounds(10, 28, 259, 29);
		barra.add(lblNombre);
		lblNombre.setText("¡Hola! " + GUI_InicioSesion.user);

		JLabel lblNewLabel = new JLabel("¡Impulsando tú patrimonio! ");
		lblNewLabel.setIcon(new ImageIcon("imagenes/favicon.png"));
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		lblNewLabel.setBounds(208, 11, 273, 28);
		getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(124, 267, 114, 29);
		Principal.add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				
				idioma = comboBox.getSelectedItem().toString();
				if(idioma.equals("Maya")) {
					lblIdioma.setText("ajaw");
					lblNombre.setText("kó'ko " + GUI_InicioSesion.user);
					lblMenu.setText("ajkun");
					lblPrincipal.setText("k'uchul");
					lblInversiones.setText("Winikchaj");
					lblCuenta.setText("Inwinaleob");
					lblOperaciones.setText("Utzilal");
					lblSalir.setText("ts'o'ok");
					lblBienvenidoAItz.setText("K'aaba' Itzá Inversiones le túumben");
					lblTPatrimonio.setText("ka'a'ab ku yóok'ol k'aaba'al");
					lblEsteEsEl.setText("U k'iino' ti' le k'a'anal jump'éel, bix a k'o'olal u táanakil");
					lblEnLaBarra.setText("Tuukul tuunichil");
					lblFecha.setText("K'iino' " + fecha);
				} else {
					lblIdioma.setText("IDIOMA:");
					lblNombre.setText("¡Hola! " + GUI_InicioSesion.user);
					lblMenu.setText("Menu");
					lblPrincipal.setText("Principal");
					lblInversiones.setText("Invertir");
					lblCuenta.setText("Mis Inversiones");
					lblOperaciones.setText("Operaciones");
					lblSalir.setText("Salir");
					lblBienvenidoAItz.setText("¡BIENVENIDO A ITZÁ INVERSIONES, EL LUGAR EN DONDE IMPULSAMOS\r\n");
					lblTPatrimonio.setText("TÚ PATRIMONIO!");
					lblEsteEsEl.setText("ESTA ES LA PANTALLA PRINCIPAL, POR FAVOR ELIJA SUS OPCIONES \r\n");
					lblEnLaBarra.setText("EN LA BARRA LATERAL.");
				}
				
			}
		});
		comboBox.setFont(new Font("Roboto", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Español", "Maya"}));
	}
	
}