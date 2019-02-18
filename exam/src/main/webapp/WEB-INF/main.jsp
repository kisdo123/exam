<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/exam/css/main.css">
<script type="text/javascript" src="/exam/script/main.js"></script>
</head>
<body>
	<div>
		이름 :
		<input type="text" value="${loginUser.name }" readonly>
		<br>
		아이디 :
		<input type="text" value="${loginUser.id }" name="id" readonly>
		<div id="formdiv">
			<form action="attendForm.do">
				<input type="hidden" value=${loginUser.userNo }>
				<input type="submit" value="출근">
			</form>

			<form action="leaveForm.do">
				<input type="submit" value="퇴근">
			</form>

			<form action="logout.do">
				<input type="submit" value="로그아웃">
			</form>
		</div>
	</div>
	
	<c:forEach var="commutes" items="${commutes}">
		<c:if test="${!empty commutes}">
			<input type="hidden" class="event" value="${commutes.getAttend()}">
		</c:if>
	</c:forEach>

	<div id="calendardiv">
		<table id="calendar">
			<tr>
				<td id="prevCalendar">&lt;</td>
				<td id="tbCalendarYM" colspan="5">yyyy년 m월</td>
				<td id="nextCalendar">&gt;</td>
			</tr>
			<tr id="daytr">
				<td>일</td>
				<td>월</td>
				<td>화</td>
				<td>수</td>
				<td>목</td>
				<td>금</td>
				<td>토</td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		buildCalendar();
	</script>

</body>
</html>