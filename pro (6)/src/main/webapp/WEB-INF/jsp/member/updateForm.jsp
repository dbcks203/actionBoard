<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/tag.jsp"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>로그인 수정</title>
<style>
  .cls1 {
     font-size:40px;
     text-align:center;
   }
</style>
</head>
<body>
 <h1 class="memberUp">회원 정보 수정</h1>
<form  method="post" action="/member/update">
 <table style="text-align:center" >
   <tr>
     <td width="200"><p align="right" >아이디</td>
     <td width="400"><input type="text" name="uid" id="uid" value = "${session_member.uid}" readonly="readonly" ></td>
     
   </tr>
 <tr>
     <td width="200"><p align="right" >비밀번호</td>
     <td width="400"><input type="password" name="pwd" id="pwd" value="${session_member.pwd}">
     </td>
   </tr>
   <tr>
     <td width="200"><p align="right" >이름</td>
     <td width="400"><input type="text" name="name2" id="name2" value="${session_member.name}" ></td>
   </tr>
   <tr>
     <td width="200"><p align="right" >성별</td>
     <td width="400"><input type="text" name="sex" id="sex"  value="${session_member.sex}"></td>
   </tr>
   <tr>
     <td width="200"><p align="right" >전화번호</td>
     <td width="400"><input type="text" name="phone" id="phone" value="${session_member.phone}"></td>
   </tr>
   <tr>
     <td width="200"><p align="right" >주소</td>
     <td width="400"><input type="text" name="address" id="address" value="${session_member.address}"></td>
   </tr>
   <tr>
     <td width="200"><p align="right" >이메일</td>
     <td width="400"><input type="text" name="email" id="email" value="${session_member.email}" ></td>
   </tr>
   
   <tr align="center" >
    <td colspan="2" width="400">
    	<input type="button" value="수정하기" id="updateBtn">
        <input type="reset" value="다시입력" > 
        <input type="button" value="돌아가기" onclick="history.back();"> 
       </td>
   </tr>
 </table>
 
<script type="text/javascript">
let updateBtn = document.querySelector("#updateBtn");
updateBtn.onclick = () => {
	let param = {
		uid : uid.value,
		pwd : pwd.value,
		name2 : name2.value,
		sex : sex.value,
		address : address.value,
		phone : phone.value,
		email : email.value
	};
	
	fetch("<c:url value='/member/update'/>", {
	
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
		
		}
	});
}
</script>

</form>
</html>



