package doctors.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.daos.DoctorsDAO;
import doctors.exceptions.DBManagerException;
import doctors.exceptions.InvalidFieldException;
import doctors.framework.ActionController;
import doctors.framework.IValidatable;
import doctors.models.Doctor;

public class GetDoctorController extends ActionController implements IValidatable {

	int doctorId;
	
	@Override
	public String execute() throws ServletException, IOException {
		// If validation passes, return the doctor record from the DAO and forward to profile.jsp
		
		DoctorsDAO dd;
		try {
			dd = new DoctorsDAO();
			Doctor doctor = dd.GetById(doctorId);
			
			if(doctor==null) {
				this.message = "Ο γιατρός δε βρέθηκε!";
				return "/index.jsp";
			}
			
			this.model.put(ActionController.ENTITY_HASHMAP_KEY, doctor);
			
		} catch (DBManagerException e) {
			// TODO Auto-generated catch block
			return "/error.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "/error.jsp";
		}

		return "/profile.jsp";
	}

	@Override
	public String validate(HttpServletRequest req) {
		// We must validate that we have parameter with name doctorid on the request!
		
		this.returnUrl = "/error.jsp";
		
		try {
			doctorId = getIntField("doctorid");
		} catch (InvalidFieldException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
		
		return "";
	}

}
