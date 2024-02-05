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
	
	.form-group button {
		width: 100%; /* 버튼의 넓이를 100%로 설정 */
		padding: 10px;
		box-sizing: border-box;
		background-color: #F5DEB3;
		color: #000000;
		border: none;
		border-radius: 4px;
		cursor: pointer;
		font-size: 14px;
	}
	
	.form-group button:hover {
		background-color: #F5DEB3;
	}
	
	#duruduru-image {
		width: 270px; /* 원하는 크기로 조절하세요 */
		height: auto;
		
	}
	</style>
	</head>
	<body>
	<input type="hidden" name="orderSeq" id="orderSeq" value=${userOrder.orderSeq }>
		<div class="container">
		
			<div class="login-container">
			<center>
			<h1>주문하기</h1>
				<img id="duruduru-image" src="/images/duruduru.png"
					alt="Duruduru Logo">
					
	</center>
	<br>
		<div class="form-group">
		 <table class="table">
	    <tr style="text-align: center;">
	        <th style="vertical-align: middle;">상품이미지</th>
	        <th style="vertical-align: middle;">상품명</th>
	        <th style="vertical-align: middle;">개수</th>
	        <th style="vertical-align: middle;">가격</th>
	        <th style="vertical-align: middle;">총액</th>
	    <c:forEach var="basket" items="${basketList}">
	        <c:if test="${basket.user.userSeq == principal.userSeq}">
	            <tr>	
	                <td style="vertical-align: middle; text-align: center;"><img src="${basket.product.productFilepath}" style="width: 100px; "></td>
	                <td style="vertical-align: middle; text-align: center;">${basket.product.productName}</td>
	                <td style="vertical-align: middle; text-align: center;">${basket.basketProductAmount}</td>
	                <td style="vertical-align: middle; text-align: center;">${basket.product.productPrice}</td>	
	                <td style="vertical-align: middle; text-align: center;">${basket.basketProductPrice}</td>
	                <input type="hidden" id="productSeq_${basket.basketSeq}" name="productSeq" value="${basket.product.productSeq}"> 
	                <input type="hidden" class="basketSeq" name="basketSeq" value="${basket.basketSeq}">
	              
	            </tr>
	        </c:if>
	    </c:forEach>
	</table>
	<center>
	<br>
	<form id="form" action="/usePoint/${principal.userSeq }" method="get">
  <input type="hidden" id="userSeq" name="userSeq" value="${principal.userSeq }">
  <h4>총 주문금액 : ${totalPrice }</h4>
  <input type="hidden" id="orderPrice" name="orderPrice" value="${totalPrice }">
  <br>
  <h5>
    <input type="text" id="point" name="point" style="width: 60px; height: 40px">
    포인트
    <input type="submit" value="사용" style="width: 50px; height: 40px; background-color: orange; color: white;">
  </h5>
  <h5>보유 포인트 : ${principal.point }</h5>
</form>

<script>
  document.getElementById('form').addEventListener('submit', function(event) {
    event.preventDefault(); // 기본 이벤트 동작 방지

    // 사용자가 입력한 값 가져오기
    var point = parseInt(document.getElementById('point').value);
    var userPoint = parseInt('${principal.point}');
    var orderPrice = parseInt('${totalPrice}');

    // 보유 포인트보다 많이 입력된 경우
    if (point > userPoint) {
      alert('보유 포인트보다 많습니다.');
    } 
    // 물건 가격보다 높게 입력된 경우
    else if (point > orderPrice) {
      alert('물건 가격보다 낮게 입력해주세요.');
    } 
    // 유효한 경우
    else {
      // 폼 전송
      this.submit();
    }
  });
</script>
	<br>
				<div class="form-group">
					<label for="orderName">주문 성함</label> <input type="text" id="orderName"
						name="orderName" required>
				</div>
				<div class="form-group">
					<label for="orderPhonenumber">주문 전화번호</label> <input type="text" id="orderPhonenumber"
						name="orderPhonenumber" required>
				</div>
				<div class="form-group">
					<label for="email">이메일</label> <input type="email" id="orderEmail"
						name="oderEmail" required> <small id="emailError"
						class="text-danger"></small>
				</div>
		
					<div class="form-group">
					<label for="orderAddress">주문 주소</label>
					<input class="form-control" style="width: 40%; display: inline;"
						placeholder="우편번호" name="orderZipcode" id="orderZipcode" type="text"
						readonly="readonly">
					<button type="button" class="btn btn-default"
						onclick="execPostCode();">
						<i class="fa fa-search"></i> 우편번호 찾기
					</button>
				</div>
				<div class="form-group">
					<input class="form-control" style="top: 5px;" placeholder="도로명 주소"
						name="orderAddress" id="orderAddress" type="text" readonly="readonly" />
				</div>
				<div class="form-group">
					<input class="form-control" placeholder="주문 상세주소" name="orderAddressDetail"
						id="orderAddressDetail" type="text" />
				</div>
	
				<div class="form-group">
					<label for="orderRequest">주문 요청사항</label> <textarea id="orderRequest" name="orderRequest" style="width: 650px; height: 150px;"></textarea>
				</div>
	
				
	
				<script>
					const emailInput = document.getElementById('orderEmail');
					const emailError = document.getElementById('emailError');
	
					emailInput.addEventListener('input', function(event) {
						if (!emailInput.checkValidity()) {
							emailError.textContent = '올바른 이메일 형식으로 입력해주세요.';
						} else {
							emailError.textContent = '';
						}
					});
				</script>
	
	
				<br>
				<div class="form-group">
				
		
					<button id="btn-insertOrder" onclick="location.href='/goPay'">주문 제출</button>
				</div> 
	
			</div>
		</div>
	
	</body>
	<script src="/js/order.js"></script>
	<script src="/js/coolSMS.js"></script>
	<script src="/js/user.js"></script>
	<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>
	</html>