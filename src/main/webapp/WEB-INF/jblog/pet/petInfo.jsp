<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jblog/layout/header.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>PetInfo</title>
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

.pet-image {
	max-width: 150px;
	max-height: 150px;
	width: auto;
	height: auto;
}

.content-wrapper {
	display: flex;
	justify-content: space-between;
}

.image-holder {
	flex: 0 0 auto; /* 이미지의 크기를 유지하고 좌측 정렬 */
	margin-right: 20px; /* 이미지와 나머지 컨텐츠 사이의 여백 설정 */
}

.form-group {
	flex: 1 1 auto; /* 나머지 공간을 차지하도록 설정 */
}
/* 수정된 스타일 끝 */

</style>

</head>

<body>
	<br>
	<br>
	<div class="container col-md-8 col-sm-8 col-xs-8">
		<div class="sidebar">
			<a href="/mypage" onclick="/mypage">내 정보</a> <a
				href="/petInfo/${principal.userSeq}">반려동물 정보</a> <a href="/mywritten"
				onclick="/mywritten">내가 쓴 글</a> <a href="/orderHistory"
				onclick="/orderHistory">주문내역</a> <a href="#"
				onclick="loadPage('exchangeRefund')">교환 및 환불</a>
		</div>
		<div class="login-container ">
			<center>
				<img id="duruduru-image" src="/images/duruduru.png"
					alt="Duruduru Logo">
			</center>

			<!-- 여기까지 로고 -->
			<form>
				<br> <input type="hidden" id="userSeq" name="userSeq"
					value="${principal.userSeq }">
				<div class="form-group">
					<h5>${principal.nickname }님의 귀여운 동물친구</h5>
				</div>
<br>
				<c:forEach var="pet" items="${myPetList}">
					<div class="content-wrapper">
						<div class="image-holder">
							<img alt="pet-item" src="${pet.petFilepath }"
								class="pet-image img-fluid">
						</div>
						<div class="form-group">
							<label for="nickname">동물 이름</label> ${pet.petName }
						</div>
						<div class="form-group">
							<label for="nickname">동물 종류</label> ${pet.petType }
							<!-- 동물정보 수정 버튼 추가 -->
							<br> <br>
							<div class="form-group">
								<button type="button" class="btn-updatePet" data-pet-seq="${pet.petSeq}">동물정보 수정</button>
							</div>
						</div>
						<div class="form-group">
							<label for="nickname">동물 생일</label> ${pet.petBirthday } <br>
							<br>

							<div class="form-group">
								<button type="button" class="btn-deletePet" data-pet-seq="${pet.petSeq}">동물정보 삭제</button>
							</div>
						</div>
					</div>
					<br>
					<hr>
					<br>
				</c:forEach>
			</form>
			<br>
			<div class="form-group">
				<button type="button" id="btn-insertPet">동물정보 입력</button>
			</div>
		</div>
	</div>
	

	<br>
</body>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>
<script src="/js/pet.js"></script>
</html>