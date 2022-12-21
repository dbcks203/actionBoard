<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/WebSocketChatting/css/style_chatInfo.css">
<link rel="stylesheet" href="/WebSocketChatting/css/style.css">
<title>Youzan's Project</title>
<jsp:include page="/html/loginned_header.html" />
</head>
<body>
	<h1>
		ID: [ <small>${memberBean.id}</small> ]
	</h1>
	<section>
		<h3>이름: ${memberBean.name}</h3>
	</section>
	<section>
		<h3>email: ${memberBean.email}</h3>
	</section>
	<section>
		<h3>주소: ${memberBean.address}</h3>
	</section>
	<section>
		<h3>전화번호: ${memberBean.phone}</h3>
	</section>
	<section>
		<h3>가입일: ${memberBean.joinDate}</h3>
	</section>
	<input id = "drop" type="button" value="회원탈퇴" onclick="location.href='memberdrop.zan';">
	
	<input id = "update" type="button" value="회원수정" onclick="location.href='membersetupdate.zan';">
	
	<input id = "back" type="button" value ="돌아가기" onclick="location.href='/WebSocketChatting/';">
</body>

<jsp:include page="/html/footer.html" />
</html>