let BasketObject = {
    init: function() {
        let _this = this;

        // 이벤트 위임을 사용하여 document에서 버튼 클릭 이벤트를 감지
        // 아래에 변수가 동적으로 생성이 되는데, 이를 효율적으로 사용하기 위해선 이벤트 위임을 사용해야함
        // document - 이벤트를 캐치할 최상위 엘리먼트
        // document 아래에 있는 모든 요소중에서 .btn-updateBasket 클래스를 가진 요소가 클릭됐을 때 function을 실행한다는 뜻
        $(document).on("click", ".btn-updateBasket", function() {
            let basketSeq = $(this).closest("tr").find(".basketSeq").val();
            _this.updateBasket(basketSeq);
        });

    },

    updateBasket: function(basketSeq) {
        alert("상품 개수 수정");
        let updateBasketData = {
            productSeq: $("#productSeq_" + basketSeq).val(),
            basketProductAmount: $("#basketProductAmount_" + basketSeq).val(), // 동적으로 변한 변수들을 받아서 저장
            basketSeq: basketSeq
        }

        console.log(updateBasketData.productSeq);
		console.log(updateBasketData.basketProductAmount);
		// 객체를 폼 데이터 형태로 직렬화
		let formData = $.param(updateBasketData);


        $.ajax({
            type: "PUT",
            url: "/updateBasket",
            data: formData,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8"
        }).done(function(response) {
            alert("수정 완료");
            location = "/basket";
        }).fail(function(error) {
            alert("에러 발생");
            location = "/basket";
        });
    },
}

BasketObject.init();