package doctors.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doctors.exceptions.DBManagerException;
import doctors.framework.DBHandler;
import doctors.framework.DBManager;
import doctors.models.Working_Hour;

public class WorkingHoursDAO extends DBHandler<Working_Hour> {

	public WorkingHoursDAO() throws DBManagerException {
		super(DBManager.getInstance().getConnection());	// Inject the Connection dependency to the DAO on initialization
	}

	@Override
	public void Create(Working_Hour entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Working_Hour> GetAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Working_Hour> GetWorkingHoursForDoctor(int doctor_id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(Working_Hour entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Working_Hour entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Working_Hour GetById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Working_Hour Populate(ResultSet rst, boolean loadForeign) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
