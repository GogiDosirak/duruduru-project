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
            <a href="#" onclick="loadPage('exchangeRefund')">교환 및 환불</a>
        </div>
 
<input type="hidden" name="orderSeq" id="orderSeq" value=${userOrder.orderSeq }>

	
	
		<div class="login-container">
<center>
		<h1>내가 쓴 글</h1>
			<img id="duruduru-image" src="/images/duruduru.png"
				alt="Duruduru Logo">
		</center>		

<br>
	<div class="form-group">
	 <table class="table">
    <tr style="text-align: center;">
        <th style="vertical-align: middle;">게시판명</th>
        <th style="vertical-align: middle;">글제목</th>
        <th style="vertical-align: middle;">조회수</th>
        <th style="vertical-align: middle;">날짜</th>
        </tr>
        <c:forEach var="freeBoard" items="${freeBoard}">
            <tr>	
                <td style="vertical-align: middle; text-align: center;">자유게시판</td>
                <td style="vertical-align: middle; text-align: center;"><a href="/getfreeboard/${freeBoard.frboSeq }">${freeBoard.frboTitle }</a></td>
                <td style="vertical-align: middle; text-align: center;">${freeBoard.frboCnt}</td>
                <td style="vertical-align: middle; text-align: center;">${freeBoard.frboDate}</td>	  
            </tr>
    </c:forEach>
            <c:forEach var="usedBoard" items="${usedBoard}">
            <tr>	
                <td style="vertical-align: middle; text-align: center;">중고거래게시판</td>
                <td style="vertical-align: middle; text-align: center;"><a href="/getUsedBoard/${usedBoard.usboSeq }">${usedBoard.usboTitle}</a></td>
                <td style="vertical-align: middle; text-align: center;">${usedBoard.usboCnt}</td>
                <td style="vertical-align: middle; text-align: center;">${usedBoard.usboDate}</td>	  
            </tr>
    </c:forEach>
                <c:forEach var="walkBoard" items="${walkBoard}">
            <tr>	
                <td style="vertical-align: middle; text-align: center;">산책게시판</td>
                <td style="vertical-align: middle; text-align: center;"><a href="/getWalkBoard/${walkBoard.waboSeq }">${walkBoard.waboTitle}</a></td>
                <td style="vertical-align: middle; text-align: center;">${walkBoard.waboCnt}</td>
                <td style="vertical-align: middle; text-align: center;">${walkBoard.waboDate}</td>	  
            </tr>
    </c:forEach>
                    <c:forEach var="walkCheckBoard" items="${walkCheckBoard}">
            <tr>	
                <td style="vertical-align: middle; text-align: center;">산책인증게시판</td>
                <td style="vertical-align: middle; text-align: center;"><a href="/getWach/${walkCheckBoard.wachboSeq}">${walkCheckBoard.wachboTitle}</a></td>
                <td style="vertical-align: middle; text-align: center;">${walkCheckBoard.wachboCnt}</td>	  
                <td style="vertical-align: middle; text-align: center;">${walkCheckBoard.wachboDate}</td>	  
            </tr>
    </c:forEach>
                       <c:forEach var="inquiry" items="${inquiry}">
            <tr>	
                <td style="vertical-align: middle; text-align: center;">1:1문의게시판</td>
                <td style="vertical-align: middle; text-align: center;"><a href="/getInquiry/${inquiry.inquirySeq }">${inquiry.inquiryTitle }</a></td>
                <td style="vertical-align: middle; text-align: center;">${inquiry.inquiryCnt}</td>	  
                <td style="vertical-align: middle; text-align: center;">${inquiry.inquiryDate}</td>	  
            </tr>
    </c:forEach>

                           <c:forEach var="findPetBoard" items="${findPetBoard}">
            <tr>	
                <td style="vertical-align: middle; text-align: center;">동물찾기게시판</td>
                <td style="vertical-align: middle; text-align: center;"><a href="/getFindPetBoard/${findPetBoard.fpSeq }">${findPetBoard.fpTitle }</a></td>
                <td style="vertical-align: middle; text-align: center;">${findPetBoard.fpCnt}</td>	  
                <td style="vertical-align: middle; text-align: center;">${findPetBoard.fpDate}</td>	  
            </tr>
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