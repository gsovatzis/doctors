package doctors.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import doctors.exceptions.DBManagerException;
import doctors.exceptions.InvalidFieldException;
import doctors.framework.DBHandler;
import doctors.framework.DBManager;
import doctors.models.Specialty;

public class SpecialtiesDAO extends DBHandler<Specialty> {

	private final String createSpecialty = "INSERT INTO Specialties VALUES (?,?);";
	private final String getAll = "SELECT * FROM Specialties";
	private final String getSpecialtiesForDoctor = "SELECT Specialties.specialty_id, Specialties.specialty_name, Doctors_Specialties.doctor_id FROM Specialties"
			                                     + " INNER JOIN Doctors_Specialties ON Specialties.specialty_id = Doctors_Specialties.specialty_id"
	                                             + " WHERE Doctors_Specialties.doctor_id = ?";
	public SpecialtiesDAO() throws DBManagerException {
		super(DBManager.getInstance().getConnection());	// Inject the Connection dependency to the DAO on initialization
	}

	@Override
	public void Create(Specialty entity) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(createSpecialty);
			stmt.setInt(1, entity.getSpecialty_id());
			stmt.setString(2,entity.getSpecialty_name());
			stmt.executeUpdate();
		}catch(SQLException ex) {
			
			throw ex;
		}finally {
			if(stmt!=null) stmt.close();
		}
    }		
		
		
		
	

	@Override
	public ArrayList<Specialty> GetAll() throws SQLException {
		ArrayList<Specialty> specialties = new ArrayList<Specialty>();
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Specialty specialty = null;
		try {
			stmt = conn.prepareStatement(getAll);
			rst = stmt.executeQuery();
			while(rst.next()) {
				
				
				specialty = Populate(rst,true);
				specialties.add(specialty);
				
			}
			
			
		}catch(SQLException ex) {
			
			throw ex;
		}finally {
			
			if(stmt!=null) stmt.close();
		}
		return specialties;
	}
	
	public ArrayList<Specialty> GetSpecialtiesForDoctor(int doctor_id,boolean Loadforeign) throws SQLException {
		ArrayList<Specialty> specialties = new ArrayList<Specialty>();
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Specialty specialty = null;
		try {
			
			stmt = conn.prepareStatement(getSpecialtiesForDoctor);
			stmt.setInt(1,doctor_id);
			rst = stmt.executeQuery();
			while(rst.next()) {
				
				specialty = Populate(rst,Loadforeign);
				specialties.add(specialty);
				
		    }
		}catch(SQLException ex) {
			
			throw ex;
		}finally {
			
			if(rst!=null) rst.close();
			if(stmt!=null) stmt.close();
		}
		return specialties;
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
		return this.GetById(id, true);
	}
	
	@Override
	public Specialty GetById(int id, boolean loadForeign) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Specialty Populate(ResultSet rst, boolean loadForeign) throws SQLException {
		Specialty specialty = new Specialty();
		try {
			specialty.setSpecialty_id(rst.getInt("specialty_id"));
			specialty.setSpecialty_name(rst.getString("specialty_name"));
			if(loadForeign = true) {
			      try {
					DoctorsDAO dd = new DoctorsDAO();
				    specialty.setRelatedDoctors(dd.GetDoctorsForSpecialty(specialty.getSpecialty_id())); //Πρεπει να φτιαξουμε μια μεθοδος στο doctordaos που θσα γυρναει doctors με βαση το specialty_id//
				} catch (DBManagerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
			
		}catch(SQLException ex) {
			
			throw new SQLException(ex.toString());
		} catch (InvalidFieldException e) {
			
			e.printStackTrace();
		} 
		
		
		return specialty;
   }

}
