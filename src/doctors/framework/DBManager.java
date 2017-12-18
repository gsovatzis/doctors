package doctors.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private static DBManager instance = null;
	
	private Connection conn = null;
	
	private String errorMessages = "";
	
	private DBManager() {
		
	}

	// SINGLETON PATTERN
	public static DBManager getInstance() {
		if (instance==null) {
			instance = new DBManager();			
		}
		
		return instance;
	}
	
	public void openConnection(String DBURL, String userName, String Password) throws SQLException {
		try {
		      // for JDBC driver to connect to mysql, the .newInstance() method
		      // can be ommited
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
	    } catch (Exception e1) {
		      errorMessages = "MySQL Driver error: <br>" + e1.getMessage();
		      throw new SQLException(errorMessages);
	    }

	    try {
	    	conn = DriverManager.getConnection(DBURL,userName,Password);
	    	
	    	
	    } catch (Exception e2) {
	    	errorMessages = "Could not establish connection with the Database Server: <br>"
	          + e2.getMessage();
	    	conn = null;
	    	throw new SQLException(errorMessages);
	    }
		
	}
	
	public void closeConnection() throws SQLException {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				errorMessages = "Could not close connection with the Database Server: <br>"
				          + e.getMessage();
				      throw new SQLException(errorMessages);
			}
		}
	}
	
	public Connection getConnection() {
		return conn;
	}
	
}
