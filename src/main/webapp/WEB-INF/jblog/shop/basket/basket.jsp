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
            <input type="hidden" id="productSeq_${basket.basketSeq}" name="productSeq" value="${basket.product.productSeq}"> 
            <input type="hidden" class="basketSeq" name="basketSeq" value="${basket.basketSeq}">
            <td><button type="button" class="btn btn-warning btn-updateBasket">수정</button> <button type="button" class="btn btn-danger btn-deleteBasket">삭제</button></td>
            
        </tr>
       
    </c:if>
</c:forEach>
  </table>	
  <h5>장바구니 총 금액 : ${totalPrice } &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;	
</div>
<center>
<button id="btn-buyProduct" class="btn btn-danger">주문하기</button>
</center>
 <script src="/js/basket.js"></script>
<%@include file = "/WEB-INF/jblog/layout/footer.jsp"%>