<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "/WEB-INF/jblog/layout/header.jsp"%>
	<div class="container mt-3">
  <h2>1:1문의</h2>         
  <table class="table">
    <thead>
      <tr>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <c:forEach var="inquiry" items="${inquiryList }">
    <tr>
    <td><a href="/getInquiry/${inquiry.inquirySeq }">${inquiry.inquiryTitle }</a> </td>
    <td>${inquiry.user.nickname }</td>
    <td>${inquiry.inquiryDate }</td>
    <td>${inquiry.inquiryCnt }</td>
    </tr>	
    </c:forEach>
    

  </table>
</div>
<br>
<br>
      <center>
         <button onclick="location.href='/insertInquiry'" class="btn btn-warning">글등록</button>
      </center>

<%@include file = "/WEB-INF/jblog/layout/footer.jsp"%>