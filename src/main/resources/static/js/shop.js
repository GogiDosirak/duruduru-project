let ProductObject = {
	init: function() {
		let _this = this;
		$("#btn-insertProduct").on("click", () => {
			_this.insertProduct();

		}),
			$("#btn-deleteProduct").on("click", () => {
				_this.deleteProduct();

			}),

			$("#btn-insertBasket").on("click", () => {
				_this.insertBasket();

			});


	},

	insertProduct: function() {
		alert("글등록 요청됨");
		let insertProductData = {
			productName: $("#productName").val(),
			productContent: $("#productContent").val(),
			productPrice: $("#productPrice").val(),
			productStock: $("#productStock").val()
		}

		$.ajax({
			type: "POST",
			url: "/insertProduct",
			data: JSON.stringify(insertProductData),
			contentType: "application/json; charset=utf-8"

		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/mall"    // /auth/getBoardList
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);

		});


	},

	deleteProduct: function() {
		alert("글삭제 요청됨");
		let deleteProductData = {
			productSeq: $("#productSeq").val()
		};
		$.ajax({
			type: "DELETE",
			url: "/deleteProduct/" + deleteProductData.productSeq,
			data: JSON.stringify(deleteProductData),
			contentType: "application/json; charset=utf-8"

		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/mall"
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);

		});


	},

	insertBasket: function() {
		alert("장바구니 추가 완료");


		let insertBasketData = {
			productAmount: $("#productAmount").val(),
			productSeq: $("#productSeq").val(),
			userSeq: $("#userSeq").val()
		}
		// 객체를 폼 데이터 형태로 직렬화
		let formData = $.param(insertBasketData);

		$.ajax({
			type: "POST",
			url: "/insertBasket",
			data: formData, // 직렬화한 데이터를 전송
			contentType: "application/x-www-form-urlencoded; charset=UTF-8" // 폼 데이터 전송을 위한 contentType
		}).done(function(response) {
			alert("완료");
			location = "/basket";	
		}).fail(function() {
			alert("에러 발생");
			location = "/basket";
		});
	},

}
ProductObject.init();