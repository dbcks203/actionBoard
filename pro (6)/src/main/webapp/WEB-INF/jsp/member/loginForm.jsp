<%@ page language="java" contentType="text/html; charset=UTF-8"	 pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/tag.jsp"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="/pro/css/login.css" rel="stylesheet" />
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="sign-in-container">
				<form id="loginForm" action="" method="POST">
					<h1>로그인</h1>
					<input type="text" name="uid" id="uid" placeholder="아이디를 입력해주세요"> <br/> 
					<input type="password" name="pwd" id="pwd" placeholder="비밀번호를 입력해주세요"> <br/>
					<input type="submit" name="enter" value="로그인"> 
					<input type="reset" name="cancle" value="초기화" >
					<br/>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	const uid = document.querySelector("#uid");
	const pwd = document.querySelector("#pwd");
	let form = document.querySelector("#loginForm");
	form.addEventListener("submit", async (e) => {
		e.preventDefault();		
		if (uid.value == "") {
			alert("아이디를 입력해주세요");
			uid.focus();
			return false;
		}
		if (pwd.value == "") {
			alert("비밀번호를 입력해주세요");
			pwd.focus();
			return false;
		}
		const requestBody = {
				uid: uid.value
				, pwd: pwd.value
		}
		const response = await fetch("<c:url value='/member/login.do'/>", {
			method: 'POST'
			, headers: {
				'Content-Type': 'application/json;charset=utf-8'
			}
			, body: JSON.stringify(requestBody)
		});
		
		const json = await response.json();
		alert (json.message);
		if (json.status) {
			location.href = json.url;
		}
		return false;
	})
	
	
	</script>
</body>
</html>