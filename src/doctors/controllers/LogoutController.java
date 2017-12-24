package doctors.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.framework.ActionController;

public class LogoutController extends ActionController {

	@Override
	public String execute() throws ServletException, IOException {
		// Invalidate session
		
		// Redirect to index
		session.invalidate();
		
		return "/Index";	// This is the page where we will be transfered after logout

	}

	@Override
	public String validate(HttpServletRequest req) {
		// Since logout doesn't implement IValidatable and no need to validate something 
		// -> THIS WILL NEVER BE CALLED
		return "";
	}


}
