package doctors.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.framework.ActionController;
import doctors.framework.IAuthorizable;
import doctors.framework.IValidatable;

public class RateAppointmentController extends ActionController implements IValidatable, IAuthorizable {

	@Override
	public String execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validate(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}
