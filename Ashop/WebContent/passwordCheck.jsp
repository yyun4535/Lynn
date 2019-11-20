<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form class="row contact_form" action="Qna.do?command=chk"
		method="post" novalidate="novalidate">
		<div class="s_product_text">
			Please, enter your password
			<div class="s_product_text">
				<h5>
					<input type="hidden" name="qnum" value="${qnum}"> <input
						type="hidden" name="pnum" value="${pnum}"> <input
						type="hidden" name="id" value="${id}"> <input
						type="password" name="pwd">
				</h5>
			</div>
		</div>
		<input type="submit" value="Submit">
	</form>
	<a href="javascript:history.back();">Back</a>
</body>

</html>