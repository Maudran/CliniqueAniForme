package fr.eni.aniforme.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
	// bloc d'initialisation statique
	static String connexionString;

	static {
		try {
			Class.forName(Settings.getProperty("driverdb"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		connexionString = Settings.getProperty("connexionString");
		System.out.println("driverdb: " + Settings.getProperty("driverdb"));
		System.out.println("urldb: " + connexionString);
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(connexionString);
	}

}
