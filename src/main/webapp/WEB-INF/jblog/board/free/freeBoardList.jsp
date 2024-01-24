<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "/WEB-INF/jblog/layout/header.jsp"%>
	<div class="container mt-3">
  <h2>자유게시판</h2>         
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
    <c:forEach var="freeboard" items="${getFreeBoardList }">
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

<br>
<br>
<br>

<center>
 <button id="btn-writeFreeBoard" class="btn btn-warning">글등록</button>
</center>
<script src="/js/commuboard.js"></script>
<%@include file = "/WEB-INF/jblog/layout/footer.jsp"%>