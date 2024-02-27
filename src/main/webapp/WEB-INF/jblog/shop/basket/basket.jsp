<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "/WEB-INF/jblog/layout/header.jsp"%>
	<div class="container mt-3">

  <h2>장바구니</h2>         
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
                <td style="vertical-align: middle; text-align: center;"><input type="text" id="basketProductAmount_${basket.basketSeq}" name="basketProductAmount" value="${basket.basketProductAmount}" style="width: 30px;"></td>
                <td style="vertical-align: middle; text-align: center;">${basket.product.productPrice}</td>	
                <td style="vertical-align: middle; text-align: center;">${basket.basketProductPrice}</td>
                <input type="hidden" id="productSeq_${basket.basketSeq}" name="productSeq" value="${basket.product.productSeq}"> 
                <input type="hidden" class="basketSeq" name="basketSeq" value="${basket.basketSeq}">
                <td style="vertical-align: middle; text-align: center;"><button type="button" class="btn btn-warning btn-updateBasket">수정</button> <button type="button" class="btn btn-danger btn-deleteBasket">삭제</button></td>
            </tr>
        </c:if>
    </c:forEach>
</table>	
  <h4>장바구니 총 금액 : ${totalPrice }</h4> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;	
</div>
<center>
<input type="hidden" id="userSeq" name="userSeq" value=${principal.userSeq }>
<c:if test="${totalPrice != 0 }">
<button onclick="location.href='/order/${principal.userSeq}'" class="btn btn-danger">주문하기</button>
</c:if>
</center>
 <script src="/js/basket.js"></script>
<%@include file = "/WEB-INF/jblog/layout/footer.jsp"%>