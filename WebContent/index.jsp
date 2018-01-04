<%@page import="doctors.models.Working_Hour"%>
<%@page import="doctors.models.Doctor"%>
<%@page import="doctors.models.Specialty"%>
<%@page import="doctors.models.City"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>

<%

	ArrayList<Specialty> specialties = new ArrayList<Specialty>();
	if(model.containsKey(ActionController.SPECIALTIES_ARRAY_LIST)) {
		specialties=(ArrayList<Specialty>)model.get(ActionController.SPECIALTIES_ARRAY_LIST);
	}

	ArrayList<City> cities = new ArrayList<City>();
	if(model.containsKey(ActionController.CITIES_ARRAY_LIST)) {
		cities=(ArrayList<City>)model.get(ActionController.CITIES_ARRAY_LIST);
	}
	
	ArrayList<Doctor> searchResults = new ArrayList<Doctor>();
	if(model.containsKey(ActionController.SEARCH_RESULTS_ARRAY_LIST)) {
		searchResults=(ArrayList<Doctor>) model.get(ActionController.SEARCH_RESULTS_ARRAY_LIST);
	}
	
	String doctorname = "";
	int selectedCity=0, selectedRating=0, selectedSpecialty=0;
	if(model.containsKey(ActionController.DOCTORNAME)) {
		doctorname=(String)model.get(ActionController.DOCTORNAME);
	}
	
	if(model.containsKey(ActionController.CITY)) {
		selectedCity=(Integer)model.get(ActionController.CITY);
	}
	
	if(model.containsKey(ActionController.RATING)) {
		selectedRating=(Integer)model.get(ActionController.RATING);
	}
	
	if(model.containsKey(ActionController.SPECIALTY)) {
		selectedSpecialty=(Integer)model.get(ActionController.SPECIALTY);
	}
	
	
%>

