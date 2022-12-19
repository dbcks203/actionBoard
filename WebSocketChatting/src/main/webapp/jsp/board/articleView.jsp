<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/WebSocketChatting/css/style.css">
<title>Youzan's Project</title>
<jsp:include page="/html/loginned_header.html" />
</head>
<body>
	<section class="main-section">
		<h1>
			Board <small>View</small>
		</h1>

		<table class="table table-bordered">
			<tr>
				<th>번호</th>
				<td>${boardDTO.seq}</td>
				<th>작성자</th>
				<td>${ boardDTO.userid }</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>${ boardDTO.regdate }</td>
				<th>읽음</th>
				<td>${ boardDTO.readcount }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">${ boardDTO.subject }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">${ boardDTO.content }</td>
			</tr>
		</table>
		<div class="btns">
			<c:if
				test="${fn:toUpperCase(boardDTO.userid) eq fn:toUpperCase(sessionScope.id)}">
				<button type="button" class="btn btn-primary"
					onclick="location.href='articlesetedit.zan?seq=${boardDTO.seq}';">수정하기</button>

				<button type="button" class="btn btn-primary"
					onclick="location.href='articledelete.zan?seq=${boardDTO.seq}';">삭제하기</button>
			</c:if>

			<c:if test="${boardDTO.seq == boardDTO.parentNo}">
				<a href="<c:url value='/board/replyForm.zan?number=${boardDTO.seq}'/>">답글</a><br/>
			</c:if>

			<button type="button" class="btn btn-default"
				onclick="location.href ='articlelist.zan';">돌아가기</button>

		</div>
	</section>
</body>
<jsp:include page="/html/footer.html" />
</html>