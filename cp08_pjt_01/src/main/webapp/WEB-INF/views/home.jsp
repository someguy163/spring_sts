<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<button> <a href="/hello/signUp">signUp</a></button>
<button> <a href="/hello/signIn">signIn</a></button>
</body>
</html>
