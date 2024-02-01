let InquiryObject = {
	init: function() {
		let _this = this;
		$("#btn-insertInquiry").on("click", () => {
			_this.insertInquiry();

		}),
				$("#btn-deleteInquiry").on("click", () => {
			_this.deleteInquiry();

		}),
		$("#btn-updateInquiry").on("click", () => {
			_this.updateInquiry();

		});





	},

	insertInquiry: function() {
		let insertInquiryData = {
			inquiryTitle : $("#inquiryTitle").val(),
			inquiryContent : $("#inquiryContent").val()
	
		}

		$.ajax({
			type: "POST",
			url: "/insertInquiry",
			data: JSON.stringify(insertInquiryData),
			contentType: "application/json; charset=utf-8"

		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/inquiry"    // /auth/getBoardList
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);

		});


	},
		deleteInquiry: function() {
		let deleteInquiryData = {
			inquirySeq : $("#inquirySeq").val()
	
		}

		$.ajax({
			type: "DELETE",
			url: "/deleteInquiry/" + deleteInquiryData.inquirySeq,
			data: JSON.stringify(deleteInquiryData),
			contentType: "application/json; charset=utf-8"

		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/inquiry"    
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);

		});


	},
	
	updateInquiry: function() {
		let updateInquiryData = {
			inquirySeq : $("#inquirySeq").val(),
			inquiryTitle : $("#inquiryTitle").val(),
			inquiryContent : $("#inquiryContent").val()
	
		}

		$.ajax({
			type: "PUT", //PutMapping이므로 Update가 아니라 Put을 넣어줘야함!!
			url: "/updateInquiry/" + updateInquiryData.inquirySeq,
			data: JSON.stringify(updateInquiryData),
			contentType: "application/json; charset=utf-8"

		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/inquiry"    // /auth/getBoardList
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);

		});

	},
	
	}
	InquiryObject.init();