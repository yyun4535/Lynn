<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<%
		session.invalidate();
	%>
	
	<script>
			alert("로그아웃이 완료되었습니다");
			location.href = "Madd.do?command=Index";
	</script>

</body>

</html>