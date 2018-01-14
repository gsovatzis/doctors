package doctors.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import doctors.exceptions.DBManagerException;
import doctors.exceptions.InvalidFieldException;
import doctors.framework.DBHandler;
import doctors.framework.DBManager;
import doctors.models.Working_Hour;

public class WorkingHoursDAO extends DBHandler<Working_Hour> {
	
	private String createWorking_hour = "INSERT INTO Working_hours(working_hours_id,work_day,from_hour,to_hour,doctor_id) VALUES(?,?,?,?,?);";
    private String getAllWorking_hours ="SELECT * FROM Working_hours";
    private String getById ="SELECT * FROM working_hours WHERE working_hours_id = ?";
    private String getWorkingHoursforDoctor = "SELECT * FROM Working_hours WHERE doctor_id = ?"; 
	
    
    public WorkingHoursDAO() throws DBManagerException {
		super(DBManager.getInstance().getConnection());	// Inject the Connection dependency to the DAO on initialization
	}

	@Override
	public void Create(Working_Hour entity) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(createWorking_hour);
			stmt.setInt(1,entity.getWorking_hours_id());
			stmt.setInt(2,entity.getWork_day());
			stmt.setInt(3,entity.getFrom_hour());
			stmt.setInt(4,entity.getTo_hour());
			stmt.executeUpdate();
		}catch(SQLException ex) {
			throw ex;
		}finally {
			
			if(stmt!=null) stmt.close();
		}
		
	}

	@Override
	public ArrayList<Working_Hour> GetAll() throws SQLException {
		
		ArrayList<Working_Hour> working_hours = new ArrayList<Working_Hour>();
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.prepareStatement(getAllWorking_hours);
			rst = stmt.executeQuery();
			while(rst.next()) {
				
				Working_Hour working_hour = new Working_Hour();
				working_hour = Populate(rst,true);
				working_hours.add(working_hour);
				
				
			}
			
		}catch(SQLException ex) {
			
			throw ex;
		}finally {
			if(rst!=null) rst.close();
			if(stmt!=null) stmt.close();
			
		}
				
		
		return working_hours;
	}
	
	public ArrayList<Working_Hour> GetWorkingHoursForDoctor(int doctor_id) throws SQLException {
	    ArrayList<Working_Hour> working_hours = new ArrayList<Working_Hour>();
	    PreparedStatement stmt = null;
	    ResultSet rst = null;
	    Working_Hour working_hour = null;
	    try {
	    	stmt = conn.prepareStatement(getWorkingHoursforDoctor);
	        stmt.setInt(1, doctor_id);
	        rst = stmt.executeQuery();
	        while(rst.next()) {
	        	
	        	working_hour = Populate(rst,true);
	        	working_hours.add(working_hour);
	        }
	    	
	   }catch(SQLException ex) {
		   
		   throw ex;
		   
	   }finally {
		   
		   if(rst!=null) rst.close();
		   if(stmt!=null) stmt.close();
	   }
		return working_hours;
	}

	@Override
	public void Update(Working_Hour entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Working_Hour entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override 
	public Working_Hour GetById(int id) throws SQLException {
		return this.GetById(id, true);
	}
	
	@Override
	public Working_Hour GetById(int id, boolean loadForeign) throws SQLException {
		Working_Hour working_hour = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.prepareStatement(getById);
			stmt.setInt(1,id);
			rst = stmt.executeQuery();
			if(rst.next()) {
				
				working_hour = Populate(rst,loadForeign);
				
			}
		}catch(SQLException ex) {
			
			throw ex;
		}finally {
			if(rst!=null) rst.close();
			if(stmt!=null) stmt.close();
		}
		
		return working_hour;
	}

	@Override
	protected Working_Hour Populate(ResultSet rst, boolean loadForeign)  {
		Working_Hour working_hour = null;
		try {
			working_hour = new Working_Hour(rst.getInt("working_hours_id"),rst.getInt("work_day"),rst.getInt("from_hour"),rst.getInt("to_hour"));
		} catch (InvalidFieldException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return working_hour;
	}

}
