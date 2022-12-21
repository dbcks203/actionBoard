<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<h3>List</h3>
	<div class="table-wrapper">
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>참여 인원</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="chatRoomDTO" items="${roomList}" varStatus="listRoomStatus">
					<tr>
						<td>${chatRoomDTO.seq}</td>

						<td><a href="chatinfo.zan?seq=${chatRoomDTO.seq}">${chatRoomDTO.title}</a></td>
						<td>${chatRoomDTO.clientCount}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr></tr>
			</tfoot>
		</table>
	</div>
	<button type="button" onclick="location.href='/WebSocketChatting/jsp/chat/chatcreate.jsp';">채팅방 생성</button>


</body>
<jsp:include page="/html/footer.html" />
</html>