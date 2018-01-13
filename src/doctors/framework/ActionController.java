package doctors.framework;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import doctors.framework.DBManager;
import doctors.models.User;
import doctors.exceptions.InvalidFieldException;

public abstract class ActionController extends HttpServlet {

	// Key to retrieve current user from session
	public static final String USER_SESSION_KEY = "user";
	
	// Model hashmap and message to be transferred with the request
	public static final String MODEL_REQUEST_KEY = "model";
	public static final String MESSAGE_REQUEST_KEY = "message";
	
	// Entities to be transferred with the request (inside the model hashmap -> those are the hashmap keys)
	public static final String ENTITY_HASHMAP_KEY = "entity";
	public static final String CITIES_ARRAY_LIST = "cities";
	public static final String SPECIALTIES_ARRAY_LIST = "specialties";
	public static final String SEARCH_RESULTS_ARRAY_LIST = "searchResults";
	public static final String DOCTORNAME = "doctorname";
	public static final String SPECIALTY = "specialty";
	public static final String CITY = "city";
	public static final String RATING = "rating";
	
	public static String paramServlet = "";
	
	protected String returnUrl="";
	protected String message="";	// The action message to be returned to the page (it can be an error, or info message)
	protected HashMap<String, Object> model = new HashMap<String, Object>();
	
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	protected HttpSession session;
	protected ServletContext application;
	
	protected String connectDb() {
		
		if(DBManager.getInstance().getConnection()==null) {
			try {
				
				// Get connection configuration from web.xml
				String dbURL = application.getInitParameter("dburl");
				String dbUser = application.getInitParameter("dbuser");
				String dbPass = application.getInitParameter("dbpass");
				
				DBManager.getInstance().openConnection(dbURL, dbUser, dbPass);
				return "";
			} catch (SQLException ex) {
				return ex.getMessage();
			}
		}
		
		return "";
	}
	
		
	public abstract String execute() throws ServletException, IOException;	// Every application action must implement the execute method!
	
	public abstract String validate(HttpServletRequest req); // Every application action must implement the validate method!
	
	protected void reloadRequestValues() {
		if(req.getAttribute(MESSAGE_REQUEST_KEY)!=null) {
			message =(String) req.getAttribute(MESSAGE_REQUEST_KEY);
		}
		
		if(req.getAttribute(MODEL_REQUEST_KEY)!=null) {
			model = (HashMap<String, Object>) req.getAttribute(MODEL_REQUEST_KEY);
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//super.service(req, resp);
		
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();
		this.application = getServletContext();
		
		paramServlet = application.getInitParameter("servlet");
		
		reloadRequestValues();	// If we come from a previous action, chain the message and model
		
		connectDb();	// Connect to the database -> TODO: If error on connection, redirect to error Page
		
		if(this instanceof IAuthorizable) {
			if(!authorized()) {	// Check if user is authorized (logged-in)
				this.message = "Δεν είστε συνδεδεμένος. Παρακαλώ κάντε login.";
				showPage("/login.jsp", message, null);
				return;
			}
		}
				
		if(this instanceof IValidatable)
		{	
			String validationErrors = validate(req); // Check if action needs to validate form parameters
			if(!validationErrors.isEmpty()) {			
				showPage(returnUrl, validationErrors, model);
				return;
			} else {
				showPage(execute(), message, model); // If no validation errors, execute the action
				return;
			}
		} else {
			showPage(execute(), message, model);	 // If no validation is required, just execute the specified action
		}
		
				
	}
	
	// This method just checks if attribute with name user exists for the current session
	public boolean authorized() throws ServletException {
		if(session.getAttribute(USER_SESSION_KEY)==null)
		{
			return false;
		}
		
		return true;
	};  
	
	public User getCurrentUser() throws ServletException {
		if(session.getAttribute(USER_SESSION_KEY)==null)
		{
			return null;
		}
		
		return (User)session.getAttribute(USER_SESSION_KEY);
	}
	
	public int getIntField(String requestParameter) throws InvalidFieldException {
		// Check if field exists on request
		if(!req.getParameterMap().containsKey(requestParameter))
			throw new InvalidFieldException("Field " + requestParameter + " not found");
		
		String parameter = req.getParameter(requestParameter);
		
		int intParameter;
		
		// Convert field to integer
		try {
			intParameter = Integer.parseInt(parameter);	
		} catch(NumberFormatException ex) {
			intParameter = Integer.MIN_VALUE;
		}
		
		return intParameter;
		
	}
	
	public String getStringField(String requestParameter) throws InvalidFieldException, UnsupportedEncodingException {
		// Check if field exists on request
		if(!req.getParameterMap().containsKey(requestParameter))
			throw new InvalidFieldException("Field " + requestParameter + " not found");
		
		String parameter = req.getParameter(requestParameter);
		
		// The correct way to decode from ISO-8859-1 (default tomcat URL encoding) to UTF-8
		String encParameter = new String(parameter.getBytes("iso-8859-1"), "utf-8");
					
		
		// return String field
		return encParameter;
	}
	
	public void showPage(String url, String message, HashMap<String, Object> model) throws IOException, ServletException {
		// This method forwards the request to the specified page, including message and model!
		req.setAttribute(MESSAGE_REQUEST_KEY, message);
		req.setAttribute(MODEL_REQUEST_KEY, model);
		
		this.message = null;	// Clean message on current controller
		
		RequestDispatcher dispatcher = application.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
	public static String getFinalUrl(String url) {
		return getFinalUrl(url, false);
	}
	
	public static String getFinalUrl(String url, boolean fromController) {
		
		boolean hasServlet = false;
		hasServlet = Boolean.parseBoolean(paramServlet);
		
		if(url.toLowerCase().contains("jsp"))
			return url;
		else {
			if(hasServlet)
				return "servlet/" + url;
			else
				if(fromController)
					return "/"+ url;
				else
					return url;
		}
		
	}

}
