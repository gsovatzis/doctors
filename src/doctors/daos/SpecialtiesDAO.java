package doctors.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doctors.exceptions.DBManagerException;
import doctors.framework.DBHandler;
import doctors.framework.DBManager;
import doctors.models.Specialty;

public class SpecialtiesDAO extends DBHandler<Specialty> {

	public SpecialtiesDAO() throws DBManagerException {
		super(DBManager.getInstance().getConnection());	// Inject the Connection dependency to the DAO on initialization
	}

	@Override
	public void Create(Specialty entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Specialty> GetAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Specialty> GetSpecialtiesForDoctor(int doctor_id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void Update(Specialty entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Specialty entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Specialty GetById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Specialty Populate(ResultSet rst, boolean loadForeign) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
