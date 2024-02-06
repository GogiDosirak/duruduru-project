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
    // 필수 입력 필드가 비어 있는지 확인
    if ($("#orderName").val() === '' || $("#orderPhonenumber").val() === '' || $("#orderEmail").val() === '' || $("#orderAddress").val() === '' || $("#orderAddressDetail").val() === '' || $("#orderZipcode").val() === '') {
        alert('주문 성함, 주문 전화번호, 이메일, 주문 주소 등 필수 정보를 모두 입력해주세요.');
        return; // 필수 정보가 입력되지 않았을 경우 함수 종료
    }

    // 주문 정보 객체 생성
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
    };

    // 주문 정보를 서버에 전송하는 AJAX 요청
    $.ajax({
        type: "POST",
        url: "/insertOrder",
        data: JSON.stringify(insertOrderData),
        contentType: "application/json; charset=utf-8"
    }).done(function(response) {
        let message = response["data"];
        alert(message);
        location = "/goPay"; // 주문 삽입 성공 시 결제 페이지로 이동
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
	