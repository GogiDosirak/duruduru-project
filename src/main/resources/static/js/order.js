let OrderObject = {
	init: function() {
		let _this = this;
		$("#btn-insertOrder").on("click", () => {
			_this.insertOrder();

		});
			



	},

	insertOrder: function() {
		let insertOrderData = {
			orderSeq: $("#orderSeq").val(),
			orderName: $("#orderName").val(),
			orderPhonenumber: $("#orderPhonenumber").val(),
			orderEmail: $("#orderEmail").val(),
			orderAddress: $("#orderAddress").val(),
			orderAddressDetail: $("#orderAddressDetail").val(),
			orderRequest: $("#orderRequest").val(),
			orderZipcode: $("#orderZipcode").val(),
			orderPrice: $("#orderPrice").val()
		}

		$.ajax({
			type: "POST",
			url: "/insertOrder",
			data: JSON.stringify(insertOrderData),
			contentType: "application/json; charset=utf-8"

		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/pay/" + insertOrderData.orderSeq    // /auth/getBoardList
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);

		});


	}
	}
	OrderObject.init();
	