<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>My Spring WebSocket Chatting</title>
<link rel="stylesheet" href="/css/chatroom.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Cinzel:wght@400;500;600;700&family=Poppins:wght@200;300;400;500&display=swap"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">

<style>
#menu {
	width: 310px;
}

button#uploadFile {
	margin-left: 225px;
	margin-top: -55px;
}

input {
	padding-left: 5px;
	outline: none;
	width: 250px;
	margin-top: 15px;
}

.btn fa fa-download {
	background-color: DodgerBlue;
	border: none;
	color: white;
	padding: 12px 30px;
	cursor: pointer;
	font-size: 20px;
}

body {
	font-family: 'Jua', sans-serif; /* 변경할 폰트 지정 */
}

.input-group {
	width: 80.5%
}

.chat-container {
	position: relative;
}

.chat-container .btn-group {
	position: absolute;
	bottom: -12px;
	right: -50px;
	transform: translate(-50%, -50%);
}
</style>
</head>
<body>
	<noscript>
		<h2>Sorry! Your browser doesn't support Javascript</h2>
	</noscript>


	<div id="username-page">
		<div class="username-page-container">
			<h1 class="title">두루두루 채팅방</h1>
			<form id="usernameForm" name="usernameForm">
				<h2>${principal.nickname}님환영합니다</h2>
				<input type="hidden" id="name" autocomplete="off"
					class="form-control" value="${principal.nickname}" /> <input
					type="hidden" id="crSeq" autocomplete="off" class="form-control"
					value="${room.crSeq}" />
				<div class="form-group">
					<button type="submit" class="accent username-submit">입장하기</button>
				</div>
			</form>
		</div>
	</div>


	<div id="chat-page" class="hidden">
		<div class="btn-group dropend">
			<button class="btn btn-secondary dropdown-toggle" type="button"
				id="showUserListButton" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">참가한 유저</button>
			<div id="list" class="dropdown-menu"
				aria-labelledby="showUserListButton">
				<c:forEach var="user" items="${user}">
					<center>
						<div class="card">
							<div class="card-body">${user.user.nickname}</div>
							<c:if test="${principal.userSeq eq user.user.userSeq}">



								<button onclick="disconnected()">나가기</button>


							</c:if>
						</div>

					</center>
				</c:forEach>
			</div>
		</div>
		<div class="chat-container">
			<div class="chat-header">
				<div class="button-container">
					<!-- 왼쪽에 다음 버튼 -->
					<button class="back-button" onclick="location.href='/chat'">이전</button>
				</div>

				<h2>${room.crName}</h2>

				<div class="button-container">


					<button onclick="copyToClipboard()">채팅 코드</button>

					<form id="copyForm">
						<input type="hidden" id="copyInput" name="copyInput"
							value="${room.crSeq}">
					</form>

				</div>
			</div>

			<ul id="messageArea">

			</ul>
			<form id="messageForm" name="messageForm" nameForm="messageForm">
				<div class="form-group">
					<div class="input-group clearfix">
						<input type="hidden" id="userSeq" value="${principal.userSeq}" />
						<input type="text" id="message" placeholder="메세지를 입력해주세요"
							autocomplete="off" class="form-control" />
						<button type="submit" class="primary">Send</button>
					</div>
				</div>
			</form>

			<div class="btn-group dropend">
				<button class="btn btn-secondary dropdown-toggle" type="button"
					id="showMenu" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">파일 업로드</button>
				<div id="menu" class="dropdown-menu"
					aria-labelledby="dropdownMenuButton">
					<input type="file" id="file">
					<button type="button" class="btn btn-primary" id="uploadFile"
						onclick="uploadFile()">저장</button>

				</div>
			</div>
		</div>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="/js/Socket.js"></script>


</body>
</html>