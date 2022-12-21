<%@page import="noticeBoard.NoticeBoard"%>
<%@page import="noticeBoard.NoticeBoardDAO"%>
<%@page import="java.util.List"%>
<%@page import="join1.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 목록</title>
<link rel="stylesheet" href="/pro/css/board.css">
</head>

<%
request.setCharacterEncoding("UTF-8");
String text = request.getParameter("text");
Member member = (Member) session.getAttribute("session_member");
if (member == null) {
	response.sendRedirect("/pro/index.html");
} else {
	NoticeBoardDAO noticeBoardDAO = new NoticeBoardDAO();
	List<NoticeBoard> list = noticeBoardDAO.listNoticeBoards(text);
	request.setAttribute("list", list);
}
%>
<body>
	<h2>공지글</h2>
	<form method="post" name="search" action="">
		<tr>
			<td>검색어</td>
			<td><input type="text" placeholder="제목이나 내용을 입력해주세요."
				name="text" maxlength="130"></td>
			<td><input type="submit" name="search" id="search" value="검색">
		</tr>
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
					<c:forEach var="noticeBoard" items="${list}">
						<tr>
							<td>${noticeBoard.number}</td>
							<td><a href="<c:url value='/noticeBoard/view?number=${noticeBoard.number}'/>">${noticeBoard.title}</a></td>
							<td>${noticeBoard.writeId}</td>
							<td>${noticeBoard.writeDate}</td>
							<td>${noticeBoard.viewCount}</td>
						</tr>
					</c:forEach>
				</div>
			</div>
		</tr>
		</thead>
	</table>
	<c:if test="${session_member.admin == 'Y'}">
		<a href="<c:url value='/jsp/noticeBoard/writeForm.jsp'/>">글쓰기</a>
		<br />
	</c:if>
	<a href="<c:url value='/jsp/board/boardMain.jsp'/>">돌아가기</a>
	<br />
</body>
</html>