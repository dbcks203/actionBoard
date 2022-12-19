<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<section>
<h2>아이디 찾기</h2>
<form action="findinfo.zan" method="GET">
이름을 입력하세요 <input type="text" name="name">
<button type="submit">아이디 찾기</button>
</form>
</section>


<section>
<h2>비밀번호 찾기</h2>
<form action="findinfo.zan" method="GET">
아이디를 입력하세요 <input type="text" name="id">
<button type="submit">비밀번호</button>
</form>
</section>
</body>
<jsp:include page="/html/footer.html" />
</html>