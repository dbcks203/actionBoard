<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="/WebSocketChatting/css/style_boardList.css">
<link rel="stylesheet" href="/WebSocketChatting/css/style.css">
<script src="https://code.jquery.com/jquery-2.2.4.js" integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" crossorigin="anonymous"></script>
<title>Youzan's Project</title>


<jsp:include page="/html/loginned_header.html" />

</head>
<body>
	<h2>Message Board</h2>
	<h3>List</h3>

	<form name="searchForm">
		<table>
			<tr>
				<td>검색어</td>
				<td><input type="text" placeholder="제목,내용,작성자를 입력" id="text" name="text" value="${param.text}" maxlength="130"></td>
				<td><input type="button" value="검색" onclick="jsSearch()">
			</tr>
		</table>
	</form>
	<select id="dataPerPage">
		<option value="10">10개씩보기</option>
		<option value="15">15개씩보기</option>
		<option value="20">20개씩보기</option>
		<option value="25">25개씩보기</option>
		<option value="30">30개씩보기</option>
	</select>

	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody id=dataTableBody>
		</tbody>
	</table>

	<ul id="pagingul">
	</ul>
	<button type="button" class="btn btn-primary"
				onclick="location.href='/WebSocketChatting/jsp/board/articleWrite.jsp';">글쓰기</button>

</body>
<script type="text/javascript">
	let currentPageNo = 1;
	let totalPageNo;
	let listSize;
	let startPageNo;
	let endPageNo;
	let search;
	listSize = $("#dataPerPage").val();
	loadList();

	$("#dataPerPage").change(function() {
		listSize = $("#dataPerPage").val();
		currentPageNo = 1;
		loadList();
	});

	function movePage(pageNo) {
		currentPageNo = pageNo;
		loadList();
	}
	
	function jsSearch() {
		search = document.querySelector("#text").value;
		currentPageNo = 1;
		loadList();
	}
	
	function displayData() {
		let pageHtml = "";
		if(currentPageNo!=1){
			pageHtml += "<li><a href='javascript:movePage(1)'> &lt;&lt; </a></li>";
			pageHtml += "<li><a href='javascript:movePage("+(currentPageNo-1)+")'> &lt; </a></li>";
		}
		for (var i = startPageNo; i <= endPageNo; i++) {
			if (currentPageNo == i)
				pageHtml += "<li class='on'><a href=javascript:movePage("+i+")>"+i+"</a></li>";
			else
				pageHtml += "<li><a href=javascript:movePage("+i+")>"+i+"</a></li>";
		}

		if(currentPageNo != totalPageNo){
			pageHtml += "<li><a href='javascript:movePage("+(currentPageNo+1)+")'> &gt; </a></li>";
			pageHtml += "<li><a href='javascript:movePage("+totalPageNo+")'> &gt;&gt; </a></li>";
		}

		let chartHtml = "";
		articleList.forEach(function(article) {
			chartHtml += "<tr>";
			chartHtml += "<td>" + article.seq + "</td>";
			if(article.seq==article.parentNo)
				chartHtml += "<td><a href=articleview.zan?seq="+article.seq+">" + article.subject + "</td>";
			else
				chartHtml += "<td><a href=articleview.zan?seq="+article.seq+">/t[답변] " + article.subject + "</td>";
			chartHtml += "<td>" + article.userid + "</td>";
			chartHtml += "<td>" + article.regdate + "</td>";
			chartHtml += "<td>" + article.readcount + "</td>";
			chartHtml += "</tr>";
		});
		$("#dataTableBody").html(chartHtml);
		$("#pagingul").html(pageHtml);
	}

	function loadList() {
		$.ajax({
			method : "GET",
			url : "articlelist.zan",
			data : {
				"text" : search,
				"listSize" : listSize,
				"pageNo" : currentPageNo
			},
			dataType : "json",
			success : function(json) {
				console.log("currentPage : " + currentPageNo);
				totalPageNo = json.totalPageNo;
				startPageNo = json.startPageNo;
				endPageNo = json.endPageNo;
				articleList = json.articleList;
				displayData();
			}
		});
	}
</script>
<jsp:include page="/html/footer.html" />
</html>