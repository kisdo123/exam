<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/exam/css/pagelogin.css">
<link rel="stylesheet" href="/exam/css/bootstrap.min">
</head>
<body class="text-center" oncontextmenu="return false" ondragstart="return false" onselect="return false">
	<form action="login.do" method="post" class="form-signin">
		<input type="text" id="inputID" class="form-control" placeholder="ID" name="id" value="${param.id }" required autofocus>
		<input type="password" id="inputPW" class="form-control" name="pw" value="${param.pw }" placeholder="PW" required>
		<div id="buttonandsubmit">
			<input class="btn btn-lg btn-primary btn-block" type="submit" value="로그인">
			<input class="btn btn-lg btn-primary btn-block" id="signinbtn" type="button" value="회원가입">
		</div>
	</form>
</body>
</html>