<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jblog/layout/header.jsp"%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/js/orderAddressapi.js"></script>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Join</title>
<style>
    .container {
        display: flex;
        align-items: flex-start; /* 수정: 세로 상단 정렬 */
        justify-content: space-between; /* 좌우 정렬 */
        margin: 0; /* 기존 margin 설정 제거 */
    }

    .login-container {
        background-color: #FFFAF0;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 20px;
        width: 800px;
        margin: 0 5px; /* 수정: 양쪽으로 5px의 margin 추가 */
    }

    .login-container h2 {
        text-align: center;
        margin-bottom: 20px;
    }

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
}

.form-group input {
	width: 100%;
	padding: 8px;
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.form-group button {
	width: 100%; /* 버튼의 넓이를 100%로 설정 */
	padding: 10px;
	box-sizing: border-box;
	background-color: #F5DEB3;
	color: #000000;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 14px;
}

.form-group button:hover {
	background-color: #F5DEB3;
}

#duruduru-image {
	width: 270px; /* 원하는 크기로 조절하세요 */
	height: auto;
	
}
/* 수정된 스타일 시작 */
    .sidebar {
        display: flex;
        flex-direction: column; /* 세로로 나열 */
        align-items: flex-start; /* 좌측 정렬 */
        margin-right: 10px; /* 오른쪽 여백 추가 */
    }

    .sidebar a {
        padding: 12px;
        text-decoration: none;
        color: #000000;
        font-size: 23px;
        display: block;
        margin-bottom: 10px;
    }

    .sidebar a:hover {
        background-color: #F5DEB3;
    }
</style>
</head>
<body>
<br>
    <div class="container">
        <div class="sidebar">
            <a href="/mypage" onclick="loadPage('profile')">내 정보</a>
            <a href="/petInfo/${principal.userSeq}" onclick="loadPage('petInfo')">반려동물 정보</a>
            <a href="/mywritten/${principal.userSeq }" onclick="loadPage('myPosts')">내가 쓴 글</a>
            <a href="/orderHistory" onclick="loadPage('myPosts')">주문내역</a>
        </div>
 
<input type="hidden" name="orderSeq" id="orderSeq" value=${userPay.paySeq }>

	
	
		<div class="login-container">
<center>
		<h1>주문내역</h1>
			<img id="duruduru-image" src="/images/duruduru.png"
				alt="Duruduru Logo">
		</center>		


<br>
	<div class="form-group">
	 <table class="table">
    <tr style="text-align: center;">
        <th style="vertical-align: middle;">주문번호</th>
        <th style="vertical-align: middle;">주문자명</th>
        <th style="vertical-align: middle;">주문주소</th>
        <th style="vertical-align: middle;">주문액수</th>
        <th style="vertical-align: middle;">주문날짜</th>
    <c:forEach var="order" items="${orderList}">
        <c:if test="${order.user.userSeq == principal.userSeq}">
            <tr>	
                <td style="vertical-align: middle; text-align: center;">${order.paySeq }</td>
                <td style="vertical-align: middle; text-align: center;">${order.payName}</td>
                <td style="vertical-align: middle; text-align: center;">${order.payAddress}</td>
                <td style="vertical-align: middle; text-align: center;">${order.payPrice}원</td>	
                <td style="vertical-align: middle; text-align: center;">${order.payDate}</td>
              
            </tr>
        </c:if>
    </c:forEach>
</table>
<br>
<br>
</div>
</div>
</div>


</center>
<center>
<br>
<br>
</center>

</body>
<script src="/js/order.js"></script>
<script src="/js/coolSMS.js"></script>
<script src="/js/user.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>
</html>