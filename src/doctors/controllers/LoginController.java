package doctors.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.daos.UsersDAO;
import doctors.exceptions.DBManagerException;
import doctors.exceptions.InvalidFieldException;
import doctors.framework.ActionController;
import doctors.framework.IValidatable;
import doctors.models.User;

public class LoginController extends ActionController implements IValidatable {

	User entity=null;
	
	@Override
	public String execute() throws ServletException, IOException {
		// Create the user DAO
				
		try {
			UsersDAO ud = new UsersDAO();
			
			entity = ud.findUser(getStringField("email"));
	    	if(entity!=null) {
	    		if(entity.getPassword().equals(getStringField("password"))) {
	    			// THIS SETS A GLOBAL "user" SESSION VARIABLE FOR ALL PAGES
	    			req.getSession().setAttribute(ActionController.USER_SESSION_KEY, entity);
	    			return "/index.jsp";
	    		} else {
	    			message = "Λάθος στοιχεία! Δοκιμάστε ξανά...";
	    		}
	    	} else {
	    		message = "Λάθος στοιχεία! Δοκιμάστε ξανά...";
	    	}
			
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		// if I am here, some error occured, so go back to login.jsp
		return "/login.jsp";
	
	}

	@Override
	public String validate(HttpServletRequest req) {
		// this method will validate the JSP parameters before executing the action
		this.returnUrl = "/login.jsp";

		try {
			
			entity = new User();
						
			entity.setEmail(getStringField("email"));
			entity.setPassword(getStringField("password"));
			
			// We put the model to be returned to the page in case of error...
			this.model.put(ActionController.ENTITY_HASMAP_KEY, entity);
			
		} catch(Exception ex) {
			return ex.getMessage();
		}
		
		return "";	// Παντα πρέπει να επιστρέφουμε ένα άδειο string αν δεν υπάρχουν σφάλματα
	}

}
