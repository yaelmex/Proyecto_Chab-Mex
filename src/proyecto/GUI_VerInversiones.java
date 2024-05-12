package proyecto;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

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
		table.setRowSelectionAllowed(false);
		table.setBounds(24, 67, 509, 288);
		tablaInversiones.addColumn("Número de Operación");
		tablaInversiones.addColumn("Operacion");
		tablaInversiones.addColumn("Monto");
		tablaInversiones.addColumn("Fecha");
		tablaInversiones.addColumn("Tasa");
		tablaInversiones.addColumn("Meses");
		llenarTabla.getOperaciones(usuario);
		tablaInversiones.addRow(new Object[]{"Número de Operacion","Operacion", "Monto", "Fecha", "Tasa", "Meses"});
		updateInversiones();
		table.setModel(tablaInversiones);
		add(table);
		
		JButton btnRetiraInv = new JButton("Retirar Inversion");
		btnRetiraInv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String NumOperacion= tablaInversiones.getValueAt(table.getSelectedRow(), 0).toString();
				
				String NomOperacion = tablaInversiones.getValueAt(table.getSelectedRow(), 1).toString();
				
				double MontoTotal = Double.parseDouble(tablaInversiones.getValueAt(table.getSelectedRow(), 2).toString());
				
				String fecha = tablaInversiones.getValueAt(table.getSelectedRow(), 3).toString();
				
				Float TasaReal = Float.parseFloat(tablaInversiones.getValueAt(table.getSelectedRow(), 4).toString());
				
				int Meses = Integer.parseInt(tablaInversiones.getValueAt(table.getSelectedRow(), 5).toString());
				
				/* Calcula el valor de la inversion y lo añade al saldo de la cuenta bancaria
				 * elegida para depositar el monto final*/
				llenarTabla.terminaInversion(usuario, MontoTotal, Meses, TasaReal, NumOperacion); 
				
				/* Elimina la informacion de la inversiond de la tabla visible en la GUI*/
				tablaInversiones.removeRow(table.getSelectedRow());
				
				/* Elimina los datos de la inversion de la base de datos y la tabla ´operaciones'*/
				llenarTabla.deleteInversion(NumOperacion); 
				
			    llenarTabla.UpdateHistorial(usuario, NomOperacion, MontoTotal, fecha);
			}
		});
		btnRetiraInv.setBounds(130, 361, 200, 74);
		add(btnRetiraInv);
		
		JLabel lblRegistroInversiones = new JLabel("Registro de inversiones realizadas");
		lblRegistroInversiones.setFont(new Font("Roboto", Font.BOLD, 25));
		lblRegistroInversiones.setBounds(64, 10, 435, 55);
		add(lblRegistroInversiones);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon((new ImageIcon("imagenes/imgManos.jpg"
				+ "")));
		lblFondo.setBounds(554, 0, 231, 472);
		add(lblFondo);
		

	}
	
	public void updateInversiones() {
		for(int i = 0; i < llenarTabla.DatosOperacion.size(); i = i + 6) {
			tablaInversiones.addRow(new Object[]{
					llenarTabla.DatosOperacion.get(i),   	//Numero de Operacion
					llenarTabla.DatosOperacion.get(i+1), 	// Operacion "Inversion en: Tipo de inversion"
					llenarTabla.DatosOperacion.get(i+2), 	// Monto invertido
					llenarTabla.DatosOperacion.get(i+3), 	// Fecha de inicio y termino de la inversión
					llenarTabla.DatosOperacion.get(i+4), 	// Tasa bajo la que se calculará el rendimiento y el interés
					llenarTabla.DatosOperacion.get(i+5)});  // Numero de meses que dura la inversion
			}
	}
}
