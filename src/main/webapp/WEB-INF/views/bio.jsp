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
				<!-- Form to update profile details such as bio and skills -->
				<h2>Update your profile</h2>
				<sf:form class="form-horizontal" action="updateBioDetails" modelAttribute="ahBio">
					<div class="form-group">
						<sf:label path="bio" class="control-label col-sm-2" for="bio">Bio:</sf:label>
						<div class="col-sm-10">
							<textarea name="bio" class="form-control" id="bio" rows="5"></textarea>
						</div>
					</div>
					<div class="form-group">
						<sf:label path="skills" class="control-label col-sm-2" for="skills">Skills:</sf:label>
						<div class="col-sm-10">
							<input name="skills" type="text" class="form-control" id="skills"/>
						</div>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-default">Submit</button>
					</div>
				</sf:form>	
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