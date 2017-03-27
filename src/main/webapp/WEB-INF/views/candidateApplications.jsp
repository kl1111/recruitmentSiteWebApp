<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
				<li><a href="${contextPath}/account"><span
						class="glyphicon glyphicon-user"></span> Account </a></li>
				<li><a href="${contextPath}/login"><span
						class="glyphicon glyphicon-log-out"></span> Logout </a></li>
			</ul>
		</div>
	</nav>
	<div class="text-center">
		<h1>Summary of Assassins Job Applications</h1>
	</div>
	<!--Main body-->
	<div class="container">
		<div class="row">

			<!--left side-->
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
			</div>
			
			
				<!--Main Body-->
			<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-center">	
				<form action="deleteApplication" method=POST>
					<h2>Applied Positions</h2>

					<!--Add job listings here-->
					<div class="container-fluid">
						Job Id - Title<br> Description<br> Application Date <br>
						<div class="pull-right">
							<button type="submit" class="btn btn-default btn-sm">Delete</button>
						</div>

					</div>
				</form>
			</div>
			
			<!-- Right side -->
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
			</div>
			
		</div>
	</div>
	
	<hr>
			
			<!-- footer -->

				<footer>
					<div class="container">
						<div class="row text-center">
							<p class="copyright text-muted">&copy; Assassins For Hire
								2017</p>
						</div>
					</div>
				</footer>
</body>
</html>