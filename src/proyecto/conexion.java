package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class conexion {
	
	private static final String controlador = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/bd_inversiones";
	private static final String usuario = "root";
	private static final String contraseña = "yaelXD48";
	private ResultSet myRs;
	
	
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
	
	
	
}
