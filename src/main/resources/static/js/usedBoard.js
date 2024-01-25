let usedBoardObject = {
	init: function() {
		let _this = this;
		$("#btn-insertProduct").on("click", () => {
			_this.insertProduct();

		});
			
	},

	insertProduct: function() {
		alert("글등록 요청됨");
		let insertProductData = {
			usboTitle: $("#usboTitle").val(),
			usboContent: $("#usboContent").val(),
			
		}

		$.ajax({
			type: "POST",
			url: "/insertUsedBoard",
			data: JSON.stringify(insertProductData),
			contentType: "application/json; charset=utf-8"

		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/usedBoardList"    // /auth/getBoardList
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);

		});


	},

	

}
usedBoardObject.init();