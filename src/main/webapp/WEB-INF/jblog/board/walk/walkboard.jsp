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
</style>
</head>

<input id="waboSeq" type="hidden" value="${walkBoard.waboSeq}">
<div class="logo center-align">
	<a href="/"> <img src="/images/duruduru.png" alt="로고 이미지">
	</a>
</div>
<hr>
<h2 class="center-align">산책게시판</h2>
<div class="container">
	<h2 id="title" align="center">${walkBoard.waboTitle}</h2>
	<h3>${walkBoard.user.nickname}</h3>
	<h6>날짜 : ${walkBoard.waboDate} 조회수 : ${walkBoard.waboCnt}</h6>
	<hr>

	<div class="details">

		<h4 id="content">${walkBoard.waboContent}</h4>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>
	</div>

</div>

<div align="center">
	<a href="/walk"><h5>뒤로가기</h5></a>
</div>

<br>

<div align="center">
	<c:if test="${principal.userid eq walkBoard.user.userid}">
		<button type="button" id="go-updateWaBoard" class="btn btn-warning">글
			수정하기</button>
		<button type="button" id="btn-deleteWaBoard" class="btn btn-danger">글
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
							<textarea rows="1" cols="99" id="waboCoContent"
								placeholder="댓글을 입력하세요"></textarea>
							<button id="btn-WalkBoardInsertComment" class="btn btn-warning">댓글
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

				<c:forEach var="walkBoardComment" items="${walkBoardCommentList}">
					<c:if
						test="${walkBoard.waboSeq == walkBoardComment.walkBoard.waboSeq}">
						<tr>
							<td>${walkBoardComment.user.nickname}</td>
							<td>${walkBoardComment.waboCoContent}</td>
							<td>${walkBoardComment.waboCoDate}</td>
							<td><c:if
									test="${principal.userid eq walkBoardComment.user.userid}">
									<button type="button"
										class="btn btn-danger btn-sm btn-delete-comment"
										data-co-seq="${walkBoardComment.waboCoSeq}"
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
<script src="/js/walkboard.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>