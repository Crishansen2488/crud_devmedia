package br.com.devmedia.crud.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.DriverManager;

public class ConexaoUtil {

	private static ResourceBundle configDB = ResourceBundle.getBundle(Constantes.CONEXAO_BD_PROPERTIES);

	public static Connection getConexao() throws ClassNotFoundException, SQLException {
		Class.forName(configDB.getString(Constantes.CONEXAO_BD_DRIVER));
		
		return DriverManager.getConnection(
				configDB.getString(Constantes.CONEXAO_BD_URL), 
				configDB.getString(Constantes.CONEXAO_BD_USER),
				configDB.getString(Constantes.CONEXAO_BD_PASSWORD)
				);
	}
	
}
