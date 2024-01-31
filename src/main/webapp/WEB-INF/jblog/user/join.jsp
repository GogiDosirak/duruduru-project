<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jblog/layout/header.jsp"%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/js/addressapi.js"></script>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Join</title>
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

			<div class="form-group">
				<label for="userid">아이디</label> <input type="text" id="userid"
					name="userid" required>
			</div>
			<div class="form-group">
				<button id="btn-chid">아이디확인</button>
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="password"
					id="password" name="password" required> <small
					id="passwordError" class="text-danger"></small>
			</div>

			<div class="form-group">
				<label for="passwordConfirm">비밀번호 확인</label> <input type="password"
					id="passwordConfirm" name="passwordConfirm" required> <small
					id="passwordConfirmError" class="text-danger"></small>
			</div>

			<script>
				const passwordInput = document.getElementById('password');
				const passwordError = document.getElementById('passwordError');
				const passwordConfirmInput = document
						.getElementById('passwordConfirm');
				const passwordConfirmError = document
						.getElementById('passwordConfirmError');

				passwordInput
						.addEventListener(
								'input',
								function(event) {
									if (!passwordInput.checkValidity()) {
										passwordError.textContent = '비밀번호는 최소 8자 이상이어야 하고 숫자, 영어, 특수문자(!@#$%^&*-)가 포함되어 있어야 합니다.';
									} else {
										passwordError.textContent = '';
									}
								});

				passwordConfirmInput.addEventListener('input', function(event) {
					if (passwordConfirmInput.value !== passwordInput.value) {
						passwordConfirmError.textContent = '비밀번호가 일치하지 않습니다.';
					} else {
						passwordConfirmError.textContent = '';
					}
				});
			</script>
			<div class="form-group">
				<label for="nickname">닉네임</label> <input type="text" id="nickname"
					name="nickname" required>
			</div>

			<div class="form-group">
				<label for="email">이메일</label> <input type="email" id="email"
					name="email" required> <small id="emailError"
					class="text-danger"></small>
			</div>

			<script>
				const emailInput = document.getElementById('email');
				const emailError = document.getElementById('emailError');

				emailInput.addEventListener('input', function(event) {
					if (!emailInput.checkValidity()) {
						emailError.textContent = '올바른 이메일 형식으로 입력해주세요.';
					} else {
						emailError.textContent = '';
					}
				});
			</script>


			<%-- <!-- 전화인증 시작 -->

			<div class="form-group">
				<div class="container mt-3">
					<div>
						<label id="statusText" style="color: black;">인증이 필요합니다</label>
						<button type="button" class="btn btn-primary"
							data-bs-toggle="modal" data-bs-target="#myModal">전화인증</button>
					</div>

				</div>
			</div>

			<!-- The Modal -->
			<div class="modal" id="myModal">
				<div class="modal-dialog">
					<div class="modal-content">

						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">전화인증</h4>

							<button type="button" class="btn-close" data-bs-dismiss="modal" id="close-modal1"></button>
						</div>

						<!-- Modal body -->
						<div class="modal-body">
							<div class="form-group">
								<label for="phonenumber">전화번호</label> <input type="text"
									id="phonenumber" name="phonenumber" required>
							</div>
							<div class="form-group">
								<button id="btn-requestSMS">인증문자 보내기</button>
							</div>
							<input type="hidden" id="numStr"
								value="<%=session.getAttribute("numStr")%>">
							<div class="form-group">
								<label for="checknumber">인증번호입력</label> <input type="text"
									id="checknumber" name="checknumber" required>
							</div>
							<div class="form-group">
								<button id="btn-checknumber">인증번호 입력</button>
							</div>
						</div>

						<!-- Modal footer -->
						<div class="modal-footer center">
							<button type="button" class="btn btn-danger"
								data-bs-dismiss="modal" id="close-modal2">Close</button>
						</div>

					</div>
				</div>
			</div>

			<!-- 전화인증 끝 --> --%>

			<div class="form-group">
				<input class="form-control" style="width: 40%; display: inline;"
					placeholder="우편번호" name="zipcode" id="zipcode" type="text"
					readonly="readonly">
				<button type="button" class="btn btn-default"
					onclick="execPostCode();">
					<i class="fa fa-search"></i> 우편번호 찾기
				</button>
			</div>
			<div class="form-group">
				<input class="form-control" style="top: 5px;" placeholder="도로명 주소"
					name="address" id="address" type="text" readonly="readonly" />
			</div>
			<div class="form-group">
				<input class="form-control" placeholder="상세주소" name="addressDetail"
					id="addressDetail" type="text" />
			</div>

			<br>
			<div class="form-group">
				<button id="btn-join">회원가입</button>
			</div>

		</div>
	</div>

</body>
<script src="/js/coolSMS.js"></script>
<script src="/js/user.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>
</html>