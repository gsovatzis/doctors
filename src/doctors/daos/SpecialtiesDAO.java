package doctors.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doctors.exceptions.DBManagerException;
import doctors.exceptions.InvalidFieldException;
import doctors.framework.DBHandler;
import doctors.framework.DBManager;
import doctors.models.Specialty;

public class SpecialtiesDAO extends DBHandler<Specialty> {

	private final String createSpecialty = "INSERT INTO Specialties VALUES (?,?);";
	private final String getAll = "SELECT * FROM Specialties";
	
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
			stmt.close();
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
			
			stmt.close();
		}
		return specialties;
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
		}catch(SQLException ex) {
			
			throw new SQLException(ex.toString());
		} catch (InvalidFieldException e) {
			
			e.printStackTrace();
		} 
		
		
		return specialty;
   }

}
