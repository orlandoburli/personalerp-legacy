package br.com.orlandoburli.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import br.com.orlandoburli.SystemManager;

public final class ConnectionFactory {
	private static ConnectionFactory instance = null;

	private Connection connection;

	// Construtor privado para ninguem instanciar
	private ConnectionFactory() {
		super();
	}

	/**
	 * Retorna instancia unica da Factory
	 * 
	 * @return
	 */
	public static ConnectionFactory getFactory() {
		if (instance == null) {
			instance = new ConnectionFactory();
		}
		return instance;
	}

	private Connection getNewConnection() throws ClassNotFoundException, SQLException, NamingException {
		if (SystemManager.getProperty("debug.connection.factory").equalsIgnoreCase("true")) {
			System.out.println("Retornando nova conexão");
		}
		
		if (SystemManager.getProperty("db.type").equalsIgnoreCase("datasource")) {
			InitialContext cxt = new InitialContext();

			String dsName = SystemManager.getProperty("db.datasourcename");

			DataSource ds = (DataSource) cxt.lookup(dsName);

			if (ds == null) {
				return null;
			}
			Connection conn = ds.getConnection();

			conn.setAutoCommit(false);
			if (conn.getMetaData().getDatabaseMajorVersion() >= 9) {
				conn.prepareStatement("set application_name = 'PersonalERP';").execute();
			}
			return conn;
		} else {
			Class.forName(SystemManager.getProperty("db.classdriver"));
			String url = "jdbc:postgresql://" + SystemManager.getProperty("db.host") + ":" + SystemManager.getProperty("db.port") + "/" + SystemManager.getProperty("db.database");
			Connection conn = null;
			conn = DriverManager.getConnection(url, SystemManager.getProperty("db.user"), SystemManager.getProperty("db.pass"));
			conn.setAutoCommit(false);
			return conn;
		}
	}

	public Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				this.connection = getNewConnection();
			}
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			e.printStackTrace();
		}
		
		if (SystemManager.getProperty("debug.connection.factory").equalsIgnoreCase("true")) {
			System.out.println("Retornando conexão");
		}
		return connection;
	}

	public void commit() throws SQLException {
		
		if (SystemManager.getProperty("debug.connection.factory").equalsIgnoreCase("true")) {
			System.out.println("Executando commit na conexão");
		}
		
		if (connection != null) {
			try {
				connection.commit();
				connection.close();
				connection = null;
				
				if (SystemManager.getProperty("debug.connection.factory").equalsIgnoreCase("true")) {
					System.out.println("Conexão commitada e finalizada");
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}

	public void rollback() throws SQLException {
		if (SystemManager.getProperty("debug.connection.factory").equalsIgnoreCase("true")) {
			System.out.println("Executando rollback na conexão");
		}
		
		if (connection != null) {
			try {
				connection.rollback();
				connection.close();
				connection = null;
				
				if (SystemManager.getProperty("debug.connection.factory").equalsIgnoreCase("true")) {
					System.out.println("Rollback executado na conexão");
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}
}
