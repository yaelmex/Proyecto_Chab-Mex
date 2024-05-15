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
	ArrayList<String> DatosUsuario = new ArrayList<String>();
	ArrayList<String> DatosHistorial = new ArrayList<String>();
	String[] NuevoSaldo = new String[1];
	
	
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
	
	// El numero de meses y la tasa la puse para que se me hiciera más fácil la obtención de los datos de la inversión y calcular 
		// el saldo final
		public boolean Invertir(String operacion, Double monto, String fecha, int numMeses, float tasa) {
			try {
				Connection MyConn = DriverManager.getConnection(url, usuario, contraseña);
				String sql = "INSERT INTO operaciones (Operacion, Monto, Fecha, NumeroMeses, TasaInteres) VALUES(?, ?, ?, ?, ?)";
				PreparedStatement pstmt = MyConn.prepareStatement(sql);
				pstmt.setString(1, operacion);
				pstmt.setDouble(2, monto);
				pstmt.setString(3, fecha);
				pstmt.setInt(4, numMeses);
				pstmt.setFloat(5, tasa);
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
	
	//La cuenta de deposito la necesité para actualizar el saldo de la cuenta bancaria cuando se termina la inversión - Ricardo
		public void relacionar(String entidad, String user, String operacion, String CtaDeposito) {
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
				
				
				String sql = "INSERT INTO intermedia ( IDtasa, Usuario, CuentaDeposito) VALUES ( ?, ?, ?)";
				PreparedStatement pstmt = MyConn.prepareStatement(sql);
				pstmt.setInt(1, IDtasa);
				pstmt.setString(2, Usuario);
				pstmt.setString(3, CtaDeposito);
				pstmt.executeUpdate();
				pstmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	
	/* Registro de nuevo usuario a la base de datos */
	public void addNuevoUsuario (String User, String Pass1, String Pass2, String tel, String nom, String direc){
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
				String sql = "Insert into usuarios (Usuario, Contraseña, Telefono, Domicilio, Nombre) values (?, ?, ?, ?,  ?)";
				PreparedStatement ps = conexion.prepareStatement(sql);
				ps.setString(1, NewUser);
				ps.setString(2, contraAceptada);
				ps.setString(3, tel);
				ps.setString(4, direc);
				ps.setString(5, nom);
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
	
	/* Obtención de los datos de la inversión seleccionada */
	public void getOperaciones (String User) {
		String numOperacion = ""; String Opera = ""; String Monto = ""; 
		String Fecha = ""; String Tasa = ""; String NumMeses = "";
		try {
			Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
		    String Operacion=  "SELECT NumOperacion, Operacion, Monto, Fecha, TasaInteres, NumeroMeses"
		    		+ " FROM operaciones, intermedia WHERE Usuario like  ? AND IDoperacion = NumOperacion";
		    PreparedStatement pstmt2 = conexion.prepareStatement(Operacion);
			pstmt2.setString(1, User);
			ResultSet setOperacionTabla = pstmt2.executeQuery();
		    while(setOperacionTabla.next()) {
		    	numOperacion = setOperacionTabla.getString("NumOperacion");
		    	Opera = setOperacionTabla.getString("Operacion");
		    	Monto = setOperacionTabla.getString("Monto");
		    	Fecha = setOperacionTabla.getString("Fecha");
		    	Tasa = setOperacionTabla.getString("TasaInteres");
		    	NumMeses = setOperacionTabla.getString("NumeroMeses");
		    	DatosOperacion.add(numOperacion);
		    	DatosOperacion.add(Opera);
		    	DatosOperacion.add(Monto);
		    	DatosOperacion.add(Fecha);
		    	DatosOperacion.add(Tasa);
		    	DatosOperacion.add(NumMeses);
		    	
		    }
		
	    	} catch(Exception e) {
	    		System.out.println("Ha ocurrido un error en la obtención de los datos de la inversión");
	    		e.printStackTrace();
			}
	}
	
	/* Adicion de datos de cuenta bancaria a la base de datos*/

	public void addCuentaBanco (String TipoCuenta, String Banco, String NumCuenta, String CLABE)
	{
		// Seccion de validacion de datos
		
		if(CLABE.length() == 18 && NumCuenta.length() > 12) {
			
		//Seccion de adicion de datos a base de datos
			try {
				double saldo = 0;
				Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
				String sql = "Insert into cuentasbancarias (Tipo_Cuenta, Banco, Usuario, Numero_Cuenta, CLABE, Saldo) values (?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = conexion.prepareStatement(sql);
				ps.setString(1, TipoCuenta);
				ps.setString(2, Banco);
				ps.setString(3, GUI_InicioSesion.user);
				ps.setString(4, NumCuenta);
				ps.setString(5, CLABE);
				ps.setDouble(6, saldo);
				ps.executeUpdate();
		    	} catch(Exception e) {
		    		System.out.println("Error en el registro de cuenta bancaria");
		    		e.printStackTrace();
		    	}

		} else {
				JOptionPane.showMessageDialog(null, "Verifique los datos insertados en NumCuenta y CLABE");
		}
	}
	
	/* Obtención de los datos de la cuenta de banco*/
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
	    		System.out.println("Ha ocurrido un error en la busqueda de la información de la cuenta bancaria");
	    		e.printStackTrace();
			}
	}
	
	/* Eliminación de la cuenta bancaria de la base de datos*/
	public void deleteCuentaBanco(String TipoCuenta, String Banco, String NumCuenta, String CLABE, String Usuario) {
		try {
			Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
		    String DeleteCuentaBanco=  "Delete FROM cuentasbancarias WHERE Usuario = ? and Tipo_Cuenta like ? and Banco like ? "
		    		+ " and Numero_Cuenta like ? and CLABE like ?";
		    PreparedStatement pstmt2 = conexion.prepareStatement(DeleteCuentaBanco);
			pstmt2.setString(1, Usuario);
			pstmt2.setString(2, TipoCuenta);
			pstmt2.setString(3, Banco);
			pstmt2.setString(4, NumCuenta);
			pstmt2.setString(5, CLABE);
			pstmt2.executeUpdate();
		
	    	} catch(Exception e) {
	    		System.out.println("Error al eliminar la cuenta bancaria");
	    		e.printStackTrace();
			}
	} 
	
	/* Eliminación de la información de la inversión de la base de datos*/
	public void deleteInversion(String numOperacion) {
		try {
			Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
		    String DeleteCuentaBanco=  "Delete from operaciones where NumOperacion = ?;";
		    PreparedStatement pstmt2 = conexion.prepareStatement(DeleteCuentaBanco);
			pstmt2.setString(1, numOperacion);
			pstmt2.executeUpdate();
	    	} catch(Exception e) {
	    		System.out.println("No se ha podido eliminar la inversión de manera correcta");
	    		e.printStackTrace();
			}
	}

	
	/* Obtención de los datos actuales del usuario*/
	public void getUserData(String User) {
		String Contraseña = ""; String numeroDeTelefono = ""; String domicilio = "";
		try {
			Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
		    String Operacion=  "SELECT Contraseña, Telefono, Domicilio from usuarios where Usuario = ?";
		    PreparedStatement pstmt2 = conexion.prepareStatement(Operacion);
			pstmt2.setString(1, User);
			ResultSet setOperacionTabla = pstmt2.executeQuery();
		    while(setOperacionTabla.next()) {
		    	Contraseña = setOperacionTabla.getString("Contraseña");
		    	numeroDeTelefono = setOperacionTabla.getString("Telefono");
		    	domicilio = setOperacionTabla.getString("Domicilio");
		    	DatosUsuario.add(Contraseña);
		    	DatosUsuario.add(numeroDeTelefono);
		    	DatosUsuario.add(domicilio);
		    }
		
	    	} catch(Exception e) {
	    		System.out.println("No se encontraron los datos completos del usuario");
	    		e.printStackTrace();
			}
			}
		
		/* Actualización de los datos ingresados por el usuario */
		public void setUserData(String User, String Telefono, String Password, String Domicilio) {
			String Contraseña = ""; String numeroDeTelefono = ""; String domicilio = "";
			try {
				Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
			    String Operacion=  "update usuarios set Telefono = ?, Contraseña = ?, Domicilio = ? where Usuario = ?";
			    PreparedStatement pstmt2 = conexion.prepareStatement(Operacion);
				pstmt2.setString(1, Telefono);
				pstmt2.setString(2, Password);
				pstmt2.setString(3, Domicilio);
				pstmt2.setString(4, User);
				pstmt2.executeUpdate();
				pstmt2.close();
				
				 String rellenar =  "SELECT Contraseña, Telefono, Domicilio FROM usuarios WHERE Usuario = ?";
				 PreparedStatement pstmt3 = conexion.prepareStatement(rellenar);
				 pstmt3.setString(1, User);		 
				ResultSet setOperacionTabla = pstmt3.executeQuery();
				
				  while(setOperacionTabla.next()) {
				    	Contraseña = setOperacionTabla.getString("Contraseña");
				    	numeroDeTelefono = setOperacionTabla.getString("Telefono");
				    	domicilio = setOperacionTabla.getString("Domicilio");
				    	DatosUsuario.add(Contraseña);
				    	DatosUsuario.add(numeroDeTelefono);
				    	DatosUsuario.add(domicilio);
				    }
				  setOperacionTabla.close();
		    	} catch(Exception e) {
		    		System.out.println("Por favor intente llenar lod datos nuevamente");
		    		e.printStackTrace();
				}
		}
		
		/* Terminación de la inversión y actualización del nuevo monto en la cuenta bancaria*/
		public void terminaInversion(String User, double monto, int tiempo, float tasa, String numOperacion) {
				String numCuenta = getCuenta(numOperacion);
				double SaldoAnterior = antiguoSaldo(numCuenta);
				System.out.println(SaldoAnterior);
			try {
				Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
			    String Operacion=  "update cuentasbancarias set Saldo = ? where Numero_Cuenta = ?";
			    PreparedStatement pstmt2 = conexion.prepareStatement(Operacion);
			    double NuevoSaldo = 0;
				double VF = monto * Math.pow((1+tasa), tiempo);
				double ganancia = VF - monto;
				double rendimiento = (ganancia/monto)*100;
				NuevoSaldo = SaldoAnterior + VF;
				pstmt2.setDouble(1, NuevoSaldo);
				pstmt2.setString(2, numCuenta);
				pstmt2.executeUpdate();
		    	} catch(Exception e) {
		    		System.out.println("No se ha podido concluir el proceso de finalizacion de la inversión de manera satisfactoria");
		    		e.printStackTrace();
				}
		}
		
		/* Obtencion del saldo actual de la cuenta bancaria para posteriormente añadirle el monto de la inversión*/
		public double antiguoSaldo(String numCuenta) {
			Double SaldoAnterior = 0.0;
;			try {
				Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
				 String Operacion= "select Saldo from cuentasbancarias where Numero_Cuenta = ?";
				 PreparedStatement pstmt2 = conexion.prepareStatement(Operacion);
				 pstmt2.setString(1, numCuenta);
				 ResultSet setOperacionTabla = pstmt2.executeQuery();
				 while(setOperacionTabla.next()) {
					 SaldoAnterior= setOperacionTabla.getDouble("Saldo");
				    }
			} catch(Exception e) {
	    		System.out.println("Error en el saldo anterior de la cuenta de depósito");
	    		e.printStackTrace();
			}
			return SaldoAnterior;
		}
		
		/* Obtencion de los datos de la cuenta */
		public String getCuenta(String numOperacion) {
			String Cuenta = "";
			try {
				Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
			    String Operacion=  "SELECT CuentaDeposito FROM intermedia, operaciones where IDoperacion = ?";
			    PreparedStatement pstmt2 = conexion.prepareStatement(Operacion);
			    pstmt2.setString(1, numOperacion);
			    ResultSet setOperacionTabla = pstmt2.executeQuery();
			    System.out.println(Cuenta);
			    while(setOperacionTabla.next()) {
			    	Cuenta = setOperacionTabla.getString("CuentaDeposito");
				    }
		    	} catch(Exception e) {
		    		System.out.println("Error en la obtención de los datos de la cuenta");
		    		e.printStackTrace();
				}
				return Cuenta;
		}
		

		/* Separacion de operacion específica para la obtención de la tasa de interés, utilizada para rellenar
		 * la tabla mostrada en la GUI_VerInversiones*/
		public float getTasa(String opcion) {
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
			return tasa;
		}
		
		public void AddToHistorial(String User, String tipOperacion, Double Monto, String Fecha, String Estado) {
			try {
				Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
			    String Operacion=  "Insert into historialinversiones (Usuario, operacion, Monto, Fecha, Estado) values(?, ?, ?, ?, ?)";
			    PreparedStatement pstmt2 = conexion.prepareStatement(Operacion);
			    pstmt2.setString(1, User);
			    pstmt2.setString(2, tipOperacion);
			    pstmt2.setDouble(3, Monto);
			    pstmt2.setString(4, Fecha);
			    pstmt2.setString(5, Estado);
			    pstmt2.executeUpdate();
		    	} catch(Exception e) {
		    		System.out.println("Error en la obtención de los datos de la cuenta");
		    		e.printStackTrace();
				}
		}
		
		public void getHistory(String User) {
			String operacion = ""; String Monto = ""; String Fecha = ""; String Estado = "";
			try {
				Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
			    String Operacion=  "Select operacion, Monto, Fecha, Estado from historialinversiones where Usuario like ?";
			    PreparedStatement pstmt2 = conexion.prepareStatement(Operacion);
			    pstmt2.setString(1, User);
			    ResultSet setOperacionTabla = pstmt2.executeQuery();
			    while(setOperacionTabla.next()) {
			    	operacion = setOperacionTabla.getString("operacion");
			    	Monto = setOperacionTabla.getString("Monto");
			    	Fecha = setOperacionTabla.getString("Fecha");
			    	Estado = setOperacionTabla.getString("Estado");
			    	DatosHistorial.add(operacion);
			    	DatosHistorial.add(Monto);
			    	DatosHistorial.add(Fecha);
			    	DatosHistorial.add(Estado);
			    }
		    	} catch(Exception e) {
		    		System.out.println("Error en la obtención de los datos del historial");
		    		e.printStackTrace();
				}
		}
		
		public void UpdateHistorial(String User, String tipOperacion, Double Monto, String Fecha) {
			try {
				Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
			    String Operacion=  "update historialinversiones set Estado = 'Finalizada' where Usuario = ? and operacion = ? and Monto = ? and Fecha = ? and Estado = 'Activa'";
			    PreparedStatement pstmt2 = conexion.prepareStatement(Operacion);
			    pstmt2.setString(1, User);
			    pstmt2.setString(2, tipOperacion);
			    pstmt2.setDouble(3, Monto);
			    pstmt2.setString(4, Fecha);
			    pstmt2.executeUpdate();
		    	} catch(Exception e) {
		    		System.out.println("Error en la actualización del estado de la operación");
		    		e.printStackTrace();
				}
	


	
	}
}