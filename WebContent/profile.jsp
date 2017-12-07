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
					<h2>Δημήτρης Πετράκης</h2>
					<h4>Καρδιολόγος, Παθολόγος</h4>
					
					<p>
						<span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;
						Ροδόπης 1, Αθήνα
					</p>
					<p>
						<span class="glyphicon glyphicon-time"></span>&nbsp;&nbsp;
						Δευτέρα - Παρασκευή 12:00 - 20:00
					</p>
					<p>
						<span class="glyphicon glyphicon-earphone"></span>&nbsp;&nbsp;
						21012345678
					<p>
						<span class="glyphicon glyphicon-phone"></span>&nbsp;&nbsp;
						6977123456
					</p>
					<p>
						<span class="glyphicon glyphicon-print"></span>&nbsp;&nbsp;
						21012345678
					</p>
					<p>
						<span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;
						<a href="mailto:jim.petrakhs@hotmail.com">jim.petrakhs@hotmail.com</a>
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
				<h2>Σχόλια για το γιατρό</h4>
			</div>
			
			<div class="row">
			
				<div class="col-md-12">
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
					<div class="row">
						<div class="col-md-6">
							Πολύ καλός γιατρός, εξυπηρετικότατος
						</div>
						<div class="col-md-3">
							30/08/2017 - 12:22
						</div>
						<div class="col-md-3">
							<span class="glyphicon glyphicon-star"></span>&nbsp;
							<span class="glyphicon glyphicon-star"></span>&nbsp;
							<span class="glyphicon glyphicon-star"></span>&nbsp;
							<span class="glyphicon glyphicon-star"></span>&nbsp;
						</div>
					</div>
					<br/>
					<div class="row">
						<div class="col-md-6">
							Δεν έκανε σωστή διάγνωση
						</div>
						<div class="col-md-3">
							5/9/2017 - 17:52
						</div>
						<div class="col-md-3">
							<span class="glyphicon glyphicon-star"></span>&nbsp;
							<span class="glyphicon glyphicon-star"></span>&nbsp;
						</div>
					</div>
					
					
					<!-- END COMMENTS -->
				
					<hr/>
					<form action="appointment.jsp" method="post">
					  <div class="form-group row">
						<div class="col-md-9"></div>
						<div class="col-md-3">
							<div class="form-check form-check-inline">
							  <button type="submit" class="btn btn-warning">Κλείσιμο ραντεβού</button>
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