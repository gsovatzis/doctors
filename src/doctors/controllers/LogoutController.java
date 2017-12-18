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
		
		return "/logout.jsp";	// This is the page where we will be transfered next...

	}

	@Override
	public String validate(HttpServletRequest req) {
		// Since logout action is not validatable, this method will never be called
		return null;
	}

}
