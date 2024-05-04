package proyecto;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class GUI_VerInversiones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	DefaultTableModel tablaInversiones = new DefaultTableModel();
	String usuario = GUI_InicioSesion.user;
	conexion llenarTabla = new conexion();

	/**
	 * Create the panel.
	 */
	public GUI_VerInversiones() {
		
		setBackground(new Color(255, 255, 255));
		setBounds(new Rectangle(0, 0, 785, 472));
		setLayout(null);
		
		table = new JTable();
		table.setBounds(25, 43, 509, 133);
		tablaInversiones.addColumn("Operacion");
		tablaInversiones.addColumn("Monto");
		tablaInversiones.addColumn("Fecha");
		tablaInversiones.addRow(new Object[]{"Operacion", "Monto", "Fecha"});
		llenarTabla.getOperaciones(usuario);
		
		for(int i = 0; i < llenarTabla.DatosOperacion.size(); i = i + 3) {
		tablaInversiones.addRow(new Object[]{llenarTabla.DatosOperacion.get(i), llenarTabla.DatosOperacion.get(i+1),
		llenarTabla.DatosOperacion.get(i+2)});
		}
		table.setModel(tablaInversiones);
		add(table);
		
		JButton btnRetiraInv = new JButton("Retirar Inversion");
		btnRetiraInv.setBounds(575, 39, 176, 39);
		add(btnRetiraInv);
		
		
		

	}
}
