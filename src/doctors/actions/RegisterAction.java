package doctors.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.entities.User;
import doctors.exceptions.InvalidFieldException;
import doctors.framework.Action;
import doctors.framework.IValidatable;

public class RegisterAction extends Action implements IValidatable {

	User userToBeRegistered = new User();
		
	@Override
	public String execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
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
