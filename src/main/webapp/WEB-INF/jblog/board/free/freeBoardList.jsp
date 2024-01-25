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
  <h2>자유게시판</h2>
  
  
 <!-- 검색버튼 form -->
   <form action="/freeboard" method="GET" class="form-inline p-2 bd-highlight" role="search">        
<input type="text" name="searchKeyword" class="form-control" id="searchKeyword" placeholder="검색">        
<button class="btn btn-success bi bi-search"></button>    
</form>
  <input type="hidden" id="searchKeyword" value="${searchKeyword }">     
      
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
    <c:forEach var="freeboard" items="${getFreeBoardList.content }">
    <tr>
    <td>${freeboard.frboSeq }</td>
    <td><a href="/getfreeboard/${freeboard.frboSeq }">${freeboard.frboTitle }</a></td>
    <td>${freeboard.user.nickname }</td>
    <td>${freeboard.frboCnt }</td>
    <td>${freeboard.frboDate }
    </c:forEach>
    </tbody>
    

  </table>
</div>
<div class="container mt-3 text-center">
    <ul class="pagination">
<li class="page-item <c:if test="${getFreeBoardList.first }">disabled</c:if>"><a class="page-link" href="?page=${getFreeBoardList.number-1 }">Previous</a></li>

<c:forEach var="page" begin="1" end="${getFreeBoardList.totalPages }">
<li class="page-item"><a class="page-link" href="?page=${page -1}">${page }</a></li>
</c:forEach>
<li class="page-item <c:if test="${getFreeBoardList.last }">disabled</c:if>"><a class="page-link" href="?page=${getFreeBoardList.number+1 }">Next</a></li>
</ul>
</div>

<br>
<br>
<br>

<center>
 <button id="btn-writeFreeBoard" class="btn btn-warning">글등록</button>
</center>





<script src="/js/commuboard.js"></script>
<%@include file = "/WEB-INF/jblog/layout/footer.jsp"%>