<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/styles/AFH.css">
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Home</title>
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
		
			<!-- Begin of Main Body-->
			<div class="col-lg-8">
				
				
				<h2>Latest Job Listings</h2>
				
				
				
				<!--Beginning of job listings here-->
			<c:forEach items="${allActiveJobs}" var="theJob">   	
				<div class="job-listings">
				  
						<sf:form class="navbar-form navbar-center" action="applyJob" method="POST" modelAttribute="job">  
								<div class="pull-left">
								
								<input id="indexJobId" style="display:none" name="jobId" value="<c:out value="${theJob.getJobId() }"/>" 
								/>    
								
								<span>Job title: </span> ${theJob.getJobTitle() } <br>
								<span>Date posted: </span> ${theJob.getDatePosted() } <span>by </span> 	${theJob.getUser().getName() }	 <br>
								<span>Description: </span>  ${theJob.getDescription() }	
								</div>
								
								<div class="pull-right">
									<button id="indexApplyButton" type="submit" class="btn btn-default ">Apply</button>
								</div>
								<div class="clearfix"></div>
					 	</sf:form>   
					
				</div>		<!--Beginning of job listings here-->	
			
			</c:forEach> 	
			
			
			
			
			</div> <!-- End of Main Body-->

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