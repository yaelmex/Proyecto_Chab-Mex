package proyecto;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class GUI_CuentayTarjeta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtBanco;
	private JTextField txtNumCuenta;
	private JTextField txtCLABE;
	private JTable table;
	DefaultTableModel CuentasBancoUser = new DefaultTableModel();
	conexion llenarTabla = new conexion();

	/**
	 * Create the panel.
	 */
	public GUI_CuentayTarjeta() {
		setBackground(new Color(255, 255, 255));
		setBounds(new Rectangle(0, 0, 785, 472));
		setLayout(null);
		
		JLabel lblMetodosPago = new JLabel("Metodos de Pago y Tarjetas");
		lblMetodosPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblMetodosPago.setBounds(45, 28, 179, 14);
		add(lblMetodosPago);

		
		JPanel panel = new JPanel();
		panel.setVisible(false);
		panel.setBounds(414, 28, 326, 436);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblCUenta = new JLabel("Tipo de cuenta");
		lblCUenta.setBounds(41, 36, 84, 14);
		panel.add(lblCUenta);
		
		JLabel lblBanco = new JLabel("Banco");
		lblBanco.setBounds(41, 104, 46, 14);
		panel.add(lblBanco);
		
		txtBanco = new JTextField();
		txtBanco.setColumns(10);
		txtBanco.setBounds(41, 124, 247, 20);
		panel.add(txtBanco);
		
		JLabel lblNumCuenta = new JLabel("Numero de Cuenta");
		lblNumCuenta.setBounds(41, 165, 104, 14);
		panel.add(lblNumCuenta);
		
		txtNumCuenta = new JTextField();
		txtNumCuenta.setColumns(10);
		txtNumCuenta.setBounds(41, 185, 247, 20);
		panel.add(txtNumCuenta);
		
		JLabel lblCLABE = new JLabel("CLABE interbancaria");
		lblCLABE.setBounds(41, 226, 120, 14);
		panel.add(lblCLABE);
		
		txtCLABE = new JTextField();
		txtCLABE.setColumns(10);
		txtCLABE.setBounds(41, 246, 247, 20);
		panel.add(txtCLABE);
		
		JComboBox boxTipoCuenta = new JComboBox();
		boxTipoCuenta.setModel(new DefaultComboBoxModel(new String[] {"Caja de Ahorro", "Cuenta Corriente"}));
		boxTipoCuenta.setBounds(41, 56, 247, 22);
		panel.add(boxTipoCuenta);
		
		
		JButton AddCuentaBanco = new JButton("Añadir Cuenta Bancaria");
		AddCuentaBanco.setBounds(72, 300, 179, 35);
		panel.add(AddCuentaBanco);
		
		JButton lblQuitaCuentaBanc = new JButton("Eliminar Cuenta Bancaria");
		lblQuitaCuentaBanc.setBounds(72, 360, 179, 35);
		panel.add(lblQuitaCuentaBanc);
		lblQuitaCuentaBanc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexion DeleteCuentaBanco = new conexion();
				
				
				DeleteCuentaBanco.deleteCuentaBanco(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(),  //Tipo de cuenta
						table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), 								//Banco
						Double.parseDouble(table.getModel().getValueAt(table.getSelectedRow(), 3).toString()), 			//Numero de cuenta
						Double.parseDouble(table.getModel().getValueAt(table.getSelectedRow(), 4).toString()),			//CLABE
						table.getModel().getValueAt(table.getSelectedRow(), 2).toString());								//Usuario
			}
		});
		AddCuentaBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexion metodo = new conexion();
				double digNumCuenta = Double.parseDouble(txtNumCuenta.getText());
				double valCLABE = Double.parseDouble(txtCLABE.getText());
				metodo.addCuentaBanco(boxTipoCuenta.getSelectedItem().toString(), txtBanco.getText(), digNumCuenta, valCLABE);
				
			}
		});
		
		table = new JTable();
		table.setBounds(10, 59, 326, 259);
		CuentasBancoUser.addColumn("Tipo de cuenta");
		CuentasBancoUser.addColumn("Banco");
		CuentasBancoUser.addColumn("Usuario");
		CuentasBancoUser.addColumn("Numero de cuenta");
		CuentasBancoUser.addColumn("CLABE");
		CuentasBancoUser.addRow(new Object[] { "Tipo de cuenta", "Banco", "Usuario", "Numero de cuenta", "CLABE"});
		llenarTabla.getDatosCuentaBanco(GUI_InicioSesion.user);
		for(int i = 0; i < llenarTabla.DatosTarjeta.size(); i = i + 5) {
			CuentasBancoUser.addRow(new Object[]{llenarTabla.DatosTarjeta.get(i), llenarTabla.DatosTarjeta.get(i+1),
			llenarTabla.DatosTarjeta.get(i+2), llenarTabla.DatosTarjeta.get(i+3), llenarTabla.DatosTarjeta.get(i+4)});
			}
		
		table.setModel(CuentasBancoUser);
		add(table);
		
		JButton AddNvaCtaBanco = new JButton("Añadir Cuenta de Banco");
		AddNvaCtaBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
			}
		});
		AddNvaCtaBanco.setBounds(81, 407, 179, 35);
		add(AddNvaCtaBanco);
	}
}
