<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Login Success page</title>
</head>
<body>

	<h1>Logged In</h1>
	
	Username was: ${user.username} <BR>
	Password was: ${user.password} <BR>

</body>
</html>