<!DOCTYPE html>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="Exercise Template 2017-2018">
	<meta name="author" content="jdoe@example.com">

	<title>Εφαρμογή αναζήτησης γιατρών</title>

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
					<h2>Καλως ήρθατε στην εφαρμογή αναζήτησης γιατρών.</h2>
					<h3>Παρακαλώ αναζητήστε γιατρό για να κλείσετε ραντεβού...</h3>
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
				<h4>Αναζήτηση γιατρών με κριτήρια...</h4>
			</div>
			
			<div class="row">
			
				<div class="col-md-12">
				
					<form action="SearchDoctors" method="post">
						  <div class="form-group row">
							<div class="col-md-4"><label for="doctorname" class="col-form-label">Όνομα ή επώνυμο γιατρού</label></div>
							<div class="col-md-3"><label for="specialty" class="col-form-label">Ειδικότητα γιατρού</label></div>
							<div class="col-md-2"><label for="city" class="col-form-label">Πόλη γιατρού</label></div>
							<div class="col-md-2"><label for="rating" class="col-form-label">Βαθμολογία γιατρού</label></div>
							<div class="col-md-1"></div>
						  </div>
						  <div class="form-group row">
							<div class="col-md-4">
								<input type="text" class="form-control" id="doctorname" name="doctorname" 
								value="<%=doctorname!=null?doctorname:""%>"
								placeholder="Αναζήτηση για ονοματεπώνυμο...">
							</div>
							<div class="col-md-3">
								<select class="form-control" id="specialty" name="specialty">
								  <option value="0">Επιλέξτε ειδικότητα...</option>
								  <%
								  	for(Specialty specialty : specialties) {
								  %>
								  		<option <%=selectedSpecialty==specialty.getSpecialty_id()?"selected":"" %> 
								  			value=<%=specialty.getSpecialty_id()%>>
								  			<%=specialty.getSpecialty_name()%>
							  			</option>
								  <% } %>
								</select>
							</div>
							<div class="col-md-2">
								<select class="form-control" id="city" name="city">
								  <option value="0">Παρακαλώ επιλέξτε πόλη...</option>
								   <%
								   	  for(City city : cities) {
								   %>
								   		<option <%=selectedCity==city.getCity_id()?"selected":"" %> 
								   			value="<%=city.getCity_id()%>">
								   			<%=city.getCity_name()%>
						   				</option>
								   <% } %>
								</select>
							</div>
							<div class="col-md-2">
								<select class="form-control" id="rating" name="rating">
								  <option value="0">Βαθμολογία...</option>
								  <% for(int i=1;i<5;i++) { %>
								  	<option <%=selectedRating==i?"selected":"" %> value="<%=i%>">&gt; <%=i%></option>
								  <% } %>
								</select>
							</div>
							<div class="col-md-1 text-right">
								<button type="submit" class="btn btn-info">Αναζήτηση</button>
							</div>
						  </div>
					</form>
				
				</div>
			
			</div>
			
			<div class="page-header">
				<h4>Αποτελέσματα αναζήτησης</h4>
			</div>
			
				<% if(searchResults.size()==0) { %>
				
					<div class="row">
						<div class="col-md-12 text-center">
							Δεν βρέθηκαν γιατροί με τα κριτήρια αναζήτησης που δώσατε. Παρακαλώ δοκιμάστε ξανά!
						</div>
					</div>
					<br/>
				
				<% } else { %>
				
				<% for(Doctor doctor : searchResults) { %>
					
					<div class="row">
						<div class="col-md-1">
							<img src="images/member1.png" class="img-thumbnail" alt="Member">
						</div>
						<div class="col-md-4 text-left inline">
							<a href="GetDoctor?doctorid=<%=doctor.getDoctor_id()%>"><h3><%=doctor.getUser().getFirst_name()%>&nbsp;<%=doctor.getUser().getLast_name()%></a>
								<br/>
								<small>
									<% for (Specialty specialty : doctor.getSpecialties()) { %>
										<%=specialty.getSpecialty_name() %>&nbsp;
									<% } %>
								</small>
							</h3> 
							<p><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;<%=doctor.getUser().getAddress() %>,&nbsp;<%=doctor.getUser().getCity().getCity_name()%></p> 
						</div>
						<div class="col-md-3 text-left inline">
							<% for(Working_Hour wh : doctor.getWorking_hours()) { %>
								<span class="glyphicon glyphicon-time"></span>&nbsp;<%=wh.getWorkDayName(wh.getWork_day())%>:&nbsp;<%=wh.getWorkingHour(wh.getFrom_hour()) %>&nbsp;-&nbsp;<%=wh.getWorkingHour(wh.getTo_hour()) %><br/>
							<% } %>
						</div>
						<div class="col-md-2 text-left inline">
							<% if(doctor.getRating()==0) { %>
								Ο γιατρός δεν έχει ακόμη αξιολογηθεί!
							<% } else { 
								for(int i=1;i<=Math.round(doctor.getRating());i++) {
							%>
								<span class="glyphicon glyphicon-star"></span>&nbsp;
								<% } %>
							<% } %>
						</div>
						<div class="col-md-2">
							<a href="GetAppointment?doctorid=<%=doctor.getDoctor_id()%>" class="btn btn-warning">Κλείστε ραντεβού</a>
						</div>
					</div>
					<br/>
			
				<% } %>
			<% } %>
				
			
			<% if(!message.isEmpty()) { %>		
				<div class="alert alert-warning text-center" role="alert"><%=message%></div>
			<% } %>
			
		</div>
		<!-- /container -->

		<!-- footer -->
		<%@include file="footer.jsp" %>
		<!-- End footer -->
		
		<!-- =================== Place all javascript at the end of the document so the pages load faster =================== -->
		<!-- jQuery library -->
		<script src="js/jquery.min.js"></script>
		<!-- Bootstrap core JavaScript -->
		<script	src="js/bootstrap.min.js"></script>
	</body>
</html>
		