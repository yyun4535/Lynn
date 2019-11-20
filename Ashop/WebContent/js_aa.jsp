<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="DAO.MDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<script>
	function letchange() {
	
		var frm = document.forms["aaa"];
			var a = frm["userid"].value;
		location.href="./Madd.do?command=Idchk&id=" + a
	}

	function sendCheckValue() {
		
		// 중복체크 결과인 idCheck 값을 전달한다.
		opener.document.Join.idDuplication.value = "idCheck";
		// 회원가입 화면의 ID입력란에 값을 전달
			var frm = document.forms["aaa"];
				var a = frm["userid"].value;
				opener.document.Join.id.value = a;
		
		if (opener != null) {
			self.close();
		}
	}
	
	
	function pValue(x){
		if(x==1){
			document.getElementById("useBtn").style.visibility='hidden';
		}
		
    }
</script>
<body onload="pValue(${chk})">

	<!--  el로 변경가능 -->
	<form name="aaa">
		<input type=text name="userid" value=${id }> <input
			type=button id="mybutton" onclick="letchange()" value="중복검사"><br>

		<input id="cancelBtn" type="button" value="취소"
			onclick="window.close()"> <input id="useBtn" type="button"
			value="사용하기" onclick="sendCheckValue()">
	</form>
</body>
</html>