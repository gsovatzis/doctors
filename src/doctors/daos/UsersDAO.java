package doctors.daos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import doctors.framework.DBHandler;
import doctors.framework.DBManager;
import doctors.models.User;

public class UsersDAO extends DBHandler<User> {

	protected final String findUserByEmail = "SELECT user_id, first_name, last_name, address, landline, mobile, fax, email, pass, city_id FROM Users WHERE email=?;";
	protected final String findUserById = "SELECT user_id, first_name, last_name, address, landline, mobile, fax, email, pass, city_id FROM Users WHERE user_id=?;";
	protected final String findAllUsers = "SELECT * FROM Users";
	protected final String createUserQuery = "INSERT INTO Users (first_name,last_name,address,landline,mobile,fax,email,pass,city_id) VALUES (?,?,?,?,?,?,?,?,?);";
	
    public UsersDAO() {
    	super(DBManager.getInstance().getConnection());	// Inject the Connection dependency to the DAO on initialization
	}

    public User findUser(String username) throws SQLException { //μεθοδος που θα μου επιστρέφει τον user με βάση το username του//
    	
    	User user = null;	// Initially our return object is null, if no user is found NULL will be returned
		
		try {
			// Prepare query parameters
			PreparedStatement findUserStmt = conn.prepareStatement(findUserByEmail);
			ResultSet rst = null;
			
			findUserStmt.setString(1, username);
			
			// Execute the query and get result set from database
			rst = findUserStmt.executeQuery();
			
			// If the result set moves to the next record, then user is found 
			// -> fill the user object to be returned from the Database
			if(rst.next()) {
				user = Populate(rst,false);
			}
			
			// Close query and result set
			findUserStmt.close();
			rst.close();
		
		} catch (SQLException ex) {
			throw ex;
		} 

		return user;

    }
        

    //Αυτή η μέθοδος θα χρησιμοποιηθει απο την registercontroller και θα ελενγχει αν τα στοιχεια της εγγραφης ειναι εγκυρα σύμφωνα με τις προυποθέσεις των σετερς που ορίσαμε στην κλάση User

	/* (non-Javadoc)
	 * @see doctors.framework.DBHandler#Create(java.lang.Object)
	 */
	@Override
	public void Create(User entity) throws SQLException  {
	
		PreparedStatement stmt = null;
		try {
		    stmt = conn.prepareStatement(createUserQuery);
		//	stmt.setInt(1,entity.getUser_id()); --> We don't need to insert user_id: IT IS AUTO-GENERATED
			stmt.setString(1,entity.getFirst_name());
			stmt.setString(2,entity.getLast_name());
			stmt.setString(3,entity.getAddress());
			stmt.setString(4,entity.getLand_line());
			stmt.setString(5,entity.getMobile());
			stmt.setString(6, entity.getFax());
            stmt.setString(7, entity.getEmail());
            stmt.setString(8,entity.getPassword());
            stmt.setInt(9,entity.getCity().getCity_id()); 
            stmt.executeUpdate();
		} catch(SQLException ex) {
			throw ex;
		} finally {
			stmt.close();
		}
	}


	@Override
	public ArrayList<User> GetAll() throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		PreparedStatement stmt = null;
		ResultSet r = null;		
		try {
			stmt = conn.prepareStatement(findAllUsers);
			r = stmt.executeQuery();
			while(r.next()) {
				User user = Populate(r,true);
				users.add(user);
			}
		}catch(SQLException ex) {
			throw ex;
		}finally {
			r.close();
			stmt.close();
		}
		
		return users;
	}


	@Override
	public void Update(User entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void Delete(User entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User GetById(int id) throws SQLException {
		// This method calls GetById -> with true by default on loadForeign
		return this.GetById(id, true);
	}

	@Override
	public User GetById(int id, boolean loadForeign) throws SQLException {
		User user = null;	// Initially our return object is null, if no user is found NULL will be returned
		PreparedStatement findUserStmt = null;
		ResultSet rst = null;
		
		try {
			// Prepare query parameters
			findUserStmt = conn.prepareStatement(findUserById);
			findUserStmt.setInt(1, id);
			
			// Execute the query and get result set from database
			rst = findUserStmt.executeQuery();
			
			// If the result set moves to the next record, then user is found 
			// -> fill the user object to be returned from the Database
			if(rst.next()) {
				user = Populate(rst, loadForeign);
			}
			
		} catch (SQLException ex) {
			throw ex;
		} finally {
			// Close query and result set
			findUserStmt.close();
			rst.close();
		}

		return user;
	}


	/* (non-Javadoc)
	 * @see doctors.framework.DBHandler#Populate(java.sql.ResultSet)
	 */
	@Override
	protected User Populate(ResultSet rst, boolean loadForeign) throws SQLException {
		User user = new User();
		try {
			user.setUser_id(rst.getInt("user_id")); 
			user.setFirst_name(rst.getString("first_name"));
			user.setLast_name(rst.getString("last_name"));
			user.setAddress(rst.getString("address"));
			user.setLandline(rst.getString("landline"));
			user.setMobile(rst.getString("mobile"));
			user.setFax(rst.getString("fax"));
			user.setEmail(rst.getString("email"));
			user.setPassword(rst.getString("pass"));
			
			if(loadForeign==true) {
				// Also populate user's city object
				CitiesDAO cd = new CitiesDAO();
				user.setCity(cd.GetById(rst.getInt("city_id")));
				
				// Also populate user's appointments
				AppointmentsDAO ad = new AppointmentsDAO();
				user.setAppointments(ad.GetAppointmentsForUser(user.getUser_id()));
			}
			
		} catch (Exception ex) {
			throw new SQLException(ex.toString());
		}
		return user;
	}
}
