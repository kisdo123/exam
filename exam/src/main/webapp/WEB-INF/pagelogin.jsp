<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="/exam/css/pagelogin.css">
<link rel="stylesheet" href="/exam/css/bootstrap.min.css">
<script type="text/javascript" src="/exam/script/pagejoin.js"></script>
<script type="text/javascript" src="/exam/script/duplicate.js"></script>
</head>
<body class="text-center" oncontextmenu="return false" ondragstart="return false" onselect="return false">
	<div id="login">
		<form action="login.do" method="post">
			<div class="form-group">
				<input type="text" class="form-control" id="inputID" placeholder="ID" name="id" value="${param.id }" minlength="4" required>
			</div>
			<div class="form-group">
				<input type="password" id="inputPW" class="form-control" name="pw" value="${param.pw }" minlength="6" maxlength="20" placeholder="PW" required>
			</div>
			<div class="form-group">
				<input class="btn btn-lg btn-primary btn-block" type="submit" value="로그인">
			</div>
		</form>
		<button id="movejoinbtn">회원가입</button>
	</div>
	<jsp:include page="/popup/pagejoin.jsp"></jsp:include>
</body>
</html>