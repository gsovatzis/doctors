<%@page import="java.util.HashMap"%>
<%@page import="doctors.framework.ActionController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<%

	// IMPORTANT: This page should be included on all JSP pages
	
	/* 
		The purpose of this page is to tranfer the message from a controller to the JSP result page
		and to include the hashmap of the entities to be transferred across requests!
	*/

	// Get the message from the request (if exists)
	String message = "";
	if(request.getAttribute(ActionController.MESSAGE_REQUEST_KEY)!=null)
		message = (String)request.getAttribute(ActionController.MESSAGE_REQUEST_KEY);
	
	// Get a collection of models (if exists)
	HashMap<String, Object> model = new HashMap<String, Object>();
	if(request.getAttribute(ActionController.MODEL_REQUEST_KEY)!=null)
		model = (HashMap<String, Object>)request.getAttribute(ActionController.MODEL_REQUEST_KEY);
	
%>

