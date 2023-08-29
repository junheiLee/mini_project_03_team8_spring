function go_save() {
	if (document.formm.id.value == "") {
		alert("아이디를 입력하여 주세요.");
		document.formm.id.focus();
	} else if (document.formm.id.value != document.formm.reid.value) {
		alert("아이디 중복확인을 해주세요.");
		document.formm.id.focus();
	} else if (document.formm.pwd.value == "") {
		alert("비밀번호를 입력해 주세요.");
		document.formm.pwd.focus();
	} else if ((document.formm.pwd.value != document.formm.pwdCheck.value)) {
		alert("비밀번호가 일치하지 않습니다.");
		document.formm.pwd.focus();
	} else if (document.formm.name.value == "") {
		alert("이름을 입력해 주세요.");
		document.formm.name.focus();
	} else if (document.formm.email.value == "") {
		alert("이메일을 입력해 주세요.");
		document.formm.email.focus();
	} else {
		document.formm.action = contextPath + "/member/join_pro";
		document.formm.submit();
	}
}

function idcheck() {
	var id = document.formm.id.value;
	
	if (id.trim() === "") {
		alert('아이디를 입력하여 주십시오.');
		document.formm.id.focus();
		return;
	}

	$.ajax({
        url: contextPath + '/api/member/checkId',
        method: 'POST',
        data: { id: id },
        success: function(response) {
            if (response === 'available') {
                $("#idAvailabilityMessage").text("사용 가능한 아이디입니다.").css("color", "blue");
                document.formm.reid.value = id;
            } else {
                $("#idAvailabilityMessage").text("사용 불가능한 아이디입니다.").css("color", "red");
            }
        },
        error : function() {
			alert("서버와 통신 중 오류가 발생했습니다.");
		}
    });
}

function post_zip() {
	var url = contextPath + "/member/find_zip_num";
	window
			.open(
					url,
					"_blank_1",
					"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=550, height=300, top=300, left=300, ");
}

function go_next() {
	if (document.formm.okon1[0].checked == true) {
		document.formm.submit();
	} else if (document.formm.okon1[1].checked == true) {
		alert('약관에 동의하셔야만 합니다.');
	}
}

function findMemberId() {
	var name = document.findId.name.value;
	var email = document.findId.email.value;

	if (name === "" || email === "") {
		alert("이름과 이메일을 모두 입력해 주세요.");
		return;
	}
	
	var formData = {
			name: name,
			email: email
    };

	$.ajax({
		type : "POST",
		url : contextPath + "/api/member/find_member_id",
		data: JSON.stringify(formData),
        contentType: 'application/json; charset=utf-8',
		success : function(foundId) {
			if (foundId === "") {
				alert("해당하는 아이디가 없습니다.");
			} else {
				alert("찾은 아이디: " + foundId);
				window.location.href = contextPath + "/member/login";
			}
		},
		error : function() {
			alert("서버와 통신 중 오류가 발생했습니다.");
		}
	});
}

function findPassword() {
	var id = document.findPW.memberId.value;
	var name = document.findPW.name.value;
	var email = document.findPW.email.value;

	if (id === "" || name === "" || email === "") {
		alert("아이디, 이름, 이메일을 모두 입력해 주세요.");
		return;
	}
	
	var formData = {
			id: id,
			name: name,
			email: email
    };

	$.ajax({
		type : "POST",
		url : contextPath + "/api/member/find_member_password",
		data: JSON.stringify(formData),
        contentType: 'application/json; charset=utf-8',
		success : function(foundPassword) {
			if (foundPassword === "") {
				alert("해당하는 비밀번호가 없습니다.");
			} else {
				alert("찾은 비밀번호: " + foundPassword);
				window.location.href = contextPath + "/member/login";
			}
		},
		error : function() {
			alert("서버와 통신 중 오류가 발생했습니다.");
		}
	});
}