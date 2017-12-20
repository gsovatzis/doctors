<%@page import="doctors.framework.ActionController"%>
<%@page import="doctors.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%

	//Get the current user from session (if exists)
	User loggedInUser = null;
	if(session.getAttribute(ActionController.USER_SESSION_KEY)!=null)
		loggedInUser=(User) session.getAttribute(ActionController.USER_SESSION_KEY);
	

%>

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
				<li><a href="GotoRegister">Εγγραφή</a></li>					
				<li><a href="login.jsp">Είσοδος</a></li>					
			</ul>
			<% if(loggedInUser!=null) { %>		
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
							<i class="glyphicon glyphicon-user"></i>&nbsp;<%=loggedInUser.getFirst_name() %> <%=loggedInUser.getLast_name() %><span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
		                  <li><a href="Logout"><i class="glyphicon glyphicon-log-out"></i>&nbsp;Έξοδος</a></li>
		                </ul>
	                </li>
				</ul>
			<% } %>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>
