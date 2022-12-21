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
	<table>
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<div class="board_list_body">
					<div class="item">
						<tr>
							<td>${viewBoard.number}</td>
							<td>${viewBoard.title}</td>
							<td>${viewBoard.writeId}</td>
							<td>${viewBoard.writeDate}</td>
							<td>${viewBoard.viewCount}</td>
						</tr>
			<tr align="center">
				<td width="100%" colspan="5" style="height: 200px;">
					${viewBoard.content}</td>
			</tr>
			</div>
	</table>
	<a href="<c:url value='/jsp/board/boardList.jsp'/>">목록</a>
	<br>
	<br/>
	<a href="<c:url value='/board/updateForm?number=${viewBoard.number}'/>">수정</a>
	<br>
	<br/>
	<a href="<c:url value='/board/deleteForm?number=${viewBoard.number}'/>">삭제</a>
	<br>
	<br/>
	<input type="button" name="cancle" value="취소" onClick="history.back()">
</body>
</html>