package doctors.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.daos.AppointmentsDAO;
import doctors.exceptions.DBManagerException;
import doctors.exceptions.InvalidFieldException;
import doctors.framework.ActionController;
import doctors.framework.IAuthorizable;
import doctors.framework.IValidatable;
import doctors.models.Appointment;
import doctors.models.Doctor;

public class BookAppointmentController extends ActionController implements IValidatable, IAuthorizable {

	Appointment newAppointment = null;
	
	@Override
	public String execute() throws ServletException, IOException {
		
		try {
			AppointmentsDAO ad = new AppointmentsDAO();
			ad.Create(newAppointment);
		} catch (DBManagerException e) {
			return "/error.jsp";
		} catch (SQLException e) {
			return "/error.jsp";
		}
				
		// Άν το ραντεβού κλειστεί κανονικά, θα γυρίσουμε στην index με ένα μήνυμα
		this.message = "Το ραντεβού σας κλείστηκε με επιτυχία!";
		return "/Index";
	}

	@Override
	public String validate(HttpServletRequest req) {
		// We must have the following request parameters:
		// doctorid, examination, date, time
		
		this.returnUrl = "/GetAppointment?doctorid=";
		
		int doctorid=0;
		
		String examDate;
		int examTime;
		
		try {
			doctorid=getIntField("doctorid");
		} catch(InvalidFieldException ex) {
			this.returnUrl = "/error.jsp";
		}
		
		try {
			Doctor doc = new Doctor();
			doc.setDoctor_id(doctorid);
			
			examDate = getStringField("date");
			examTime = getIntField("time");
			if(examTime==-1)
				throw new InvalidFieldException("Δεν επιλέξατε ώρα");
			
			DateFormat df= new SimpleDateFormat("dd/MM/yyyy");
			Date appointmentDate = df.parse(examDate);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(appointmentDate);
			cal.set(Calendar.HOUR_OF_DAY, examTime);
			
			newAppointment = new Appointment();
			newAppointment.setDoctor(doc);
			newAppointment.setMedical_examination(getStringField("examination"));
			newAppointment.setUser(getCurrentUser());
			newAppointment.setAppointment_date_time(cal.getTime());
			
		} catch(ParseException ex) {
			this.returnUrl = "/GetAppointment?doctorid" + doctorid;
			return "Δεν δώσατε την ημερομηνία στη μορφή ΗΗ/ΜΜ/ΕΕΕΕ";
		} catch(InvalidFieldException ex) {
			this.returnUrl = "/GetAppointment?doctorid" + doctorid;
			return ex.getMessage();
		} catch (UnsupportedEncodingException e) {
			this.returnUrl = "/error.jsp";
			return e.getMessage();
		} catch (ServletException e) {
			this.returnUrl = "/error.jsp";
			return e.getMessage();
		}
		
		return "";	// If everything goes OK, return empty string
	}

}
