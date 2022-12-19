<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/style_login.css">

    <title>Youzan's Project</title>
</head>

<body>
   <c:choose>
    <c:when test="${sessionScope.id eq null}">
		<jsp:include page="/html/defualt_header.html"/>
    </c:when>
    <c:otherwise>
		<jsp:forward page="/aleadyLogin.jsp"/>
    </c:otherwise>
    </c:choose>
    <div class="login_containers">
        <h2>
            로그인
        </h2>
        <form method="post" action="login.zan">
            <h3>아이디</h3>
            <div class="loginID">
                <input type="text" class="input" placeholder="아이디" name="user_id" maxlength="20">
            </div>
            <h3>비밀번호</h3>
            <div class="loginPassword">
                <input type="password" class="input" placeholder="비밀번호" name="user_pw" maxlength="20">
            </div>
            <input type="submit" class="bt_login" value="로그인">
        </form>
    </div>
    <jsp:include page="/html/footer.html"/>
</body>

</html>
