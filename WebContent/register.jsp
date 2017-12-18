<%@page import="doctors.models.City"%>
<%@page import="java.util.ArrayList"%>
<%@page import="doctors.daos.CitiesDAO"%>
<%@page import="doctors.framework.ActionController"%>
<%@page import="doctors.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	User u = null;
	
	int selectedCity = 0;

	if(request.getAttribute(ActionController.MODEL_REQUEST_KEY)!=null) {
		u=(User)request.getAttribute(ActionController.MODEL_REQUEST_KEY);
		selectedCity = u.getCity().getCity_id();
	}
	
	String message="";
	if(request.getAttribute(ActionController.MESSAGE_REQUEST_KEY)!=null) {
		message=(String)request.getAttribute(ActionController.MESSAGE_REQUEST_KEY);
	}
	
	CitiesDAO cd = new CitiesDAO();
	ArrayList<City> cities = cd.GetAll();
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

	<title>Εγγραφή</title>

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
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span> <span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="index.jsp">Αρχική</a></li>
						<li class="active"><a href="register.jsp">Εγγραφή</a></li>					
						<li><a href="login.jsp">Είσοδος</a></li>					
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</nav>

		<div class="container theme-showcase" role="main">

			<!-- Main jumbotron for a primary marketing message or call to action -->
			<div class="jumbotron row">
				<div class="col-md-6">
					<h2>Καλως ήρθατε στην εφαρμογή αναζήτησης γιατρών.</h2>
					<h3>Εγγραφείτε στην πλατφόρμα μας!!!</h3>
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
				<h4>Εγγραφή μέλους</h4>
			</div>
			
			<div class="row">
			
				<div class="col-md-12">
				
					<form action="#" method="post">
					  <div class="form-group row">
						<div class="col-md-2">
							<label for="firstname" class="col-sm-3 col-form-label">Όνομα</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="form-control" id="firstname" placeholder="Παρακαλώ βάλτε το όνομα σας..." required value="<%=u!=null?u.getFirst_name():""%>">
							<p class="label label-danger">
								* Απαιτείται
							</p>
						</div>
						<div class="col-md-2">
							<label for="lastname" class="col-sm-3 col-form-label">Επώνυμο</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="form-control" id="lastname" placeholder="Παρακαλώ βάλτε το επώνυμο σας..." required value="<%=u!=null?u.getLast_name():""%>">
							<p class="label label-danger">
								* Απαιτείται
							</p>
						</div>
					  </div>
					  
					  <div class="form-group row">
						<div class="col-md-2">
							<label for="address" class="col-sm-3 col-form-label">Διεύθυνση</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="form-control" id="lastname" placeholder="Παρακαλώ βάλτε το επώνυμο σας..." required value="<%=u!=null?u.getAdress():""%>">
							<p class="label label-danger">
								* Απαιτείται
							</p>
						</div>
						<div class="col-md-2">
							<label for="city" class="col-sm-3 col-form-label">Πόλη</label>
						</div>
						<div class="col-md-4">
							<select class="form-control" id="city">
							   <option value="0">Παρακαλώ επιλέξτε πόλη...</option>
							   <%
							   	  for(City city : cities) {
							   %>
							   		<option <%=selectedCity==city.getCity_id()?"selected":""%> value="<%=city.getCity_id()%>"><%=city.getCity_name()%></option>
							   <% } %>
							</select>
							<p class="label label-danger">
								* Απαιτείται
							</p>
						</div>
					  </div>
					  
					  <div class="form-group row">
						<div class="col-md-2">
							<label for="landline" class="col-sm-3 col-form-label">Σταθερό</label>
						</div>
						<div class="col-md-4">
							<input type="tel" class="form-control" id="landline" placeholder="Είσαγετε σταθερό τηλέφωνο..." value="<%=u!=null?u.getLand_line():""%>">
						</div>
						<div class="col-md-2">
							<label for="mobile" class="col-sm-3 col-form-label">Κινητό</label>
						</div>
						<div class="col-md-4">
							<input type="tel" class="form-control" id="mobile" placeholder="Εισάγετε κινητό τηλέφωνο..." required value="<%=u!=null?u.getMobile():""%>">
							<p class="label label-danger">
								* Απαιτείται
							</p>
						</div>
					  </div>
					  
					  <div class="form-group row">
						<div class="col-md-2">
							<label for="fax" class="col-sm-3 col-form-label">Αριθμός Fax</label>
						</div>
						<div class="col-md-4">
							<input type="tel" class="form-control" id="fax" placeholder="Είσαγετε αριθμό fax..." value="<%=u!=null?u.getFax():""%>">
						</div>
						<div class="col-md-2">
							<label for="email" class="col-sm-3 col-form-label">Email</label>
						</div>
						<div class="col-md-4">
							<input type="email" class="form-control" id="email" placeholder="Εισάγετε email (θα είναι και το όνομα χρήστη σας)" required value="<%=u!=null?u.getEmail():""%>">
							<p class="label label-danger">
								* Απαιτείται
							</p>
						</div>
					  </div>
					  
					  <div class="form-group row">
						<div class="col-md-2">
							<label for="pass1" class="col-sm-3 col-form-label">Συνθηματικό</label>
						</div>
						<div class="col-md-4">
							<input type="password" class="form-control" id="pass1" placeholder="Είσαγετε το συνθηματικό σας (password)..." required>
							<p class="label label-danger">
								* Απαιτείται
							</p>
						</div>
						<div class="col-md-2">
							<label for="pass2" class="col-sm-3 col-form-label">Επανάληψη Συνθηματικού</label>
						</div>
						<div class="col-md-4">
							<input type="password" class="form-control" id="pass2" placeholder="Επαναεισάγετε το συνθηματικό σας για επιβεβαίωση..." required>
							<p class="label label-danger">
								* Απαιτείται
							</p>
						</div>
					  </div>
					  
					  <!-- VALIDATION ERROR EXIST - SHOW THEM -->
						<div class="alert alert-danger" role="alert">
	  						<%=message%>
						</div>
					  
					  <hr/>
					  
					  <div class="form-group row">
						<div class="col-md-9"></div>
						<div class="col-md-3">
							<div class="form-check form-check-inline">
							  <button type="submit" class="btn btn-success">Εγγραφή</button>
							  <button type="reset" class="btn btn-danger">Καθάρισμα φόρμας</button>
							</div>
						</div>
					  </div>
					  
					</form>
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