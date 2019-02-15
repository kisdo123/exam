<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="popupbackground"></div>
<div id="popup">
	<h1 id="title">회원가입</h1>
	<form id="FormBYinput" action="join.do" method="post">
		<input type="text" class="input" name="id" placeholder="ID" required>
		<br>
		<input type="password" class="input" name="pw" placeholder="PW" required>
		<br>
		<input type="text" class="input" name="name" placeholder="Name" required>
		<br>

		<input type="hidden" name="admin">
		<input type="submit" id="btnjoin" class="btn btn-lg btn-primary btn-block" value="회원가입">
	</form>
</div>
