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
  max-width: 500px;
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
    <div class="/logo center-align">
        <a href="/">
            <img src="/images/duruduru.png" alt="로고 이미지">
        </a>
    </div>
    <hr>
    <h2 class="center-align">이벤트게시판</h2>
    <div class="container">
    <input type="hidden" id="eventSeq" value="${findEventBoard.eventSeq }">
        <h2 id="title" align="center">${findEventBoard.eventTitle}</h2>
        <h3 align="left">${findEventBoard.user.nickname}</h3>   
        <h6 align="left">날짜 : ${findEventBoard.eventDate}&nbsp;&nbsp;&nbsp;&nbsp;조회수 : ${findEventBoard.eventCnt}</h6>
        <hr>
        
        <div class="details" >
        
            <h4 id="content">${findEventBoard.eventContent}</h4>
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
    <a href="/event"><h5>뒤로가기</h5></a>
    </div>
    <br>
    
    <div align="center">
        <c:if test="${not empty principal and principal.role eq 'ADMIN' }">
            <button type="button" id="go-updateEvnetboard" class="btn btn-warning">글 수정하기</button>
            <button type="button" id="btn-deleteEventboard" class="btn btn-danger">글 삭제하기</button>
        </c:if>
        </div>
        
        <script src="/js/event.js"></script>
        
 <%@include file = "/WEB-INF/jblog/layout/footer.jsp"%>