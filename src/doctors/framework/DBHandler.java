package doctors.framework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import doctors.exceptions.DBManagerException;

public abstract class DBHandler<Entity> {

	protected Connection conn = null;
	
	private final String lastInsertIdQuery = "SELECT LAST_INSERT_ID() AS LastInsId";
	
	public DBHandler() throws DBManagerException {
		// Get the DB connection on initialization
		conn = DBManager.getInstance().getConnection();		// SINGLETON PATTERN
		
		if(conn==null) 
			throw new DBManagerException("Δεν μπορώ να συνδεθώ με τη βάση δεδομένων!");
	}
	
	public abstract void Create(Entity entity) throws SQLException;
	
	public abstract List<Entity> GetAll() throws SQLException;
	
	public abstract void Update(Entity entity) throws SQLException;

	public abstract void Delete(Entity entity) throws SQLException;
	
	public abstract Entity GetById(int id) throws SQLException;
	
	protected abstract Entity Populate(ResultSet rst) throws SQLException;
	
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
