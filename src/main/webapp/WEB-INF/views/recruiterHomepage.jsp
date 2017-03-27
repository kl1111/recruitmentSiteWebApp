<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/styles/AFH.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Welcome</title>
</head>

<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index"> Assassins For Hire </a>
			</div>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="${contextPath}/jobs"><span
						class="glyphicon glyphicon-th-list"></span> Jobs </a></li>
				<li><a href="${contextPath}/account"><span
						class="glyphicon glyphicon-user"></span> Account </a></li>
				<li><a href="logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout </a></li>
			</ul>
		</div>
	</nav>

	<!--Main body-->
	<div class="container">
		<div class="row">

			<div class="col-md-8">
				<h1>Welcome to the Recruiter Homepage</h1>

				<form action="goToPostJob" method=POST>
					<button type="submit" class="btn btn-default btn-sm">Post
						a new Job</button>
				</form>

				<h2 >Applicants</h2>
					<!--Beginning of job listings here-->

					<c:forEach items="${allAppliedApplications}" var="application">

						<div class="job-listings">

							<span> Job ID: ${application.getJob().getJobId()} </span> <br>
							<span>Job title: </span> ${application.getJob().getJobTitle() } <br>
							<span>Applicant Name: </span> ${application.getUser().getName() }
							<br> <span>Email: </span> ${application.getUser().getEmail() }
							<br> <span>Bio: </span> ${application.getUser().getBio().getBio() } <br>
						</div>
					</c:forEach>
			</div>
				<br>
				<br>
				<br>
				
				
				<div class="col-md-4">   <!-- Beginning of Side Menu  -->

				<!-- Beginning of Search Bar -->
					<div class="well">
						<h4>Job Search</h4>
						<form  action= "jobsByJobTitle" method="POST" modelAttribute="jobTitle" style="border-style:none">
							<div class="input-group">
						
									<input type="text" class="form-control" id="searchJobInput" name="jobTitle" placeholder="Find Jobs"> <span
										class="input-group-btn">
										<button class="btn btn-default" type="submit" >
											<span class="glyphicon glyphicon-search"></span>
										</button>
									</span>
							</div>	
						</form>	
							
					</div> <!-- End of Search Bar -->

					<!-- Beginning of Manage account  -->
					<div class="well">
						<h4>Manage your Account</h4>
						<div class="row">
							<div class="col-lg-8">
								<ul class="list-unstyled" id="manageAccountListItems">
									<li><a href="returnToAccountPage">Update Account
											Details</a></li>
									<li><a href="returnToAccountPage">Delete Account
											Details</a></li>
									<li><a href="returnToAccountPage">Deactivate Account
											Details</a></li>
								</ul>
							</div>
						</div>	
					</div> <!-- End of Manage account  -->
				</div>  <!-- End of Side Menu  -->

		</div>  <!--End of row-->
	</div> <!-- End of Main Body-->
	
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