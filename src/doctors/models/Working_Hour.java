package doctors.models;

import doctors.exceptions.InvalidFieldException;
import java.util.Date;

public class Working_Hour extends Entity {

	private int working_hours_id;
	private Date work_day;
	private String from_hour;
	private String to_hour;
    //Στην βαση το χουμε βαλει να συνδεεται με doctor και εδω οχι τι παιζει ??//
	public Working_Hour(int working_hours_id, Date work_day, String from_hour, String to_hour) throws InvalidFieldException {

		this.setWorking_hours_id(working_hours_id);
		this.setWork_day(work_day);
		this.setFrom_hour(from_hour);
		this.setTo_hour(to_hour);
	}

	public Working_Hour() {

	}

	public int getWorking_hours_id() {

		return working_hours_id;
	}

	public void setWorking_hours_id(int working_hours_id) throws InvalidFieldException {
		//Τι ελενγχο να βαλω εφοσον ειναι ιντ στην βαση και εδω στρινγκ//

		this.working_hours_id = working_hours_id;

	}

	public Date getWork_day() {

		return work_day;
	}

	public void setWork_day(Date work_day) throws InvalidFieldException {
        //Τι συνθηκη να βαλω;//
		this.work_day = work_day;
	}

	public String getFrom_hour() {

		return from_hour;
	}

	public void setFrom_hour(String from_hour) throws InvalidFieldException {

        if(from_hour == null) throw new InvalidFieldException("Δώστε την ώρα που αρχίζει να εργάζεται ο γιατρός");

        if(from_hour.length() == 0) throw new InvalidFieldException("Δώστε την ώρα που αρχίζει να εργάζεται ο γιατρός");

        if(from_hour.length() > 11) throw new InvalidFieldException("Η ώρα που αρχίζει να δουλέυει δεν θα πρέπει να ξεπερνάει τους 11 χαρακτήρες");

		this.from_hour = from_hour;
   }

	public String getTo_hour() {

		return to_hour;
	}

	public void setTo_hour(String to_hour) throws InvalidFieldException {

		if(to_hour == null) throw new InvalidFieldException("Δώστε μέχρι τι ώρα θα εργάζεται ο γιατρός");

		if(to_hour.length() == 0) throw new InvalidFieldException("Δώστε μέχρι τι ώρα θα εργάζεται ο γιατρός");

		if(to_hour.length() > 11) throw new InvalidFieldException("H ώρα μέχρι την οποία θα δουλεύει ο γιατρός δεν θα πρέπει να ξεπερνάει τους 11 χαρακτήρες");


		this.to_hour = to_hour;

	}

}
