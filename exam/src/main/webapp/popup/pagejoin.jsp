<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="popupbackground"></div>
<div id="popup">
	<h1 id="title">회원가입</h1>
	<form id="FormBYinput" action="join.do" method="post" name="form" onsubmit="return false">
		<div class="divheight">
			<input type="text" class=".col-xs-6 .col-sm-4 margin-left joininput" name="id" placeholder="ID" id="id" minlength="4" autofocus>
			<button class=".col-md-4" id="idDuplicate">중복확인</button>
			<input type="hidden" name="idDuplication" value="idUncheck">
			<p id="id_msg" class="margin-left"></p>
		</div>
		<div class="divheight">
			<input type="password" class=".col-xs-6 .col-sm-4 margin-left joininput" name="pw" placeholder="PW" id="pw" minlength="6" maxlength="20">
			<p id="pw_msg" class="margin-left"></p>
		</div>
		<div class="divheight">
			<input type="password" class=".col-xs-6 .col-sm-4 margin-left joininput" name="pwCheck" placeholder="pwCheck" minlength="6" maxlength="20" id="pwCheck">
			<p id="pwCheck_msg" class="margin-left"></p>
		</div>
		<div class="divheight">
			<input type="text" class=".col-xs-6 .col-sm-4 margin-left joininput" name="name" placeholder="Name" id="name">
			<p id="name_msg" class="margin-left"></p>
			<br>
		</div>
		<input type="hidden" name="admin">
		<input type="submit" id="btnjoin" onclick="check()" class="btn btn-lg btn-primary btn-block" value="회원가입">
	</form>
</div>
