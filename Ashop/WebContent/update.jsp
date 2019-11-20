<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-lg-6">
		<div class="review_box">
			<h4>Please, Enter a change QnA contents.</h4>
			<form class="row contact_form" action="Qna.do?command=Update"
				method="post">
				<input type=hidden name="pnum" value="${pnum}"> <input
					type=hidden name="qnum" value="${qnum}"> <input type=hidden
					name="id" value="${id}">

				<div class="col-md-12">
					<div class="form-group">
						<div>
							<h5>Title :</h5>
						</div>
						<input type="text" class="form-control" name="title"
							placeholder="Title" />
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<div>
							<h5>Content :</h5>
						</div>
						<textarea class="form-control" name="content" rows="4"
							placeholder="Content"></textarea>
					</div>
				</div>
				<div class="col-md-12 text-right">
					<input id="cancelBtn" type="button" value="취소"
						onclick="window.close()">
				</div>
				<div class="col-md-12 text-right">
					<input type="submit" value="수정하기" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>