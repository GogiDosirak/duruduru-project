<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>duruduru</title>
<style>
img {
	max-width: 100%;
	height: auto;
}

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
 #rightClickMenu {
            display: none;
            position: fixed;
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 10px;
        }
</style>
</head>

<input id="wareSeq" type="hidden" value="${walkReservation.wareSeq}">
<div class="logo center-align">
	<a href="/"> <img src="/images/duruduru.png" alt="로고 이미지">
	</a>
</div>
<hr>
<h2 class="center-align"> 산책모임게시판</h2>
<div class="container">
	<h2 id="title" align="center">${walkReservation.wareTitle}</h2>
	
	
	<!-- 1대1 채팅 시작 -->
	<h3>${walkReservation.user.nickname}</h3>
	<input type="hidden" name="userSeq" id="userSeq"
		value="${walkReservation.user.userSeq}">
	<button type="button" class="btn btn-outline-dark"
		onclick="doSomething()" style="float: right;">1:1대화하기</button>

	<script>
		function openNewWindow(crSeq) {
			var newWindowUrl = "/chat/room/" + crSeq;
			window.open(newWindowUrl, '_blank', 'width=1100,height=700');
		}

		function doSomething() {
			let userSeq = $("#userSeq").val();
			console.log("userSeq 값: ", userSeq);
			$.ajax({
				type : "post",
				url : "/chat/onebyone/" + userSeq,
				success : function(result) {
					console.log("동작완료")

					let crSeq = result;
					console.log("crSeq 값:", crSeq);
					openNewWindow(crSeq);

				}
			})
		}
	</script>
	<!-- 1대1 채팅 끝 -->
	<h6>날짜 : ${walkReservation.wareDate}&nbsp;&nbsp; 조회수 : ${walkReservation.wareCnt}</h6>
	<hr>

	<div class="details">

		<h4 id="content">${walkReservation.wareContent}</h4>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>
	</div>

</div>

<div align="center">
	<a href="/walkReservation"><h5>뒤로가기</h5></a>
</div>

<br>

<div align="center">
	<c:if test="${principal.userid eq walkReservation.user.userid  or principal.role eq 'ADMIN'}">
		<button type="button" id="go-updateWalkReservation" class="btn btn-warning">글
			수정하기</button>
		<button type="button" id="btn-deleteWalkReservation" class="btn btn-danger">글
			삭제하기</button>
	</c:if>
</div>

<hr>

<div class="container mt-3">
	<h2>&nbsp; 댓글 등록</h2>
	<div class="comment-container">
		<table class="table">
			<thead>
				<tr>
					<td align="right">
						<div class="comment-input-container">
							<textarea rows="1" cols="99" id="wareCoContent"
								placeholder="댓글을 입력하세요"></textarea>
							<button id="btn-WalkReservationInsertComment" class="btn btn-warning">댓글
								등록</button>
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
					<td>작성일</td>
					<td></td>
				</tr>

				<c:forEach var="walkReservationComment" items="${walkReservationCommentList}">
					<c:if
						test="${walkReservation.wareSeq == walkReservationComment.walkReservation.wareSeq}">
						<tr>
							<td>${walkReservationComment.user.nickname}</td>
							<td>${walkReservationComment.wareCoContent}</td>
							<td>${walkReservationComment.wareCoDate}</td>
							<td><c:if
									test="${principal.userid eq walkReservationComment.user.userid or principal.role eq 'ADMIN'}">
									<button type="button"
										class="btn btn-danger btn-sm btn-delete-comment"
										data-co-seq="${walkReservationComment.wareCoSeq}"
										style="padding: 1px 1px;">삭제하기</button>
								</c:if></td>
						</tr>
					</c:if>
				</c:forEach>
			</thead>
		</table>
	</div>
</div>
</html>
<script src="/js/walkReservation.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>