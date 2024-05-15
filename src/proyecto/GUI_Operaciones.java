package proyecto;

import javax.swing.JPanel;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_Operaciones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	conexion operaciones = new conexion();
	private JTextField textMonto;


	/**
	 * Create the panel.
	 */
	public GUI_Operaciones() {
		setBackground(new Color(19, 45, 70));
		setBounds(new Rectangle(196, 116, 785, 472));
		setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(57, 44, 169, 17);
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Roboto", Font.BOLD, 15));
		add(lblUsuario);
		lblUsuario.setText("Usuario: JMEX");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 109, 666, 233);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Roboto", Font.BOLD, 12));
		scrollPane.setViewportView(table);
		modeloTabla.addColumn("Operacion");
		modeloTabla.addColumn("Monto");
		modeloTabla.addColumn("Plazo");
		modeloTabla.addColumn("Tasa de Interes");
		modeloTabla.addColumn("GAT");
		rellenarTabla();
		
		
		
		JLabel lblInversiones = new JLabel("Inversiones:");
		lblInversiones.setForeground(Color.WHITE);
		lblInversiones.setFont(new Font("Roboto", Font.BOLD, 14));
		lblInversiones.setBounds(57, 72, 169, 15);
		add(lblInversiones);
		
		JLabel lblAgregarMontoA = new JLabel("Agregar monto a Inversión:");
		lblAgregarMontoA.setForeground(Color.WHITE);
		lblAgregarMontoA.setFont(new Font("Roboto", Font.BOLD, 15));
		lblAgregarMontoA.setBounds(57, 365, 217, 17);
		add(lblAgregarMontoA);
		
		textMonto = new JTextField();
		textMonto.setFont(new Font("Roboto", Font.BOLD, 11));
		textMonto.setColumns(10);
		textMonto.setBounds(285, 364, 186, 20);
		add(textMonto);
		
		JButton btnAbonar = new JButton("Abonar");
		btnAbonar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila == -1) {
					JOptionPane.showMessageDialog(null, "No tienes seleccionado ninguna fila");
				} else {
					String dato = modeloTabla.getValueAt(fila, 0).toString();
					double monto = Double.parseDouble(textMonto.getText());
					boolean valid = operaciones.abonar(dato, monto);
					if(valid) {
						JOptionPane.showMessageDialog(null, "Abono realizado con éxito", "Proceso éxitoso", JOptionPane.PLAIN_MESSAGE);
						 modeloTabla.setRowCount(0);
						rellenarTabla();
					} else {
						JOptionPane.showMessageDialog(null, "Algo salió mal", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		btnAbonar.setForeground(Color.WHITE);
		btnAbonar.setFont(new Font("Roboto", Font.BOLD, 17));
		btnAbonar.setBackground(new Color(19, 45, 70));
		btnAbonar.setBounds(524, 365, 199, 50);
		add(btnAbonar);
		
		if(GUI_Principal.idioma.equals("Maya")) {
			lblUsuario.setText("Yuumkab: " + GUI_InicioSesion.user);
			lblAgregarMontoA.setText("Jach a talamak a Inversiones:");
			btnAbonar.setText("Tumkab");
		} else {
			lblUsuario.setText("Usuario: " + GUI_InicioSesion.user);
			lblAgregarMontoA.setText("Agregar monto a Inversión: ");
			btnAbonar.setText("Abonar");
		}

	}
	
	public void rellenarTabla() {
		
		ArrayList<Object[]> extraidos = new ArrayList<Object[]>();
		extraidos = operaciones.getInversiones(GUI_InicioSesion.user);
		
		for(int i = 0; i < extraidos.size(); i++) {
			modeloTabla.addRow(new Object[] {extraidos.get(i)[0], extraidos.get(i)[1], extraidos.get(i)[2], extraidos.get(i)[3], extraidos.get(i)[4]});
		}
		
		
		table.setModel(modeloTabla);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		table.getColumnModel().getColumn(4).setCellRenderer(tcr);
		
		TableColumnModel columnModel = table.getColumnModel();
		
	    columnModel.getColumn(0).setPreferredWidth(300);
	    columnModel.getColumn(1).setPreferredWidth(75);
	    columnModel.getColumn(2).setPreferredWidth(150);
	    columnModel.getColumn(3).setPreferredWidth(100);
	    columnModel.getColumn(4).setPreferredWidth(75); 
	}
}