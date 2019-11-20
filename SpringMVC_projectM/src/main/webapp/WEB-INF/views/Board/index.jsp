<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<link rel="stylesheet" href="/css/bootstrap.css">

</head>
<body>
	<div class="">
		<h2>Login</h2>
		<form action="LoginPro" method="post">
			ID: <input type="text" name="id"> PassWord : <input
				type="password" name="pwd"> <input type="submit"
				value="Login">
		</form>
		<a href="/love/Join">Join </a> <a href="/love/List">List</a>
	</div>
</body>
</html>