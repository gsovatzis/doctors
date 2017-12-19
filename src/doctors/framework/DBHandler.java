package doctors.framework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import doctors.exceptions.DBManagerException;

public abstract class DBHandler<Model> {

	protected Connection conn = null;
	
	private final String lastInsertIdQuery = "SELECT LAST_INSERT_ID() AS LastInsId";
	
	public DBHandler(Connection conn) throws DBManagerException {
		// Inject the DB connection on initialization
		this.conn = conn;
	}
	
	public abstract void Create(Model entity) throws SQLException;
	
	public abstract ArrayList<Model> GetAll() throws SQLException;
	
	public abstract void Update(Model entity) throws SQLException;

	public abstract void Delete(Model entity) throws SQLException;
	
	public abstract Model GetById(int id) throws SQLException;
	
	protected abstract Model Populate(ResultSet rst, boolean loadForeign) throws SQLException;
	
	public int GetLastInsertId() throws SQLException {
		// This method returns the last inserted id in the database session!
		int result = 0;
		
		Statement lastInsertIdStmt = conn.createStatement();
		ResultSet rst = null;
		
		try {
			rst = lastInsertIdStmt.executeQuery(lastInsertIdQuery);
			while(rst.next()) {
				result = rst.getInt("LastInsId");
			}
		
		} catch(SQLException ex) {
			throw ex;
			
		} finally {
		
			// Close query and result set
			rst.close();
			lastInsertIdStmt.close();
		}
		
		return result;

	}

	
}
