package doctors.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.daos.UsersDAO;
import doctors.exceptions.DBManagerException;
import doctors.exceptions.InvalidFieldException;
import doctors.framework.ActionController;
import doctors.framework.IValidatable;
import doctors.models.City;
import doctors.models.User;

public class RegisterController extends ActionController implements IValidatable {

		
	@Override
	public String execute() throws ServletException, IOException {
		// TODO Take the userToBeRegistered object and write that to the DB, using the UsersDAO.Create method
		
		try {
			UsersDAO ud = new UsersDAO();
			ud.Create((User)model);
		} catch (DBManagerException e) {
			// If DBManagerException, show register.jsp including the error
			
			// TODO: Put message logic to register.jsp (see login.jsp to understand)
			this.message = e.getMessage();
			return "/register.jsp";
		} 
		
		return "/index.jsp";	// If everything goes OK, go to index.jsp page
	}

	@Override
	public String validate(HttpServletRequest req) {
		this.returnUrl = "/register.jsp";
				
		// TODO Fill the userToBeRegistered entity from the request parameters.
		try {
			User userToBeRegistered = new User();
			this.model=userToBeRegistered;
			
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
			return ex.getMessage();
		}
		
		// If any exception comes up, return an errors String.
		
		return "";	// Always return an empty string (NOT NULL)
	}

}
