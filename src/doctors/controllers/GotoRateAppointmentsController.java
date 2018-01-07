package doctors.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.daos.UsersDAO;
import doctors.framework.ActionController;
import doctors.framework.IAuthorizable;
import doctors.framework.IValidatable;
import doctors.models.User;

public class GotoRateAppointmentsController extends ActionController implements IAuthorizable {

	@Override
	public String execute() throws ServletException, IOException {
		
		User u = getCurrentUser();
		UsersDAO ud = new UsersDAO();
		
		try {
			u = ud.GetById(u.getUser_id());
			this.model.put(ENTITY_HASHMAP_KEY, u);
			
		} catch (SQLException e) {
			return "/error.jsp";
		}
		
		// TODO Auto-generated method stub
		return "/myappointments.jsp";
	}

	@Override
	public String validate(HttpServletRequest req) {
		// Nothing to validate
		return null;
	}

}
