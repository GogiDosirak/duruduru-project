<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
/* 화살표 색상 */
/* Custom styling for carousel control icons */
.carousel-control-prev-icon, .carousel-control-next-icon {
	background-color: transparent; /* Make the background transparent */
	border: none; /* Remove border */
	font-size: 7rem; /* Adjust the font size as needed */
}

.carousel-control-prev-icon::before, .carousel-control-next-icon::before
	{
	content: '\2039'; /* Unicode character for left-pointing arrow */
	color: black; /* Arrow color */
}

.carousel-control-next-icon::before {
	content: '\203A'; /* Unicode character for right-pointing arrow */
}
/* 화살표 색상 끝 */
.image-container {
	text-align: center;
}

.image-container img {
	max-width: 500px; /* 최대 너비 500px */
	max-height: 500px; /* 최대 높이 500px */
	width: auto;
	height: auto;
}

.carousel-inner img {
	max-width: 500px; /* 최대 너비 500px */
	max-height: 500px; /* 최대 높이 500px */
	width: auto;
	height: auto;
}
#scrollToTop {
    position: fixed;
    bottom: 20px;
    right: 20px;
    display: none;
    cursor: pointer;
    width: 40px; /* 이미지의 가로 크기 */
    height: 40px; /* 이미지의 세로 크기 */
    background-image: url('images/pointingup.png'); /* 이미지 경로 */
    background-size: cover; /* 이미지를 요소에 맞게 조절 */
    background-color: transparent;
    border: none;
    transition: background-color 0.3s;
    
}#scrollToTop.visible {
    display: block;
}
</style>

<div class="container mt-3">
	<h2>
		<a href="/sns"> 두루두루 SNS</a>
	</h2>
	<br>

	<h5>
		<a href="/insertSNS" class="btn btn-warning">글등록 하기</a>
	</h5>


		<!-- 상단 랭킹 슬라이드 -->
		<!-- Carousel -->
		<div id="demo" class="carousel slide" data-bs-ride="carousel"
			align="center">
			<h1>우리동네 최강 귀요미들 랭킹 TOP3</h1>
			<br>
			<!-- Indicators/dots -->
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#demo" data-bs-slide-to="0"
					class="active"></button>
				<button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
				<button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
			</div>

			<!-- The slideshow/carousel -->

			<div class="carousel-inner" align="center">
				<c:forEach var="sns" items="${likeCntList}" varStatus="loop" end="2">
					<div class="carousel-item${loop.first ? ' active' : ''}">
						<h5>${sns.user.nickname }님네귀요미</h5>
						<img alt="pet-item" src="${sns.filepath}"
							class="img-fluid sns-pet-image" data-snsbo-seq="${sns.snsboSeq}">
					</div>
				</c:forEach>
			</div>

			<!-- Left and right controls/icons -->
			<button class="carousel-control-prev" type="button"
				data-bs-target="#demo" data-bs-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#demo" data-bs-slide="next">
				<span class="carousel-control-next-icon"></span>
			</button>
		</div>

		<!-- 상단 랭킹 슬라이드 끝 -->

		<br>
		<br>
		<br>
		<hr>
		<!-- sns 게시글 리스트 시작 -->
		<c:forEach var="sns" items="${SNSList}">
			<div class="container d-flex align-items-center justify-content-center"
				style="min-height: 100vh;" id="${sns.snsboSeq}">
				<div class="text-center">
					<div class="image-container mx-auto">
						<img src="${sns.filepath}">
					</div>
					<br>
					<div class="text-container">
						<h3>${sns.snsboContent }</h3>
						<c:set var="isLike" value="-1" />
						<c:forEach var="like" items="${likeList}">
							<c:if test="${like.snsBoard.snsboSeq eq sns.snsboSeq}">
								<c:if test="${principal.userSeq eq like.user.userSeq }">
									<c:set var="isLike" value="${like.liboSeq }" />
								</c:if>
							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${isLike eq -1}">
								<button type="button"
									class="btn btn-warning btn-sm btn-insertLike"
									data-like-seq="${sns.snsboSeq}">좋아요</button>
							</c:when>
							<c:otherwise>
								<button type="button"
									class="btn btn-warning btn-sm btn-deleteLike"
									data-unlike-seq="${isLike }"
									data-unlike-snsbo-seq="${sns.snsboSeq }">좋아요취소</button>

							</c:otherwise>
						</c:choose>
						좋아요 : ${sns.likeCnt} <br> <br>
						<p>작성자: ${sns.user.nickname }</p>
						<br> <br> <br>
						<c:if
							test="${fn:trim(principal.userid) == fn:trim(sns.user.userid)}">
							<a href="/updateSns/${sns.snsboSeq} " class="btn btn-warning">수정하기</a> &nbsp;&nbsp;&nbsp;
    			<button type="button" class="btn btn-danger btn-delete-snsbo"
								data-sns-seq="${sns.snsboSeq}">삭제하기</button>
						</c:if>

						<br> <br>
						<!-- 댓글 시작 -->
						<div class="container mt-3">
							<button type="button" class="btn btn-outline-secondary"
								data-bs-toggle="collapse" data-bs-target="#demo${sns.snsboSeq}">댓글열기</button>

							<div id="demo${sns.snsboSeq}" class="collapse">
								<div class="container mt-3">
									<h2>&nbsp; 댓글 등록</h2>
									<div class="comment-container">
										<table class="table">
											<thead>
												<tr>
													<td align="right">
														<div class="comment-input-container">
															<textarea rows="1" cols="99" placeholder="댓글을 입력하세요"></textarea>
															<button type="button"
																class="btn btn-warning btn-sm btn-snsBoardInsertComment"
																data-co-seq="${sns.snsboSeq }">댓글등록</button>
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

												<c:forEach var="snsComment" items="${snsCommentList}">
													<c:if
														test="${sns.snsboSeq == snsComment.snsBoard.snsboSeq}">
														<tr>
															<td>${snsComment.user.nickname}</td>
															<td>${snsComment.snsboCoContent}</td>
															<td>${snsComment.snsboCoDate}</td>
															<td><c:if
																	test="${principal.userid eq snsComment.user.userid}">
																	<button type="button"
																		class="btn btn-danger btn-sm btn-delete-comment"
																		data-co-seq="${snsComment.snsboCoSeq}"
																		style="padding: 1px 1px;">삭제하기</button>
																</c:if></td>
														</tr>
													</c:if>
												</c:forEach>
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>
						<!-- 댓글 끝 -->
					</div>
				</div>
			</div>
			<hr>
		</c:forEach>
		<!-- sns 게시글 리스트 끝 -->

