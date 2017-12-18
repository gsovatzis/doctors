package doctors.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import doctors.exceptions.DBManagerException;
import doctors.framework.DBHandler;
import doctors.models.Appointment;
import doctors.models.City;
import doctors.models.User;

public class AppointmentsDAO extends DBHandler<Appointment> {

	public AppointmentsDAO() throws DBManagerException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Create(Appointment entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Appointment> GetAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Appointment> GetAppointmentsForDoctor(int doctor_id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Appointment> GetAppointmentsForClient(int user_id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void Update(Appointment entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Appointment entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Appointment GetById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Appointment Populate(ResultSet rst) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}
