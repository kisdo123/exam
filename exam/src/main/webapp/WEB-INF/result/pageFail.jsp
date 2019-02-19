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
			alert("�α����� �ʿ��� �����Դϴ�.");
			location.href = "/exam/loginForm.do";
		</script>
	</c:if>

	<c:if test="${logging }">
		<script>
			alert("�α����� �Դϴ�");
			location.href = "/exam/moveCommute.do";
		</script>
	</c:if>

	<c:if test="${userExist }">
		<script>
			alert("�̹� �����ϴ� �����Դϴ�");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${userNotFound }">
		<script>
			alert("������ ã�� �� �����ϴ�");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${NotFoundCommtues }">
		<script>
			alert("��� ����� ã�� �� �����ϴ�");
			location.href = '${ret }';
		</script>
	</c:if>
	<c:if test="${PasswordNotMatch }">
		<script>
			alert("�α��ο� �����Ͽ����ϴ�. ���̵�� ��й�ȣ�� Ȯ���� �ּ���");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${registerFail }">
		<script>
			alert("ȸ�����Կ� �����Ͽ����ϴ�");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${FailInsertCommute }">
		<script>
			alert("���ó���� �����Ͽ����ϴ�.");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${AlreadyAttend }">
		<script>
			alert("�̹� ���ó�� �Ǿ����ϴ�.");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${FailUpdateCommute }">
		<script>
			alert("���ó���� �����Ͽ����ϴ�.");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${FailAttendCheck }">
		<script>
			alert("���ó���� �����ʾҽ��ϴ� ���ó�� ���ּ���.");
			location.href = '${ret }';
		</script>
	</c:if>

	<c:if test="${CalendarException }">
		<script>
			alert("Ķ���� ��ȸ ����");
			location.href = '${ret }';
		</script>
	</c:if>

</body>
</html>