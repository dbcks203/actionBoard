<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 상세보기</title>
</head>
<h2>공지글</h2>
<body>
<table border="1">
<tr align = "center" bgcolor = "yellow">
	<td width = "2%"><b>글번호</b></td>
	<td width = "7%"><b>제목</b></td>
	<td width = "7%"><b>작성자</b></td>
	<td width = "7%"><b>작성일</b></td>
	<td width = "4%"><b>조회수</b></td>	
</tr>

<tr align = "center" >
	<td width = "2%"><b>${viewNoticeBoard.number}</b></td>
	<td width = "7%"><b>${viewNoticeBoard.title}</b></td>
	<td width = "7%"><b>${viewNoticeBoard.writeId}</b></td>
	<td width = "7%"><b>${viewNoticeBoard.writeDate}</b></td>
	<td width = "7%"><b>${viewNoticeBoard.viewCount}</b></td>
</tr>

<tr align = "center" >
	<td width = "100%" colspan="5" style="height: 200px;">
		${viewNoticeBoard.content}
	</td>
</tr>

</table>
<a href="<c:url value='/jsp/noticeBoard/noticeList.jsp'/>">목록</a><br/><br>
<c:if test="${session_member.admin == 'Y'}">
<a href="<c:url value='/noticeBoard/updateForm?number=${viewNoticeBoard.number}'/>">수정</a><br/><br>
<a href="<c:url value='/noticeBoard/deleteForm?number=${viewNoticeBoard.number}'/>">삭제</a><br/><br>
</c:if>
<input type="button" name="cancle" value="취소" onClick="history.back()">

</body>
</html>