<!-- 스크롤 시작 -->
 <div id="scrollToTop" onclick="scrollToTop()">
    <script>
    /* 우하단 스크롤 화살표 시작*/
        // Show/hide scroll-to-top button based on scroll position
        window.onscroll = function() {
            showScrollToTopButton();
        };

        // Function to show/hide scroll-to-top button
        function showScrollToTopButton() {
            var scrollToTopButton = document.getElementById("scrollToTop");
            if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                scrollToTopButton.style.display = "block";
            } else {
                scrollToTopButton.style.display = "none";
            }
        }

        // Function to scroll to the top
        function scrollToTop() {
            document.body.scrollTop = 0;
            document.documentElement.scrollTop = 0;
        }
        /* 우하단 스크롤 화살표 끝*/
        
        /* 좋아요 눌렀을 때 그 화면에서 로딩되기 시작 */
        document.addEventListener("DOMContentLoaded", function() {
            // 페이지 로드 시 저장된 스크롤 위치가 있다면 저장된 위치로 스크롤 위치 설정
            var storedScrollTop = localStorage.getItem('scrollTop');
            if (storedScrollTop) {
                document.documentElement.style.scrollBehavior = 'auto'; // 스크롤 부드러움 제거
                window.scrollTo(0, storedScrollTop);
                document.documentElement.style.scrollBehavior = 'smooth'; // 원래 스크롤 부드러움으로 복원
            }
        });

        // 특정 이벤트(예: 페이지 언로드) 발생 시 스크롤 위치 저장
        window.onbeforeunload = function() {
            // 페이지 언로드 시 현재 스크롤 위치를 저장
            localStorage.setItem('scrollTop', window.scrollY);
            /* 좋아요 눌렀을 때 그 화면에서 로딩되기 끝 */
        };
    </script>
</div>
<!-- 스크롤 끝 -->
</div>
	</body>
	</html>
	<script src="/js/sns.js"></script>
	<%@ include file="/WEB-INF/jblog/layout/footer.jsp"%>