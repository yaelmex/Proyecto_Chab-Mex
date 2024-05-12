package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class conexion {
	
	private static final String controlador = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/bd_inversiones";
	private static final String usuario = "root";
	private static final String contraseña = "yaelXD48";
	private ResultSet myRs;
	ArrayList<String> DatosOperacion = new ArrayList<String>();
	ArrayList<String> DatosTarjeta = new ArrayList<String>();
	
	
	static {
		try {
			Class.forName(controlador);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, usuario, contraseña);
			System.out.println("Conexión establecida con éxito");
		}  catch(SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
		
		return conexion;
	}
	
	public ArrayList<String> FiltrarInversiones(String eleccion){
		try {
			ArrayList<String> empresas = new ArrayList<String>();
			Connection  MyConn = DriverManager.getConnection(url, usuario, contraseña);
		    Statement myStmt  = MyConn.createStatement();
		    
		    
		    if(eleccion.equals("1 Mes") | eleccion.equals("3 Meses") | eleccion.equals("6 Meses")) {
		    	myRs =  myStmt.executeQuery("SELECT `Entidad Financiera` FROM tasas WHERE `Tasa de Interes` > 0.08");
		    } else {
		    	myRs =  myStmt.executeQuery("SELECT `Entidad Financiera` FROM tasas WHERE `Tasa de Interes` < 0.08");
		    }
		    
		    while(myRs.next()) {
		    	empresas.add(myRs.getString("Entidad Financiera"));
		    }
		    
		    return empresas;
      
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(myRs != null) {
				try {
					myRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;	
	}
	
	public Object[] simularInversion(String opcion, Double monto, float tiempo, String eleccion) {
		float tasa = 0;
		try {
			Connection MyConn = DriverManager.getConnection(url, usuario, contraseña);
			String sql = "SELECT `Tasa de Interes` FROM tasas WHERE `Entidad Financiera` =  ?";
			PreparedStatement pstmt = MyConn.prepareStatement(sql);
			pstmt.setString(1, opcion);
			myRs = pstmt.executeQuery();
	
			if (myRs.next()) {
                tasa = myRs.getFloat("Tasa de Interes");
            }
			
			double VF = monto * Math.pow((1+tasa), tiempo);
			double ganancia = VF - monto;
			double rendimiento = (ganancia/monto)*100;
			
			Object[] filas = new Object[4];
			filas[0] = String.valueOf(monto);
			filas[1] = eleccion;
			filas[2] = String.valueOf(rendimiento);
			filas[3] = String.valueOf(VF);
			
			return filas;
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if(myRs != null) {
					myRs.close();
				}
			} catch (SQLException a) {
				a.printStackTrace();
			}
		}
		return null;    
	}
	
	public ArrayList<Object[]> getInversiones(String usuarioBuscar) {
		try {
			Connection MyConn = DriverManager.getConnection(url, usuario, contraseña);
			String busquedasql = "SELECT Operacion, Monto, Fecha, `Tasa de Interes`, GAT FROM operaciones, tasas, intermedia WHERE Usuario like ? "
					+ "AND IDoperacion = NumOperacion AND IDtasa = tasas.ID";
			PreparedStatement pstmt = MyConn.prepareStatement(busquedasql);
			pstmt.setString(1, usuarioBuscar);
			myRs = pstmt.executeQuery();
			ArrayList<Object[]> extraidos = new ArrayList<Object[]>();
			
			while(myRs.next()) {
				Object[] filas = new Object[5];
				filas[0] = myRs.getString("Operacion");
				filas[1] = myRs.getDouble("Monto");
				filas[2] = myRs.getString("Fecha");
				filas[3] = myRs.getFloat("Tasa de Interes");
				filas[4] = myRs.getFloat("GAT");
				extraidos.add(filas);
			}
			
			return extraidos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean abonar(String operacion, double monto) {
		
		try {
			Connection MyConn = DriverManager.getConnection(url, usuario, contraseña);
			String sql = "SELECT Monto FROM operaciones WHERE Operacion = ?";
			PreparedStatement pstmt = MyConn.prepareStatement(sql);
			pstmt.setString(1, operacion);
			myRs = pstmt.executeQuery();
			double montoAnterior = 0;
			
			if(myRs.next()) {
				montoAnterior = myRs.getDouble("Monto");
			}
			
			double suma = montoAnterior + monto;
			
			String sql_2 = "UPDATE operaciones SET Monto = ? WHERE Operacion = ?";
			PreparedStatement pstmt2 = MyConn.prepareStatement(sql_2);
			pstmt2.setDouble(1, suma);
			pstmt2.setString(2, operacion);
			pstmt2.executeUpdate();
			pstmt2.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(myRs != null) {
					myRs.close();
				}
			} catch (SQLException a) {
				a.printStackTrace();
			}
		}
		return false;	
	}
	
	public boolean Invertir(String operacion, Double monto, String fecha) {
		try {
			Connection MyConn = DriverManager.getConnection(url, usuario, contraseña);
			String sql = "INSERT INTO operaciones (Operacion, Monto, Fecha) VALUES(?, ?, ?)";
			PreparedStatement pstmt = MyConn.prepareStatement(sql);
			pstmt.setString(1, operacion);
			pstmt.setDouble(2, monto);
			pstmt.setString(3, fecha);
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;		
	}
	
	public boolean Ingresar(String usuarioBuscar, String contraseñaBuscar) {
		try {
			Connection MyConn = DriverManager.getConnection(url, usuario, contraseña);
			String sql = "SELECT * FROM usuarios WHERE Usuario = ? AND Contraseña = ?";
			PreparedStatement pstmt = MyConn.prepareStatement(sql);
			pstmt.setString(1, usuarioBuscar);
			pstmt.setString(2, contraseñaBuscar);
			myRs = pstmt.executeQuery();

			if (myRs.next()) {
				return true;
            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(myRs != null) {
					myRs.close();
				}
			} catch (SQLException a) {
				a.printStackTrace();
			}
		}
		return false;
	}
	
	public void relacionar(String entidad, String user, String operacion) {
		try {
			Connection MyConn = DriverManager.getConnection(url, usuario, contraseña);
			
			int IDoperacion = 0;
			int IDtasa = 0;
			String Usuario = "";
			
			String sqlBuscarUsuario = "SELECT Usuario FROM usuarios WHERE Usuario = ?";
			PreparedStatement pstmt1 = MyConn.prepareStatement(sqlBuscarUsuario);
			pstmt1.setString(1, user);
			ResultSet rs1 = pstmt1.executeQuery();
			
			if(rs1.next()) {
				Usuario = rs1.getString("Usuario");
			}
			
			pstmt1.close();
			
			String sqlBuscarOperacion = "SELECT NumOperacion FROM operaciones WHERE Operacion = ?";
			PreparedStatement pstmt2 = MyConn.prepareStatement(sqlBuscarOperacion);
			pstmt2.setString(1, operacion);
			ResultSet rs2 = pstmt2.executeQuery();
			
			if(rs2.next()) {
				IDoperacion = rs2.getInt("NumOperacion");
			}
			
			pstmt2.close();
			
			String sqlBuscarTasa = "SELECT ID FROM tasas WHERE `Entidad Financiera` =  ?";
			PreparedStatement pstmt3 = MyConn.prepareStatement(sqlBuscarTasa);
			pstmt3.setString(1, entidad);
			ResultSet rs3 = pstmt3.executeQuery();
			
			if(rs3.next()) {
				IDtasa = rs3.getInt("ID");
			}
			
			pstmt3.close();
			
			
			String sql = "INSERT INTO intermedia (IDoperacion, IDtasa, Usuario) VALUES (?, ?, ?)";
			PreparedStatement pstmt = MyConn.prepareStatement(sql);
			pstmt.setInt(1, IDoperacion);
			pstmt.setInt(2, IDtasa);
			pstmt.setString(3, Usuario);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	
	public void addNuevoUsuario (String User, String Pass1, String Pass2){
		String NewUser = User; String contra1 = Pass1;
		
		if(User.equals("INGRESE SU USUARIO") || Pass1.equals("********") || Pass2.equals("********")) {
			JOptionPane.showMessageDialog(null, "Porfavor ingrese la información necesaria");
			return;
		}
		else {
		
		if(Pass1.equals(Pass2)) {
			String contraAceptada = contra1;
			try {
				Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
				String sql = "Insert into usuarios (Usuario, Contraseña) values (?, ?)";
				PreparedStatement ps = conexion.prepareStatement(sql);
				ps.setString(1, NewUser);
				ps.setString(2, contraAceptada);
				ps.executeUpdate();
		    	} catch(Exception e) {
		    		System.out.println("Error en el registro de usuario");
		    		e.printStackTrace();
		    	}
				}
			else {
				JOptionPane.showMessageDialog(null, "Las dos contraseñas deben coincidir");
			} 
		}
	}
	
	public void getOperaciones (String User) {
		String Opera = ""; String Monto = ""; String Fecha = "";
		try {
			Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
		    String Operacion=  "SELECT Operacion, Monto, Fecha FROM operaciones, intermedia WHERE Usuario like  ? AND IDoperacion = NumOperacion";
		    PreparedStatement pstmt2 = conexion.prepareStatement(Operacion);
			pstmt2.setString(1, User);
			ResultSet setOperacionTabla = pstmt2.executeQuery();
		    while(setOperacionTabla.next()) {
		    	Opera = setOperacionTabla.getString("Operacion");
		    	Monto = setOperacionTabla.getString("Monto");
		    	Fecha = setOperacionTabla.getString("Fecha");
		    	DatosOperacion.add(Opera);
		    	DatosOperacion.add(Monto);
		    	DatosOperacion.add(Fecha);
		    	
		    }
		
	    	} catch(Exception e) {
	    		System.out.println("Error en el registro de usuario");
	    		e.printStackTrace();
			}
	}
	

	public void addCuentaBanco (String TipoCuenta, String Banco, double NumCuenta, double CLABE)
	{
		// Obtencion del total de digitos en la CLABE bancaria
		double digitos = (int)(Math.log10(CLABE)+1);
		// Seccion de validacion de datos
		
		if(digitos == 18 && NumCuenta > 12) {
			
		//Seccion de adicion de datos a base de datos
			try {
				Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
				String sql = "Insert into cuentasbancarias (Tipo_Cuenta, Banco, Usuario, Numero_Cuenta, CLABE) values (?, ?, ?, ?, ?)";
				PreparedStatement ps = conexion.prepareStatement(sql);
				ps.setString(1, TipoCuenta);
				ps.setString(2, Banco);
				ps.setString(3, GUI_InicioSesion.user);
				ps.setDouble(4, NumCuenta);
				ps.setDouble(5, CLABE);
				ps.executeUpdate();
		    	} catch(Exception e) {
		    		System.out.println("Error en el registro de usuario");
		    		e.printStackTrace();
		    	}

		} else {
				JOptionPane.showMessageDialog(null, "Verifique los datos insertados en NumCuenta y CLABE");
		}
	}
	
	public void getDatosCuentaBanco (String User) {
		String TipoCuenta = ""; String nomBanco= ""; String nomUsuario = "";
		String NumeroCuenta = ""; String numCLABE = "";
		try {
			Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
		    String DatoCuentaBanco=  "SELECT Tipo_Cuenta, Banco, Usuario, Numero_Cuenta, CLABE "
		    		+ "FROM cuentasbancarias WHERE Usuario like  ?";
		    PreparedStatement pstmt2 = conexion.prepareStatement(DatoCuentaBanco);
			pstmt2.setString(1, User);
			ResultSet setOperacionTabla = pstmt2.executeQuery();
		    while(setOperacionTabla.next()) {
		    	TipoCuenta = setOperacionTabla.getString("Tipo_Cuenta");
		    	nomBanco = setOperacionTabla.getString("Banco");
		    	nomUsuario = setOperacionTabla.getString("Usuario");
		    	NumeroCuenta = setOperacionTabla.getString("Numero_Cuenta");
		    	numCLABE = setOperacionTabla.getString("CLABE");
		    	DatosTarjeta.add(TipoCuenta);
		    	DatosTarjeta.add(nomBanco);
		    	DatosTarjeta.add(nomUsuario);
		    	DatosTarjeta.add(NumeroCuenta);
		    	DatosTarjeta.add(numCLABE);
		    }
		
	    	} catch(Exception e) {
	    		System.out.println("Error en el registro de usuario");
	    		e.printStackTrace();
			}
	}
	
	public void deleteCuentaBanco(String TipoCuenta, String Banco, double NumCuenta, double CLABE, String Usuario) {
		try {
			Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
		    String DeleteCuentaBanco=  "Delete FROM cuentasbancarias WHERE Usuario = ? and Tipo_Cuenta like ? and Banco like ? "
		    		+ " and Numero_Cuenta like ? and CLABE like ?";
		    PreparedStatement pstmt2 = conexion.prepareStatement(DeleteCuentaBanco);
			pstmt2.setString(1, Usuario);
			pstmt2.setString(2, TipoCuenta);
			pstmt2.setString(3, Banco);
			pstmt2.setDouble(4, NumCuenta);
			pstmt2.setDouble(5, CLABE);
			pstmt2.executeUpdate();
		
	    	} catch(Exception e) {
	    		System.out.println("Error en el registro de usuario");
	    		e.printStackTrace();
			}
	}
	
	public String cambiaCuentaDeposito(String cuenta) {
		String nuevaCuenta = "";
		return nuevaCuenta = "";
	}
	
}