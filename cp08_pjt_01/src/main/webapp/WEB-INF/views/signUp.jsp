<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>This is SignUp Page</h1>

<form action="/hello/signUpConfirm">
	id : <input type="text" name="m_id" /> <br />
	pw : <input type="password" name="m_pw" /> <br />
	eMail : <input type="text" name="m_mail"/> <br />
	phone : <input type="text" name="m_phone" /> <br />
	<button type="submit">SIGN UP</button> 
	<button type="reset">CANCEL</button>
</form>
</body>
</html>