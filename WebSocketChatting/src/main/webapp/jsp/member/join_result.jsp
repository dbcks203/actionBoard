<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="../../css/style.css">
<title>Youzan's Project</title>
</head>
<c:choose>
	<c:when test="${sessionScope.id eq null}">
		<jsp:include page="/html/defualt_header.html" />
	</c:when>
	<c:otherwise>
		<jsp:forward page="/member/aleadyLogin.jsp" />
	</c:otherwise>
</c:choose>
<body>
	가입되었습니다
	<a href="/index.jsp">회원목록이동</a>
</body>
<jsp:include page="/html/footer.html" />
</html>
