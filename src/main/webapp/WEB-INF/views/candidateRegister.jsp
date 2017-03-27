<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/styles/AFH.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Jobseeker</title>
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${contextPath}/index"> Assassins For Hire </a>
			</div>
		
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${contextPath}/register"><span class="glyphicon glyphicon-user"></span> Register </a></li>
				<li><a href="${contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> Login </a></li>
			</ul>
		</div>
	</nav>
	
	<!--Main body-->
	<div class="container">
		<div class="row">
		
			<!--left side-->
			<div class="col-xs-2 col-sm-3 col-md-2 col-lg-2">
			</div>
		
			<!--Main Body-->
			<div class="col-xs-8 col-sm-6 col-md-8 col-lg-8 text-center">
			
				<h1>Register as a jobseeker</h1>
				
			
				<form class="form-horizontal" action="submitCandidateRegDetails" method=POST modelAttribute="ahUser">
					<div class="form-group">
						<label class="control-label col-sm-2" for="fname">Full Name:</label>
						<div class="col-sm-10">
							<input type="text" name="name" class="form-control" id="fname">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Email:</label>
						<div class="col-sm-10">
							<input type="email" name="email" class="form-control" id="email">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pw">Password:</label>
						<div class="col-sm-10">	
							<input type="password" name="password" class="form-control" id="pw">
						</div>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-default">Submit</button>
						<button type="reset" class="btn btn-default">Reset</button>
					</div>
				</form>	
				<br>
				<p><a href="recruiter-register.html">Not a jobseeker? Click here to register as new recruiter</a></p>
			</div>

			<!--Right side-->
			<div class="col-xs-2 col-sm-3 col-md-2 col-lg-2">
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