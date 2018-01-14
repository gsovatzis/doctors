package doctors.daos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import doctors.exceptions.DBManagerException;
import doctors.framework.DBHandler;
import doctors.framework.DBManager;
import doctors.models.Doctor;
import doctors.models.User;


public class DoctorsDAO extends DBHandler<Doctor>  {
	
	protected final String returnDoctorsQuery = "SELECT doctor_id, user_id FROM DOCTORS; ";
	
	protected final String getDoctorById = "SELECT doctor_id, user_id FROM doctors WHERE doctors.doctor_id = ?";
	
	protected final String getDoctorRatingQuery = "SELECT AVG(rating) AS avg_rating FROM appointments WHERE doctor_id=?";
	
	protected String searchDoctorsQuery = "SELECT DISTINCT doctors.doctor_id, doctors.user_id FROM doctors "
                                              + " INNER JOIN users ON doctors.user_id = users.user_id"
                                          	  + " INNER JOIN doctors_specialties ON doctors.doctor_id = doctors_specialties.doctor_id";
                                   
                                                 
	public ArrayList<Doctor> SearchDoctors(String fullName, int specialtyId, int cityId, int minRating) throws SQLException {
		 ArrayList<Doctor> doctors = new ArrayList<Doctor>();
		 
		 PreparedStatement stmt = null;
		 ResultSet rst = null;
		 
		 try {
			 
			 // Phase 1: Complete the SQL Statement with the correct parameters
			 
			 if(fullName!=null) {
				 searchDoctorsQuery = addWhereOrAnd(searchDoctorsQuery) + " CONCAT(users.first_name,\" \", users.last_name) LIKE ?";
			 }
			 
			 if(specialtyId > 0) {
				 searchDoctorsQuery = addWhereOrAnd(searchDoctorsQuery) + " doctors_specialties.specialty_id=?";
			 }
			 
			 if(cityId > 0) {
				 searchDoctorsQuery = addWhereOrAnd(searchDoctorsQuery) + " users.city_id=?";
			 }
			 
			 stmt = conn.prepareStatement(searchDoctorsQuery);

			 // Phase 2: Pass the actual parameter values to the SQL prepared statement
			 int paramCount = 0;
			 
			 if(fullName!=null) {
				 stmt.setString(++paramCount, "%" + fullName + "%");
			 }
			 
			 if(specialtyId > 0) {
				 stmt.setInt(++paramCount, specialtyId);
			 }
			 
			 if(cityId > 0) {
				 stmt.setInt(++paramCount, cityId);
			 }
			 
			 rst = stmt.executeQuery();
			 
			 while(rst.next()) {
				 Doctor doctor = Populate(rst, false);
				 
				 /*  ΠΡΟΣΟΧΗ με LIKE το Fullname για τις κολώνες first_name και last_name
				  *  μετά το populate, την ώρα που θα γεμίζεις το arraylist doctors, θα κοιτάς
				  *  αν η ιδιότητα doctor.getRating είναι μεγαλύτερη ή ίση από το minRating που έχω δώσει
				  *  σαν παράμετρο της μεθόδου => αν δεν είναι δεν το κάνεις add στα αποτελέσματα
				  */
				 
				 if(minRating > 0) {
					 if(doctor.getRating()>minRating) {
						 doctors.add(doctor);
					 }
				 } else {
					 doctors.add(doctor);
				 }
				 
			 }
			 
		 } catch(SQLException ex) {
			 throw ex;
		 } finally {
			 if(rst!=null) rst.close();
			 if(stmt!=null) stmt.close();
		 }
		 
		 
		 return doctors;
	 }
	
	
	/*
	SELECT CONCAT(first_name," ", last_name) AS fullName FROM `users` 
	WHERE CONCAT(first_name," ", last_name) LIKE '%ΣΟΒ%' */
	
	 public DoctorsDAO() throws DBManagerException {
		 super(DBManager.getInstance().getConnection());	// Inject the Connection dependency to the DAO on initialization
	 }
	 
	 public ArrayList<Doctor> GetDoctorsForSpecialty(int specialtyId) throws SQLException {
		 // TODO: Implement this method to return doctors related to a specialty
		 return null;
	 }
	 
	 
    
	 private double GetRatingForDoctor(int doctorId) throws SQLException {
		 // This method returns the Average rating for the specific doctor id
		 // taking into account all of his appointments
		 
		 PreparedStatement stmt = null;
		 ResultSet rst = null;
		 
		 try {
			 stmt = conn.prepareStatement(getDoctorRatingQuery);
			 stmt.setInt(1, doctorId);
			 
			 rst = stmt.executeQuery();
			 if (rst.next()) {
				return rst.getDouble("avg_rating");
			 }
			 
		 } catch(SQLException ex) {
			 throw ex;
		 } finally {
			 if(rst!=null) rst.close();
			 if(stmt!=null) stmt.close();
		 }
		 
		 return 0;
	 }
	 
	@Override 
    public ArrayList<Doctor> GetAll() throws SQLException { //Σου επιστρεφει ολη την λιστα με τους γιατρους//
    	PreparedStatement stmt = null;
		ResultSet rst = null; 
		ArrayList<Doctor> doctors = new ArrayList<Doctor>();
		try {
			stmt = conn.prepareStatement(returnDoctorsQuery); 
			rst = stmt.executeQuery();
			while(rst.next()) {
				Doctor doctor = Populate(rst,false);
				doctors.add(doctor);
			}
			
		}catch(SQLException ex) {
			throw ex;
		}finally {
			if(rst!=null) rst.close();
			if(stmt!=null) stmt.close();
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
			
			// 4. Διαβάζω τις βαθμολογίες του γιατρού βάσει των ραντεβού που έχει ολοκληρώσει και γεμίζω
			// τη μεταβλητή στιγμιοτύπου rating
			doctor.setRating(GetRatingForDoctor(doctor.getDoctor_id()));
			
			// TODO: Να αποφασίσω αν αυτό χρειάζεται να μπει στο loadForeign
			// 5. Διαβάζω τις ειδικότητες του γιατρού και τις βάζω στο κατάλληλο object
			SpecialtiesDAO sd = new SpecialtiesDAO();
			doctor.setSpecialties(sd.GetSpecialtiesForDoctor(doctor.getDoctor_id(),false));

			if(loadForeign==true) {
				// 6. Διαβάζω τα ραντεβού του γιατρού και τα βάζω στο κατάλληλο object
				AppointmentsDAO ad = new AppointmentsDAO();
				doctor.setAppointments(ad.GetAppointmentsForDoctor(doctor.getDoctor_id()));
			}
			
		} catch(SQLException e) {
			throw e;
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
		return this.GetById(id, true);
	}
	
	@Override
	public Doctor GetById(int id, boolean loadForeign) throws SQLException {
		Doctor doctor = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.prepareStatement(getDoctorById);
			stmt.setInt(1,id);
			rst = stmt.executeQuery();
			if(rst.next()) {
				
				doctor = Populate(rst,loadForeign);
			}
			
			
		}catch(SQLException ex) {
			
			throw  ex;
		}finally {
			if(rst!=null) rst.close();
			if(stmt!=null) stmt.close();
		}
		return doctor;
	}
	
	
	

	
	
	
	
	
	
	
	
	
}