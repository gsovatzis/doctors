package doctors.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.framework.Action;

public class LogoutAction extends Action {

	@Override
	public void execute() throws ServletException, IOException {
		// Invalidate session
		
		// Redirect to index
		session.invalidate();
		
		

	}

	@Override
	public String validate(HttpServletRequest req) {
		// Since logout action is not validatable, this method will never be called
		return null;
	}

}
