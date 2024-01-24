<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@include file="/WEB-INF/jblog/layout/header.jsp" %>
    
  
      <br>
      <center>
  <h2>자유게시판 글 상세</h2>
         
       
         <div class="col-md-8">
            <div class="mb-3 mt-3">
            <table class="table">
            <tr>
            <th>제목</th>
           <td> ${findFreeBoard.frbo_title } </td>
           </tr>
           
           <tr>
           <th>작성자</th>
           <td>${findFreeBoard.user.nickname } </td>
           </tr>
           
           <tr>
           <th>내용</th>
           <td> ${findFreeBoard.frbo_content }
           </td>
           </tr>
           </table>
            </div>
         </div>

<div class="col-md-8">
    <div class="mb-3">
        
        
    </div>
</div>


      </center>
      <center>
         <button id="btn-inserFreeBoard" class="btn btn-warning">글수정</button>
      </center>
      
      <script src="/js/commuboard.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp" %>