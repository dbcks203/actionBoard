<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<c:url value="/static/css/style.css" />">
<link rel="icon" type="image/png" href="<c:url value="/static/images/icon.jpg" />">

<title>Youzan's Project</title>
</head>
<body>
	<div class="container">
		<div class="form-container">
			<div class="tab-control">
				<h3 class="tab-control-btn register">Information</h3>
			</div>
			<div class="register-form form active">
			
				<form action="join.zan" method="post">
					<input type="text" class="txt-input border" placeholder="Userid" name="userid"> 
					<input type="password" class="txt-input border" placeholder="Password" name="password"> 
					<input type="password" class="txt-input border" placeholder="Re Password"> 
					<input type="text" class="txt-input border" placeholder="Name" name="name"> 
					<input type="text" class="txt-input border" placeholder="Email" name="email"> 
					<input type="text" class="txt-input border" placeholder="Address" name="address"> 
					<input type="text" class="txt-input border" placeholder="Phone" name="phone"> 
					<select name="sex" class="txt-input border gender-select" id="">
						<option value="M">Male</option>
						<option value="F">Female</option>
					</select> 
					<button type="submit" class="btn btn-login border">Register</button>

					<a href="<c:url value="/WebSocketChatting/index.jsp"/>" class="btn btn-login border" style="background-color: grey; text-align: center;">Go back</a>

				</form>
			</div>
		</div>
	</div>
</body>
</html>