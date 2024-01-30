let OrderObject = {
	init: function() {
		let _this = this;
		$("#btn-insertOrder").on("click", () => {
			_this.insertOrder();

		}),
				$("#btn-deleteOrder").on("click", () => {
			_this.deleteOrder();

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
			location = "/goPay";  
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);

		});


	},
	deleteOrder: function() {
		let deleteOrderData = {
			orderSeq: $("#orderSeq").val()

		}

		$.ajax({
			type: "DELETE",
			url: "/deleteOrder/" + deleteOrderData.orderSeq ,
			data: JSON.stringify(deleteOrderData),
			contentType: "application/json; charset=utf-8"

		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/basket"  
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);

		});


	}
	}
	OrderObject.init();
	