<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/style_join.css">

<script src="https://kit.fontawesome.com/e1bd1cb2a5.js"></script>


<title>Youzan's Project</title>
</head>
<c:choose>
	<c:when test="${sessionScope.id eq null}">
		<jsp:include page="/html/defualt_header.html" />
	</c:when>
	<c:otherwise>
		<jsp:forward page="/aleadyLogin.jsp" />
	</c:otherwise>
</c:choose>
<body>

	<div class="join_container">
		<h2>회원 가입</h2>
		<form method="post" action="join.zan">
			<h3>아이디</h3>
			<div class="joinID">
				<input type="text" class="input" style="ime-mode: disabled;" placeholder="아이디" name="userid" title="아이디" maxlength="20"> 
				<input type="button" class="bt_join" value="중복확인" id="dupUidCheckButton">
			</div>
			<h3>비밀번호</h3>
			<div class="joinPassword">
				<input type="password" class="input" placeholder="비밀번호" name="pwd" title="비밀번호" maxlength="20">
			</div>
			<h3>이름</h3>
			<div class="joinName">
				<input type="text" class="input" placeholder="이름" name="name" title="이름" maxlength="20">
			</div>
			<h3>이메일</h3>
			<div class="joinEmail">
				<input type="text" class="input" placeholder="이메일" name="email" title="이메일" maxlength="50">
			</div>
			<h3>주소</h3>
			<div class="joinAdrress">
				<input type="text" class="input" placeholder="주소" name="address">
			</div>
			<h3>전화번호</h3>
			<div class="joinPhone">
				<input type="text" class="input" placeholder="전화번호" name="phone">
			</div>
			<h3>성별</h3>
			<div class="joinGender">
				<input type="radio" name="sex" value="M" title="성별"> 남자 
				<input type="radio" name="sex" value="F" title="성별"> 여자
			</div>

			<input type="submit" class="bt_join" value="회원가입">
		</form>
	</div>
</body>
<jsp:include page="/html/footer.html" />
</html>
