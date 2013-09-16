package br.com.orlandoburli.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.vo.IValueObject;

public abstract class BaseDAO {
	public static final String br = "\n";

	private String lastMessage;
	private boolean autoCommit = true;
	private Connection connection;

	public BaseDAO() {}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		try {
			if (isAutoCommit() || connection == null || connection.isClosed()) {
				connection = getNewConnection();
			}
			return connection;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Connection getNewConnection() throws ClassNotFoundException, SQLException, NamingException {
		if (SystemManager.getProperty("db.type").equalsIgnoreCase("datasource")) {
			InitialContext cxt = new InitialContext();

			String dsName = SystemManager.getProperty("db.datasourcename");

			DataSource ds = (DataSource) cxt.lookup(dsName);

			if (ds == null) {
				return null;
			}
			Connection conn = ds.getConnection();

			conn.setAutoCommit(isAutoCommit());
			if (conn.getMetaData().getDatabaseMajorVersion() >= 9) {
				conn.prepareStatement("set application_name = 'PersonalERP';").execute();
			}
			return conn;
		} else {
			Class.forName(SystemManager.getProperty("db.classdriver"));
			String url = "jdbc:postgresql://" + SystemManager.getProperty("db.host") + ":" + SystemManager.getProperty("db.port") + "/" + SystemManager.getProperty("db.database");
			Connection conn = null;
			conn = DriverManager.getConnection(url, SystemManager.getProperty("db.user"), SystemManager.getProperty("db.pass"));
			conn.setAutoCommit(isAutoCommit());
			return conn;
		}
	}

	public void commit() throws SQLException {
		if (connection != null) {
			try {
				connection.commit();
				connection.close();
				connection = null;
			} catch (SQLException e) {
				throw e;
			}
		}
	}

	public void rollback() {
		if (connection != null) {
			try {
				connection.rollback();
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void mergeDAO(BaseDAO anotherDao) throws SQLException {
		try {
			anotherDao.setAutoCommit(false);
			this.connection = anotherDao.getConnection();
			this.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected ResultSet getResult(String statement, Connection conn) throws ClassNotFoundException, SQLException {
		ResultSet result = null;
		PreparedStatement prepared = conn.prepareStatement(statement);
		if (SystemManager.getProperty("debug.sql").equalsIgnoreCase("true") && SystemManager.getProperty("debug.sql.select").equalsIgnoreCase("true")) {
			// System.out.print(Utils.getNow() + " ");
			System.out.println(statement);
		}
		result = prepared.executeQuery();
		return result;
	}

	public CachedRowSet getRowSet(String statement) throws SQLException, ClassNotFoundException {
		CachedRowSet rowset = null;
		rowset = new CachedRowSetImpl();
		Connection conn = getConnection();
		ResultSet result = getResult(statement, conn);
		rowset.populate(result);
		result.close();
		if (isAutoCommit()) {
			conn.close();
		}
		return rowset;
	}

	protected boolean executeUpdate(String statement) throws ClassNotFoundException, SQLException, IllegalArgumentException, IllegalAccessException {
		return executeUpdate(statement, null);
	}

	protected boolean executeUpdate(String statement, IValueObject vo) throws ClassNotFoundException, SQLException, IllegalArgumentException, IllegalAccessException {
		return executeUpdate(new String[] { statement }, new IValueObject[] { vo });
	}

	protected boolean executeUpdate(String[] statements, IValueObject[] vos) throws ClassNotFoundException, SQLException, IllegalArgumentException, IllegalAccessException {
		boolean sucesso = false;
		Connection conn = getConnection();

		for (int i = 0; i < statements.length; i++) {
			if (SystemManager.getProperty("debug.sql").equalsIgnoreCase("true")) {
				if (vos[i] != null && vos[i].IsNew() && SystemManager.getProperty("debug.sql.insert").equalsIgnoreCase("true")) {
					System.out.println(statements[i]);
				} else if (SystemManager.getProperty("debug.sql.update").equalsIgnoreCase("true")) {
					System.out.println(statements[i]);
				}
			}

			CallableStatement prepared = conn.prepareCall(statements[i]);

			// Seta os parametros pelo vo
			if (vos[i] != null) {
				DaoUtils.voToPreparedStatement(vos[i], prepared);
			}

			sucesso = false;

			if (vos[i] != null && DaoUtils.hasAutoIncrement(vos[i]) && vos[i].IsNew()) {
				ResultSet keys = prepared.executeQuery(); // Executa como query
				if (keys.next()) {
					sucesso = true;
					DaoUtils.setIdReturned(vos[i], keys.getInt(1)); // Busca o
																	// Id
																	// retornado
																	// e seta no
																	// VO
					keys.close();
				}
			} else {
				sucesso = prepared.executeUpdate() >= 0;
			}
			if (!sucesso) {
				if (SystemManager.getProperty("debug.sql").equalsIgnoreCase("true")) {
					System.out.println("ERRO NO UPDATE!!!");
				}
				conn.prepareStatement("");
				conn.rollback();
				conn.close();
				return false;
			}
		}
		if (isAutoCommit()) {
			conn.commit();
			conn.close();
		}
		return sucesso;
	}

	protected boolean executeDelete(String statement, IValueObject vo) throws ClassNotFoundException, SQLException, IllegalArgumentException, IllegalAccessException {
		boolean sucesso = false;
		Connection conn = getConnection();
		if (SystemManager.getProperty("debug.sql").equalsIgnoreCase("true") && SystemManager.getProperty("debug.sql.delete").equalsIgnoreCase("true")) {
			System.out.println(statement);
		}
		CallableStatement prepared = conn.prepareCall(statement);

		// Seta os parametros pelo vo
		if (vo != null) {
			DaoUtils.voToPreparedStatement(vo, prepared, true);
		}

		sucesso = false;

		if (vo != null && DaoUtils.hasAutoIncrement(vo) && vo.IsNew()) {
			ResultSet keys = prepared.executeQuery(); // Executa como query
			if (keys.next()) {
				sucesso = true;
				DaoUtils.setIdReturned(vo, keys.getInt(1)); // Busca o Id
															// retornado e seta
															// no VO
				keys.close();
			}
		} else {
			sucesso = prepared.executeUpdate() >= 0;
		}
		if (!sucesso) {
			if (SystemManager.getProperty("debug.sql").equalsIgnoreCase("true") && SystemManager.getProperty("debug.sql.delete").equalsIgnoreCase("true")) {
				System.out.println("ERRO NO DELETE!!!");
			}
			conn.rollback();
			conn.close();
			return false;
		}
		if (isAutoCommit()) {
			conn.commit();
			conn.close();
		}
		return sucesso;
	}

	protected String getTableName() {
		return getSchemaName() + "." + this.getClass().getSimpleName().replaceAll("DAO", "");
	}

	public String getSchemaName() {
		String nomeSchema = SystemManager.getProperty("db.schema");
		return nomeSchema;
	}

	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}

	public String getLastMessage() {
		return lastMessage;
	}

	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}

	public boolean isAutoCommit() {
		return autoCommit;
	}
}