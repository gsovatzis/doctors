package doctors.models;

import java.util.ArrayList;

import doctors.exceptions.InvalidFieldException;

public class Specialty extends Entity {

	private int specialty_id;
	private String specialty_name;
	private ArrayList<Doctor> relatedDoctors = new ArrayList<Doctor>();

	public Specialty(int specialty_id, String specialty_name) throws InvalidFieldException {
		this.setSpecialty_id(specialty_id);
		this.setSpecialty_name(specialty_name);
	}

	public Specialty() {

	}

	public int getSpecialty_id() {

		return specialty_id;
	}

	public void setSpecialty_id(int specialty_id) throws InvalidFieldException {
        //Τι ελεγχο να βαλω εφοσον ειναι ιντ στην βαση και εδω στρινγκ//
		this.specialty_id = specialty_id;
	}

	public String getSpecialty_name() {

		return specialty_name;
	}

	public void setSpecialty_name(String specialty_name) throws InvalidFieldException {
        if(specialty_name == null) throw new InvalidFieldException("Δώστε το όνομα της ειδικότητας ");

        if(specialty_name.length() == 0) throw new InvalidFieldException("Δώστε το όνομα της ειδικότητας");

        if(specialty_name.length() > 20) throw new InvalidFieldException("Το όνομα της ειδικότητας πρέπει να είναι μέχρι 20 χαρακτήρες");

		this.specialty_name = specialty_name;
    }

	public ArrayList<Doctor> getRelatedDoctors() {
		return relatedDoctors;
	}

	public void setRelatedDoctors(ArrayList<Doctor> relatedDoctors) {
		this.relatedDoctors = relatedDoctors;
	}

}

