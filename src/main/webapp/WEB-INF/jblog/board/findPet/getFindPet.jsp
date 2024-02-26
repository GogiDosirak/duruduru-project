<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <%@include file="/WEB-INF/jblog/layout/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>duruduru</title>
    <style>
    
    img {
  max-width: 100%;
  height: auto;
}
    
    
        body {
            font-family: 'Jua', sans-serif;
            background-color: #f6f5f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1000px;
            margin: 2rem auto;
            background-color: #FFFAF0;
            padding: 1.5rem;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #333;
        }

        hr {
            border-top: 2px solid #ccc;
            margin: 1.5rem 0;
        }

        .details {
            display: flex;
            flex-direction: column;
        }

        .details label {
            font-size: 1.2rem;
            color: #555;
            margin-bottom: 0.5rem;
        }

        .details label:first-child {
            font-size: 1.5rem;
            color: #333;
            margin-bottom: 1rem;
        }

        .logo img {
            max-width: 200px;
            height: auto;
        }

        .center-align {
            text-align: center;
        }
        
    </style>
</head>
<body>
    <div class="logo center-align">
        <a href="/">
            <img src="/images/duruduru.png" alt="로고 이미지">
        </a>
    </div>
    <hr>
    <h2 class="center-align">동물을 찾아요</h2>
    <div class="container">
    <input type="hidden" id="fpSeq" value="${findFindPetBoard.fpSeq }">
        <h2 id="title" align="center">${findFindPetBoard.fpTitle}</h2>
        <h3 align="left">${findFindPetBoard.user.nickname}</h3>   
        <h6 align="left">날짜 : ${findFindPetBoard.fpDate}&nbsp;&nbsp;&nbsp;&nbsp;조회수 : ${findFindPetBoard.fpCnt}  </h6>
        <hr>
        
        <div class="details" >
        
            <h4 id="content">${findFindPetBoard.fpContent}</h4>
            <br>
        <br>
        <br>
          <br>
        <br>
        <br>
          <br>
        <br>
        <br>
        </div>
             
    </div>
    
     <div align="center">
    <a href="/findPetBoard"><h5>뒤로가기</h5></a>
    </div>
  <br>
  
    
    <div align="center">
        <c:if test="${principal.userid eq findFindPetBoard.user.userid or principal.role eq 'ADMIN'}">
            <a href="/updateFindPetBoard?fpSeq=${findFindPetBoard.fpSeq }" class="btn btn-warning">글 수정하기</a>
            <button type="button" id="btn-deleteFindPetBoard" class="btn btn-danger">글 삭제하기</button>
        </c:if>
        </div>
    <hr>
    
    
    
     <div class="container mt-3">
<h2> &nbsp; 댓글 등록</h2> 
<div class="comment-container">
    <table class="table">
        <thead>
            <tr>
                <td align="right">
                    <div class="comment-input-container">
                        <textarea rows="1" cols="99" id="fpCoContent" placeholder="댓글을 입력하세요"></textarea>
                        <button id="btn-insertFindPetComment" class="btn btn-warning">댓글 등록</button>
                    </div>
                </td>
            </tr>
        </thead>
    </table>
</div>

<div class="container mt-3">
<h2>댓글 보기</h2>
 <table class="table">
 <thead>
 
 <tr>
 <td>작성자</td>
 <td>내용</td>
 <td>작성일</td><td>
 </td>
 </tr>
 
 <c:forEach var="findPetComment" items="${findPetCommentList}">
 
 <c:if test="${findFindPetBoard.fpSeq == findPetComment.findPetBoard.fpSeq}">
 
 <tr>
 <td>${findPetComment.user.nickname }</td>
 <td>${findPetComment.fpCoContent }</td>
 <td>${findPetComment.fpCoDate }</td>
  <td>
 <c:if test="${principal.userid eq findPetComment.user.userid or principal.role eq 'ADMIN'}">
<button type="button"  class="btn btn-danger btn-sm btn-delete-comment" data-co-seq="${findPetComment.fpCoSeq }" style="padding: 1px 1px;">삭제하기</button> 
</c:if>
 </td>
 </tr>
 </c:if>
 </c:forEach>
 
 
 
</thead>
</table>
</div>
    
    
 </div>   
    
    
    
    
   <script src="/js/findPetBoard.js"></script>
</body>
</html>

<%@include file = "/WEB-INF/jblog/layout/footer.jsp"%>