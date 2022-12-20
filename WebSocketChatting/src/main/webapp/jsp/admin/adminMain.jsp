<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-2.2.4.js" integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" crossorigin="anonymous"></script>
<title>Youzan's Project Master Page</title>
</head>
<body>
<span style="font-size: 8rem;">관 리 자</span>
<a href="chatinfo.zan">회원 목록</a>

<h2>MasterPage</h2>
	<h3>관 리 자</h3>

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
				<th>아이디</th>
				<th>이름</th>
				<th>성별</th>
				<th>이메일</th>
				<th>주소</th>
				<th>번호</th>
				<th>가입일</th>
			</tr>
		</thead>
		<tbody id=dataTableBody>
		</tbody>
	</table>

	<ul id="pagingul"></ul>
</body>


<script type="text/javascript">
	let currentPageNo = 1;
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
		let chartHtml = "";
		memberList.forEach(function(member) {
			chartHtml += "<tr>";
			chartHtml += "<td>" + member.userid + "</td>";
			chartHtml += "<td>" + member.name + "</td>";
			chartHtml += "<td>" + member.sex + "</td>";
			chartHtml += "<td>" + member.email + "</td>";
			chartHtml += "</tr>";
		});
		$("#dataTableBody").html(chartHtml);
		
	}

	function loadList() {
		$.ajax({
			method : "GET",
			url : "memberlist.zan",
			data : {
				"text" : search,
				"listSize" : listSize,
				"pageNo" : currentPageNo
			},
			dataType : "json",
			success : function(json) {
				console.log("currentPage : " + currentPageNo);
				memberList = json.memberList;
				$("#pagingul").html(json.pageHtml);
				displayData();
			}
		});
	}
</script>
<jsp:include page="/html/footer.html" />


</body>
</html>