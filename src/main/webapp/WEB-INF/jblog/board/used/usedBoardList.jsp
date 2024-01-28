<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>

<div class="container mt-3">
	<a href="/usedboard"><h2>중고물품거래</h2></a>

	<style>
.product-image {
	width: 300px;
	height: 300px;
	object-fit: cover;
}
</style>

<div id="searchForm">
    <form action="/usedboard" method="GET" class="form-inline">
        <input type="text" name="searchKeyword" class="form-control-sm" id="searchKeyword" placeholder="제목으로 검색하기">
        <button class="btn btn-warning bi bi-search" id="searchButton">검색</button>
    </form>
</div>
<br> 
	<div class="container-fluid mt-3">
		<div class="row">
			<c:forEach var="used" items="${usedBoardList.content}">
				<div class="col-md-3 mb-4">
					<div
						class="product-card image-zoom-effect link-effect d-flex flex-wrap">
						<div class="image-holder">
							<a href="/getUsedBoard/${used.usboSeq }"><img
								src="${used.usedFilepath}" alt="product-item"
								class="product-image img-fluid"></a>
						</div>
						<div class="cart-concern">
							<h3 class="card-title text-uppercase pt-3 text-primary">
								<a href="/getUsedBoard/${used.usboSeq }"
									class="text-primary">${used.usboTitle}</a>
							</h3>
							
						</div>
						<div class="image-holder" >
					<h6>${used.user.nickname }</h6>
					</div>
					<div class="image-holder" >
					<h6>${used.usboCnt }</h6>
					</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	
	<div class="container mt-3 mx-auto d-flex justify-content-center">
    <ul class="pagination">
    <input type="hidden" id="searchKeyword" value="${searchKeyword }"> 
<li class="page-item <c:if test="${usedBoardList.first }">disabled</c:if>"><a class="page-link" href="?searchKeyword=${searchKeyword }&page=${usedBoardList.number-1 }">Previous</a></li>

<c:forEach var="page" begin="1" end="${usedBoardList.totalPages }">
<li class="page-item"><a class="page-link" href="?searchKeyword=${searchKeyword }&page=${page -1}">${page }</a></li>
</c:forEach>
<li class="page-item <c:if test="${usedBoardList.last }">disabled</c:if>"><a class="page-link" href="??searchKeyword=${searchKeyword }&page=${usedBoardList.number+1 }">Next</a></li>
</ul>
</div>
<br>
<br>
<br>
<center>
    <a href="/insertUsedBoard" class="btn btn-warning">글등록</a>
</center>

<script src="/js/usedBoard.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>