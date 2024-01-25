<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
/* 페이지네이션을 가운데 정렬하는 스타일 */
.pagination {
	display: flex;
	justify-content: center;
	list-style: none;
	padding: 0;
}

.pagination li {
	margin: 0 5px;
}

​ /* Optional: 페이지 링크 스타일 조정 */  
.pagination a {
	text-decoration: none;
	color: #007bff; /* 링크 색상을 원하는 색상으로 조정 */
}

.pagination a:hover {
	text-decoration: underline;
}
</style>


<div class="container mt-3">



	<h2>산책게시판</h2>

<div id="searchForm">
    <form action="/walk" method="GET" class="form-inline">
        <input type="text" name="searchKeyword" class="form-control-sm" id="searchKeyword" placeholder="제목으로 검색하기">
        <button class="btn btn-warning bi bi-search" id="searchButton">검색</button>
    </form>
</div>
<br>

	<table class="table">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
		</thead>

		<c:forEach var="walkboard" items="${walkboardpage.content}">
			<tr>
				<td>${walkboard.waboSeq}</td>
				<td><a href="/getWalkBoard/${walkboard.waboSeq}">${walkboard.waboTitle}</a></td>
				<td>${walkboard.user.nickname}</td>
				<td>${walkboard.waboCnt}</td>
				<td>${walkboard.waboDate}</td>
			</tr>
		</c:forEach>

	</table>

	<input type="hidden" id="searchKeyword" name="searchKeyword"
		value=${searchKeyword }>
	<ul class="pagination">
		<li
			class="page-item <c:if test="${walkboardpage.first }">disabled</c:if>"><a
			class="page-link"
			href="?searchKeyword=${searchKeyword }&page=${pwalkboardpage.number-1 }">Previous</a></li>

		<c:forEach var="page" begin="1" end="${walkboardpage.totalPages }">
			<li class="page-item"><a class="page-link"
				href="?searchKeyword=${searchKeyword }&page=${page -1}">${page }</a></li>
		</c:forEach>
		<li
			class="page-item <c:if test="${walkboardpage.last }">disabled</c:if>"><a
			class="page-link"
			href="?searchKeyword=${searchKeyword }&page=${walkboardpage.number+1 }">Next</a></li>
	</ul>
</div>
<br>
<br>
<br>
<center>
	<button id="btn-writeWalkBoard" class="btn btn-warning">글등록</button>
</center>

<script src="/js/walkBoard.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>
