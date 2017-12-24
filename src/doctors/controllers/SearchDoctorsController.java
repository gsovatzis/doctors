package doctors.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.framework.ActionController;

public class SearchDoctorsController extends ActionController {

	@Override
	public String execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Να πάρει τα κριτήρια από το request
		
		// Να καλέσει στο DoctorsDAO τη μέθοδο SearchDoctors
		
		// Να επιστρέψει τα αποτελέσματα στην index
		
		return "/Index";
	}

	@Override
	public String validate(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}
