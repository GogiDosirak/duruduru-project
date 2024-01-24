<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>
<div class="container mt-3">
	<h2>산책게시판</h2>
	
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
		
<c:forEach var="walkboard" items="${walkBoardList}">
  <tr>
    <td>${walkboard.waboSeq}</td>
    <td><a href="/getWalkBoard/${walkboard.waboSeq}"> ${walkboard.waboTitle}</a></td>
    <td>${walkboard.user.nickname}</td>
    <td>${walkboard.waboCnt}</td>
    <td>${walkboard.waboDate}</td>
  </tr>
</c:forEach>

	</table>

</div>
<br>
<br>
<br>
<center>
	<button id="btn-writeWalkBoard" class="btn btn-warning">글등록</button>
</center>
   <script src="/js/walkBoard.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>
