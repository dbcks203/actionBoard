<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/WebSocketChatting/css/style_boardList.css">
<link rel="stylesheet" href="/WebSocketChatting/css/style.css">
<script src="https://code.jquery.com/jquery-2.2.4.js"
	integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
	crossorigin="anonymous"></script>
<title>Youzan's Project Master Page</title>
</head>
<body>
	<h2>MasterPage</h2>
	<h3>관 리 자</h3>

	<form name="searchForm">
		<table>
			<tr>
				<td>검색어</td>
				<td><input type="text" placeholder="제목,내용,작성자를 입력" id="text"
					name="text" value="${param.text}" maxlength="130"></td>
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
				<th>아이디</th>
				<th>이름</th>
				<th>성별</th>
				<th>이메일</th>
				<th>주소</th>
				<th>번호</th>
				<th>가입일</th>
				<th>활성화</th>
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
			chartHtml += "<td>" + member.id + "</td>";
			chartHtml += "<td>" + member.name + "</td>";
			chartHtml += "<td>" + member.sex + "</td>";
			chartHtml += "<td>" + member.email + "</td>";
			chartHtml += "<td>" + member.address + "</td>";
			chartHtml += "<td>" + member.phone + "</td>";
			chartHtml += "<td>" + member.joindate + "</td>";
			chartHtml += "<td>" + member.available + "</td>";
			chartHtml += "<td><a href='#' class='deleteUids' data-uid="+member.id+">삭제</a></td>";
			
			var dump = member.available == 'Y' ? '사용' : '미사용';
			chartHtml += "<td><a href='#' class='useYns' ><span id="+member.id+" data-uid="+member.id+" data-useyn="+member.available+">"+dump+"</span></a></td>";
			chartHtml += "</tr>";
		});
		$("#dataTableBody").html(chartHtml);
		$(".deleteUids").on("click", e => {
	    	let aLink = e.target;
	    	
			e.preventDefault();
	    	if (!confirm("삭제 할시겠습니까?")) return;
	    	
	    	let param = {
	    		uid : aLink.getAttribute("data-uid") 	
	    	};
	    	$.ajax({
				type:"post"
				,async: true
				,url : "<c:url value='/admindelete.zan'/>"
				,data : JSON.stringify(param)	
				,dataType : "JSON"
				,contentType:"application/json;charset=utf-8"
				,success : (jsonResult, textStatus) => {
					alert(jsonResult.message);
					if (jsonResult.status == true) {
						loadList();    			
					}
					
				}
	 		});
	    });
		
		
		$(".useYns").on("click", e => {
			let aLink = e.target;
			console.log(aLink);
			e.preventDefault();
			let uid = aLink.getAttribute("data-uid");
			
	    	let useYn = aLink.getAttribute("data-useyn");
	
			if (!confirm((useYn == 'Y' ? '미사용' : '사용') +  "으로 변경하시겠습니까?")) return;
	    	
	    	let param = {
	       		uid : uid 	
	       		,useYn : useYn 	
	    	};
	    	$.ajax({
				type:"post"
				,async: true
				,url : "<c:url value='/adminuseyn.zan'/>"
				,data : JSON.stringify(param)	
				,dataType : "JSON"
				,contentType:"application/json;charset=utf-8"
				,success : (jsonResult) => {
					alert(jsonResult.message);
		    		if (jsonResult.status == true) {
		    			//location.reload();
		    			//searchForm.submit();
		    			let useYnSpan = document.querySelector("#useYn_" + uid);
		    			if (useYnSpan != null) {
		    				useYnSpan.innerText = (useYn == 'Y' ? '미사용' : '사용');
		    				//data-useyn 변수의 값을 변경한다 
		    				aLink.setAttribute("data-useyn", useYn == 'Y' ? 'N' : 'Y');
		    			}
		    			loadList();
		    		}
				}
	 		});
		  });
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
				console.log(json);
				$("#pagingul").html(json.pageHtml);
				displayData();
			}
		});
	}
</script>
<jsp:include page="/html/footer.html" />


</body>
</html>