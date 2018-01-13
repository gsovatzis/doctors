package doctors.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import doctors.daos.UsersDAO;
import doctors.framework.ActionController;
import doctors.framework.IValidatable;
import doctors.models.City;
import doctors.models.User;

public class RegisterController extends ActionController implements IValidatable {

	User userToBeRegistered=null;	// THE MODEL TO BE USED FOR VALIDATION AND PAGE PASSING
		
	@Override
	public String execute() throws ServletException, IOException {
		// Take the userToBeRegistered object and write that to the DB, using the UsersDAO.Create method
		
		try {
			UsersDAO ud = new UsersDAO();
			ud.Create(userToBeRegistered);
		} catch (SQLException ex) {
			// If any exception, show register.jsp including the error
			this.message = ex.getMessage();
			return "/GotoRegister";
		} 
		
		//return "/Index";	// If everything goes OK, go to index.jsp page
		return getFinalUrl("Index",true);
	}

	@Override
	public String validate(HttpServletRequest req) {
		this.returnUrl = getFinalUrl("GotoRegister",true);
		
		String errorMsg = "";
		
		// Fill the userToBeRegistered entity from the request parameters.
		try {
			userToBeRegistered = new User();
						
			userToBeRegistered.setEmail(getStringField("email"));
			userToBeRegistered.setFirst_name(getStringField("firstname"));
			userToBeRegistered.setLast_name(getStringField("lastname"));
			userToBeRegistered.setAddress(getStringField("address"));
		    userToBeRegistered.setCity(new City(getIntField("city")));
			userToBeRegistered.setFax(getStringField("fax"));
			userToBeRegistered.setMobile(getStringField("mobile"));
			userToBeRegistered.setLandline(getStringField("landline"));
			
			// Check that pass1 is equal to pass2
			String pass1 = getStringField("pass1");
			String pass2 = getStringField("pass2");
			
			if(!pass1.equals(pass2)) {
				return "Το πεδίο Συνθηματικό δεν ταιριάζει με το πεδίο Επανάληψη Συνθηματικού";
			} else {
				userToBeRegistered.setPassword(getStringField("pass1"));
			}
			
		} catch (Exception ex) {
			// If any exception comes up, return an error String.
			errorMsg = ex.getMessage();
		} finally {
			// Whatever happens, store the user to be registered in the model hashmap to be passed
			// with the next request
			this.model.put(ENTITY_HASHMAP_KEY, userToBeRegistered);
		}
				
		return errorMsg;	// Always return an empty string (NOT NULL)
	}

}
