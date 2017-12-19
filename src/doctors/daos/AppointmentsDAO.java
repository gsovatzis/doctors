package doctors.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doctors.exceptions.DBManagerException;
import doctors.framework.DBHandler;
import doctors.framework.DBManager;
import doctors.models.Appointment;
import doctors.models.City;
import doctors.models.User;

public class AppointmentsDAO extends DBHandler<Appointment> {

	protected final String findAppointmentsForUser = "SELECT appointment_id, user_id, doctor_id, appointment_date_time, medical_examination, user_comments, rating FROM Appointments WHERE user_id=?;";
	protected final String findAppointmentsForDoctor = "SELECT appointment_id, user_id, doctor_id, appointment_date_time, medical_examination, user_comments, rating FROM Appointments WHERE doctor_id=?;";
	
	public AppointmentsDAO() throws DBManagerException {
		super(DBManager.getConnection());	// Inject the Connection dependency to the DAO on initialization
	}

	@Override
	public void Create(Appointment entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Appointment> GetAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Appointment> GetAppointmentsForDoctor(int doctor_id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Appointment> GetAppointmentsForUser(int user_id) throws SQLException {
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
	protected Appointment Populate(ResultSet rst, boolean loadForeign) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}
