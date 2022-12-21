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
<h2>Qna</h2>
<body>
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
			</tr>
		</thead>
	</table>
</body>
</html>