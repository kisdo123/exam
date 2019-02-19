<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${loginFilter }">
		<script>
			alert("로그인이 필요한 서비스입니다.");
			location.href = "/exam/loginForm.do";
		</script>
	</c:if>

	<c:if test="${logging }">
		<script>
			alert("로그인중 입니다");
			location.href = "/exam/moveCommute.do";
		</script>
	</c:if>

	<c:if test="${userExist }">
		<script>
			alert("이미 존재하는 유저입니다");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${userNotFound }">
		<script>
			alert("유저를 찾을 수 없습니다");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${NotFoundCommtues }">
		<script>
			alert("출근 목록을 찾을 수 없습니다");
			location.href = '${ret }';
		</script>
	</c:if>
	<c:if test="${PasswordNotMatch }">
		<script>
			alert("로그인에 실패하였습니다. 아이디와 비밀번호를 확인해 주세요");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${registerFail }">
		<script>
			alert("회원가입에 실패하였습니다");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${FailInsertCommute }">
		<script>
			alert("출근처리에 실패하였습니다.");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${AlreadyAttend }">
		<script>
			alert("이미 출근처리 되었습니다.");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${FailUpdateCommute }">
		<script>
			alert("퇴근처리에 실패하였습니다.");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${FailAttendCheck }">
		<script>
			alert("출근처리가 되지않았습니다 출근처리 해주세요.");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${CalendarException }">
		<script>
			alert("캘린더 조회 실패");
			location.href = '${ret }';
		</script>
	</c:if>

</body>
</html>