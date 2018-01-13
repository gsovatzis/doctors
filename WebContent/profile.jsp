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
    	Doctor doctor = new Doctor();
    	if(model.containsKey(ActionController.ENTITY_HASHMAP_KEY)) {
    		doctor = (Doctor)model.get(ActionController.ENTITY_HASHMAP_KEY);
    	}
    
    %>
	<title>Προφίλ γιατρού</title>

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
					<h2><%=doctor.getUser().getFirst_name()!=null?doctor.getUser().getFirst_name():""%>&nbsp;<%=doctor.getUser().getLast_name()!=null?doctor.getUser().getLast_name():""%></h2>
					<h4>
						<ul>
						<% for (Specialty specialty : doctor.getSpecialties()) { %>
							<li><%=specialty.getSpecialty_name() %></li>
						<% } %>
						</ul>
					</h4>
					<hr/>
					<p>
						<span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;
						<%=doctor!=null?doctor.getUser().getAddress():""%>
					</p>
					<hr/>
					<p>
						
						<% for(Working_Hour wh : doctor.getWorking_hours()) { %>
									<span class="glyphicon glyphicon-time"></span>&nbsp;&nbsp;<%=wh.getWorkDayName(wh.getWork_day())%>:&nbsp;<%=wh.getWorkingHour(wh.getFrom_hour()) %>&nbsp;-&nbsp;<%=wh.getWorkingHour(wh.getTo_hour()) %><br/>
								<% } %>
					</p>
					<hr/>
					<p>
						<span class="glyphicon glyphicon-earphone"></span>&nbsp;&nbsp;
						<%=doctor.getUser().getLand_line()!=null?doctor.getUser().getLand_line():"-" %>
					<p>
						<span class="glyphicon glyphicon-phone"></span>&nbsp;&nbsp;
						<%=doctor.getUser().getMobile()!=null?doctor.getUser().getMobile():"-" %>
					</p>
					<p>
						<span class="glyphicon glyphicon-print"></span>&nbsp;&nbsp;
						<%=doctor.getUser().getFax()!=null?doctor.getUser().getFax():"-" %>
					</p>
					<p>
						<span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;
						<a href="mailto:<%=doctor.getUser().getEmail()!=null?doctor.getUser().getEmail():"" %>"><%=doctor.getUser().getEmail()!=null?doctor.getUser().getEmail():"" %></a>
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
				<h4></h4>
					
				
			</div>
			
			<div class="row">
			
			<% if(doctor.getAppointments().size()==0) { %>
				<div class="col-md-12 text-center">
					Δεν βρεθηκαν σχόλια για το γιατρό!!!
				
			<% } else { %>
				
					<!-- START COMMENTS -->
					<div class="row">
						<div class="col-md-6">
							<h4>Σχόλιο</h4>
						</div>
						<div class="col-md-3">
							<h4>Ημερομηνία υποβολής</h4>
						</div>
						<div class="col-md-3">
							<h4>Βαθμολογία</h4>
						</div>
					</div>
					<% for (Appointment appointment : doctor.getAppointments()) { %>
						<div class="row">
							<div class="col-md-6">
								<%=appointment.getUser_comments()!=null?appointment.getUser_comments():"Δεν έγινε σχόλιο για αυτό το ραντεβού..." %>
							</div>
							<div class="col-md-3">
								<% DateFormat df = new SimpleDateFormat("EEEE, dd/MM/yyyy, HH:mm");
									String appointmentDate = df.format(appointment.getAppointment_date_time());
								%>
								<%=appointmentDate %>
							</div>
							<div class="col-md-3">
								<% for(int i=1;i<=appointment.getRatings();i++) { %>
									<span class="glyphicon glyphicon-star"></span>&nbsp;
								<% } %>
							</div>
						</div>
					<br/>
					<% } %>
					
					<!-- END COMMENTS -->
			<% } %>
				
					
				
					<hr/>
					
				  <div class="row">
					<div class="col-md-9"></div>
					<div class="col-md-3">
						<div class="form-check form-check-inline">
						  <a href="<%=ActionController.getFinalUrl("GetAppointment") %>?doctorid=<%=doctor.getDoctor_id()%>" class="btn btn-warning">Κλείσιμο ραντεβού</a>
						</div>
					</div>
				  </div>
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