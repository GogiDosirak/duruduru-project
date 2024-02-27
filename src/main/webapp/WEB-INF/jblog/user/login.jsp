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
            align-items: center;
            justify-content: center;
        }
 
        .login-container {
            background-color: #FFFAF0;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 300px;
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

    </style>
</head>
<body>
<br>
<br>
<div class="container">
    <div class="login-container">
        <img id="duruduru-image" src="images/duruduru.png" alt="Duruduru Logo">
        
            <div class="form-group">
                <label for="userid">아이디</label>
                <input type="text" id="userid" name="userid" required>
            </div>

            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" required>
            </div>

            <div class="form-group">
                <button id="btn-login" class="btn btn-warning" >로그인</button>
            </div>
        
    </div>
</div>
<br>
<center>
    <button type="button" class="btn btn-warning" onclick="location.href='/join'">회원가입</button>
    &nbsp;&nbsp;&nbsp;
    <button type="button" class="btn btn-warning" onclick="location.href='/findid'">아이디/비밀번호 찾기</button>
    </center>
</div>

</body>
<script src="/js/user.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp" %>
</html>