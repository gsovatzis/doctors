package doctors.daos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doctors.exceptions.DBManagerException;
import doctors.framework.DBHandler;
import doctors.framework.DBManager;
import doctors.models.Doctor;
import doctors.models.User;


public class DoctorsDAO extends DBHandler<Doctor>  {
	
	protected final String returnDoctorsQuery = "SELECT doctor_id, user_id FROM DOCTORS "
											  + "INNER JOIN USERS ON DOCTORS.user_id = USERS.user_id ";
	
	 public DoctorsDAO() throws DBManagerException {
		 super(DBManager.getConnection());	// Inject the Connection dependency to the DAO on initialization
	 }
    
	@Override 
    public ArrayList<Doctor> GetAll() throws SQLException { //Σου επιστρεφει ολη την λιστα με τους γιατρους//
    	PreparedStatement stmt = null;
		ResultSet rst = null; 
		ArrayList<Doctor> doctors = new ArrayList<Doctor>();
		try {
			stmt = conn.prepareStatement(returnDoctorsQuery); //γιατι βγαζει ερρορ στο conn;
			rst = stmt.executeQuery();
			while(rst.next()) {
				Doctor doctor = Populate(rst,false);
				doctors.add(doctor);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			rst.close();
			stmt.close();
		}
		
		return doctors;
			
  }

	protected Doctor Populate(ResultSet rst, boolean loadForeign) throws SQLException {
		
		Doctor doctor = new Doctor();
		try {
			
			// 1. Ορίζω τις βασικές ιδιότητες του γιατρού
			doctor.setDoctor_id(rst.getInt("doctor_id"));
			
			// 2. Διαβάζω τον σχετιζόμενο χρήστη με τον γιατρό (αν υπάρχει) και τον βάζω στο κατάλληλο object
			UsersDAO ud = new UsersDAO();
			User u = ud.GetById(rst.getInt("user_id"));
			doctor.setUser(u);
			
			// 3. Διαβάζω τις ώρες εργασίας του γιατρού και τις βάζω στο κατάλληλο object
			WorkingHoursDAO wd = new WorkingHoursDAO();
			doctor.setWorking_hours(wd.GetWorkingHoursForDoctor(doctor.getDoctor_id()));
						
			// 4. Διαβάζω τις ειδικότητες του γιατρού και τις βάζω στο κατάλληλο object
			SpecialtiesDAO sd = new SpecialtiesDAO();
			doctor.setSpecialties(sd.GetSpecialtiesForDoctor(doctor.getDoctor_id()));

			if(loadForeign==true) {
				// 5. Διαβάζω τα ραντεβού του γιατρού και τα βάζω στο κατάλληλο object
				AppointmentsDAO ad = new AppointmentsDAO();
				doctor.setAppointments(ad.GetAppointmentsForDoctor(doctor.getDoctor_id()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctor;
	}

	@Override
	public void Create(Doctor entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update(Doctor entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Doctor entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Doctor GetById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
	
	
	
	
	
	
	
}