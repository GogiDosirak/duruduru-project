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


<div class="container mt-3 text-center">
    <ul class="pagination justify-content-center">
        <li class="page-item ${walkboardpage.first ? 'disabled' : ''}">
            <a class="page-link" href="?page=${walkboardpage.number - 1}" ${walkboardpage.first ? 'tabindex="-1" aria-disabled="true"' : ''}>Previous</a>
        </li>
        
        <c:choose>
            <c:when test="${walkboardpage.totalPages > 0}">
                <c:forEach var="pageNumber" begin="0" end="${walkboardpage.totalPages - 1}">
                    <li class="page-item ${pageNumber == walkboardpage.number ? 'active' : ''}">
                        <a class="page-link" href="?page=${pageNumber}">${pageNumber + 1}</a>
                    </li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <li class="page-item">
                    <span class="page-link">No Pages</span>
                </li>
            </c:otherwise>
        </c:choose>

        <li class="page-item ${walkboardpage.last ? 'disabled' : ''}">
            <a class="page-link" href="?page=${walkboardpage.number + 1}" ${walkboardpage.last ? 'tabindex="-1" aria-disabled="true"' : ''}>Next</a>
        </li>
    </ul>
</div>
</div>
<br>
<br>
<br>
<center>
	<button id="btn-writeWalkBoard" class="btn btn-warning">글등록</button>
</center>
   <script src="/js/walkBoard.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>
