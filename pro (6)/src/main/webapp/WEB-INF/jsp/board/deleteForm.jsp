<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
</head>
<body>
	<form action="" method="POST">
		비밀번호 : <input type="text" name="pwd" id="pwd"><br> <br>
		<input type="submit" id="delete" name="delete" value="삭제"> <input
			type="button" name="cancel" value="취소" onclick="history.back();">
	</form>
	<script type="text/javascript">
	const pwdInput = document.querySelector("#pwd");
	const deleteSubmit = document.querySelector("#delete");
	let pwd = pwdInput.value;
	deleteSubmit.onclick = e => {
		e.preventDefault();
		let param = {
			"number" : new URLSearchParams(location.search).get("number"),
			"pwd" : pwd				
		};
		console.log(param);	
		fetch("<c:url value='/board/delete'/>", {
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