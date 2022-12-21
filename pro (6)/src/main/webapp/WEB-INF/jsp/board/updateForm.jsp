<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/tag.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link rel="stylesheet" href="/pro/css/board.css">
</head>
<body>
	<h2>게시글 수정</h2>
	<table>
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<div class="board_list_body">
				<div class="item">
					<tr>
						<td><input type="text" name="number" id="number"
							value="${board.number}" readonly /></td>
						<td><input type="text" name="title" id="title"
							value="${board.title}" /></td>
						<td><input type="text" name="writeId" id="writeId"
							value="${board.writeId}" readonly /></td>
						<td><input type="text" name="writeDate" id="writeDate"
							value="${board.writeDate}" readonly /></td>
						<td><input type="text" name="viewCount" id="viewCount"
							value="${board.viewCount}" readonly /></td>
					</tr>
			<tr align="center">
				<td width="100%" colspan="5" style="height: 200px;"><input
					type="text" name="content" id="content" value="${board.content}" />
			</td>
			</tr>
			</div>
			</div>
		</thead>
	</table>
	<a href="<c:url value='/jsp/board/boardList.jsp'/>">목록</a>
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
//updateServlet @와 통일
	fetch("<c:url value='/board/update'/>", {	
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
			location.href = "<c:url value='/board/view?number=${board.number}'/>"
		}
	});
	}	
</script>
</body>
</html>