package doctors.daos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


	public List<User> getUsers() { //μέθοδος που θα μου επιστρέφει την λίστα με όλους τους users θα την χρειαστώ στις επόμενες μεθόδους μου //
    	return null;
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
        

    public void authenticateRegistration(String firstname , String lastname , String address , City city , String landline , String mobile , String Fax ,String email ,String password ,String password_2) {

    //Αυτή η μέθοδος θα χρησιμοποιηθει απο την registercontroller και θα ελενγχει αν τα στοιχεια της εγγραφης ειναι εγκυρα σύμφωνα με τις προυποθέσεις των σετερς που ορίσαμε στην κλάση User

    }

    //Σκέφτομαι να βάλω μια μέθοδο η οποία θα πραγματοποιεί στην ουσία την ενέργεια επισκόπηση του τρέχοντος ραντεβού μου θα τρέχει ολους τους
    //Users και θα γυρναει τα ραντεβου εκεινου του οποιου το id ταιριαζει με την παραμετρο
     /*public appointments FindtheAppointmentList(String user_idd) {

         return appointments;

     }*/


	@Override
	public void Create(User entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<User> GetAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
