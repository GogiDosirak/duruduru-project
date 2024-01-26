<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "/WEB-INF/jblog/layout/header.jsp"%>
	<div class="container mt-3">

  <h2>장바구니</h2>         
  <table class="table">
      <tr>
        <th>상품이미지</th>
        <th>상품명</th>
        <th>개수</th>
        <th>가격</th>
        <th>총액</th>
      </tr>
      <c:forEach var="basket" items="${basketList}">
    <c:if test="${basket.user.userSeq == principal.userSeq}">
        <tr>
            <td><img src="${basket.product.productFilepath}" style="width: 50px; height: 50px;"></td>
            <td>${basket.product.productName}</td>
            <td><input type="text" id="basketProductAmount_${basket.basketSeq}" name="basketProductAmount" value="${basket.basketProductAmount}" style="width: 30px;"></td>
            <td>${basket.product.productPrice}</td>
            <td>${basket.basketProductPrice}</td>
            <input type="hidden" id="productSeq_${basket.basketSeq}" name="productSeq" value="${basket.product.productSeq}"> <!-- js에서 문자열을 동적으로 생성하는 방법, for문에서 계속 변수가 바뀌는데 변수명이 같다면 js에서 못불러옴. 그래서 _${basket.basketSeq}해줘서 basketSeq에 따라 productSeq_1 이런식으로 동적으로 변수를 지정해준 것 일반적으로 for문에서 돌면서 생성되는 HTML 엘리먼트들의 ID나 클래스는 중복되지 않도록 고유한 값을 가져야 합니다. 그렇지 않으면 JavaScript나 CSS에서 이 엘리먼트들을 식별하기 어렵습니다.

따라서 basketProductAmount_${basket.basketSeq}와 같은 형태로 동적으로 변수명을 지정하는 이유는 각각의 상품에 대한 고유한 식별자를 부여하기 위해서입니다. 이를 통해 JavaScript에서 각 상품에 대한 조작을 수월하게 할 수 있습니다.

만약 중복된 ID나 클래스를 사용한다면, JavaScript나 CSS에서 원하는 대로 특정 엘리먼트를 선택하기 어려워집니다. 따라서 동적으로 생성되는 엘리먼트에는 서로 다른 고유한 식별자를 부여하는 것이 좋습니다.-->
            <input type="hidden" class="basketSeq" name="basketSeq" value="${basket.basketSeq}">
            <td><button type="button" class="btn btn-warning btn-updateBasket">수정</button></td>
        </tr>
        <script src="/js/basket.js"></script>
    </c:if>
</c:forEach>
  </table>	
  <h5>장바구니 총 금액 : ${totalPrice } &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;	
</div>
<center>
<button id="btn-buyProduct" class="btn btn-danger">주문하기</button>
</center>

<%@include file = "/WEB-INF/jblog/layout/footer.jsp"%>