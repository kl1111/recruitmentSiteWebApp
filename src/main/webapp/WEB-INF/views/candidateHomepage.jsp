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
						class="glyphicon glyphicon-shopping-cart"></span> Jobs </a></li>
				<li><a href="${contextPath}/account"><span
						class="glyphicon glyphicon-user"></span> Account </a></li>
				<li><a href="${contextPath}/logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout </a></li>
			</ul>
		</div>
	</nav>


	<!-- main body -->
	<div class="container">
	
		<div class="row"> 

			<div class="col-md-8">
					<h1>Welcome to the Candidate Homepage</h1>
					



					<h2 id="candidateAppHeading">My applications</h2>
						
						<!--Beginning of applied jobs here-->
						<c:forEach items="${allAppliedApplications}" var="application">
							<form class="form-horizontal" action="cancelApplication" method="POST" modelAttribute="ahApplication" style="border:none" id="appliedApplication">
							<div class="job-listings">
								 
								 	<input style="display:none" name="applicationId" value="<c:out value="${application.getApplicationId() }"/>" 
								/>   
									<p class="label label-info">${application.getJob().getJobStatus()}</p>   &nbsp; 
									<p class="label label-success">SUBMITTED</p> 
									<br>
									<span> Job ID: ${application.getJob().getJobId()} </span> <br>
									<span>Job title: </span> ${application.getJob().getJobTitle() } <br>
									<span>Job Description: </span> ${application.getJob().getDescription() }<br>
									
									
									<div class="text-right">
										<button class="btn btn-danger btn-md " type="submit">Cancel  Application</button>
									</div>
								
							</div>
							</form>
						</c:forEach> <!--End of applied jobs here-->
						
						
						
						
						<!--Beginning of cancelled jobs here-->
						<c:forEach items="${allCancelledApplications}" var="application">
							<form class="form-horizontal" action="resendApplication" method="POST" modelAttribute="ahApplication" style="border:none" id="appliedApplication">
							<div class="job-listings">
								 
								 	<input style="display:none" name="applicationId" value="<c:out value="${application.getApplicationId() }"/>" 
								/>   
									<p class="label label-info">${application.getJob().getJobStatus()}</p>   &nbsp; 
									<label  class="label label-danger"> CANCELLED</label>
									<br>
									<span> Job ID: ${application.getJob().getJobId()} </span> <br>
									<span>Job title: </span> ${application.getJob().getJobTitle() } <br>
									<span>Job Description: </span> ${application.getJob().getDescription() }<br>
									
									<div class="text-right">
										<button class="btn btn-success " type="submit"> Resend this Application</button>
										<button class="btn btn-danger" type="submit"  onclick="form.action='deleteApplication'; form.method='POST';"> Delete this Application</button>
									</div>
								
							</div>
							</form>
						</c:forEach> <!--End of cancelled jobs here-->
			</div>
			
			
			<br>
			<br>
			<br>
			<br>
			<br>

			
			<div class="col-md-4"> <!-- Beginning of Side Menu  -->
	
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
							<div class="col-lg-6">
								<ul class="list-unstyled" id="manageAccountListItems">
									<li><a href="returnToAccountPage">Update Account Details</a></li>
									<li><a href="updateBio">Update Bio Details</a></li>
									<li><a href="returnToAccountPage">Delete Account Details</a></li>
									<li><a href="returnToAccountPage">Deactivate Account Details</a></li>
								</ul>
							</div>
						</div> 	
					</div>  <!-- End of Manage account  -->		
			</div> <!-- Beginning of Side Menu  -->
		
		</div>  <!--End of row-->
	</div>  <!--End of Main Body-->
	
	<hr>
	<footer>  <!-- Beginning of footer -->
		<div class="container">
			<div class="row text-center">
				<p class="copyright text-muted">&copy; Assassins For Hire 2017</p>
			</div>
		</div>
	</footer>
		




</body>
</html>