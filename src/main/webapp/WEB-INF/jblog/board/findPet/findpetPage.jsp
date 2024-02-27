<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>

<div class="container mt-3">
	<a href="/findPetBoard"><h2>동물찾기게시판</h2></a>






<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Get Location</title>
</head>
<body>

<script>
document.addEventListener("DOMContentLoaded", function() {
    getLocation();
});

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        alert("Geolocation is not supported by this browser.");
    }
}

function showPosition(position) {
    var latitude = position.coords.&{principal.latitude};
    var longitude = position.coords.&{principal.longitude};

    // 서버로 위도와 경도 전송
    window.location.href = "/used-boards?latitude=" + latitude + "&longitude=" + longitude	;
}
</script>

</body> 







	<style>
.product-image {
	width: 300px;
	height: 300px;
	object-fit: cover;
}
</style>

<div id="searchForm">
    <form action="/findPetBoard" method="GET" class="form-inline">
        <input type="text" name="searchKeyword" class="form-control-sm" id="search" placeholder="제목으로 검색하기">
        <button class="btn btn-warning bi bi-search" id="searchButton">검색</button>
    </form>
</div>
<br> 
	<div class="container-fluid mt-3">
		<div class="row">
			<c:forEach var="findPet" items="${findPetBoardpage.content}">
				<div class="col-md-3 mb-4">
					<div
						class="product-card image-zoom-effect link-effect d-flex flex-wrap">
						<div class="image-holder">
							<a href="/getFindPetBoard/${findPet.fpSeq}"><img
								src="${findPet.fpFilepath}" alt="product-item"
								class="product-image img-fluid"></a>
						</div>
						<div class="cart-concern">
							<h3 class="card-title text-uppercase pt-3 text-primary">
								<a href="/getFindPetBoard/${findPet.fpSeq }"
									class="text-primary">${findPet.fpTitle}</a>
								<div class="image-holder">
									<h5>${findPet.user.address.split(' ')[1] }</h5>
								</div>

							</h3>
							
						</div>
						<div class="image-holder" >
					<h6>${findPet.user.nickname }</h6>
					</div>
					<div class="image-holder" >
					<h6>${findPet.fpCnt }</h6>
					</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	
	<div class="container mt-3 mx-auto d-flex justify-content-center">
    <ul class="pagination">
    <input type="hidden" id="searchKeyword" value="${searchKeyword }"> 
<li class="page-item <c:if test="${findPetBoardpage.first }">disabled</c:if>">
<a class="page-link" href="?searchKeyword=${searchKeyword }&page=${findPetBoardpage.number-1 }">Previous</a></li>
<c:forEach var="page" begin="1" end="${findPetBoardpage.totalPages+1}">
<li class="page-item"><a class="page-link" href="?searchKeyword=${searchKeyword }&page=${page -1}">${page }</a></li>
</c:forEach>
<li class="page-item"><a class="page-link" href="?searchKeyword=${searchKeyword }&page=${findPetBoardpage.number+1 }">Next</a></li>
</ul>
</div>
<br>
<br>
<br>
<center>
    <a href="/insertFindPet" class="btn btn-warning">글등록</a>
</center>

<script src="/js/findPetBoard.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>