package doctors.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.daos.CitiesDAO;
import doctors.exceptions.DBManagerException;
import doctors.framework.ActionController;
import doctors.models.City;

public class GotoRegisterController extends ActionController {

	@Override
	public String execute() throws ServletException, IOException {
		// Add Cities to the model to be used by the page
		
		CitiesDAO cd;
		try {
			// Connect to the database via the Cities DAO and get all cities in an arraylist
			cd = new CitiesDAO();
			ArrayList<City> cities = cd.GetAll();
			
			// Put the cities arraylist to the model hashmap that will be forwarded with the request
			this.model.put(ActionController.CITIES_ARRAY_LIST, cities);
			
		} catch (Exception e) {
			this.ex=e;
			return "/error.jsp";
		} 
		
		return "/register.jsp";
	}

	@Override
	public String validate(HttpServletRequest req) {
		// Nothing to validate
		return null;
	}

}
