<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/styles/AFH.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Account</title>
</head>

<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${contextPath}/index"> Assassins For Hire </a>
			</div>
		
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${contextPath}/#"><span class="glyphicon glyphicon-log-out"></span> Logout </a></li>
			</ul>
		</div>
	</nav>
	
	<!--Main body-->
	<div class="container">
		<div class="row">
		
			<!--left side-->
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
			</div>
		
			<!--Main Body-->
			<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-center">
			
			<!-- Form to update user account details -->
			<h2> Update user details</h2>
				<sf:form class="form-horizontal" action="updateAccountDetails" modelAttribute="ahUser">
					<div class="form-group">
						<sf:label path="name" class="control-label col-sm-3" for="cname">Name:</sf:label>
						<div class="col-sm-9">
							<sf:input path="name" type="text" class="form-control" id="cname"/>
						</div>
					</div>
					<div class="form-group">
						<sf:label path="email" class="control-label col-sm-3" for="email">Email:</sf:label>
						<div class="col-sm-9">
							<sf:input path="email" type="email" class="form-control" id="email"/>
						</div>
					</div>
					<div class="form-group">
						<sf:label path="password" class="control-label col-sm-3" for="pw">Password:</sf:label>
						<div class="col-sm-9">	
							<sf:input path="password" type="password" class="form-control" id="pw"/>
						</div>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-default">Update</button>
					</div>
				</sf:form>	
				<br>	
				
				<!-- button to deactivate account -->
				<form id="delete" class="form-horizontal" action="deactiveAccount" method=POST>
				<div class="text-center" id="delete-acc">
						<button type="submit" class="btn btn-danger">Deactive Account</button>
				</div>
				</form>											
			</div>
			
			<!--Right side-->
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
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