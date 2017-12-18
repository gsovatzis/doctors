package doctors.models;

import doctors.exceptions.InvalidFieldException;
import java.util.ArrayList;
import java.util.List;

public class User {
	private int user_id;
	private String first_name;
	private String last_name;
	private String address;
	private String landline;
	private String mobile;
	private String fax;
	private String email;
	private String password;
	private City city;
	
	//This property relates a USER with one or more APPOINTMENTS (that's why we use an arraylist)
	private List<Appointment> appointments = new ArrayList<Appointment>();


	public User(int user_id, String first_name, String last_name, String address, String landline, String mobile,
			String fax, String email, String password, City city) throws InvalidFieldException {



		// ΔΕΝ πρέπει να κάνει assign κατευθείαν την ιδιότητα. Πρέπει να περάσεις από τον setter
		// για να γίνει ο έλεγχος της σωστής τιμής (σύμφωνα με αυτά που απαιτεί η βάση δεδομένων)
		// this.first_name = first_name;
		this.setUser_id(user_id);
		this.setFirst_name(first_name);
		this.setLast_name(last_name);
		this.setAddress(address);
		this.setLandline(landline);
		this.setMobile(mobile);
		this.setFax(fax);
		this.setEmail(email);
		this.setPassword(password);
		this.setCity(city);
 }

	public User() {

	}

	public int getUser_id() {

		return user_id;
	}

	public void setUser_id(int user_id) throws InvalidFieldException {
		this.user_id = user_id;
	}

	public String getFirst_name() {

		return first_name;
	}

	public void setFirst_name(String first_name) throws InvalidFieldException {

		if(first_name==null)
			throw new InvalidFieldException("Πρέπει να δώσετε το όνομα σας");

		if(first_name.length()==0)
			throw new InvalidFieldException("Πρέπει να δώσετε το όνομα σας");

		if(first_name.length() > 30)
			throw new InvalidFieldException("Το όνομα σας πρέπει να είναι μέχρι 30 χαρακτήρες");

		this.first_name = first_name;
	}

	public String getLast_name() {

		return last_name;
	}

	public void setLast_name(String last_name) throws InvalidFieldException {
        if(last_name == null) throw new InvalidFieldException("Δώστε το επίθετο σας");

        if(last_name.length() == 0) throw new InvalidFieldException("Δώστε το επίθετο σας");

        if(last_name.length() > 30) throw new InvalidFieldException("Tο επίθετο σας πρέπει να είναι μέχρι 30 χαρακτήρες");
		this.last_name = last_name;
	}

	public String getAdress() {

		return address;
	}

	public void setAddress(String address) throws InvalidFieldException {
		if(address == null) throw new InvalidFieldException("Δώστε την διευθυνσή σας");

		if(address.length() == 0) throw new InvalidFieldException("Δώστε την διευθυνσή σας");

		if(address.length() > 30)  throw new InvalidFieldException("Η διευθυνσή σας δεν θα πρέπει να ξεπερνάει τους 30 χαρακτήρες");

		this.address = address;
	}

	public String getLand_line() {

		return landline;

	}

	public void setLandline(String landline) throws InvalidFieldException {
	   if(landline == null) return;	// We don't care because landline can be nullable in the DB

	   if(landline.length() > 35) throw new InvalidFieldException("Το σταθερό σας θα πρέπει να εέναι μέχρι 35 χαρακτήρες");

		this.landline = landline;
	}

	public String getMobile() {

		return mobile;
	}

	public void setMobile(String mobile) throws InvalidFieldException {
		if(mobile == null) throw new InvalidFieldException("Δώστε το κινητό σας");

        if(mobile.length() == 0) throw new InvalidFieldException("Δώστε το κινητό σας");
        
		this.mobile = mobile;
	}

	public String getFax() {

		return fax;

	}

	public void setFax(String fax) throws InvalidFieldException {
        if(fax == null) return;	// We don't care because fax can be nullable in the DB

        if(fax.length() > 30) throw new InvalidFieldException("Το φαξ σας δεν θα πρέπει να ξεπερνάει τους 30 χαρακτήρες");

		this.fax = fax;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) throws InvalidFieldException {
        if(email == null) throw new InvalidFieldException("Δώστε το εμαιλ σας");

        if(email.length() == 0) throw new InvalidFieldException("Δώστε το εμαιλ σας");

        if(email.length() > 30) throw new InvalidFieldException("Tο εμαιλ σας δεν θα πρέπει να ξεπερνάει τους 30 χαρακτήρες");

		this.email = email;

	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) throws InvalidFieldException {
		if(password == null) throw new InvalidFieldException("Δώστε τον κωδικό σας");

		if(password.length() == 0) throw new InvalidFieldException("Δώστε τον κωδικό σας");

		if(password.length() > 30) throw new InvalidFieldException("Ο κωδικός σας δεν θα πρέπει να ξεπερνάει τους 30 χαρακτήρες");

		this.password = password;
	}


	public City getCity() {
		return city;
	}

	public void setCity(City city) throws InvalidFieldException {
		//Τι ελεγχο να κανω εδω//
		this.city = city;
	}

   public List<Appointment> getAppointments() {

	   return appointments;
   }

   public void setAppointments(Appointment appointment) {

       appointments.add(appointment);
   }


}
