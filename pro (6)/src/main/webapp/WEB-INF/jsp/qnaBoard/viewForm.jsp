<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Qna 상세보기</title>
<link rel="stylesheet" href="/pro/css/board.css">
</head>
<body>
	<h2>Qna</h2>
	<table>
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<div class="board_list_body">
				<div class="item">
			<tr>
				<td>${viewQnaBoard.number}</td>
				<td>${viewQnaBoard.title}</td>
				<td>${viewQnaBoard.writeId}</td>
				<td>${viewQnaBoard.writeDate}</td>
				<td>${viewQnaBoard.viewCount}</td>
			</tr>

			<tr align="center">
				<td width="100%" colspan="5" style="height: 200px;">
					${viewQnaBoard.content}</td>
			</tr>
			</div>
			</div>
		</thead>
	</table>
	<a href="<c:url value='/jsp/qnaBoard/qnaList.jsp'/>">목록</a>
	<br />
	<br>
	<c:if test="${session_member.admin == 'Y'}">
		<a href="/pro/jsp/qnaBoard/updateForm?number=${viewQnaBoard.number}">수정</a>
		<br><br>
		<a href="<c:url value='/qnaBoard/deleteForm?number=${viewQnaBoard.number}'/>">삭제</a>
		<br><br>
	</c:if>
	<input type="button" name="cancle" value="돌아가기"
		onClick="history.back()">
</body>
</html>