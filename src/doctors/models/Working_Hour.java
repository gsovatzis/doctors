package doctors.models;

import doctors.exceptions.InvalidFieldException;

public class Working_Hour extends Entity {

	private String[] days = {"Κυριακή", "Δευτέρα", "Τρίτη", "Τεταρτη", "Πέμπτη", "Παρασκευή", "Σάββατο"};
	
	private int working_hours_id;
	private int work_day;	// 1=ΔΕΥΤΕΡΑ, 2=ΤΡΙΤΗ, 3=ΤΕΤΑΡΤΗ, 4=ΠΕΜΠΤΗ, 5=ΠΑΡΑΣΚΕΥΗ, 6=ΣΑΒΒΑΤΟ
	private int from_hour;	// από 00:00 (0) έως 23:00 (23)
	private int to_hour; // από 00:00 (0) έως 23:00 (23) --> πρέπει η τιμή να είναι μεγαλύτερη του from_hour
    
	public Working_Hour(int working_hours_id, int work_day, int from_hour, int to_hour) throws InvalidFieldException {

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
		this.working_hours_id = working_hours_id;

	}

	public int getWork_day() {

		return work_day;
	}
	
	public String getWorkDayName(int workDay) {
		return days[workDay];
	}

	public String getWorkingHour(int workHour) {
		if(workHour < 10)
			return "0" + String.valueOf(workHour) + ":00";
		else
			return String.valueOf(workHour) + ":00";
	}
	
	public void setWork_day(int work_day) throws InvalidFieldException {
        if(work_day<1 || work_day>6)
        	throw new InvalidFieldException("Οι μέρες που μπορεί να δουλέψει ο γιατρός είναι από Δευτέρα (1) έως Σάββατο (6)");
		this.work_day = work_day;
	}

	public int getFrom_hour() {

		return from_hour;
	}

	public void setFrom_hour(int from_hour) throws InvalidFieldException {

		if(from_hour < 0 || from_hour > 23)
			throw new InvalidFieldException("Η ώρα από πρέπει να είναι από 00:00 έως 23:00");
		
		this.from_hour = from_hour;
   }

	public int getTo_hour() {

		return to_hour;
	}

	public void setTo_hour(int to_hour) throws InvalidFieldException {

		if(to_hour < 0 || to_hour > 23)
			throw new InvalidFieldException("Η ώρα έως πρέπει να είναι από 00:00 έως 23:00");
		
		if(to_hour <= this.from_hour)
			throw new InvalidFieldException("Η ώρα έως πρέπει να είναι μεγαλύτερη από την ώρα από...");
		
		this.to_hour = to_hour;

	}

}
