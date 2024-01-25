<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>

<div class="container mt-3">
		<a href="/mall"><h2>두루두루 쇼핑몰</h2></a>

	<style>
.product-image {
	width: 300px;
	height: 300px;
	object-fit: cover;
}
</style>
	   
<div id="searchForm">
    <form action="/mall/search" method="GET" class="form-inline">
        <input type="text" name="keyword" class="form-control-sm" id="search" placeholder="상품명">
        <button class="btn btn-warning bi bi-search" id="searchButton">검색</button>
    </form>
</div>
<br>
	<div class="container-fluid mt-3">
		<div class="row">
			<c:forEach var="productItem" items="${productSearchList.content}">
				<div class="col-md-3 mb-4">
					<div
						class="product-card image-zoom-effect link-effect d-flex flex-wrap">
						<div class="image-holder">
							<a href="/getProduct/${productItem.productSeq }"><img
								src="${productItem.productFilepath}" alt="product-item"
								class="product-image img-fluid"></a>
						</div>
						<div class="cart-concern">
							<h3 class="card-title text-uppercase pt-3 text-primary">
								<a href="/getProduct/${productItem.productSeq}"
									class="text-primary">${productItem.productName}</a>
							</h3>
							<div class="cart-info">
								<a class="pseudo-text-effect" href="#" data-after="ADD TO CART">
									<span>${productItem.productPrice}</span>
								</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<ul class="pagination">
		<input type="hidden" id="keyword" name="keyword" value=${keyword }>
		<li
			class="page-item <c:if test="${productSearchList.first }">disabled</c:if>"><a
			class="page-link"
			href="?keyword=${keyword }&page=${productSearchList.number-1 }">Previous</a></li>

		<c:forEach var="page" begin="1" end="${productSearchList.totalPages }">
			<li class="page-item"><a class="page-link"
				href="?keyword=${keyword }&page=${page-1 }">${page }</a></li>
		</c:forEach>
		<li
			class="page-item <c:if test="${productSearchList.last }">disabled</c:if>"><a
			class="page-link"
			href="?keyword=${keyword }&page=${productSearchList.number+1 }">Next</a></li>
	</ul>
	<c:if test="${not empty principal and principal.role eq 'ADMIN'}">
		<div class="container mt-3">
			<div class="row justify-content-end">
				<div class="col-auto">
					<button type="button" onclick="location.href='/insertProduct'"
						id="btn-insertProduct" class="btn btn-danger">상품 등록하기</button>

				</div>
			</div>
		</div>
	</c:if>
</div>

<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>