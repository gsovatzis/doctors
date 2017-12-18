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


	@Override
	public String execute() throws ServletException, IOException {
		// Create the user DAO
		User u=null;
		this.model = u;
		
		try {
			UsersDAO ud = new UsersDAO();
			
			u = ud.findUser(getStringField("email"));
	    	if(u!=null) {
	    		if(u.getPassword().equals(getStringField("password"))) {
	    			// THIS SETS A GLOBAL "user" SESSION VARIABLE FOR ALL PAGES
	    			req.getSession().setAttribute(ActionController.USER_SESSION_KEY, u);	
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
		
		String username="";
		String password="";
		String errorMessage="";	// Παντα πρέπει να επιστρέφουμε ένα άδειο string
		
		try {
			username = getStringField("email");
			password = getStringField("password");
		} catch(Exception ex) {
			if(username.equals("")) {
				errorMessage = errorMessage + "Πρέπει να εισάγετε όνομα χρήστη!<br/>";
			}
			
			if(password.equals("")) {
				errorMessage = errorMessage + "Πρέπει να εισάγετε κωδικό πρόσβασης!<br/>";
			}
		}
		
		return errorMessage;
	}

}
