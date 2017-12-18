package doctors.models;

import doctors.exceptions.InvalidFieldException;

public class City extends Entity {

	private int city_id;
	private String city_name;

	public City(int city_id, String city_name) throws InvalidFieldException {

		this.setCity_id(city_id);
		this.setCity_name(city_name);

	}

	public City(int city_id) {
		this.setCity_id(city_id);
	}
	
	public City() {

	}

	public int getCity_id() {

		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {

		return city_name;
	}

	public void setCity_name(String city_name) throws InvalidFieldException {
        if(city_name == null) throw new InvalidFieldException("Δώστε το όνομα της πόλης στην οποία ανήκεται");

		if(city_name.length() == 0) throw new InvalidFieldException("Δώστε το όνομα της πόλης στην οποία ανήκεται");

		if(city_name.length() > 30) throw new InvalidFieldException("To όνομα της πόλης σας δεν θα πρέπει να ξεπερνάει τους 30 χαρακτήρες");
		
		this.city_name = city_name;
   }

}