package doctors.framework;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import doctors.framework.DBManager;
import doctors.entities.User;
import doctors.exceptions.InvalidFieldException;

public abstract class Action extends HttpServlet {

	protected String returnUrl="";
	protected String message="";	// The action message to be returned to the page (it can be an error, or info message)
	protected Object myEntity="";
	
	protected final String dbErrorMsg = "Δεν μπόρεσα να συνδεθώ στη βάση δεδομένων!<br/>";
	
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	protected HttpSession session;
	protected ServletContext application;
	
	protected String connectDb() {
		
		// Get connection configuration from web.xml
		String dbURL = application.getInitParameter("dburl");
		String dbUser = application.getInitParameter("dbuser");
		String dbPass = application.getInitParameter("dbpass");
		
		if(DBManager.getInstance().getConnection()==null) {
			try {
				DBManager.getInstance().openConnection(dbURL, dbUser, dbPass);
				return "";
			} catch (SQLException ex) {
				return ex.getMessage();
			}
		}
		
		return "";
	}
		
	public abstract String execute() throws ServletException, IOException;	// Every application action must implement the execute method!
	
	public abstract String validate(HttpServletRequest req);	// Every application action must implement the validate method!

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.service(req, resp);
		
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();
		this.application = getServletContext();
		
		connectDb();			// Connect to the database -> TODO: If error on connection, redirect to error Page
		
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
				showPage(returnUrl, validationErrors, null);
				return;
			} else {
				showPage(execute(), message, null);		// If no validation errors, execute the action
			}
		} else {
			showPage(execute(), message, null);	// If no validation required, just execute the specified action
		}
				
	}
	
	// This method just checks if attribute with name user exists for the current session
	public boolean authorized() throws ServletException {
		if(session.getAttribute("user")==null)
		{
			return false;
		}
		
		return true;
	};  
	
	
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
	
	public void showPage(String url, String message, Object entity) throws IOException, ServletException {
		// This method forwards the request to the specified page, including message and entity!
		req.setAttribute("message", message);
		req.setAttribute("entity", entity);
		
		RequestDispatcher dispatcher = application.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}

}
