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
import java.awt.Cursor;

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
		lblMetodosPago.setBounds(0, 23, 461, 25);
		add(lblMetodosPago);

		
		JPanel panel = new JPanel();
		panel.setVisible(false);
		panel.setBounds(459, 10, 326, 436);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblCUenta = new JLabel("Tipo de cuenta");
		lblCUenta.setBounds(41, 78, 163, 14);
		panel.add(lblCUenta);
		

		
		JLabel lblBanco = new JLabel("Banco");
		lblBanco.setBounds(41, 140, 105, 14);
		panel.add(lblBanco);
		
		txtBanco = new JTextField();
		txtBanco.setColumns(10);
		txtBanco.setBounds(41, 160, 247, 20);
		panel.add(txtBanco);
		
		JLabel lblNumCuenta = new JLabel("Numero de Cuenta");
		lblNumCuenta.setBounds(42, 200, 162, 14);
		panel.add(lblNumCuenta);
		
		txtNumCuenta = new JTextField();
		txtNumCuenta.setColumns(10);
		txtNumCuenta.setBounds(41, 220, 247, 20);
		panel.add(txtNumCuenta);
		
		JLabel lblCLABE = new JLabel("CLABE interbancaria");
		lblCLABE.setBounds(41, 260, 179, 14);
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
		AddCuentaBanco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AddCuentaBanco.setBounds(54, 322, 216, 35);
		panel.add(AddCuentaBanco);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				txtBanco.setText(null);
				txtNumCuenta.setText(null);
				txtCLABE.setText(null);
				lblFondo.setVisible(true);
				
			}
		});
		btnCancelar.setBounds(54, 378, 216, 35);
		panel.add(btnCancelar);
		
		JLabel lblInstrucciones = new JLabel("Rellena los espacios con la información señalada");
		lblInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstrucciones.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblInstrucciones.setBounds(0, 10, 326, 62);
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
		lblQuitaCuentaBanc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblQuitaCuentaBanc.setBounds(103, 400, 238, 35);
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
		AddNvaCtaBanco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AddNvaCtaBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				lblFondo.setVisible(false);
			}
		});
		AddNvaCtaBanco.setBounds(103, 354, 238, 35);
		add(AddNvaCtaBanco);
		
		if(GUI_Principal.idioma.equals("Maya")) {
			lblMetodosPago.setText("Tuunichob k'u k'abaal y tuunichob");
			lblCUenta.setText("Tipo tuunich u k'abaal");
			lblBanco.setText("Tuunichil tu k'abaal");
			 lblNumCuenta.setText("Tzolaj u k'abaal");
			 lblCLABE.setText("CLABE interbancaria");
			 AddCuentaBanco.setText("Tz'íib k'abaal tuunich u k'abaal");
			 btnCancelar.setText("Téen");
			 lblInstrucciones.setText("Chíibaloob u k'abalo' tu ti' u k'áajal ti'");
			 lblQuitaCuentaBanc.setText("Téen u k'abaal tuunich u k'abaal");
			 AddNvaCtaBanco.setText("Tz'íib k'abaal tuunich u k'abaal");
		} else {
			lblMetodosPago.setText("Metodos de Pago y Tarjetas");
			lblCUenta.setText("Tipo de cuenta");
			lblBanco.setText("Banco");
			 lblNumCuenta.setText("Numero de Cuenta");
			 lblCLABE.setText("CLABE interbancaria");
			 AddCuentaBanco.setText("Añadir Cuenta Bancaria");
			 btnCancelar.setText("Cancelar");
			 lblInstrucciones.setText("Rellena los espacios con la información señalada");
			 lblQuitaCuentaBanc.setText("Eliminar Cuenta Bancaria");
			 AddNvaCtaBanco.setText("Añadir Cuenta de Banco");
		}
		
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