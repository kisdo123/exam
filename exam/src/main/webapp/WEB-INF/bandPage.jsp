<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/exam/script/bendPage.js"></script>
</head>
<body>

	<c:forEach var="admins" items="${admins}">
		<c:if test="${!empty admins}">
			<input type="hidden" class="memberName" value="${admins.getName()}">
			<input type="hidden" class="attend" value="${admins.getAttend()}">
			<input type="hidden" class="vacation" value="${admins.getVacation()}">
			<input type="hidden" class="textData" value="${admins.getTextData()}">
		</c:if>
	</c:forEach>
	
	<div>
		<form id="writeForm" onsubmit="writePosts()">
			<textarea name="content" id="content" cols="150" rows="30" readonly></textarea>
			<div>
				<input type="submit" value="글쓰기">
			</div>
		</form>
	</div>
</body>
</html>