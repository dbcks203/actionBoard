<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세보기</title>
<link rel="stylesheet" href="/pro/css/board.css">
</head>
<body>
	<h2>게시물</h2>
	<ul>
		<li>글번호:${viewBoard.number}</li>
		<li>제목:${viewBoard.title}</li>
		<li>작성자:${viewBoard.writeId}</li>
		<li>작성일:${viewBoard.writeDate}</li>
		<li>조회수:${viewBoard.viewCount}</li>
		<li>내용:${viewBoard.content}</li>
	</ul>

	<a href="<c:url value='/board/list.do'/>">목록</a>
	<br/>
	<a href="<c:url value='/board/updateForm?number=${viewBoard.number}'/>">수정</a>
	<br/>
	<a href="<c:url value='/board/deleteForm?number=${viewBoard.number}'/>">삭제</a>
	<br/>
	
	<c:if test="${viewBoard.number == viewBoard.parentNo}">	
		<a href="<c:url value='/board/replyForm.do?number=${viewBoard.number}'/>">답글</a>
		<br/>
	</c:if>	
	<input type="button" name="cancle" value="취소" onClick="history.back()">
</body>
</html>