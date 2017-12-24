package doctors.exceptions;

public class DBManagerException extends Exception {

	public DBManagerException() {
		
	}

	public DBManagerException(String arg0) {
		super(arg0);
		
	}

	public DBManagerException(Throwable arg0) {
		super(arg0);
		
	}

	public DBManagerException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public DBManagerException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

}
