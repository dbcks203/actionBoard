<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디찾기</title>
<link href="/pro/css/login.css" rel="stylesheet" />
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="sign-in-container">
				<form id="find_id" action="" method="POST">
					<h1>휴대폰 본인확인</h1>
					<input type="text" name="name" id="name" value=""
						placeholder="등록한 이름"> <input type="text" name="phone"
						id="phone" value="" placeholder="휴대폰번호를 '-'없이 입력"> <input
						type="submit" name="enter" value="찾기"> <input
						type="button" name="cancle" value="취소" onclick="location.href='/pro/index.html'">
				</form>
			</div>
			<div class="overlay-container">
				<div class="overlay-right">
					<h2>아이디가 기억나면?</h2>
					<p>로그인 해주세요</p>
					<button id="login" class="overlay_btn" onclick="location.href='/pro/LoginForm.jsp'">Login</button>
					<button id="main" class="overlay_btn" onclick="location.href='/pro/index.html'">처음으로</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	const nameInput = document.querySelector("#name");
	const phoneInput = document.querySelector("#phone");
	let form = document.querySelector("#find_id");
	form.addEventListener("submit", async (e) => {
		e.preventDefault();		
		if (nameInput.value == "") {
			alert("이름을 입력해주세요");
			nameInput.focus();
			return false;
		}
		if (phoneInput.value == "") {
			alert("전화번호를 입력해주세요");
			phoneInput.focus();
			return false;
		}
		
		
		const requestBody = {
				name2: nameInput.value
				, phone: phoneInput.value
		}
		const response = await fetch("/pro/findUid", {
			method: 'POST'
			, headers: {
				'Content-Type': 'application/json;charset=utf-8'
			}
			, body: JSON.stringify(requestBody)
		});
		
		const json = await response.json();
		alert (json.message);
		if (json.status) {
			location.href= "/pro/LoginForm.jsp";
		}
		return false;
	})
	
	
	</script>
</body>
</html>