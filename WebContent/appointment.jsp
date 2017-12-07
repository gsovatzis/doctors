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

	<title>Κλείσιμο ραντεβού</title>

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
					
					<br/>
					<p>
						<span class="glyphicon glyphicon-time"></span> Δευτέρα - Παρασκευή 12:00 - 20:00
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
				<h4>Κλείσιμο ραντεβού</h4>
			</div>
			
			<div class="row">
			
				<div class="col-md-12">
				
					<form action="#" method="post">
					  <div class="form-group row">
						<div class="col-md-2">
							<label for="examination" class="col-form-label">Τι εξέταση θέλετε;</label>
						</div>
						<div class="col-md-10">
							<input type="text" class="form-control" id="examination" placeholder="Παρακαλώ περιγράψτε το είδος της εξέτασης που θέλετε να κάνετε..." required>
							<p class="label label-danger">
								* Απαιτείται
							</p>
						</div>
					 </div>
					 <div class="form-group row">
						<div class="col-md-2">
							<label for="date" class="col-form-label">Ημερομηνία ραντεβού:</label>
						</div>
						<div class="col-md-4">
							<input type="text" class="form-control" id="date" placeholder="ΗΗ/ΜΜ/ΕΕΕΕ" required>
							<p class="label label-danger">
								* Απαιτείται
							</p>
						</div>
						<div class="col-md-3">
							<label for="time" class="col-form-label">Ώρα ραντεβού:</label>
						</div>
						<div class="col-md-3">
							<select class="form-control" id="time">
								  <option value="-1">Επιλέξτε ώρα...</option>
								  <option value="6">06:00</option>
								  <option value="7">07:00</option>
								  <option value="8">08:00</option>
								  <option value="9">09:00</option>
								  <option value="10">10:00</option>
								  <option value="11">11:00</option>
								  <option value="12">12:00</option>
								  <option value="13">13:00</option>
								  <option value="14">14:00</option>
								  <option value="15">15:00</option>
								  <option value="16">16:00</option>
								  <option value="17">17:00</option>
								  <option value="18">18:00</option>
								  <option value="19">19:00</option>
								  <option value="20">20:00</option>
								  <option value="21">21:00</option>
								  <option value="22">22:00</option>
								  <option value="23">23:00</option>
							</select>
							<p class="label label-danger">
								* Απαιτείται
							</p>
						</div>
					  </div>
					  
					  
					  
					  				  
					  
					  <hr/>
					  
					  <div class="form-group row">
						<div class="col-md-8"></div>
						<div class="col-md-4">
							<div class="form-check form-check-inline">
							  <button type="submit" class="btn btn-success">Κλείσιμο ραντεβού</button>
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