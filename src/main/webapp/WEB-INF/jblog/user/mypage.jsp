<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jblog/layout/header.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
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
        width: 600px;
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
        width: 100%;
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
        min-width: 250px; /* 최소 너비 */
        max-width: 250px; /* 최대 너비 */
        min-height: 250px; /* 최소 높이 */
        max-height: 250px; /* 최대 높이 */
        width: 100%;
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
    /* 수정된 스타일 끝 */
</style>
	
</head>

<body>
    <br>
    <br>
    <div class="container col-md-8 col-sm-8 col-xs-8">
        <div class="sidebar">
            <a href="#" onclick="loadPage('profile')">내 정보</a>
            <a href="#" onclick="loadPage('petInfo')">반려동물 정보</a>
            <a href="#" onclick="loadPage('myPosts')">내가 쓴 글</a>
            <a href="#" onclick="loadPage('myPosts')">주문내역</a>
            <a href="#" onclick="loadPage('exchangeRefund')">교환 및 환불</a>
        </div>
        <div class="login-container ">
        <center>
            <img id="duruduru-image" src="images/duruduru.png" alt="Duruduru Logo"> <!-- 프로필 사진 -->
            </center>
            <form>
        <form>
        <br>
        <input type="hidden" id="user_seq" name="user_seq" value="${principal.user_seq }">
            <div class="form-group">
                <label for="userid">아이디</label>
                <h5>${principal.userid }</h5>
            </div>

            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="text" id="password" name="password" value="${principal.password }">
                 <small id="passwordError" class="text-danger"></small>
            </div>
                 <div class="form-group">
                <label for="passwordConfirm">비밀번호 확인</label>
                <input type="text" id="passwordConfirm" name="passwordConfirm">
                  <small id="passwordConfirmError" class="text-danger"></small>
            </div>
            
            <script>
    const passwordInput = document.getElementById('password');
    const passwordError = document.getElementById('passwordError');
    const passwordConfirmInput = document.getElementById('passwordConfirm');
    const passwordConfirmError = document.getElementById('passwordConfirmError');

    passwordInput.addEventListener('input', function (event) {
        if (!passwordInput.checkValidity()) {
            passwordError.textContent = '비밀번호는 최소 8자 이상이어야 하고 숫자, 영어, 특수문자(!@#$%^&*-)가 포함되어 있어야 합니다.';
        } else {
            passwordError.textContent = '';
        }
    });

    passwordConfirmInput.addEventListener('input', function (event) {
        if (passwordConfirmInput.value !== passwordInput.value) {
            passwordConfirmError.textContent = '비밀번호가 일치하지 않습니다.';
        } else {
            passwordConfirmError.textContent = '';
        }
    });
</script>
                        <div class="form-group">
                <label for="nickname">닉네임</label>
                <input type="text" id="nickname" name="nickname" value="${principal.nickname }">
            </div>
            
                        <div class="form-group">
                <label for="email">이메일</label>
                <h5>${principal.email }</h5>
            </div>
                                   <div class="form-group">
                <label for="phonenumber">전화번호</label>
                 <h5>${principal.phonenumber }</h5>
            </div>
            
                        <div class="form-group">
                <label for="address">주소</label>
                <h5>${principal.address }</h5>
            </div>
                        <div class="form-group">
                <label for="address_detail">상세주소</label>
                <h5>${principal.address_detail }</h5>
            </div>
            
                       <div class="form-group">
                <label for=zipcode">우편번호</label>
                <h5>${principal.zipcode }</h5>
            </div>
           <br>
            <div class="form-group">
                <button type="button" id="btn-updateUser"">내정보 수정</button> <!-- 자바스크립트로 바꿔주기 -->
            </div>
        </form>
    </div>
</div>
</div>
    <br>
</body>
<%@include file="/WEB-INF/jblog/layout/footer.jsp" %>
<script src="/js/user.js"></script>

</html>