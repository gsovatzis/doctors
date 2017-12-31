package doctors.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import doctors.exceptions.DBManagerException;
import doctors.framework.DBHandler;
import doctors.framework.DBManager;
import doctors.models.City;

public class CitiesDAO extends DBHandler<City> {

	protected final String findCityById = "SELECT city_id, city_name FROM Cities WHERE city_id=?;";
	protected final String findAllCities = "SELECT * FROM Cities";
	
	public CitiesDAO() throws DBManagerException {
		super(DBManager.getInstance().getConnection());	// Inject the Connection dependency to the DAO on initialization
	}

	@Override
	public void Create(City entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<City> GetAll() throws SQLException {
		ArrayList<City> cities = new ArrayList<City>();	// Initially our return object is null, if no city is found NULL will be returned
		
		try {
			// Prepare query parameters
			PreparedStatement findStmt = conn.prepareStatement(findAllCities);
			ResultSet rst = null;
			
			// Execute the query and get result set from database
			rst = findStmt.executeQuery();
			
			// If the result set moves to the next record, then city is found 
			// -> fill the city object to be returned from the Database
			while(rst.next()) {
				City city = Populate(rst, false);
				cities.add(city);
			}
			
			// Close query and result set
			findStmt.close();
			rst.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return cities;
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
		return this.GetById(id, true);
	}

	@Override
	public City GetById(int id, boolean loadForeign) throws SQLException {
		City city = null;	// Initially our return object is null, if no city is found NULL will be returned
		
		try {
			// Prepare query parameters
			PreparedStatement findCityStmt = conn.prepareStatement(findCityById);
			ResultSet rst = null;
			
			findCityStmt.setInt(1, id);
			
			// Execute the query and get result set from database
			rst = findCityStmt.executeQuery();
			
			// If the result set moves to the next record, then city is found 
			// -> fill the city object to be returned from the Database
			if(rst.next()) {
				city = Populate(rst, loadForeign);
			}
			
			// Close query and result set
			findCityStmt.close();
			rst.close();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 

		return city;
	}

	@Override
	protected City Populate(ResultSet rst, boolean loadForeign) throws SQLException {
		City city = new City();
		try {
			city.setCity_id(rst.getInt("city_id")); 
			city.setCity_name(rst.getString("city_name"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}
	
	

}
