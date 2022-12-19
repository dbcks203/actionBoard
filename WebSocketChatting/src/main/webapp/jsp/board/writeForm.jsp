<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


</head>
<body>



<form name="boardForm" id="boardForm" method="post" >
	<label>제목</label>
	<input type="text" name="subject" id="subject" ><br/>
	<label>내용</label>
	<input name="content" id="content"/>	
	<label>첨부파일</label>
	<input type="file" name="filename1" id="filename1"/>
		
	<input type="submit" id="write" name="write" value="등록"  > 
	<input type="button" name="cancle" value="취소" onClick="history.back()">
</form>


<script type="text/javascript">


let boardForm = document.querySelector("#boardForm");
boardForm.addEventListener("submit", (e) => {
	e.preventDefault();
	
	fetch('articlewrite.zan', {		
		method : 'POST',
	    cache: 'no-cache',
		body: new FormData(boardForm)		
	})
	.then(response => response.json())
	.then(jsonResult => {
		alert(jsonResult.message);
		if (jsonResult.status == true) {
			location.href = jsonResult.url;
		}
	});
});

</script>
</body>
</html>