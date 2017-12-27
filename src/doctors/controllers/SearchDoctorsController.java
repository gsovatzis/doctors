package doctors.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.daos.DoctorsDAO;
import doctors.exceptions.DBManagerException;
import doctors.exceptions.InvalidFieldException;
import doctors.framework.ActionController;

public class SearchDoctorsController extends ActionController {

	
	
	@Override
	public String execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Να πάρει τα κριτήρια από το request
		
		String lastname = "";
		String firstname = "";
		int city_id = 0;
		int specialty_id = 0;
		int rating = 0;
		
		try {
			firstname = getStringField("first_name");
		} catch (InvalidFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			lastname = getStringField("Last name");
		} catch (InvalidFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fullname = firstname + lastname ;
		try {
			city_id = getIntField("city_id");
		} catch (InvalidFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			specialty_id = getIntField("specialty_id");
		} catch (InvalidFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rating = getIntField("rating_id");
		} catch (InvalidFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Να καλέσει στο DoctorsDAO τη μέθοδο SearchDoctors
		try {
			DoctorsDAO dd = new DoctorsDAO();
			try {
				dd.SearchDoctors(fullname,city_id,specialty_id,rating);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.message = e.getMessage();
				return "/Index";
			}
		} catch (DBManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Να επιστρέψει τα αποτελέσματα στην index
		//if everything goes as planed return to Index
		return "/Index";
	}
    //Nothing to validate so is empty//
	@Override
	public String validate(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}








