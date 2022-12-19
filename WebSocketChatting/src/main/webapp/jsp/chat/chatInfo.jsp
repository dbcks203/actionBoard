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
		ChatInfo: [ <small>${chatRoomDTO.title}</small> ]
	</h1>
	<section>
		<h3>방번호: ${chatRoomDTO.seq} </h3>

	</section>
	<section>
		<h3>참여인원: ${chatRoomDTO.clientCount}</h3>
		<c:forEach var="client" items="${clients}">
			${client} <br />
		</c:forEach>
	</section>
	<section>
		<h3>생성일: ${chatRoomDTO.createDate}</h3>
	</section>
	<!-- 
	<section>
		<h3>Social</h3>
		<ul class="icons alt">
			<li><a href="#" class="icon brands alt fa-twitter"><span
					class="label">Twitter</span></a></li>
			<li><a href="#" class="icon brands alt fa-facebook-f"><span
					class="label">Facebook</span></a></li>
			<li><a href="#" class="icon brands alt fa-instagram"><span
					class="label">Instagram</span></a></li>
			<li><a href="#" class="icon brands alt fa-github"><span
					class="label">GitHub</span></a></li>
		</ul>
	</section>
	 -->
	<form method="post" action="chatenter.zan">
		<input type="hidden" name="title" id="title" value = '${chatRoomDTO.title}'> 
		<input type="hidden" name="seq" id="seq" value = '${chatRoomDTO.seq}'> 
		<input type="submit" value="채팅방 입장">
	</form>
</body>
<script>
	/*
	 document.querySelector('#enter').onclick=()=>{
	
	 let form = document.createElement('form');
	 form.setAttribute('method', 'post');
	 form.setAttribute('action','chatenter.zan');
	
	 let seq = document.createElement('input');
	 seq.type = 'hidden';
	 seq.name = 'seq';
	 seq.value = '${chatRoomDTO.seq}';
	 form.appendChild(seq);
	
	 let list = document.createElement('input');
	 list.type = 'hidden';
	 list.name = 'clients';
	 list.value = '${clients}';
	 form.appendChild(list);
	
	 let list = document.createElement('input');
	 title.type = 'hidden';
	 title.name = 'title';
	 title.value = '${title}';
	 form.appendChild(title);
	
	 document.body.appendChild(form);
	 form.submit();
	 }
	 */
</script>
<jsp:include page="/html/footer.html" />
</html>