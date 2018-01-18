package doctors.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import doctors.daos.CitiesDAO;
import doctors.daos.SpecialtiesDAO;
import doctors.exceptions.DBManagerException;
import doctors.framework.ActionController;
import doctors.models.City;
import doctors.models.Specialty;

public class IndexController extends ActionController {

	@Override
	public String execute() throws ServletException, IOException {
		
		try {
			// 1. Load Specialties
			SpecialtiesDAO sd = new SpecialtiesDAO();
			ArrayList<Specialty> specialties = sd.GetAll();
			
			// 2. Load Cities
			CitiesDAO cd = new CitiesDAO();
			ArrayList<City> cities = cd.GetAll();
			
			// Put the cities and specialties arraylists to the model hashmap 
			// that will be forwarded with the request
			this.model.put(ActionController.CITIES_ARRAY_LIST, cities);
			this.model.put(ActionController.SPECIALTIES_ARRAY_LIST, specialties);
			
		} catch (Exception e) {
			this.ex=e;
			return "/error.jsp";
		}
		
		return "/index.jsp";	// Goto index
	}

	@Override
	public String validate(HttpServletRequest req) {
		// This method will not be used, as Index page doesn't validate anything
		return null;
	}

}
