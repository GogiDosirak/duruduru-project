let NoticeObject = {
	init: function() {
		let _this = this;
		$("#btn-insertNotice").on("click", () => {
			_this.insertNotice();

		}),
				$("#btn-deleteNotice").on("click", () => {
			_this.deleteNotice();

		}),
		$("#btn-updateNotice").on("click", () => {
			_this.updateNotice();

		});





	},

	insertNotice: function() {
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
		deleteNotice: function() {
		let deleteNoticeData = {
			noticeSeq : $("#noticeSeq").val()
	
		}

		$.ajax({
			type: "DELETE",
			url: "/deleteNotice/" + deleteNoticeData.noticeSeq,
			data: JSON.stringify(deleteNoticeData),
			contentType: "application/json; charset=utf-8"

		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/notice"    
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);

		});


	},
	
	updateNotice: function() {
		let updateNoticeData = {
			noticeSeq : $("#noticeSeq").val(),
			noticeTitle : $("#noticeTitle").val(),
			noticeContent : $("#noticeContent").val()
	
		}

		$.ajax({
			type: "PUT", //PutMapping이므로 Update가 아니라 Put을 넣어줘야함!!
			url: "/updateNotice/" + updateNoticeData.noticeSeq,
			data: JSON.stringify(updateNoticeData),
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