<%@page import="join1.MemberDAO"%>
<%@page import="join1.Member"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보창</title>
</head>
<%	request.setCharacterEncoding("UTF-8");
	
Member member = (Member) session.getAttribute("session_member");
if (member == null) {
	response.sendRedirect("LoginForm.jsp"); 
} else {
	MemberDAO memberDAO = new MemberDAO();
	Member viewMember = memberDAO.viewMember(member.getUid());	
	request.setAttribute("viewMember", viewMember);
}
System.out.println(session);

	
 %>

<body> ${viewMember.uid} ${viewMember.name} 님의 회원정보 입니다.
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
	<td>${viewMember.uid}</td>
	<td>${viewMember.pwd}</td>
	<td>${viewMember.name}</td>
	<td>${viewMember.sex}</td>
	<td>${viewMember.address}</td>
	<td>${viewMember.phone}</td>
	<td>${viewMember.email}</td>
	<td>${viewMember.createDate}</td>
</tr>
</table>
<br>
<a href="<c:url value='/member/updateForm'/>">정보수정</a><br/><br>
<a href="<c:url value='/member/deleteForm'/>">회원탈퇴</a><br/><br>

<input type="button" value="돌아가기" onclick="history.back();"> <br><br>
</body>
</html>




