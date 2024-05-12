package proyecto;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI_HistoryInversiones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	DefaultTableModel tablaInversiones = new DefaultTableModel();
	conexion llenarTabla = new conexion();

	/**
	 * Create the panel.
	 */
	public GUI_HistoryInversiones() {
		setBackground(new Color(19, 45, 70));
		setBounds(new Rectangle(0, 0, 785, 472));
		setLayout(null);
		
		table = new JTable();
		table.setBounds(28, 92, 720, 346);
		tablaInversiones.addColumn("Operacion");
		tablaInversiones.addColumn("Monto");
		tablaInversiones.addColumn("Fecha");
		tablaInversiones.addColumn("Estado");
		llenarTabla.getOperaciones(GUI_InicioSesion.user);
		tablaInversiones.addRow(new Object[]{"Operacion", "Monto", "Fecha", "Estado"});
		llenarTabla.getHistory(GUI_InicioSesion.user);
		updateHistorial();
		table.setModel(tablaInversiones);
		add(table);
		
		JLabel lblHistorialDeInversiones = new JLabel("Historial de inversiones realizadas");
		lblHistorialDeInversiones.setForeground(new Color(255, 255, 255));
		lblHistorialDeInversiones.setFont(new Font("Roboto", Font.BOLD, 25));
		lblHistorialDeInversiones.setBounds(195, 31, 435, 55);
		add(lblHistorialDeInversiones);
	}
	
	public void updateHistorial() {
		for(int i = 0; i < llenarTabla.DatosHistorial.size(); i = i + 4) {
			tablaInversiones.addRow(new Object[]{
					llenarTabla.DatosHistorial.get(i),   	//Tipo Operacion
					llenarTabla.DatosHistorial.get(i+1), 	//Monto
					llenarTabla.DatosHistorial.get(i+2), 	//Fecha
					llenarTabla.DatosHistorial.get(i+3)});	//Estado de la inversiónes
		}
	}
}
