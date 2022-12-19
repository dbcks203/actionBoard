<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<c:url value="/static/css/style.css"/>">
<link rel="icon" type="image/png"
	href="<c:url value="/static/images/icon.jpg" />">
</head>
<body>
	<div class="container">
		<div class="form-container">
			<h2 class="form-title">Zans Lab</h2>
			<div class="tab-control">
				<h3 class="active tab-control-btn login">Sign in</h3>
				<h3 class="tab-control-btn register">
					<a href="<c:url value="jsp/member/join.jsp"/>" style="color: white;">Sign up</a>
				</h3>
			</div>
			<div class="login-form form active">
				<form action="<c:url value="login.zan" />" method="POST">
					<input type="text" class="txt-input border" placeholder="Username"
						name="userid"> <input type="password"
						class="txt-input border" placeholder="Password" name="password">
					<button type="submit" class="btn btn-login border">Sign in</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>