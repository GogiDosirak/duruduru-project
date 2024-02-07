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
					 	 	 <input type="hidden" id="noticeSeq" name="noticeSeq" value="${getNotice.noticeSeq }">
	<hr>
	<h2 class="center-align">공지사항</h2>
	</center>
	<div class="container">
		<h2>${getNotice.noticeTitle}</h2>
		<hr>
		<div class="details">
			<label for="noticeName">작성자 : 운영자 </label>
			<label for="noticeContent">${getNotice.noticeContent }</label>
		</div>

	</div>
	<center>
		<div>
			<c:if test="${not empty principal and principal.role eq 'ADMIN'}">
				<button type="button"
					onclick="location.href='/updateNotice/${getNotice.noticeSeq}'"
					class="btn btn-warning">글 수정하기</button>
				<button type="button" id="btn-deleteNotice" class="btn btn-danger">글
					삭제하기</button>
				<script src="/js/notice.js"></script>
			</c:if>
					<div>
					<br>
			
			<a href="/notice"><h5>뒤로가기</h5></a>
		</div>
	</center>
	</div>
	</body>
<%@ include file="/WEB-INF/jblog/layout/footer.jsp" %>
</html>