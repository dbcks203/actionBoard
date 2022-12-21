<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



	<form method="post" action="memberupdate.zan">
		<input type="hidden" name="id" id="id" value="${member.id}"> 
		<label>이름</label>
		<input type="text" name="name" id="name" value="${member.name}"><br />
		<label>email</label> 
		<input type="text" name="email" id="email" value="${member.email}"><br /> 
		<label>주소</label> 
		<input type="text" name="address" id="address" value="${member.address}"><br />
		<label>전화번호</label> 
		<input type="text" name="phone" id="phone" value="${member.phone}"><br /> 
		<input type="submit" value="수정하기">
	</form>
	<button type="button" class="btn btn-default"
		onclick="location.href ='mypage.zan';">돌아가기</button>


</body>
</html>