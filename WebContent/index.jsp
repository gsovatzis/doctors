<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						<li class="active"><a href="index.jsp">Αρχική</a></li>
						<li><a href="register.jsp">Εγγραφή</a></li>					
						<li><a href="login.jsp">Είσοδος</a></li>					
					</ul>		
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="glyphicon glyphicon-user"></i>&nbsp;Χρήστης 1 <span class="caret"></span></a>
							<ul class="dropdown-menu">
			                  <li><a href="logout.jsp"><i class="glyphicon glyphicon-log-out"></i>&nbsp;Έξοδος</a></li>
			                </ul>
		                </li>
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
				
					<form action="#" method="post">
						  <div class="form-group row">
							<div class="col-md-4"><label for="doctorname" class="col-form-label">Όνομα ή επώνυμο γιατρού</label></div>
							<div class="col-md-3"><label for="specialty" class="col-form-label">Ειδικότητα γιατρού</label></div>
							<div class="col-md-2"><label for="city" class="col-form-label">Πόλη γιατρού</label></div>
							<div class="col-md-2"><label for="rating" class="col-form-label">Βαθμολογία γιατρού</label></div>
							<div class="col-md-1"></div>
						  </div>
						  <div class="form-group row">
							<div class="col-md-4">
								<input type="text" class="form-control" id="doctorname" placeholder="Αναζήτηση για ονοματεπώνυμο...">
							</div>
							<div class="col-md-3">
								<select class="form-control" id="specialty">
								  <option value="0">Επιλέξτε ειδικότητα...</option>
								  <option value="1">Καρδιολόγος</option>
								  <option value="2">Παθολόγος</option>
								  <option value="3">Ουρολόγος</option>
								  <option value="4">Παιδίατρος</option>
								</select>
							</div>
							<div class="col-md-2">
								<select class="form-control" id="city">
								  <option value="0">Επιλέξτε πόλη...</option>
								  <option value="1">Αθήνα</option>
								  <option value="2">Θεσσαλονίκη</option>
								  <option value="3">Πάτρα</option>
								  <option value="4">Βόλος</option>
								</select>
							</div>
							<div class="col-md-2">
								<select class="form-control" id="rating">
								  <option value="0">Βαθμολογία...</option>
								  <option value="1">&gt; 1</option>
								  <option value="2">&gt; 2</option>
								  <option value="3">&gt; 3</option>
								  <option value="4">&gt; 4</option>
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
			<form action="appointment.jsp" method="post">
				<div class="row">
					<div class="col-md-1">
						<img src="images/member1.png" class="img-thumbnail" alt="Member">
					</div>
					<div class="col-md-5 text-left inline">
						<a href="profile.jsp"><h3>Δημήτρης Πετράκης</a>&nbsp;<small>Καρδιολόγος, Παθολόγος</small></h3> 
						<p><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;Ροδόπης 1, Αθήνα</p> 
					</div>
					<div class="col-md-2 text-left inline">
						<span class="glyphicon glyphicon-time"></span> Δευτέρα - Παρασκευή 12:00 - 20:00
					</div>
					<div class="col-md-2 text-left inline">
						<span class="glyphicon glyphicon-star"></span>&nbsp;
						<span class="glyphicon glyphicon-star"></span>&nbsp;
						<span class="glyphicon glyphicon-star"></span>&nbsp;
						<span class="glyphicon glyphicon-star"></span>&nbsp;
					</div>
					<div class="col-md-2">
						<button type="submit" class="btn btn-warning">Κλείστε ραντεβού</button>
					</div>
				</div>
				<br/>
				
				<div class="row">
					<div class="col-md-1">
						<img src="images/member1.png" class="img-thumbnail" alt="Member">
					</div>
					<div class="col-md-5 text-left inline">
						<a href="#"><h3>Βασίλης Παναγόπουλος</a>&nbsp;<small>Παιδίατρος</small></h3> 
						<p><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;Τσαμαδού 25, Πειραιάς</p> 
					</div>
					<div class="col-md-2 text-left inline">
						<span class="glyphicon glyphicon-time"></span> Δευτέρα - Παρασκευή 17:00 - 21:00
					</div>
					<div class="col-md-2 text-left inline">
						<span class="glyphicon glyphicon-star"></span>&nbsp;
						<span class="glyphicon glyphicon-star"></span>&nbsp;
						<span class="glyphicon glyphicon-star"></span>&nbsp;
					</div>
					<div class="col-md-2">
						<button type="submit" class="btn btn-warning">Κλείστε ραντεβού</button>
					</div>
				</div>
				<br/>
				
			</form>
			
			
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