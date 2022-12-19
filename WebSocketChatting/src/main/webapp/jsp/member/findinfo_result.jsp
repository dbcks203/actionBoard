<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/WebSocketChatting/css/style_chatInfo.css">
<link rel="stylesheet" href="/WebSocketChatting/css/style.css">
<title>Youzan's Project</title>
<jsp:include page="/html/loginned_header.html" />
</head>
<body>
	<c:choose>
		<c:when test="${id ne null}">
			<h1>id 조회결과: ${id}</h1>
		</c:when>
		<c:when test="${pwd ne null}">
			<h1>pwd 조회결과: ${pwd}</h1>
		</c:when>
	</c:choose>
	
	<button type="button" onclick="location.href='/WebSocketChatting/';">돌아가기</button>
</body>
<jsp:include page="/html/footer.html" />
</html>