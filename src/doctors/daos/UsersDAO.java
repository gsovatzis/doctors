package doctors.daos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import doctors.entities.User;
import doctors.exceptions.DBManagerException;
import doctors.framework.DBHandler;
import doctors.entities.City;

public class UsersDAO extends DBHandler<User> {

	protected final String findUserQuery = "SELECT user_id, first_name, last_name, address, landline, mobile, fax, email, pass FROM Users WHERE email=?;";
	
    public UsersDAO() throws DBManagerException {
		super();
		// TODO Auto-generated constructor stub
	}



    public User findUser(String username) { //μεθοδος που θα μου επιστρέφει τον user με βάση το username του//
    	
    	User user = null;	// Initially our return object is null, if no user is found NULL will be returned
		
		try {
			// Prepare query parameters
			PreparedStatement findUserStmt = conn.prepareStatement(findUserQuery);
			ResultSet rst = null;
			
			findUserStmt.setString(1, username);
			
			// Execute the query and get result set from database
			rst = findUserStmt.executeQuery();
			
			// If the result set moves to the next record, then user is found 
			// -> fill the user object to be returned from the Database
			if(rst.next()) {
				user = Populate(rst);
			}
			
			// Close query and result set
			findUserStmt.close();
			rst.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return user;

    }
        

    
    

    //Σκέφτομαι να βάλω μια μέθοδο η οποία θα πραγματοποιεί στην ουσία την ενέργεια επισκόπηση του τρέχοντος ραντεβού μου θα τρέχει ολους τους
    //Users και θα γυρναει τα ραντεβου εκεινου του οποιου το id ταιριαζει με την παραμετρο
     /*public appointments FindtheAppointmentList(String user_idd) {

         return appointments;

     }*/



//Αυτή η μέθοδος θα χρησιμοποιηθει απο την registercontroller και θα ελενγχει αν τα στοιχεια της εγγραφης ειναι εγκυρα σύμφωνα με τις προυποθέσεις των σετερς που ορίσαμε στην κλάση User

	/* (non-Javadoc)
	 * @see doctors.framework.DBHandler#Create(java.lang.Object)
	 */
	@Override
	public void Create(User entity)  {
		// TODO Auto-generated method stub
		String findUserQuery = "INSERT INTO Users VALUES (?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement stmt = null;
		try {
			
		    stmt = conn.prepareStatement(findUserQuery);
		//	stmt.setInt(1,entity.getUser_id());
			stmt.setString(2,entity.getFirst_name());
			stmt.setString(3,entity.getLast_name());
			stmt.setString(4,entity.getAdress());
			stmt.setString(5,entity.getLand_line());
			stmt.setString(6,entity.getMobile());
			stmt.setString(7, entity.getFax());
            stmt.setString(8, entity.getEmail());
            stmt.setString(9,entity.getPassword());
            stmt.setString(10,entity.getCity().getCity_id()); //Δεν έβαλα ελενγχο εφοσον ικανοποιειται ο ελενγχος στην βαση ειναι not null//
            stmt.executeUpdate();
            
            stmt.close();
         
		}catch(Exception ex) {
			
			try {
				throw new Exception("Something went wrong" + ex.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		
	}


	@Override
	public List<User> GetAll() throws SQLException {
		// TODO Auto-generated method stub
		String returnUsersQuery = "SELECT * \r\n" + 
				"FROM Users AS s\r\n" + 
				"LEFT JOIN Cities AS d ON s.city_id = d.city_id;";
		User user = null;
		List<User> users = new ArrayList<User>();
		PreparedStatement stmt = null;
		ResultSet r = null;		
		try {
			
			stmt = conn.prepareStatement(returnUsersQuery);
			r = stmt.executeQuery();
			while(r.next()) {
				
				user = Populate(r);
				users.add(user);
			}
			
		}catch(SQLException ex) {
			
			ex.printStackTrace();
			
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
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see doctors.framework.DBHandler#Populate(java.sql.ResultSet)
	 */
	@Override
	protected User Populate(ResultSet rst) throws SQLException {
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
		
			
			// TODO: also populate user's city object
			
			// TODO: also populate user's appointments
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
