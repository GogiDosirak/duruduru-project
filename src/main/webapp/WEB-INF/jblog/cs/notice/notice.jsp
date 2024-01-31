<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "/WEB-INF/jblog/layout/header.jsp"%>
	<div class="container mt-3">
  <h2>공지사항</h2>         
  <table class="table">
    <thead>
      <tr>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <c:forEach var="notice" items="${noticeList }">
    <tr>
    <td>${notice.noticeTitle }</td>
    <td>운영자</td>
    <td>${notice.noticeDate }</td>
    <td>${notice.noticeCnt }</td>
    </tr>	
    </c:forEach>
    

  </table>
</div>
<br>
<br>
<c:if test="${principal.role eq 'ADMIN'}">
      <center>
         <button onclick="location.href='/insertNotice'" class="btn btn-warning">글등록</button>
      </center>
      </c:if>

<%@include file = "/WEB-INF/jblog/layout/footer.jsp"%>