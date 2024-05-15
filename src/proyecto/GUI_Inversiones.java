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
import java.time.Month;
import java.time.Period;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.components.DatePicker;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import com.github.lgooddatepicker.components.DateTimePicker;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI_Inversiones extends JPanel {

	private static final long serialVersionUID = 1L;
	conexion inversiones = new conexion();
	DefaultComboBoxModel modelo = new DefaultComboBoxModel();
	DefaultComboBoxModel cuentas = new DefaultComboBoxModel();
	private JTextField textMonto;
	private JTable tablaSimul;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	int validador = 0; int validadorCuentas = 0;

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
		Inicio.setEnabled(true);
		Inicio.setBounds(141, 253, 186, 22);
		add(Inicio);
		
		JLabel lblPlazo = new JLabel("Plazo:");
		lblPlazo.setForeground(Color.WHITE);
		lblPlazo.setFont(new Font("Roboto", Font.BOLD, 12));
		lblPlazo.setBounds(53, 253, 82, 18);
		add(lblPlazo);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setForeground(Color.WHITE);
		lblInicio.setFont(new Font("Roboto", Font.BOLD, 12));
		lblInicio.setBounds(141, 287, 82, 18);
		add(lblInicio);
		
		DatePicker Fin = new DatePicker();
		Fin.setEnabled(true);
		Fin.setBounds(357, 254, 186, 22);
		add(Fin);
		
		JLabel lblFin = new JLabel("Fin");
		lblFin.setForeground(Color.WHITE);
		lblFin.setFont(new Font("Roboto", Font.BOLD, 12));
		lblFin.setBounds(357, 290, 82, 18);
		add(lblFin);

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
		
		JLabel lblSimu = new JLabel("Simulador");
		lblSimu.setForeground(Color.WHITE);
		lblSimu.setFont(new Font("Roboto", Font.BOLD, 12));
		lblSimu.setBounds(692, 216, 66, 18);
		add(lblSimu);
		
		
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
		btnSimular.setBounds(297, 392, 199, 50);
		add(btnSimular);
		
		
		JLabel lblNewLabel = new JLabel("Tiempo");
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(53, 73, 112, 18);
		add(lblNewLabel);
		
		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setForeground(Color.WHITE);
		lblMonto.setFont(new Font("Roboto", Font.BOLD, 12));
		lblMonto.setBounds(53, 123, 76, 18);
		add(lblMonto);
		
		textMonto = new JTextField();
		textMonto.setFont(new Font("Roboto", Font.BOLD, 11));
		textMonto.setBounds(141, 123, 186, 20);
		add(textMonto);
		textMonto.setColumns(10);
		
		JComboBox BoxCuentaDeposito = new JComboBox();
		BoxCuentaDeposito.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_CuentayTarjeta llenarCuentas = new GUI_CuentayTarjeta();
				if(validadorCuentas == 1) {
					
				} else {
				for(int i = 0; i < llenarCuentas.datosCuenta.size(); i = i + 3) 
				{
					String cuentaReal = llenarCuentas.datosCuenta.get(i) + " " + llenarCuentas.datosCuenta.get(i+1) + " "
					+ llenarCuentas.datosCuenta.get(i+2);
					cuentas.addElement(cuentaReal);
				}
				BoxCuentaDeposito.setModel(cuentas);
				validadorCuentas = 1;
				}
			}
		});
		BoxCuentaDeposito.setBounds(214, 331, 329, 22);
		add(BoxCuentaDeposito);
		
		JLabel lblInv = new JLabel("Inversiones para ti:");
		lblInv.setForeground(Color.WHITE);
		lblInv.setFont(new Font("Roboto", Font.BOLD, 12));
		lblInv.setBounds(53, 187, 149, 18);
		add(lblInv);
		
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
				Double monto = Double.parseDouble(textMonto.getText());
				String opcion = comboInversiones.getSelectedItem().toString();
				String operacion = "Inversión en: " + opcion;
				String cuenta = BoxCuentaDeposito.getSelectedItem().toString();
				String [] parts = cuenta.split(" ");
				String num_Cuenta = parts[3];
				String fecha = "";
				
				/* Obtencion del numero de meses en enteros */
				long diff = ChronoUnit.MONTHS.between(YearMonth.from(Inicio.getDate()), YearMonth.from(Fin.getDate()));
				int numMeses = (int)diff;				
				
				if(opcion.equals("ORO") && monto < 1391.80) {
					JOptionPane.showMessageDialog(null, "El monto mínimo a invertir en oro es de: 1391.80 pesos mexicanos");
				} else {
					fecha = Inicio.getDateStringOrEmptyString() + " - " + Fin.getDateStringOrEmptyString();
					float tasa = inversiones.getTasa(opcion);	
					boolean valid = inversiones.Invertir(operacion, monto, fecha, numMeses, tasa);
					if(valid) {
						JOptionPane.showMessageDialog(null, "¡Inversión realizada con éxito!");
					} else {
						JOptionPane.showMessageDialog(null, "Algo salió mal");
					}
				}
				System.out.println(BoxCuentaDeposito.getSelectedItem().toString());
				inversiones.relacionar(opcion, GUI_InicioSesion.user, operacion, num_Cuenta);
				inversiones.AddToHistorial(GUI_InicioSesion.user, operacion, monto, fecha , "Activa");
			}
		});
		btnInvertir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInvertir.setForeground(Color.WHITE);
		btnInvertir.setFont(new Font("Roboto", Font.BOLD, 17));
		btnInvertir.setBackground(new Color(19, 45, 70));
		btnInvertir.setBounds(53, 392, 199, 50);
		add(btnInvertir);
		
		JLabel lblCuentaDeposito = new JLabel("Cuenta a Depositar:");
		lblCuentaDeposito.setForeground(Color.WHITE);
		lblCuentaDeposito.setFont(new Font("Roboto", Font.BOLD, 12));
		lblCuentaDeposito.setBounds(53, 332, 129, 18);
		add(lblCuentaDeposito);
		
		if(GUI_Principal.idioma.equals("Maya")) {
			lblNewLabel.setText("k'i'ik'");
			lblMonto.setText("p'ax");
			lblInv.setText("talaj inwinales ti");
			lblPlazo.setText("k'i'ik'al");
			lblInicio.setText("uchben");
			lblFin.setText("mako'ob");
			lblCuentaDeposito.setText("u k'áab a wa'ixik");
			lblSimu.setText("k'i'ik'ach");
			btnInvertir.setText("winikchaj");
			btnSimular.setText("k'i'ik'achaj");
			btnLimpiar.setText("ts'íik");;
		} else {
			lblNewLabel.setText("Tiempo");
			lblMonto.setText("Monto");
			lblInv.setText("Inversiones para ti:");
			lblPlazo.setText("Plazo");
			lblInicio.setText("Inicio");
			lblFin.setText("Fin");
			lblCuentaDeposito.setText("Cuenta a Depositar: ");
			lblSimu.setText("Simulador");
			btnInvertir.setText("INVERTIR");
			btnSimular.setText("SIMULAR");
			btnLimpiar.setText("LIMPIAR");
		}

		

	}
}