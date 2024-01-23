<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jblog/layout/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FindId</title>
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
			<img id="duruduru-image" src="images/duruduru.png"
				alt="Duruduru Logo">
			<form>
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="check1"
						name="option1" value="something" checked
						onchange="handleCheckboxChange('check1', 'check2')"> <label
						class="form-check-label" for="check1">아이디 찾기</label>
				</div>
				<div id="idFinder" style="display: none;">
					<!-- 여기에 아이디 찾기 컨텐츠 추가 -->
					<div class="form-group">
						<label for="phonenumber">휴대폰번호</label> <input type="text"
							id="phonenumber" name="phonenumber" required>
					</div>

					<div class="form-group">
						<label for="email">이메일</label> <input type="email" id="email"
							name="email" required>
					</div>
					<div class="form-group">
						<button id="btn-findid">아이디 찾기</button>
					</div>
				</div>

				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="check2"
						name="option2" value="something"
						onchange="handleCheckboxChange('check2', 'check1')"> <label
						class="form-check-label" for="check2">비밀번호 찾기</label>
				</div>

				<div id="passwordFinder" style="display: none;">
					<!-- 여기에 비밀번호 찾기 컨텐츠 추가 -->
					<div class="form-group">
						<label for="userid">아이디</label> <input type="text" id="userid"
							name="userid" required>
					</div>
					<div class="form-group">
						<label for="phonenumber">휴대폰번호</label> <input type="text"
							id="phonenumber2" name="phonenumber2" required>
					</div>

					<div class="form-group">
						<label for="email">이메일</label> <input type="email" id="email2"
							name="email2" required>
					</div>
					<div class="form-group">
						<button id="btn-findpw">비밀번호 찾기</button>
					</div>
				</div>
				<br>
				<center>
					<button type="button" class="btn btn-warning"
						onclick="location.href='/login'">로그인화면으로</button>

				</center>
			</form>
		</div>

		<script>
		document.addEventListener("DOMContentLoaded", function() {
            handleCheckboxChange('check1', 'check2');
        });
    	function handleCheckboxChange(checkedCheckboxId, uncheckedCheckboxId) {
        // 체크된 체크박스에 따른 div 보이도록 설정
        document.getElementById('idFinder').style.display = checkedCheckboxId === 'check1' ? 'block' : 'none';
        document.getElementById('passwordFinder').style.display = checkedCheckboxId === 'check2' ? 'block' : 'none';
        
        // 체크된 체크박스 외 다른 체크박스의 상태 초기화
        document.getElementById(uncheckedCheckboxId).checked = false;
    }
</script>




	</div>

</body>
<script src="/js/user.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>
</html>