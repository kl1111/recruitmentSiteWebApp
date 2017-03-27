<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/styles/AFH.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Jobs By Job Title</title>
</head>


<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${contextPath}/index"> Assassins For Hire </a>
			</div>
		
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${contextPath}/account">Account</a></li>
				<li><a href="${contextPath}/register"><span class="glyphicon glyphicon-user"></span> Register </a></li>
				<li><a href="${contextPath}/logout"><span class="glyphicon glyphicon-log-in"></span> Logout </a></li>
			</ul>
		</div>
	</nav>
	
	<!--Main body-->
	<div class="container">
		<div class="row">
		
			<!--left side-->
			<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
			</div>
		
			<!--Main Body-->
			<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
				<h3>Find Jobs by Job Title</h3>
				<form class="form-inline text-right" action = "jobsByJobTitle" method = POST modelAttribute="jobTitle">
  					<div class="form-group">
	    				<label class="sr-only" for="jobTitle">jobTitle</label>
    					<input type="text" class="form-control" id="jobTitle" name="jobTitle" placeholder="Find Jobs">
  					</div>
  					<button type="submit" class="btn btn-info">Find</button>
				</form>
				
				<h2>Found Job Listings</h2>
				
				<!--Add job listings here-->
				<c:forEach items="${jobsByJobTitle}" var="ahJob">
					<div class="job-listings">
						<div class="pull-left">
							<p class="label label-info">${ahJob.jobStatus}</p><br><br>
							<a href="#">${ahJob.jobTitle}</a> <span>by </span> 	${ahJob.getUser().getName()} <br>
							${ahJob.description}
						</div>
						<div class="pull-right">
							<button type="button" class="btn btn-default btn-sm">Apply</button>
						</div>
						<div class="clearfix"></div>
					</div>
				</c:forEach>			
			</div>

			<!--Right side-->
			<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
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