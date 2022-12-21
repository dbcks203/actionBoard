<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공 페이지</title>
 
    <link href="image/logo.jpg" rel="icon" type="image/jpg" />
    <link href="style.css" rel="stylesheet" />
  </head>
<body>
<div id="home" class='first'> 
      <header class="page-header wrapper">
        <!-- 로고 이미지 -->
        <h1><a href="login_success.html"><img class='logo' src="image/logo.jpg" alt="이미지없음"></a></h1>
        <!-- 메인 메뉴를 표시할 부분 -->
        <nav>
          <ul class='main'>                    
            <li><a href="/pro/jsp/chat/index.jsp">채팅</a></li>            
            <li><a href="jsp/board/boardMain.jsp">게시판</a></li>
            <li><a href="memberInfo.jsp">정보보기</a></li>     
            <li><a href="Logout">로그아웃</a></li>  
          </ul>
        </nav>      
      </header>
  
      <!-- 페이지 중심부분 문장과 버튼 -->
      <div class='home-content wrapper'>
        <h2 class="page-title"> Welcome ${session_member.uid} pages</h2>      
      </div>
    </div>
   
    
</body>
</html>