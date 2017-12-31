package doctors.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.daos.DoctorsDAO;
import doctors.exceptions.DBManagerException;
import doctors.exceptions.InvalidFieldException;
import doctors.framework.ActionController;
import doctors.models.Doctor;

public class SearchDoctorsController extends ActionController {
	
	@Override
	public String execute() throws ServletException, IOException {
		
		String doctorname = "";		// This is the full name of the doctor (to be searched with LIKE)
		int city = 0;
		int specialty = 0;
		int rating = 0;
		
		// Να πάρει τα κριτήρια από το request
		try {
			doctorname = getStringField("doctorname");
			city = getIntField("city");				// This will return the selected CITY ID
			specialty = getIntField("specialty"); 	// This will return the selected SPECIALTY ID
			rating = getIntField("rating");			// This will return the selected RATING
		} catch (InvalidFieldException e) {
			// If problem, return to index with the error message
			this.message = e.getMessage();
			return "/Index";
		}

		// Να καλέσει στο DoctorsDAO τη μέθοδο SearchDoctors
		ArrayList<Doctor> searchResults = new ArrayList<Doctor>();
		
		try {
			DoctorsDAO dd = new DoctorsDAO();
			searchResults = dd.SearchDoctors(doctorname,specialty,city,rating);
		} catch (SQLException e) {
			return "/error.jsp";
		} catch (DBManagerException e) {
			return "/error.jsp";
		}
		
		// Να επιστρέψει τα αποτελέσματα στην index
		
		this.model.put(ActionController.SEARCH_RESULTS_ARRAY_LIST, searchResults);
		this.model.put(ActionController.DOCTORNAME, doctorname);
		this.model.put(ActionController.CITY, city);
		this.model.put(ActionController.SPECIALTY, specialty);
		this.model.put(ActionController.RATING, rating);
		
		return "/Index";
	}
    //Nothing to validate so is empty//
	@Override
	public String validate(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}








