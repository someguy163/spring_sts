<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원가입 페이지 입니다</h1>


	<!-- 
	private String sId;
	private String sPw;
	private String sName;
	private int sAge;
	private char sGender;
	private String sMajor; -->
	
	<form action="/configuration/makeNewMember">
	ID : <input type="text" name="sId"/> <br />
	PW : <input type="password" name="sPw"/> <br />
	NAME : <input type="text" name="sName" /> <br />
	AGE : <input type="text" name="sAge"/> <br />
	GENDER : <input type="text" name="sGender" /> <br />
	MAJOR : <input type="text" name="sMajor" />  <br />
	<input type="submit" value="회원가입" />
	<input type="reset" value="취소" />
	</form>
</body>
</html>