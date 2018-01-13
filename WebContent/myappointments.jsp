<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="doctors.models.Appointment"%>
<%@page import="doctors.models.Specialty"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="doctors.models.Doctor" %>
<%@ page import ="doctors.models.Working_Hour" %> 

<%@include file="header.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="Exercise Template 2017-2018">
	<meta name="author" content="jdoe@example.com">
    <% 
    	//Doctor doctor = new Doctor();
    	//if(model.containsKey(ActionController.ENTITY_HASHMAP_KEY)) {
    	//	doctor = (Doctor)model.get(ActionController.ENTITY_HASHMAP_KEY);
    	//}
    	
    	User u = new User();
    	if(model.containsKey(ActionController.ENTITY_HASHMAP_KEY)) {
    		u =(User)model.get(ActionController.ENTITY_HASHMAP_KEY);
    	}
    	
    
    %>
	<title>Τα ραντεβού μου</title>

	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">

	<!-- Custom styles for this template -->
	<link href="css/theme_8XXXXXX.css" rel="stylesheet">

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	</head>

	<body>

		<!-- Fixed navbar -->
		<%@include file="navbar.jsp" %>

		<div class="container theme-showcase" role="main">

			<!-- Main jumbotron for a primary marketing message or call to action -->
			<div class="jumbotron row">
				<div class="col-md-6">
					<h2>Το προφίλ σας:</h2>
					<h4>
					    <%=u.getFirst_name() %>&nbsp;<%=u.getLast_name() %>
					</h4>
					<hr/>
					<p>
						<span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;
						<%=u!=null?u.getAddress():""%>
					</p>
					<hr/>
					<p>
						<span class="glyphicon glyphicon-earphone"></span>&nbsp;&nbsp;
						<%=u.getLand_line()!=null?u.getLand_line():"-" %>
					<p>
						<span class="glyphicon glyphicon-phone"></span>&nbsp;&nbsp;
						<%=u.getMobile()!=null?u.getMobile():"-" %>
					</p>
					<p>
						<span class="glyphicon glyphicon-print"></span>&nbsp;&nbsp;
						<%=u.getFax()!=null?u.getFax():"-" %>
					</p>
					<p>
						<span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;
						<a href="mailto:<%=u.getEmail()!=null?u.getEmail():"" %>"><%=u.getEmail()!=null?u.getEmail():"" %></a>
					</p>
				</div>
				<div class="col-md-6">
					<div class="media">
					  <div class="media-right media-middle">
						  <img class="media-object" src="images/doctors.png" alt="doctors">
					  </div>
					</div>
				</div>
			</div>

			<!-- Page Title -->
			<div class="page-header">
				<h4>Αξιολογήστε τα ραντεβού σας...</h4>
			</div>
			
			
			
			<div class="row">
			
			<% if(u.getAppointments().size()==0) { %>
				<div class="col-md-12 text-center">
					Δεν έχετε κλείσει κάποιο ραντεβού
				
			<% } else { %>
				
					<!-- START COMMENTS -->
					<div class="row">
						<div class="col-md-2">
							<h4>Γιατρός</h4>
						</div>
						<div class="col-md-1">
							<h4>Ημερομηνία ραντεβού</h4>
						</div>
						<div class="col-md-2">
							<h4>Εξέταση που έγινε</h4>
						</div>
						<div class="col-md-6">
							<h4>Σχόλιο</h4>
						</div>
						<div class="col-md-1">
							<h4>Βαθμός</h4>
						</div>
						
					</div>
					<form action="RateAppointments" method="post">
						<% for (Appointment appointment : u.getAppointments()) { %>
							<div class="row form-group">
								<div class="col-md-2">
									<%=appointment.getDoctor()!=null?appointment.getDoctor().getUser().getFirst_name() + " " + appointment.getDoctor().getUser().getLast_name():"" %>
								</div>
								<div class="col-md-1">
									<% DateFormat df = new SimpleDateFormat("EEEE, dd/MM/yyyy, HH:mm");
										String appointmentDate = df.format(appointment.getAppointment_date_time());
									%>
									<%=appointmentDate %>
								</div>
								<div class="col-md-2">
									<%=appointment.getMedical_examination()!=null?appointment.getMedical_examination():" - " %>
								</div>
								<div class="col-md-6">
									<input type="text" name="comments_<%=appointment.getAppointment_id()%>" class="form-control"
										value="<%=appointment.getUser_comments()!=null?appointment.getUser_comments():"" %>"
										placeholder="Δεν έγινε σχόλιο για αυτό το ραντεβού..."
									/>
								</div>
								<div class="col-md-1">
									<select class="form-control" name="rating_<%=appointment.getAppointment_id()%>">
									  <option value="0" <%=appointment.getRatings()==0?"selected":"" %>>Επιλέξτε Βαθμολογία...</option>
									  <% for(int i=1;i<=5;i++) { %>
									  	<option <%=appointment.getRatings()==i?"selected":"" %> value="<%=i%>"><%=i%></option>
									  <% } %>
									</select>
								</div>
								
							</div>
						<br/>
						<% } %>
						<% if(u.getAppointments().size()>0) { %>
							<div class="row">
								<div class="col-md-9"></div>
								<div class="col-md-3">
									<div class="form-check form-check-inline">
									  <button type="submit" class="btn btn-info">Υποβάλλετε τις αξιολογήσεις σας...</a>
									</div>
								</div>
						     </div>
					     <% } %>
					</form>
					<!-- END COMMENTS -->
			<% } %>
				
					<hr/>
				<br/>
				</div>
			</div>
			
		</div>
		<!-- /container -->

		<!-- footer -->
		<footer class="navbar-inverse">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<p class="text-center">&copy; Copyright 2017 by ismgroup74</p>
					</div>
				</div>
			</div>
		</footer>
		<!-- End footer -->
		
		<!-- =================== Place all javascript at the end of the document so the pages load faster =================== -->
		<!-- jQuery library -->
		<script src="js/jquery.min.js"></script>
		<!-- Bootstrap core JavaScript -->
		<script	src="js/bootstrap.min.js"></script>
	</body>
</html>