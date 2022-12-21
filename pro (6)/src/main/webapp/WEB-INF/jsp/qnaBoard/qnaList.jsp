<%@page import="qnaBoard.QnaBoard"%>
<%@page import="qnaBoard.QnaBoardDAO"%>
<%@page import="java.util.List"%>
<%@page import="join1.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Qna 목록</title>
<link rel="stylesheet" href="/pro/css/board.css">
</head>

<%
request.setCharacterEncoding("UTF-8");
String text = request.getParameter("text");
Member member = (Member) session.getAttribute("session_member");
if (member == null) {
	response.sendRedirect("/pro/index.html");
} else {
	QnaBoardDAO qnaBoardDAO = new QnaBoardDAO();
	List<QnaBoard> list = qnaBoardDAO.listQnaBoards(text);
	request.setAttribute("list", list);
}
%>
<body>
	<h2>Qna</h2>
	<form method="post" name="search" action="">
		<tr>
			<td>검색어</td>
			<td><input type="text" placeholder="제목이나 내용을 입력해주세요."
				name="text" maxlength="130"></td>
			<td><input type="submit" name="search" id="search" value="검색">
		</tr>
		</table>
	</form>
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
						<c:forEach var="qnaBoard" items="${list}">
							<tr>
								<div class="item">
								<td>${qnaBoard.number}</td>
								<td><a href="<c:url value='/qnaBoard/view?number=${qnaBoard.number}'/>">${qnaBoard.title}</a></td>
								<td>${qnaBoard.writeId}</td>
								<td>${qnaBoard.writeDate}</td>
								<td>${qnaBoard.viewCount}</td>
							</tr>
						</c:forEach>
					</div>
				</div>
			</tr>
		</thead>
	</table>
	<c:if test="${session_member.admin == 'Y'}">
		<a href="<c:url value='/jsp/qnaBoard/writeForm.jsp'/>">글쓰기</a>
		<br/>
	</c:if>
	<a href="<c:url value='/jsp/board/boardMain.jsp'/>">돌아가기</a>
	<br/>
</body>
</html>