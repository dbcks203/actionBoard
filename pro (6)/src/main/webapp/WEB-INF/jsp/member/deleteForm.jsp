<%@page import="join1.MemberDAO"%>
<%@page import="join1.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@page import="java.sql.ResultSet"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<body>
<form id="delete" action="" method = "POST">

	비밀번호 : <input type="text" name="pwd" id="pwd"><br> 
	<input type="submit" name = "delete" value="탈퇴"  >
	<input type="button" name = "cancel" value="취소" onclick="history.back();">

	</form>
	<script type="text/javascript">
	const pwdInput = document.querySelector("#pwd");	
	let form = document.querySelector("#delete");
	form.addEventListener("submit", async (e) => {
		e.preventDefault();		
		
	
	
		const requestBody = {
				pwd : pwdInput.value				
		}
		const response = await fetch("/pro/memberDelete", {
			method: 'POST'
			, headers: {
				'Content-Type': 'application/json;charset=utf-8'
			}
			, body: JSON.stringify(requestBody)
		});
		
		const json = await response.json();
		alert (json.message);
		if (json.status) {
			location.href= "/pro/index.html";
		}
		return false;
	})

	</script>

</body>
</html>