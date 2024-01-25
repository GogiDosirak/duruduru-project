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
      <c:forEach var="basket" items="${basketList }">
      <c:if test="${basket.user.userSeq == principal.userSeq}">
      <tr>
      <td><img src="${basket.product.productFilepath }"style="width: 40px; height: 40px;"></td>
      <td>${basket.product.productName }</td>
      <td>${basket.basketProductAmount }</td>
      <td>${basket.product.productPrice }</td>
      <td>${basket.basketPrice }</td>
      </tr>
      </c:if>
      
      </c:forEach>
   


  </table>
</div>

<%@include file = "/WEB-INF/jblog/layout/footer.jsp"%>