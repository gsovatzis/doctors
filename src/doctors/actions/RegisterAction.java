package doctors.actions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.daos.UsersDAO;
import doctors.entities.User;
import doctors.exceptions.DBManagerException;
import doctors.exceptions.InvalidFieldException;
import doctors.framework.Action;
import doctors.framework.IValidatable;

public class RegisterAction extends Action implements IValidatable {

	User userToBeRegistered = new User();
		
	@Override
	public String execute() throws ServletException, IOException {
		// TODO Take the userToBeRegistered object and write that to the DB, using the UsersDAO.Create method
		try {
			UsersDAO ud = new UsersDAO();
			ud.Create(userToBeRegistered);
		} catch (DBManagerException e) {
			// If DBManagerException, show register.jsp including the error
			
			// TODO: Put message logic to register.jsp (see login.jsp to understand)
			this.message = e.getMessage();
			return "/register.jsp";
			
		} catch (SQLException e) {
			// If SQLException, show register.jsp including the error
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
			userToBeRegistered.setEmail(getStringField("email"));
			
			// TODO -> fill all other fields
			
		} catch (Exception ex) {
			return ex.getMessage();
		}
		
		// If any exception comes up, return an errors String.
		
		return "";	// Always return an empty string (NOT NULL)
	}

}
