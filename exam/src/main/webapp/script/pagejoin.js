var idDup = false;
$(function() {
	// 회원가입 나타나기
	$("#movejoinbtn").click(function() {
		$("#popupbackground").css("display", "block")
		$("#popup").css("display", "block");
	})

	// 아이디
	$("#id").blur(function() {
		if ($("#id").val() == "") {
			$("#id_msg").html("아이디를 입력하세요.").css("color", "red")
			$("#id").css("border-color", "red");
			return false;
		} else {
			$("#id_msg").html("");
			$("#id").css("border-color", "gray");
		}
	});

	// id 자리수
	$("#id").blur(function() {
		if ($("#id").val() != "") {
			if ($("#id").val().length < 4) {
				$("#id_msg").html("4자이상 입력해주세요.").css("color", "red")
				$("#id").css("border-color", "red");
				return false;
			} else {
				$("#id_msg").html("");
				$("#id").css("border-color", "gray");
			}
		}
	});

	// 비밀번호
	$("#pw").blur(function() {
		if ($("#pw").val() == "") {
			$("#pw_msg").html("비밀번호를 입력하세요.").css("color", "red")
			$("#pw").css("border-color", "red");
			return false;
		}
	});
	// 비밀번호 자리수
	$("#pw").blur(function() {
		if ($("#pw").val() != "") {
			if ($("#pw").val().length < 6) {
				$("#pw_msg").html("6~20자로 입력해주세요.").css("color", "red")
				$("#pw").css("border-color", "red");
				return false;
			} else {
				$("#pw_msg").html("");
				$("#pw").css("border-color", "gray");
			}
		}
	});

	// 비밀번호 확인
	$("#pwCheck").blur(function() {
		if ($("#pwCheck").val() == "") {
			$("#pwCheck_msg").html("비밀번호를 입력하세요.").css("color", "red")
			$("#pwCheck").css("border-color", "red");
			return false;
		}
	});

	// 비밀번호 체크
	$("#pwCheck").blur(function() {
		if ($("#pwCheck").val() != "") {
			if ($("#pw").val() != $("#pwCheck").val()) {
				$("#pwCheck_msg").html("입력하신 비밀번호가 다릅니다.").css("color", "red")
				$("#pwCheck").css("border-color", "red");
				return false;
			} else {
				$("#pwCheck_msg").html("");
				$("#pwCheck").css("border-color", "gray");
			}
		}
	});

	// 이름
	$("#name").blur(function() {
		if ($("#name").val() == "") {
			$("#name_msg").html("이름을 입력하세요.").css("color", "red")
			$("#name").css("border-color", "red");
			return false;
		} else {
			$("#name_msg").html("");
			$("#name").css("border-color", "gray");
		}
	});

	$("#idDuplicate").click(function() {
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
	});

});

function check() {
	var flag = true;

	if ($("#id").val() == "") {
		$("#id_msg").html("아이디를 입력하세요.").css("color", "red")
		$("#id").css("border-color", "red");
		flag = false;
	}
	if ($("#pw").val() == "") {
		$("#pw_msg").html("비밀번호를 입력하세요.").css("color", "red")
		$("#pw").css("border-color", "red");
		flag = false;
	}
	if ($("#pwCheck").val() == "") {
		$("#pwCheck_msg").html("비밀번호를 입력하세요.").css("color", "red")
		$("#pwCheck").css("border-color", "red");
		flag = false;
	}
	if ($("#pwCheck").val() != "") {
		if ($("#pw").val() != $("#pwCheck").val()) {
			$("#pwCheck_msg").html("입력하신 비밀번호가 다릅니다.").css("color", "red")
			$("#pwCheck").css("border-color", "red");
			flag = false;
		} else {
			$("#pwCheck_msg").html("");
			$("#pwCheck").css("border-color", "gray");
		}
	}
	if ($("#name").val() == "") {
		$("#name_msg").html("이름을 입력하세요.").css("color", "red")
		$("#name").css("border-color", "red");
		flag = false;
	}
	if (flag) {
		if (idDup) {
			$("#form").submit();
		} else {
			alert("아이디 중복체크를 해주세요");
		}

	}
}