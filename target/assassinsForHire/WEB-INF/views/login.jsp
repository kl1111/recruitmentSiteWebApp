<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Login</title>
</head>
<body>


<h1>Login</h1>

	<!-- img src="<c:url value="/resources/image.jpg" />"> -->
	
	<sf:form action="submitLogin" method="POST" modelAttribute="user" >
		<sf:label path="username">Username</sf:label>
		<sf:input path="username"  size="30" />
		<br />
		<sf:label path="password">Password</sf:label>
		<sf:input path="password" size="30" />
		<input type="submit" name="commit" value="submit" />
	</sf:form>

</body>
</html>