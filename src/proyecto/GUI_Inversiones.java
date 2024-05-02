package proyecto;

import javax.swing.JPanel;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.components.DatePicker;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class GUI_Inversiones extends JPanel {

	private static final long serialVersionUID = 1L;
	conexion inversiones = new conexion();
	DefaultComboBoxModel modelo = new DefaultComboBoxModel();
	private JTextField textMonto;
	private JTable tablaSimul;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	int validador = 0;

	/**
	 * Create the panel.
	 */
	public GUI_Inversiones() {
		setBackground(new Color(19, 45, 70));
		setBounds(new Rectangle(196, 116, 785, 472));
		setLayout(null);
		
		JComboBox comboInversiones = new JComboBox();
		comboInversiones.setFont(new Font("Roboto", Font.BOLD, 12));
		comboInversiones.setBounds(214, 186, 186, 22);
		add(comboInversiones);
		
		DatePicker Inicio = new DatePicker();
		Inicio.setEnabled(false);
		Inicio.setBounds(141, 253, 186, 22);
		add(Inicio);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Plazo:");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Roboto", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(53, 253, 82, 18);
		add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Inicio");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Roboto", Font.BOLD, 12));
		lblNewLabel_1_1_1_1.setBounds(141, 287, 82, 18);
		add(lblNewLabel_1_1_1_1);
		
		DatePicker Fin = new DatePicker();
		Fin.setEnabled(false);
		Fin.setBounds(357, 254, 186, 22);
		add(Fin);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Fin");
		lblNewLabel_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Roboto", Font.BOLD, 12));
		lblNewLabel_1_1_1_1_1.setBounds(357, 290, 82, 18);
		add(lblNewLabel_1_1_1_1_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane.setBounds(438, 43, 318, 165);
		add(scrollPane);
		
		tablaSimul = new JTable();
		tablaSimul.setColumnSelectionAllowed(true);
		tablaSimul.setCellSelectionEnabled(true);
		scrollPane.setViewportView(tablaSimul);
		tablaSimul.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				
			}
		));
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Simulador");
		lblNewLabel_1_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_2.setFont(new Font("Roboto", Font.BOLD, 12));
		lblNewLabel_1_1_1_1_2.setBounds(692, 216, 66, 18);
		add(lblNewLabel_1_1_1_1_2);
		
		
		JComboBox comboTiempo = new JComboBox();
		comboTiempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eleccion = comboTiempo.getSelectedItem().toString();
				
				ArrayList<String> empresas = new ArrayList<String>();
				empresas = inversiones.FiltrarInversiones(eleccion);
				modelo.removeAllElements();
				comboInversiones.setModel(modelo);
				for(int i = 0; i < empresas.size(); i++) {
					modelo.addElement(empresas.get(i));
				}
				comboInversiones.setModel(modelo);
				
				Inicio.setDateToToday();
				LocalDate hoy = Inicio.getDate();
				
				switch(eleccion) {
				case "1 Mes":	Fin.setDate(hoy.plusMonths(1)); break;	
				case "3 Meses": Fin.setDate(hoy.plusMonths(3)); break;
				case "6 Meses": Fin.setDate(hoy.plusMonths(6)); break;
				case"1 Año": Fin.setDate(hoy.plusYears(1)); break;
				case"2 Años": Fin.setDate(hoy.plusYears(2)); break;
				case"3 Años": Fin.setDate(hoy.plusYears(3)); break;
				case"4 Años": Fin.setDate(hoy.plusYears(4)); break;
				case"5 Años": Fin.setDate(hoy.plusYears(5)); break;			
				}
				

			}
		});
		comboTiempo.setFont(new Font("Roboto", Font.BOLD, 12));
		comboTiempo.setModel(new DefaultComboBoxModel(new String[] {"1 Mes", "3 Meses", "6 Meses", "1 Año", "2 Años", "3 Años", "4 Años", "5 Años"}));
		comboTiempo.setBounds(141, 72, 186, 22);
		add(comboTiempo);
		
		JButton btnSimular = new JButton("SIMULAR");
		btnSimular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double monto = Double.parseDouble(textMonto.getText());				
				String opcion = comboInversiones.getSelectedItem().toString();
				String eleccion = comboTiempo.getSelectedItem().toString();
				
				float tiempo = 0;
				
				switch(eleccion) {
				case "1 Mes":	tiempo = 1; break;	
				case "3 Meses": tiempo = 3; break;
				case "6 Meses": tiempo = 6; break;
				case"1 Año": tiempo = 12; break;
				case"2 Años": tiempo = 24; break;
				case"3 Años": tiempo = 36; break;
				case"4 Años": tiempo = 48; break;
				case"5 Años": tiempo = 60; break;			
				}
				
				if(opcion.equals("ORO") && monto < 1391.80) {
					JOptionPane.showMessageDialog(null, "El monto mínimo a invertir en oro es de: 1391.80 pesos mexicanos");
				} else {
					Object[] filas = inversiones.simularInversion(opcion, monto, tiempo, eleccion);
					
					if(validador == 0) {
						tablaSimul.setModel(modeloTabla);
						modeloTabla.addColumn("Monto Incial");
						modeloTabla.addColumn("Tasa");
						modeloTabla.addColumn("Rendimiento");
						modeloTabla.addColumn("Monto Final");
						modeloTabla.addRow(new Object[] {filas[0].toString(), filas[1].toString(), filas[2].toString(), filas[3].toString()});
						validador++;
					} else {
						modeloTabla.addRow(new Object[] {filas[0].toString(), filas[1].toString(), filas[2].toString(), filas[3].toString()});
					}
				}	
			}
		});
		btnSimular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSimular.setForeground(Color.WHITE);
		btnSimular.setFont(new Font("Roboto", Font.BOLD, 17));
		btnSimular.setBackground(new Color(19, 45, 70));
		btnSimular.setBounds(285, 357, 199, 50);
		add(btnSimular);
		
		
		JLabel lblNewLabel = new JLabel("Tiempo");
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(53, 73, 112, 18);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Monto:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Roboto", Font.BOLD, 12));
		lblNewLabel_1.setBounds(53, 123, 76, 18);
		add(lblNewLabel_1);
		
		textMonto = new JTextField();
		textMonto.setFont(new Font("Roboto", Font.BOLD, 11));
		textMonto.setBounds(141, 123, 186, 20);
		add(textMonto);
		textMonto.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Inversiones para ti:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Roboto", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(53, 187, 149, 18);
		add(lblNewLabel_1_1);
		
		JButton btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filas = modeloTabla.getRowCount();
				for(int i = filas - 1; i >= 0; i--) {
					modeloTabla.removeRow(i);
				}
			}
		});
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Roboto", Font.BOLD, 12));
		btnLimpiar.setBackground(new Color(19, 45, 70));
		btnLimpiar.setBounds(635, 254, 121, 43);
		add(btnLimpiar);
		
		JButton btnInvertir = new JButton("INVERTIR");
		btnInvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String operacion = "Inversión";
				Double monto = Double.parseDouble(textMonto.getText());
				String opcion = comboInversiones.getSelectedItem().toString();
				
				
				if(opcion.equals("ORO") && monto < 1391.80) {
					JOptionPane.showMessageDialog(null, "El monto mínimo a invertir en oro es de: 1391.80 pesos mexicanos");
				} else {
					String fecha = Inicio.getDateStringOrEmptyString() + " - " + Fin.getDateStringOrEmptyString();
					boolean valid = inversiones.Invertir(operacion, monto, fecha);
					if(valid) {
						JOptionPane.showMessageDialog(null, "¡Inversión realizada con éxito!");
					} else {
						JOptionPane.showMessageDialog(null, "Algo salió mal");
					}
				}
					
				

				
			}
		});
		btnInvertir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInvertir.setForeground(Color.WHITE);
		btnInvertir.setFont(new Font("Roboto", Font.BOLD, 17));
		btnInvertir.setBackground(new Color(19, 45, 70));
		btnInvertir.setBounds(53, 357, 199, 50);
		add(btnInvertir);
		


		

	}
}
