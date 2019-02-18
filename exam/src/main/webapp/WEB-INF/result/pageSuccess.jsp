<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${attend }">
		<script>
			alert("출근처리 되었습니다. \n${attendTime}");
			location.href = "/exam/moveCommute.do";
		</script>
	</c:if>
	<c:if test="${leave }">
		<script>
			alert("퇴근처리 되었습니다.");
			location.href = "/exam/moveCommute.do";
		</script>
	</c:if>
	<c:if test="${join }">
		<script>
			alert("회원가입 되었습니다.");
			location.href = "/exam/loginForm.do";
		</script>
	</c:if>
	<c:if test="${login }">
		<script>
			location.href = "/exam/moveCommute.do";
		</script>
	</c:if>
</body>
</html>