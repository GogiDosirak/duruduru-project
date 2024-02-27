<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
    <%@include file="/WEB-INF/jblog/layout/header.jsp" %>

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
  <a href="/event"><h2>이벤트게시판</a></h2>
  
  
 <!-- 검색버튼 form -->
 <div id="searchForm">
    <form action="/event" method="GET" class="form-inline">
        <input type="text" name="searchKeyword" class="form-control-sm" id="search" placeholder="제목으로 검색하기">
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
    
    <tbody>
    <c:forEach var="eventboard" items="${getEventBoardList.content }">
    <tr>
    <td>${eventboard.eventSeq }</td>
    <td><a href="/getEventBoard/${eventboard.eventSeq}">${eventboard.eventTitle }</a></td>
    <td>${eventboard.user.nickname }</td>
    <td>${eventboard.eventCnt }</td>
    <td>${eventboard.eventDate }
    </c:forEach>
    </tbody>
    

  </table>
</div>
 <div class="container mt-3 text-center">
    <ul class="pagination">
<li class="page-item <c:if test="${getEventBoardList.first }">disabled</c:if>"><a class="page-link" href="?searchKeyword=${searchKeyword }&page=${getEventBoardList.number-1 }">Previous</a></li>

<c:forEach var="page" begin="1" end="${getEventBoardList.totalPages }">
<li class="page-item"><a class="page-link" href="?searchKeyword=${searchKeyword }&page=${page -1}">${page }</a></li>
</c:forEach>
<li class="page-item <c:if test="${getEventBoardList.last }">disabled</c:if>"><a class="page-link" href="?searchKeyword=${searchKeyword }&page=${getEventBoardList.number+1 }">Next</a></li>
</ul>
</div>
 
<br>
<br>
<br>

<c:if test="${not empty principal and principal.role eq 'ADMIN' }">
<center>
 <button id="btn-writeEventBoard" class="btn btn-warning">글등록</button>
</center>
</c:if>




<script src="/js/event.js"></script>
<%@include file = "/WEB-INF/jblog/layout/footer.jsp"%>