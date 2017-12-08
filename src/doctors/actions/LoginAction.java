package doctors.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.daos.UsersDAO;
import doctors.entities.User;
import doctors.exceptions.DBManagerException;
import doctors.exceptions.InvalidFieldException;
import doctors.framework.Action;
import doctors.framework.IValidatable;

public class LoginAction extends Action implements IValidatable {


	@Override
	public String execute() throws ServletException, IOException {
		// Create the user DAO
		User u=null;
		
		try {
			UsersDAO ud = new UsersDAO();
			
			u = ud.findUser(getStringField("email"));
	    	if(u!=null) {
	    		if(u.getPassword().equals(getStringField("password"))) {
	    			req.getSession().setAttribute("user", u);
	    			return "/index.jsp";
	    		} else {
	    			message = message + "Λάθος στοιχεία! Δοκιμάστε ξανά...<br/>";
	    		}
	    	} else {
	    		message = message + "Λάθος στοιχεία! Δοκιμάστε ξανά...<br/>";
	    	}
			
		} catch (Exception e) {
			message = e.getMessage() + "<br/>";
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
