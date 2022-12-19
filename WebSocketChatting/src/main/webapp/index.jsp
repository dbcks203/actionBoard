<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="/WebSocketChatting/css/style.css">
<link rel="stylesheet" href="/WebSocketChatting/css/style_index.css">
<script src="https://kit.fontawesome.com/c78845bcda.js"></script>

<title>Youzan's Project</title>
<c:choose>
	<c:when test="${sessionScope.id eq null}">
		<jsp:include page="/html/defualt_header.html" />
	</c:when>
	<c:otherwise>
		<jsp:include page="/html/loginned_header.html" />
	</c:otherwise>
</c:choose>
</head>

<body>
	<div class="main_container">
		<div class="conA">
			<div class="slide img1"></div>
			<div class="slide img2"></div>
			<div class="slide img3"></div>
		</div>

		<c:choose>
			<c:when test="${sessionScope.id eq null}">
				<jsp:include page="/jsp/member/login.jsp" />
			</c:when>
			<c:otherwise>
				<div class="conB">
					<div class="conB_title">
						<h3>Zan's Lab</h3>
					</div>
					<div class="conB_container">
						<div class="conB_small_container" id="icon1">
							<div class="conB_icon">
								<i class="fa-solid fa-comments fa-3x"></i>
							</div>
							<div class="conB_content">
								<h3>채팅하기</h3>
								<p>Start Chat</p>
							</div>
						</div>
						<div class="conB_small_container" id="icon2">
							<div class="conB_icon">
								<i class="fa-solid fa-clipboard-list fa-3x"></i>
							</div>
							<div class="conB_content">
								<h3>게시판</h3>
								<p>Message Board</p>
							</div>
						</div>

						<div class="conB_small_container" id="icon3">
							<div class="conB_icon">
								<i class="fa-solid fa-image-portrait fa-3x"></i>
							</div>
							<div class="conB_content">
								<h3>회원정보</h3>
								<p>My Page</p>
							</div>
						</div>

						<div class="conB_small_container" id="icon4">
							<div class="conB_icon">
								<i class="fa-solid fa-arrow-right-from-bracket fa-3x"></i>
							</div>
							<div class="conB_content">
								<h3>로그아웃</h3>
								<p>LogOut</p>
							</div>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<jsp:include page="/html/footer.html" />

</body>
<script>




document.getElementById("icon1").onclick= () => {
	document.location="chatlist.zan"
}

document.getElementById("icon2").onclick= () => {
	document.location="articlelist.zan"
}

document.getElementById("icon3").onclick= () => {
	document.location="mypage.zan"
}

document.getElementById("icon4").onclick= () => {
	document.location="logout.zan"
}

</script>
</html>