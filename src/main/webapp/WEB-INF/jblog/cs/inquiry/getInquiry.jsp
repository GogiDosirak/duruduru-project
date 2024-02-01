<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp" %>
<title></title>
<style>
    <style>
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

img {
	max-width: 100%;
	height: auto;
} 
  .quantity-input {
        width: 50px; /* 수량 입력창 너비 조정 */
        padding: 8px; /* 수량 입력창 안의 텍스트 여백 조정 */
        font-size: 14px;
        border: 1px solid #ced4da; /* 수량 입력창 윤곽선 스타일 */
        border-radius: 10px; /* 수량 입력창 둥글게 만들기 */
        outline: none; /* 포커스 효과 제거 */
    }
    .comment-container {
    text-align: right;
}

.comment-input-container {
    display: flex;
    justify-content: flex-end;
}

textarea {
    margin-right: 10px; /* 텍스트 박스와 버튼 간의 간격 조절 */
}
</style>
</head>
<body>
    <div class="logo center-align">
        <a href="/">
            <img src="/images/duruduru.png" alt="로고 이미지">
        </a>
    </div>
	<div class="logo center-align">
	</div>
	<center>
					 	 	 <input type="hidden" id="inquirySeq" name="inquirySeq" value="${getInquiry.inquirySeq }">
	<hr>
	<h2 class="center-align">1:1문의</h2>
	</center>
	<div class="container">
		<h2>${getInquiry.inquiryTitle}</h2>
		<hr>
		<div class="details">
			<label for="inquiryName">작성자 : ${getInquiry.user.nickname } </label>
			<label for="inquiryContent">${getInquiry.inquiryContent }</label>
		</div>

	</div>
	<div class="container mt-3">
<h2> &nbsp; 댓글 등록</h2> 
<div class="comment-container">
    <table class="table">
        <thead>
            <tr>
                <td align="right">
                    <div class="comment-input-container">
                        <textarea rows="1" cols="99" id="inquiryCoContent" placeholder="댓글을 입력하세요"></textarea>
                        <button id="btn-inquiryInsertComment" class="btn btn-warning">댓글 등록</button>
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
 <!--  
 <c:forEach var="freeComment" items="${freeCommentList }">
 
 <c:if test="${ findFreeBoard.frboSeq == freeComment.freeBoard.frboSeq}">
 
 <tr>
 <td>${freeComment.user.nickname }</td>
 <td>${freeComment.frboCoContent }</td>
 <td>${freeComment.frboCoDate }</td>
  <td>
 <c:if test="${principal.userid eq freeComment.user.userid }">
<button type="button"  class="btn btn-danger btn-sm btn-delete-comment" data-co-seq="${freeComment.frboCoSeq }" style="padding: 1px 1px;">삭제하기</button> 
</c:if>
 </td>
 </tr>
 </c:if>
 </c:forEach>
 -->
 
 
</thead>
</table>
</div>
    
    
 </div>   
	<center>
		<div>
			<c:if test="${getInquiry.user.nickname eq principal.nickname}">
				<button type="button"
					onclick="location.href='/updateInquiry/${getInquiry.inquirySeq}'"
					class="btn btn-warning">글 수정하기</button>
				<button type="button" id="btn-deleteInquiry" class="btn btn-danger">글
					삭제하기</button>
				<script src="/js/inquiry.js"></script>
			</c:if>
					<div>
					<br>
			
			<a href="/inquiry"><h5>뒤로가기</h5></a>
		</div>
	</center>
	</div>
	</body>
<%@ include file="/WEB-INF/jblog/layout/footer.jsp" %>
</html>