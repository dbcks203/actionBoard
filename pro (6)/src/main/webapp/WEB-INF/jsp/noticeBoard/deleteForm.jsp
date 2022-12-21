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
<title>공지글 삭제</title>
</head>
<body>
<form action="" method = "POST">

	비밀번호 : <input type="text" name="pwd" id="pwd"><br> 
	<c:if test="${session_member.admin == 'Y'}">
	<input type="submit" id="delete" name = "delete" value="삭제"  >
	<input type="button" name = "cancel" value="취소" onclick="history.back();">
	</c:if>
	</form>
	<script type="text/javascript">
	const pwdInput = document.querySelector("#pwd");
	const deleteSubmit = document.querySelector("#delete");
	let pwd = pwdInput.value;
	deleteSubmit.onclick = e => {
		e.preventDefault();
		let param = {
			"number" : new URLSearchParams(location.search).get("number"),
			"pwd" : pwd,				
		};
		console.log(param);	
		fetch("<c:url value='/noticeBoard/delete'/>", {
			method : 'POST',
			headers: {
			    'Content-Type': 'application/json;charset=utf-8'
			},
			body: JSON.stringify(param)
		})
		.then(response => response.json())
		.then(jsonResult => {
			alert(jsonResult.message);
			if (jsonResult.status == true) {
				location.href = jsonResult.url;
			}
		});
		}

	</script>

</body>
</html>