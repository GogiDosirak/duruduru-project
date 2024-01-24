
let smsObject = {
	init: function() {

		$("#btn-requestSMS").click(() => this.request());
		$("#btn-checknumber").click(() => this.check());
		$("#close-modal1").click(() => this.modal());
		$("#close-modal2").click(() => this.modal());

	},

	request: function() {
		alert("번호 요청");
		let phonenumber = $("#phonenumber").val();

		$.post("/send-one", { phonenumber })
			.done(response => {
				console.log(response);
				alert("메시지 전송");
			})
			.fail(error => {
				alert("에러 발생: " + error);
			});
	},

	check: function() {
		alert("인증번호 확인");
		let checknumber = $("#checknumber").val();
		let phoneNumberInput = document.getElementById("phonenumber");

		$.post("/checkSMS", { checknumber })
			.done(response => {
				console.log(response.data);
				alert(response.data);
				if (response.status != 200) {
					phoneNumberInput.value = "";
				} else {
					
					document.getElementById("statusText").innerHTML = "인증이 되었습니다";
					document.getElementById("statusText").style.color = "green";
					$('#myModal').modal('hide');
				}
			})
			.fail(error => {
				alert("에러 발생: " + error);
			});
	},

	modal: function() {
		let phoneNumberInput = document.getElementById("phonenumber");
		phoneNumberInput.value = "";
	}
};

smsObject.init();