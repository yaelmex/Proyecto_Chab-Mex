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
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Font;

public class GUI_CuentayTarjeta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtBanco;
	private JTextField txtNumCuenta;
	private JTextField txtCLABE;
	private JTable table;
	DefaultTableModel CuentasBancoUser = new DefaultTableModel();
	conexion llenarTabla = new conexion();
	ArrayList<String> datosCuenta = new ArrayList<String>();

	/**
	 * Create the panel.
	 */
	public GUI_CuentayTarjeta() {
		setBackground(new Color(19, 45, 70));
		setBounds(new Rectangle(0, 0, 785, 472));
		setLayout(null);
		
		JLabel lblMetodosPago = new JLabel("Metodos de Pago y Tarjetas");
		lblMetodosPago.setForeground(new Color(255, 255, 255));
		lblMetodosPago.setFont(new Font("Roboto", Font.BOLD, 20));
		lblMetodosPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblMetodosPago.setBounds(80, 23, 277, 25);
		add(lblMetodosPago);

		
		JPanel panel = new JPanel();
		panel.setVisible(false);
		panel.setBounds(459, 10, 326, 436);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblCUenta = new JLabel("Tipo de cuenta");
		lblCUenta.setBounds(41, 78, 84, 14);
		panel.add(lblCUenta);
		
		JLabel lblBanco = new JLabel("Banco");
		lblBanco.setBounds(41, 140, 46, 14);
		panel.add(lblBanco);
		
		txtBanco = new JTextField();
		txtBanco.setColumns(10);
		txtBanco.setBounds(41, 160, 247, 20);
		panel.add(txtBanco);
		
		JLabel lblNumCuenta = new JLabel("Numero de Cuenta");
		lblNumCuenta.setBounds(42, 200, 104, 14);
		panel.add(lblNumCuenta);
		
		txtNumCuenta = new JTextField();
		txtNumCuenta.setColumns(10);
		txtNumCuenta.setBounds(41, 220, 247, 20);
		panel.add(txtNumCuenta);
		
		JLabel lblCLABE = new JLabel("CLABE interbancaria");
		lblCLABE.setBounds(41, 260, 120, 14);
		panel.add(lblCLABE);
		
		txtCLABE = new JTextField();
		txtCLABE.setColumns(10);
		txtCLABE.setBounds(41, 280, 247, 20);
		panel.add(txtCLABE);
		
		JComboBox boxTipoCuenta = new JComboBox();
		boxTipoCuenta.setModel(new DefaultComboBoxModel(new String[] {"Caja de Ahorro", "Cuenta Corriente"}));
		boxTipoCuenta.setBounds(41, 98, 247, 22);
		panel.add(boxTipoCuenta);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon((new ImageIcon("imagenes/city.png")));
		lblFondo.setBounds(489, 0, 296, 472);
		add(lblFondo);
		
		JButton AddCuentaBanco = new JButton("Añadir Cuenta Bancaria");
		AddCuentaBanco.setBounds(72, 322, 179, 35);
		panel.add(AddCuentaBanco);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				txtBanco.setText(null);
				txtNumCuenta.setText(null);
				txtCLABE.setText(null);
				lblFondo.setVisible(true);
				
			}
		});
		btnCancelar.setBounds(72, 378, 179, 35);
		panel.add(btnCancelar);
		
		JLabel lblInstrucciones = new JLabel("Rellena los espacios con la información señalada");
		lblInstrucciones.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblInstrucciones.setBounds(41, 10, 247, 62);
		panel.add(lblInstrucciones);

		
		AddCuentaBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexion metodo = new conexion();
				metodo.addCuentaBanco(boxTipoCuenta.getSelectedItem().toString(), txtBanco.getText(), 
						txtNumCuenta.getText(), 
						txtCLABE.getText());
				llenarTabla.getOperaciones(GUI_InicioSesion.user);
				updateTabla(boxTipoCuenta.getSelectedItem().toString(), txtBanco.getText(), GUI_InicioSesion.user, 
						txtNumCuenta.getText(), 
						txtCLABE.getText());
			}
		});
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setBounds(10, 59, 422, 271);
		CuentasBancoUser.addColumn("Tipo de cuenta");
		CuentasBancoUser.addColumn("Banco");
		CuentasBancoUser.addColumn("Usuario");
		CuentasBancoUser.addColumn("Numero de cuenta");
		CuentasBancoUser.addColumn("CLABE");
		CuentasBancoUser.addRow(new Object[] { "Tipo de cuenta", "Banco", "Usuario", "Numero de cuenta", "CLABE"});
		llenarTabla.getDatosCuentaBanco(GUI_InicioSesion.user);		
		llenarTabla();
		table.setModel(CuentasBancoUser);
		add(table);
	
		
		JButton lblQuitaCuentaBanc = new JButton("Eliminar Cuenta Bancaria");
		lblQuitaCuentaBanc.setBounds(123, 400, 179, 35);
		add(lblQuitaCuentaBanc);
		
		
		lblQuitaCuentaBanc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexion DeleteCuentaBanco = new conexion();
				DeleteCuentaBanco.deleteCuentaBanco(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(),  //Tipo de cuenta
						table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), 								//Banco
						table.getModel().getValueAt(table.getSelectedRow(), 3).toString(), 			//Numero de cuenta
						table.getModel().getValueAt(table.getSelectedRow(), 4).toString(),			//CLABE
						table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
						updateTablaDelete();
			}
		});
		
		JButton AddNvaCtaBanco = new JButton("Añadir Cuenta de Banco");
		AddNvaCtaBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				lblFondo.setVisible(false);
			}
		});
		AddNvaCtaBanco.setBounds(123, 344, 179, 35);
		add(AddNvaCtaBanco);
	}
	
	public void llenarTabla() 
		{
			for(int i = 0; i < llenarTabla.DatosTarjeta.size(); i = i + 5) 
			{
				Double.parseDouble(Double.toString(56.11124).replace(".", ""));
				CuentasBancoUser.addRow(new Object[]{llenarTabla.DatosTarjeta.get(i), 
				llenarTabla.DatosTarjeta.get(i+1),
				llenarTabla.DatosTarjeta.get(i+2), 		
				llenarTabla.DatosTarjeta.get(i+3), 
				llenarTabla.DatosTarjeta.get(i+4).replaceAll("\\.\\d+$", "")});
				datosCuenta.add(llenarTabla.DatosTarjeta.get(i));
				datosCuenta.add(llenarTabla.DatosTarjeta.get(i+1));
				datosCuenta.add(llenarTabla.DatosTarjeta.get(i+3));
			}
		}
		
	public void updateTabla(String Cuenta, String Banco, String Usuario, String NumCuenta, String numCLABE) 
		{
				CuentasBancoUser.addRow(new Object[] {Cuenta, Banco, Usuario, NumCuenta, numCLABE});
		}
	
	public void updateTablaDelete() 
		{
		    	CuentasBancoUser.removeRow(table.getSelectedRow());
		}
}
