<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/WebSocketChatting/css/style_boardList.css">
<link rel="stylesheet"
	href="/WebSocketChatting/css/style_boardLobby.css">
<link rel="stylesheet" href="/WebSocketChatting/css/style.css">

<title>Youzan's Project</title>
<jsp:include page="/html/loginned_header.html" />
</head>
<body>
	<h2>Message Board</h2>
	<h3>List</h3>
	
	<div class="table-wrapper">
	<form method="post" name="searchForm" action="<c:url value='articlelist.zan'/>">
		<input type="hidden" name="pageNo" id="pageNo" value="${currentPageNo}"/>
		<table>
			<tr>
				<td>검색어</td>
				<td><input type="text" placeholder="제목,내용,작성자를 입력" name="text" value="${param.text}"
					maxlength="130"></td>
				<td><input type="button" value="검색" onclick="jsSearch()">
			</tr>
		</table>		
	</form>
	
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>읽음</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="boardDTO" items="${articleList}"
					varStatus="listArticleStatus">
					<tr>
						<td>${boardDTO.seq}</td>
						<td><a href="articleview.zan?seq=${boardDTO.seq}"> <c:choose>
									<c:when test="${boardDTO.seq == boardDTO.parentNo}">${boardDTO.subject}</c:when>
									<c:otherwise>&nbsp;[답변] ${boardDTO.subject}
									</c:otherwise>
								</c:choose>
						</a></td>
						<td>${boardDTO.userid}</td>
						<td>${boardDTO.regdate }</td>
						<td>${boardDTO.readcount }</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr></tr>
			</tfoot>
		</table>

		<c:if test="${currentPageNo != 1}">
			<a href="javascript:movePage(1)"> &lt;&lt; </a>
		&nbsp;
		<a href="javascript:movePage(${currentPageNo-1})"> &lt; </a>
		&nbsp;
	</c:if>
		<c:forEach var="pageNo" begin="${startPageNo}" end="${endPageNo}">
			<c:choose>
				<c:when test="${currentPageNo == pageNo}">
					<span style="font-size: 1.3rem;">${pageNo}</span>
				</c:when>
				<c:otherwise>
					<a href="javascript:movePage(${pageNo})">${pageNo}</a>
				</c:otherwise>
			</c:choose>
		&nbsp;
	</c:forEach>
		<c:if test="${currentPageNo != totalPageNo}">
			<a href="javascript:movePage(${currentPageNo+1})"> &gt; </a>
		&nbsp;
		<a href="javascript:movePage(${totalPageNo})"> &gt;&gt; </a>
		</c:if>

		<div class="btns">

			<button type="button" class="btn btn-primary"
				onclick="location.href='/WebSocketChatting/jsp/board/articleWrite.jsp';">글쓰기</button>

		</div>


	</div>
</body>
<script type="text/javascript">

let boardForm = document.querySelector("#boardForm");
boardForm.addEventListener("submit", (e) => {
	e.preventDefault();
	
	fetch('reply.zan', {		
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

	function movePage(pageNo) {
		document.querySelector("#pageNo").value = pageNo;
		searchForm.submit();
	}

	function jsSearch() {
		document.querySelector("#pageNo").value = 1;
		searchForm.submit();
	}
</script>
<jsp:include page="/html/footer.html" />
</html>