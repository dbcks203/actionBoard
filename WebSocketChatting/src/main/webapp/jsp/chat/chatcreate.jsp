<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/WebSocketChatting/css/style_boardList.css">
<link rel="stylesheet" href="/WebSocketChatting/css/style.css">
<script type="text/javascript" src="<c:url value="/static/js/chatbox.js" />" charset="utf-8"></script>
<title>Youzan's Project</title>
<jsp:include page="/html/loginned_header.html" />
</head>
<body>
<h2>Chat Lobby</h2>
	<h3>채팅방 생성</h3>
	<section>
		<form action="chatcreate.zan" method="GET">
			채팅방 이름을 입력하세요 <input type="text" name="chatName">
			<button type="submit">채팅방 생성</button>
		</form>
	</section>

</body>
<jsp:include page="/html/footer.html" />
</html>