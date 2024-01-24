<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp" %>
<title>${getProduct.productName}- duruduru</title>
<style>
<title>${getProduct.productName} - duruduru</title>
    <style>
        body {
            font-family: 'Jua', sans-serif;
            background-color: #f6f5f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1000px;
            margin: 2rem auto;
            background-color: #FFFAF0;
            padding: 1.5rem;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #333;
        }

        hr {
            border-top: 2px solid #ccc;
            margin: 1.5rem 0;
        }

        .details {
            display: flex;
            flex-direction: column;
        }

        .details label {
            font-size: 1.2rem;
            color: #555;
            margin-bottom: 0.5rem;
        }

        .details label:first-child {
            font-size: 1.5rem;
            color: #333;
            margin-bottom: 1rem;
        }

        .logo img {
            max-width: 200px;
            height: auto;
        }

        .center-align {
            text-align: center;
        }

img {
	max-width: 100%;
	height: auto;
} 
</style>
</head>
<body>
    <div class="logo center-align">
        <a href="/">
            <img src="/images/duruduru.png" alt="로고 이미지">
        </a>
    </div>
	<div class="logo center-align">
	</div>
	<center>
	
	<hr>
	<h2 class="center-align">두루두루 쇼핑몰</h2>
	</center>
	<div class="container">
		<h2>${getProduct.productName}</h2>
		<hr>
		<div class="details">
			<label for="productPrice">가격: ${getProduct.productPrice} 원</label>
			<label for="productStock">재고: ${getProduct.productStock} 개</label>
			<label for="productContent">${getProduct.productContent}</label>
		</div>

	</div>
	<center>
		<div>
			<a href="/mall"><h5>뒤로가기</h5></a>
		</div>
		<br>
		<div>
			<input type="hidden" id="productSeq" name="productSeq"
				value="${getProduct.productSeq }">
			<c:if test="${not empty principal and principal.role eq 'ADMIN'}">
				<button type="button"
					onclick="location.href='/updateProduct/${getProduct.productSeq}'"
					class="btn btn-warning">상품 수정하기</button>
				<button type="button" id="btn-deleteProduct" class="btn btn-danger">상품
					삭제하기</button>
				<script src="/js/shop.js"></script>
			</c:if>
	</center>
	</div>
	</body>
<%@ include file="/WEB-INF/jblog/layout/footer.jsp" %>
</html>