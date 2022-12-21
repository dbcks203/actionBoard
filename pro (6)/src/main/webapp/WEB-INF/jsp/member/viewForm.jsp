<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/tag.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보창</title>
</head>
<body> ${session_member.uid} ${session_member.name} 님의 회원정보 입니다.
<table border ="1" align ="center">
<tr align = "center" bgcolor = "lightblue">
	<td width = "7%"><b>아이디</b></td>
	<td width = "7%"><b>비밀번호</b></td>
	<td width = "7%"><b>이름</b></td>
	<td width = "7%"><b>성별</b></td>
	<td width = "7%"><b>주소</b></td>	
	<td width = "7%"><b>핸드폰번호</b></td>
	<td width = "7%"><b>이메일</b></td>
	<td width = "7%"><b>생성일자</b></td>
</tr>
<tr>
	<td>${session_member.uid}</td>
	<td>${session_member.pwd}</td>
	<td>${session_member.name}</td>
	<td>${session_member.sex}</td>
	<td>${session_member.address}</td>
	<td>${session_member.phone}</td>
	<td>${session_member.email}</td>
	<td>${session_member.createDate}</td>
</tr>
</table>
<br>
<a href="<c:url value='/member/updateForm.do'/>">정보수정</a><br/><br>
<a href="<c:url value='/member/deleteForm.do'/>">회원탈퇴</a><br/><br>

<input type="button" value="돌아가기" onclick="history.back();"> <br><br>
</body>
</html>




