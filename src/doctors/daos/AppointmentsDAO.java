package doctors.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import doctors.exceptions.DBManagerException;
import doctors.framework.DBHandler;
import doctors.framework.DBManager;
import doctors.models.Appointment;

public class AppointmentsDAO extends DBHandler<Appointment> {

	protected final String findAppointmentsForUser = "SELECT appointment_id, user_id, doctor_id, appointment_date_time, medical_examination, user_comments, rating FROM Appointments WHERE user_id=?;";
	protected final String findAppointmentsForDoctor = "SELECT appointment_id, user_id, doctor_id, appointment_date_time, medical_examination, user_comments, rating FROM Appointments WHERE doctor_id=?;";
	protected final String createAppointment = "INSERT INTO Appointments VALUES (?,?,?,?,?,?)";
	protected final String getAllAppointments = "SELECT * FROM Appointments";
	protected final String getById = "SELECT appointment_id,user_id,doctor_id,appointment_date_time,medical_examination,user_comments,rating FROM Appointments WHERE appointment_id = ?;";
	
	public AppointmentsDAO() throws DBManagerException {
		super(DBManager.getInstance().getConnection());	// Inject the Connection dependency to the DAO on initialization
	}

	@Override
	public void Create(Appointment entity) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(createAppointment);
			stmt.setInt(1,entity.getUser().getUser_id());
			stmt.setInt(2, entity.getDoctor().getDoctor_id());
			stmt.setDate(3, new java.sql.Date(entity.getAppointment_date_time().getTime()));
			stmt.setString(4,entity.getMedical_examination());
			stmt.setString(5, entity.getUser_comments());
			stmt.setInt(6,entity.getRatings());
			stmt.executeUpdate();
		}catch(SQLException ex) {
			throw ex;
		
		}finally {
			stmt.close();
		}
		
	}

	@Override
	public ArrayList<Appointment> GetAll() throws SQLException {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.prepareStatement(getAllAppointments);
			rst = stmt.executeQuery();
	
			while(rst.next()) {
				Appointment appointment = null;
				appointment = Populate(rst,true);
				appointments.add(appointment);
				
		    }
			
		}catch(SQLException ex) {
			
			throw ex;
		
		}finally {
			rst.close();
			stmt.close();
		}
		
		return appointments;
	}

	public ArrayList<Appointment> GetAppointmentsForDoctor(int doctor_id) throws SQLException {
		ArrayList<Appointment> Appointments = new ArrayList<Appointment>();
		PreparedStatement smt = null;
		ResultSet rst = null;
		Appointment appointment = null;
		try {
			
			smt = conn.prepareStatement(findAppointmentsForDoctor);
			smt.setInt(1, doctor_id);
			rst = smt.executeQuery();
			while(rst.next()) {
				appointment = Populate(rst,false);
				Appointments.add(appointment);
			}
		}catch(SQLException ex) {
			
			throw ex;
		}finally {
			rst.close();
			smt.close();
		}
		return Appointments;
	}
	
	public ArrayList<Appointment> GetAppointmentsForUser(int user_id) throws SQLException {
		ArrayList<Appointment> Appointments = new ArrayList<Appointment>();
		PreparedStatement smt = null;
	    ResultSet rst = null;
		try {
			
			smt = conn.prepareStatement(findAppointmentsForUser);
			smt.setInt(1,user_id);
			rst = smt.executeQuery();
			while(rst.next()) {
				Appointment u = new Appointment();
				u = Populate(rst,false);
				Appointments.add(u);
				
			}
		}
		catch(SQLException ex) {
			
			throw ex;
	    }
		finally {
			rst.close();
			smt.close();
		
		}
		return Appointments;
		
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
		return this.GetById(id, true);
	}
	
	@Override
	public Appointment GetById(int id, boolean loadForeign) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Appointment appointment = null;
		try {
			stmt = conn.prepareStatement(getById);
			stmt.setInt(1, id);
			rst = stmt.executeQuery();
			if(rst.next()) {
				
				appointment = Populate(rst,loadForeign);
				
				
		   }
			
		}catch(SQLException ex) {
			
			throw ex;
		
	   }
	    finally {
	    	
	    	rst.close();
	    	stmt.close();
	    }
		
		return appointment;
	}

	@Override
	protected Appointment Populate(ResultSet rst, boolean loadForeign) throws SQLException {
		Appointment appointment = new Appointment();
		try {
			appointment.setAppointment_id(rst.getInt("Appointment_id"));
			appointment.setAppointment_date_time(rst.getDate("appointment_date_time"));
			appointment.setMedical_examination(rst.getString("medical_examination"));
            appointment.setUser_comments(rst.getString("user_comments"));
            appointment.setRating(rst.getInt("rating"));
            
            if(loadForeign == true) {
                   //Also populate appointment's User object 
                   UsersDAO ud = new UsersDAO();
                   appointment.setUser(ud.GetById(rst.getInt("user_id"),false));
                   
                   //Also populate appointment's Doctor object
                   DoctorsDAO dd = new DoctorsDAO();
                   appointment.setDoctor(dd.GetById(rst.getInt("doctor_id"),false));
            }
            
       }catch(Exception ex) {
    	   
    	  throw new SQLException(ex.toString());
    	  
       }
		return appointment;
	}


}
