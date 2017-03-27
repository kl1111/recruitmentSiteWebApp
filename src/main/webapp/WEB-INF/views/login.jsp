<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/styles/AFH.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
</head>

<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index"> Assassins For Hire </a>
			</div>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="${contextPath}/register"><span
						class="glyphicon glyphicon-user"></span> Register </a></li>
			</ul>
		</div>
	</nav>

	<!--Main body-->
	<div class="container">
		<div class="row">

			<!--left side-->
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"></div>

			<!--Central Body-->
			<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-center">

				<h1>Log in to your account</h1>
				<form class="form-horizontal" action="submitLoginDetails"
					method=POST modelAttribute="ahUser">
					<div class="form-group">
						<label class="control-label col-sm-3" for="email">Email: </label>
						<div class="col-sm-9">
							<input type="email" class="form-control" id="email" name="email"
								required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="password">Password:
						</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" id="password"
								name="password" required>
						</div>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-default">Login</button>
					</div>
				</form>

			</div>

			<!--Right side-->
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"></div>

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