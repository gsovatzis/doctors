package doctors.models;

import doctors.exceptions.InvalidFieldException;
import java.util.ArrayList;

public class Doctor extends Entity {

	private int doctor_id;

	private double rating;
	
	// This property relates a DOCTOR with a USER (HAS-A) --> COMPOSITION
	private User user;

	// This property relates a DOCTOR with one or more SPECIALTIES (that's why we use an arraylist)
	private ArrayList<Specialty> specialties = new ArrayList<Specialty>();

	// This property relates a DOCTOR with one or more WORKING HOURS (that's why we use an arraylist)
	private ArrayList<Working_Hour> working_hours = new ArrayList<Working_Hour>();

	//This property relates a DOCTOR with one or more APPOINTMENTS (that's why we use an arraylist)
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();

	public ArrayList<Specialty> getSpecialties() {
		return specialties;
	}

	public void addSpecialty(Specialty specialty) {
		this.specialties.add(specialty);
	}

	public ArrayList<Appointment> getAppointments() {
	    return appointments;
    }

    public void setSpecialties(ArrayList<Specialty> specialties) {
		this.specialties = specialties;
	}

	public void setWorking_hours(ArrayList<Working_Hour> working_hours) {
		this.working_hours = working_hours;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

	public void addAppointment(Appointment appointment) {

		this.appointments.add(appointment);
    }

	public Doctor(User user, int doctor_id) throws InvalidFieldException {
		this.setUser(user);
		this.setDoctor_id(doctor_id);

	}

	public Doctor() {

	}

	public int getDoctor_id() {

		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) throws InvalidFieldException {
		this.doctor_id = doctor_id;

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) throws InvalidFieldException {
		
		this.user = user;
	}

	public ArrayList<Working_Hour> getWorking_hours() {
		return working_hours;
	}

	public void addWorking_hour(Working_Hour working_hour) {
		this.working_hours.add(working_hour);
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}



}

