<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/tag.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="/pro/css/board.css">
</head>
<body>
	<h2>게시물</h2>
	<form method="post" name="searchForm" action="<c:url value='/board/list.do'/>">
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
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		<thead>
		<tbody>
			<c:forEach var="board" items="${list}">
				<tr>
					<td>${board.number}</td>
					<td><a href="<c:url value='/board/view.do?number=${board.number}'/>">
							${board.number == board.parentNo ? '' : '<span style="margin-left:20px;">[답변]</span>'} ${board.title}
							|
							<c:choose>
								<c:when test="${board.number == board.parentNo}">${board.title}</c:when>
								<c:otherwise>&nbsp;[답변] ${board.title}</c:otherwise>
							</c:choose>
						</a></td>
					<td>${board.writeId}</td>
					<td>${board.writeDate}</td>
					<td>${board.viewCount}</td>
				</tr>
			</c:forEach>
		</tbody>
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
				<span style="font-size:1.3rem;">${pageNo}</span>
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
	<br>
	<ul>
		<li><a href="<c:url value='/board/writeForm.do'/>">글쓰기</a></li>
		<li><a href="<c:url value='/jsp/board/boardMain.jsp'/>">돌아가기</a></li>
	</ul>
	
<script type="text/javascript">
function movePage(pageNo) {
	document.querySelector("#pageNo").value = pageNo; 
	searchForm.submit();
}

function jsSearch() {
	document.querySelector("#pageNo").value = 1; 
	searchForm.submit();
}
</script>	
</body>
</html>