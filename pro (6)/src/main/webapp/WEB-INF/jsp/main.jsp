<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/tag.jsp"%>


<html>
<head>

<meta charset="UTF-8">
<title>시작페이지</title>      
    <link href='<c:url value="/image/logo.jpg"/>' rel="icon" type="image/jpg" />
    <link href='<c:url value="/style.css"/>' rel="stylesheet" />
  </head>

<body>
 <div id="home" class='first'> 
      <header class="page-header wrapper">
        <h1>
        	<a href='<c:url value="/main.do"/>'>
        		<img class='logo' src="<c:url value='image/logo.jpg'/>" alt="이미지없음">
        	</a></h1>
        <nav>
          <ul class='main'>
          	<c:choose>
          		<c:when test="${empty session_member}">
		            <li><a href="<c:url value='/member/loginForm.do'/>">로그인</a></li>
		            <li><a href="<c:url value='/member/registerForm.do'/>">회원가입</a></li>
          		</c:when>
          		<c:otherwise>
		            <li><a href="<c:url value='/member/viewForm.do'/>">${session_member.name}</a></li>
		            <li><a href="#" onclick="logout()">로그아웃</a></li>
          		</c:otherwise>
          	</c:choose> 
            <li><a href="LoginForm.jsp">채팅</a></li>
            <li><a href="<c:url value='/board/list.do'/>">게시판</a></li>
          </ul>
        </nav>      
      </header>  
      <div class='home-content wrapper'>
        <h2 class="page-title">Welcome my pages</h2>
        <a href="signUp.html" class="button">시작하기</a>
      </div>
    </div>
<script>
function logout() {
	
	fetch("<c:url value='/member/logout.do'/>")
		.then(response => response.json())
		.then(json => {
			alert (json.message);
			if (json.status) {
				location.href = json.url;
			}
	});
	
}
</script>
</body>
</html>