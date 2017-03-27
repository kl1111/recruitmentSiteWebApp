<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/styles/AFH.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Post New Job</title>
</head>

<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index"> Assassins For Hire </a>
			</div>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="${contextPath}/Job"><span
						class="glyphicon glyphicon-th-list"></span> Jobs </a></li>
				<li><a href="${contextPath}/account"><span
						class="glyphicon glyphicon-user"></span> Account </a></li>
				<li><a href="${contextPath}/logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout </a></li>
			</ul>
		</div>
	</nav>
	
	<!--Main body-->
	<div class="container-fluid">
		<div class="row">

			<!--left side-->
			<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>

			<!--Main Body-->
			
			<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
			<h1>Welcome to the postjob Homepage</h1>

		<form class="form-horizontal" action="postANewJob" method="POST" modelAttribute="ahJob">
					<div class="form-group">
						<label class="control-label col-sm-2" for="jTitle">Job Title:</label>
						<div class="col-sm-10">
							<input  name="jobTitle" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="jDescription">Description:</label>
						<div class="col-sm-10">
							<input  name="description" class="form-control" />
						</div>
					</div>
					
					<div class="text-left">
						<button type="submit" class="btn btn-default">Post a new job</button>
						
					</div>
				</form>	
	
	
				
			</div>

			<!--Right side-->
			<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>

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