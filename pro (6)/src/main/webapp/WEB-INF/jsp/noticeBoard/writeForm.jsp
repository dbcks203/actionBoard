<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
<style type="text/css">
.ck.ck-editor {
   	max-width: 1000px;
}
.ck-editor__editable {
    min-height: 300px;
}
</style>
<script src="https://cdn.ckeditor.com/ckeditor5/35.3.2/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/35.3.2/classic/translations/ko.js"></script>
</head>
<body>
<form method="post">
	<label>제목</label>
	<input type="text" name="title" id="title" ><br/>
	<label>내용</label>
	<textarea name="content" id="content"></textarea>
    
<input type="button" id="write" name="write" value="등록"  >
 
<input type="button" name="cancle" value="취소" onClick="history.back()">
</form>
<script type="text/javascript">
let write = document.querySelector("#write");
write.onclick = () => { 
jswrite();
}

function jswrite() {
	let param = {
		"title" : title.value,
		"content" : content.value
	};

	fetch('/pro/noticeBoard/write', {
		//option
		method : 'POST',
		headers: {
		    'Content-Type': 'application/json;charset=utf-8'
		},
		body: JSON.stringify(param)//{"uid":"user10","pwd":"123", "name":"홍길동"}			
	})
	.then(response => response.json())
	.then(jsonResult => {
		//처리후 메시지 출력
		alert(jsonResult.message);
		if (jsonResult.status == true) {
			//성공시 이동할 페이지로 이동한다  
			location.href = jsonResult.url;
		}
	});
	}	
</script>
</body>
</html>