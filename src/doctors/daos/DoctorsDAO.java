package doctors.daos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doctors.exceptions.DBManagerException;
import doctors.framework.DBHandler;
import doctors.models.Doctor;
import doctors.models.User;


public class DoctorsDAO extends DBHandler<Doctor>  {
	
	protected final String returnDoctorsQuery = "SELECT doctor_id, user_id FROM DOCTORS "
											  + "INNER JOIN USERS ON DOCTORS.user_id = USERS.user_id ";
	
	 public DoctorsDAO() throws DBManagerException {
			super();
			// TODO Auto-generated constructor stub
	 }
    
	@Override 
    public List<Doctor> GetAll() throws SQLException { //Σου επιστρεφει ολη την λιστα με τους γιατρους//
    	PreparedStatement stmt = null;
		ResultSet rst = null; 
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			stmt = conn.prepareStatement(returnDoctorsQuery); //γιατι βγαζει ερρορ στο conn;
			rst = stmt.executeQuery();
			while(rst.next()) {
				Doctor doctor = Populate(rst);
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

/*
    public appointments findAppointments(String doctor_idd) { //επιστρέφει τα ραντεβού του γιατρού αφου εχει βεβαιωθει οτι το doctor_idd ταιριαζει με το id του γιατρου//

         return appointments;
    }

    public working_hours findtWorking_hours(String doctor_idd) { //επιστρέφει τις ώρες εργασίες του Γιατρού αφου βεβαιωθει οτι τ
		return working_hours
    }

    public specialties findSpecialties(String doctor_idd) { //επιστρέφει τις ειδικότητες του Γιατρού αφού βεβαιωθεί ότι το 

    	return specialties;

    }

*/
	
	protected Doctor Populate(ResultSet rst) throws SQLException {
		
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
			
			
			// 5. Διαβάζω τα ραντεβού του γιατρού και τα βάζω στο κατάλληλο object
			AppointmentsDAO ad = new AppointmentsDAO();
			doctor.setAppointments(ad.GetAppointmentsForDoctor(doctor.getDoctor_id()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctor;
	}
	

	
	
	
	
	
	
	
	
	
}