<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/styles/AFH.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Register</title>
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index"> Assassins For Hire </a>
			</div>
		
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> Login </a></li>
			</ul>
		</div>
	</nav>
	
	<!--Main body-->
	<div class="container">
		<div class="row">
		
			<!--left side-->
			<div class="col-lg-2">
			</div>
		
			<!--Main Body-->
			<div class="col-lg-8">
				<h1 class="text-center">Register New Account</h1>
				<br>
				<!--Buttons-->
				<div>
					<div class="pull-left register-button" action="goToCandidateRegisterForm" method="POST">
						<a href="goToCandidateRegisterForm">Are you a jobseeker?</a>
					</div>
					<div class="pull-right register-button">
						<a href="goToRecruiterRegisterForm">Are you a recruiter?</a>
					</div>
					<div class="clearfix"></div>
				</div>			
			</div>

			<!--Right side-->
			<div class="col-lg-2">
			</div>
			
		</div>
	</div>
	<hr>
	
	<footer>
		<div class="container">
			<div class="row text-center">
				<p class="copyright text-muted">&copy; Assassins For Hire 2017</p>
			</div>
		</div>
	</footer>
	
</body>

</html>