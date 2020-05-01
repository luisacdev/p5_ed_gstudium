package es.studium.gabineteMVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Modelo
{
	/* Conexión a la Base de Datos */
	Connection connMySQL = null;
	private final static String MySQL_DB_DRIVER = "com.mysql.jdbc.Driver";
	private final static String MySQL_DB_URL = "jdbc:mysql://localhost/";
	private final static String MySQL_DB_USUARIO = "root";
	private final static String MySQL_DB_PASSWORD = "1234";

	public Connection Conexion(String gabinete)
	{
		try
		{
			Class.forName(MySQL_DB_DRIVER);
			connMySQL = DriverManager.getConnection(MySQL_DB_URL + gabinete + "?&useSSL=false", MySQL_DB_USUARIO,
					MySQL_DB_PASSWORD);
			if (connMySQL != null)
			{
				System.out.println("Conectado a la base de datos");
			}
		} catch (SQLException ex)
		{
			System.out.println("ERROR:La dirección no es válida o el usuario y clave");
			ex.printStackTrace();
		} catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-" + cnfe.getMessage());
		}
		return connMySQL;
	}

	/* Insertar datos en la Base de Datos */
	public void Alta(String nombrePsicologo)
	{
		try
		{
			connMySQL = Conexion("gabinete");
			String query = "INSERT INTO psicologos (nombrePsicologo) VALUES (?);";
			PreparedStatement pst = connMySQL.prepareStatement(query);
			pst.clearParameters();
			pst.setString(1, nombrePsicologo);
			pst.execute();
			pst.close();
			System.out.println("Registrado correctamente: " + nombrePsicologo);
		} catch (Exception ex)
		{
		}
	}

	/* Consultar ID por Nombre en la Base de Datos */
	public String ConsultaXNombre(String nombrePsicologo) throws SQLException
	{

		String idResultado = null;
		connMySQL = Conexion("gabinete");
		String query = "SELECT idPsicologo FROM psicologos WHERE nombrePsicologo='" + nombrePsicologo + "'";
		Statement st = connMySQL.createStatement();
		ResultSet res = st.executeQuery(query);
		while (res.next())
			idResultado = res.getString("idPsicologo");
		res.close();
		st.close();
		return (idResultado);
	}

	/* Consultar Nombres por ID en la Base de Datos */
	public String ConsultaXID(String idPsicologo) throws SQLException
	{

		String nombreResultado = null;
		connMySQL = Conexion("gabinete");
		String query = "SELECT nombrePsicologo FROM psicologos WHERE idPsicologo='" + idPsicologo + "'";
		Statement st = connMySQL.createStatement();
		ResultSet res = st.executeQuery(query);
		while (res.next())
			nombreResultado = res.getString("nombrePsicologo");
		res.close();
		st.close();
		return (nombreResultado);
	}

	/* Listar Nombres de la Base de Datos */
	public ArrayList<String> ConsultarPsicologos() throws SQLException
	{
		connMySQL = Conexion("gabinete");
		String query = "SELECT * FROM psicologos";
		Statement st = connMySQL.createStatement();
		ResultSet res = st.executeQuery(query);
		ArrayList<String> nombres = new ArrayList<String>();
		while (res.next())
		{
			nombres.add(res.getString(2));
		}
		res.close();
		st.close();
		return nombres;
	}

	/* Borrar Psicologos de la Base de Datos */
	public void Eliminar(String nombrePsicologo)
	{
		try
		{
			connMySQL = Conexion("gabinete");
			String query = "DELETE FROM psicologos WHERE nombrePsicologo = ?";
			PreparedStatement st = connMySQL.prepareStatement(query);
			st.setString(1, nombrePsicologo);
			st.execute();
			st.close();
		} catch (Exception ex)
		{
		}
	}

	/* Editar Nombres en la Base de Datos */
	public void Modificar(String nuevoNombre, String nombrePsicologo) throws SQLException
	{
		connMySQL = Conexion("gabinete");
		String query = "UPDATE psicologos SET nombrePsicologo='" + nuevoNombre + "' WHERE nombrePsicologo='"
				+ nombrePsicologo + "'";
		Statement pst = connMySQL.createStatement();
		pst.executeUpdate(query);
		pst.close();
	}
}