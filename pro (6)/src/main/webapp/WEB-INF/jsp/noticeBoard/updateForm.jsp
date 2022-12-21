<%@page import="java.io.PrintWriter"%>
<%@page import="board.entity.Board"%>
<%@page import="board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 수정</title>
<link rel="stylesheet" href="/pro/css/board.css">
</head>
<body>
	<h2>공지글 수정</h2>
	<table>
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<div class="board_list_body">
					<div class="item">
						<tr>
							<td><input type="text" name="number" id="number"
								value="${noticeBoard.number}" readonly /></td>
							<td><input type="text" name="title" id="title"
								value="${noticeBoard.title}" /></td>
							<td><input type="text" name="writeId" id="writeId"
								value="${noticeBoard.writeId}" readonly /></td>
							<td><input type="text" name="writeDate" id="writeDate"
								value="${noticeBoard.writeDate}" readonly /></td>
							<td><input type="text" name="viewCount" id="viewCount"
								value="${noticeBoard.viewCount}" readonly /></td>
						</tr>
			<tr align="center">
				<td width="100%" colspan="5" style="height: 200px;"><input
					type="text" name="content" id="content"
					value="${noticeBoard.content}" /></td>
			
			</div>
			</div>
			</tr>
		</thead>
	</table>
	<a href="/jsp/noticeBoard/noticeList.jsp">목록</a>
	<br />
	<br>
	<input type="button" id="update" name="update" value="수정">
	<input type="button" name="cancle" value="취소" onClick="history.back()">
	<script type="text/javascript">

let update = document.querySelector("#update");
update.onclick = () => { 
	jsupdate();
}
function jsupdate() {
	let param = {
		"number" : number.value,
		"title" : title.value,
		"content" : content.value		
	};
	fetch("<c:url value='/noticeBoard/update'/>", {
		
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
			location.href = "<c:url value='/noticeBoard/view?number=${noticeBoard.number}'/>"
		}
	});
	}	
</script>
</body>
</html>