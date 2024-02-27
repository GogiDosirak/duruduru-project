<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jblog/layout/header.jsp"%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/js/orderAddressapi.js"></script>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Join</title>
<style>
.container {
	display: flex;
	align-items: center;
	justify-content: center;
}

.login-container {
	background-color: #FFFAF0;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
	width: 700px;
}

.login-container h2 {
	text-align: center;
	margin-bottom: 20px;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
}

.form-group input {
	width: 100%;
	padding: 8px;
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 4px;
}



#duruduru-image {
	width: 270px; /* 원하는 크기로 조절하세요 */
	height: auto;
	
}
</style>
</head>
<body>

	<div class="container">
	
		<div class="login-container">
		<center>
		<h1>결제하기</h1>
			<img id="duruduru-image" src="/images/duruduru.png"
				alt="Duruduru Logo">
				
</center>
<br>
<center>
<h4>총 주문금액 : ${totalPrice - point }</h4>
<input type="hidden" id="orderPrice" name="orderPrice" value="${totalPrice - point }">
결제를 원하시면 '결제하기'버튼을 눌러주세요.
</center>
<br>
   <!-- jQuery -->
    <script
            type="text/javascript"
            src="https://code.jquery.com/jquery-1.12.4.min.js"
    ></script>
    <!-- iamport.payment.js -->
    <script
            type="text/javascript"
            src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"
    ></script>
    <script>
        var IMP = window.IMP;
        IMP.init("imp81100183");

        function requestPay() {
            IMP.request_pay(
                {
                    pg: "html5_inicis.INIBillTst",		//KG이니시스 pg파라미터 값
                    pay_method: "card",		//결제 방법
                    merchant_uid: ${userOrder.orderSeq+100},//주문번호
                    name: "${userOrder.orderName}님 주문건",		//상품 명
                    amount: ${userOrder.orderPrice},				//금액
      				buyer_email: "${userOrder.orderEmail}",
      				buyer_name: "${userOrder.orderName}",
      				buyer_tel: "${userOrder.orderPhonenumber}",
      				buyer_addr: "${userOrder.orderAddress}",
      				buyer_postcode: "${userOrder.orderZipcode}"
     	
                },
                function (rsp) {
      				//rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
                    if (rsp.success) {
                        alert("결제가 완료되었습니다.")
                        location = "/buyComplete";
                    } else {
                        alert("결제에 실패하였습니다." + rsp.error_msg);
                    }
                }
            );
        }
    </script>
			<div class="form-group">
			<input type="hidden" name="orderSeq" id="orderSeq" value=${userOrder.orderSeq }>
			<center>
				<button onclick="requestPay()" class="btn btn-danger">결제하기</button> <button id="btn-deleteOrder" class="btn btn-warning">처음으로</button>
				</center>
				
			</div>
	
		</div>
	</div>

</body>
<script src="/js/order.js"></script>
<script src="/js/coolSMS.js"></script>
<script src="/js/user.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>
</html>