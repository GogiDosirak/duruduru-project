let NoticeObject = {
	init: function() {
		let _this = this;
		$("#btn-insertNotice").on("click", () => {
			_this.insertNotice();

		});





	},

	insertNotice: function() {
		alert("공지사항 등록 요청됨");
		let insertNoticeData = {
			noticeTitle : $("#noticeTitle").val(),
			noticeContent : $("#noticeContent").val()
	
		}

		$.ajax({
			type: "POST",
			url: "/insertNotice",
			data: JSON.stringify(insertNoticeData),
			contentType: "application/json; charset=utf-8"

		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/notice"    // /auth/getBoardList
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);

		});


	},
	}
	NoticeObject.init();