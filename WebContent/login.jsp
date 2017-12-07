<%@page import="doctors.entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String message = "";
	User user = new User();	

	if(request.getAttribute("message")!=null)
		message = (String)request.getAttribute("message");

	if(request.getAttribute("entity")!=null)
		user = (User)request.getAttribute("entity");
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

		<title>Είσοδος</title>

		<!-- Bootstrap core CSS -->
		<link rel="stylesheet" href="css/bootstrap.min.css">

		<!-- Custom styles for this template -->
		<link href="css/signin.css" rel="stylesheet">

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
			  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
  
	<body>

		<div class="container">

			<div class="jumbotron row">
				<div class="col-md-6">
					<h2>Καλως ήρθατε στην εφαρμογή αναζήτησης γιατρών.</h2>
					<h3>Δώστε τα στοιχεία σας για να μπείτε στην εφαρμογή...</h3>
				</div>
				<div class="col-md-6">
					<div class="media">
					  <div class="media-right media-middle">
						  <img class="media-object" src="images/doctors.png" alt="doctors">
					  </div>
					</div>
				</div>
			</div>
			
			<% if(!message.isEmpty()) { %>		
				<div class="alert alert-danger text-center" role="alert"><%=message%></div>
			<% } %>

			  <form class="form-signin" method="post" action="Login">
				<!-- h2 class="form-signin-heading">Please sign in</h2> -->
			
				<input type="email" id="email" name="email" class="form-control" placeholder="Email διεύθυνση" 
					value="<%=user.getEmail()==null?"":user.getEmail()%>" required autofocus>
			
				<input type="password" id="password" name="password" class="form-control" placeholder="Συνθηματικό" required>
				
				<button class="btn btn-lg btn-primary btn-block" type="submit">Είσοδος</button>
			  </form>

		</div> <!-- /container -->
  
		<!-- =================== Place all javascript at the end of the document so the pages load faster =================== -->
		<!-- jQuery library -->
		<script src="js/jquery.min.js"></script>
		<!-- Bootstrap core JavaScript -->
		<script	src="js/bootstrap.min.js"></script>
	</body>

</html>