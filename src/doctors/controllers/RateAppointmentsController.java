package doctors.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.daos.AppointmentsDAO;
import doctors.exceptions.DBManagerException;
import doctors.exceptions.InvalidFieldException;
import doctors.framework.ActionController;
import doctors.framework.IAuthorizable;
import doctors.framework.IValidatable;
import doctors.models.Appointment;

public class RateAppointmentsController extends ActionController implements IValidatable, IAuthorizable {

	ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	
	@Override
	public String execute() throws ServletException, IOException {
		
		try {
			
			AppointmentsDAO ad = new AppointmentsDAO();
			
			for(Appointment appointment : appointments) {
				ad.Update(appointment);
			}
			
		} catch(Exception ex) {
			this.message = ex.getMessage();
			return "/error.jsp";
		}
		
		this.message = "Τα σχόλια σας υποβλήθηκαν επιτυχώς!";
		return "/Index";
	}

	@Override
	public String validate(HttpServletRequest req) {
		// Εδώ θα γεμίσω το arraylist με τα ραντεβού από τη σελίδα
				
		// 1. Παίρνω τα ραντεβού για τον χρήστη
		try {
			AppointmentsDAO ad = new AppointmentsDAO();
			appointments = ad.GetAppointmentsForUser(getCurrentUser().getUser_id());
			
			for(Appointment appointment : appointments) {
				appointment.setUser_comments(getStringField("comments_" + appointment.getAppointment_id()));
				appointment.setRating(getIntField("rating_" + appointment.getAppointment_id()));
			}
			
		} catch (Exception e) {
			this.returnUrl = "/error.jsp";
			return e.getMessage();
		}
		
		
		return "";	// Άν όλα πήγαν καλά, δεν επιστρέφω μήνυμα λάθους
	}

}
