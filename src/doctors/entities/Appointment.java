package doctors.entities;

import doctors.exceptions.InvalidFieldException;
import java.util.Date;

public class Appointment {

	private User user;
	private Doctor doctor;

	private int appointment_id;
	private Date appointment_date_time;
	private String medical_examination;
	private String user_comments;
	private int rating;

	public Appointment(int appointment_id, Date appointment_date_time, String medical_examination, String user_comments,
			int rating) throws InvalidFieldException {

		this.setAppointment_id(appointment_id);
		this.setAppointment_date_time(appointment_date_time);
		this.setMedical_examination(medical_examination);
		this.setUser_comments(user_comments);
		this.setRating(rating);
	}

	public Appointment() {

	}

	public int getAppointment_id() {

		return appointment_id;
	}

	public void setAppointment_id(int appointment_id) throws InvalidFieldException {
       
		this.appointment_id = appointment_id;
	}

	public Date getAppointment_date_time() {

		return appointment_date_time;
	}

	public void setAppointment_date_time(Date appointment_date_time) throws InvalidFieldException {

        if(appointment_date_time == null) throw new InvalidFieldException("Δώστε την ημερομηνία  του ραντεβού ");

        //if(appointment_date_time.length() == 0) throw new InvalidFieldException("Δώστε την ημερομηνία  του ραντεβού ");

        //if(appointment_date_time.length() > 6) throw new InvalidFieldException("Η ημερομηνία δεν θα πρέπει να ξεπερνάει τους 6 χαρακτήρες");

        this.appointment_date_time = appointment_date_time;

	}

	public String getMedical_examination() {

		return medical_examination;
	}

	public void setMedical_examination(String medical_examination) throws InvalidFieldException {

        if(medical_examination == null) throw new InvalidFieldException("Δώστε την ιατρική εξέταση");

        if(medical_examination.length() == 0) throw new InvalidFieldException("Δώστε την ιατρική εξέταση");

        if(medical_examination.length() > 200) throw new InvalidFieldException("Η ιατρική εξέταση δεν θα πρέπει να ξεπερνάει τους 200 χαρακτήρες");

		this.medical_examination = medical_examination;
	}

	public String getUser_comments() {

		return user_comments;
	}

	public void setUser_comments(String user_comments) throws InvalidFieldException {

	    if(user_comments.length() > 300) throw new InvalidFieldException("Το σχόλιο σας δεν θα πρέπει να ξεπερνάει τους 300 χαρακτήρες");

		this.user_comments = user_comments;
	}

	public int getRatings() {

		return rating;
	}

	public void setRating(int rating)throws InvalidFieldException  {

		Integer.toString(rating);
		if(rating < 1 && rating > 5) 
			throw new InvalidFieldException("Ο αριθμός κριτικής σας δεν είναι από 1 έως 5");

        this.rating = rating;
	}

//	public User getUser() {
//		return user;
//	}

//	public void setUser(User user) {
//		this.user = user;
//	}

//	public Doctor getDoctor() {
//		return doctor;
//	}

//	public void setDoctor(Doctor doctor) {
//		this.doctor = doctor;
//	}




}
