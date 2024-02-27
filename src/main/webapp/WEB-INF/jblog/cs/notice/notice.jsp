<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>
<div class="container mt-3">
	<a href="/notice"><h2>공지사항</a></h2>
	<div id="searchForm">
		<form action="/notice/searchNotice" method="GET" class="form-inline">
			<input type="text" name="keyword" class="form-control-sm" id="search"
				placeholder="제목으로 검색하기">
			<button class="btn btn-warning bi bi-search" id="searchButton">검색</button>
		</form>
	</div>
	<table class="table">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<c:forEach var="notice" items="${noticeList.content }">
			<tr>
				<td>${notice.noticeSeq }</td>
				<td><a href="/getNotice/${notice.noticeSeq }">${notice.noticeTitle }</a>
				</td>
				<td>운영자</td>
				<td>${notice.noticeDate }</td>
				<td>${notice.noticeCnt }</td>
			</tr>
		</c:forEach>


	</table>
</div>
<br>
<br>
<ul class="pagination">

	<li
		class="page-item <c:if test="${noticeList.first }">disabled</c:if>"><a
		class="page-link" href="?page=${noticeList.number-1 }">Previous</a></li>
	<c:forEach var="page" begin="1" end="${noticeList.totalPages }">
		<li class="page-item"><a class="page-link"
			href="?page=${page-1 }">${page }</a></li>
	</c:forEach>
	<li class="page-item <c:if test="${noticeList.last }">disabled</c:if>"><a
		class="page-link" href="?page=${noticeList.number+1 }">Next</a></li>
</ul>
<c:if test="${principal.role eq 'ADMIN'}">
	<center>
		<button onclick="location.href='/insertNotice'"
			class="btn btn-warning">글등록</button>
	</center>
</c:if>

<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>