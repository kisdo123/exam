function idDuplicate() {
	if ($("#id").val() == '') {
		alert("ID를 입력하세요");
		return;
	}
	var id = $("#id").val();

	$.ajax({
		type : "POST",
		url : "idDuplication.do",
		data : {
			"id" : id
		},
		error : function(error) {

		},
		success : function(result) {
			if (result === "false") {
				$("#id_msg").text('사용 가능한 아이디입니다.');
				idDup = true;
			} else if (result === "true") {
				$("#id_msg").html('이미 사용중인 아이디 입니다.');
			}
		}
	});
}