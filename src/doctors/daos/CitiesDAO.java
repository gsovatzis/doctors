package doctors.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import doctors.exceptions.DBManagerException;
import doctors.framework.DBHandler;
import doctors.models.City;

public class CitiesDAO extends DBHandler<City> {

	public CitiesDAO() throws DBManagerException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Create(City entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<City> GetAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(City entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(City entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public City GetById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected City Populate(ResultSet rst) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
