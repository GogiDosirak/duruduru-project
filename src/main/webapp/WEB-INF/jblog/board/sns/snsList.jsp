<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="post">
	<div class="nav">
		<span class="title"> 
			<a href="/main" class="title_ft">Outstagram</a>
		</span> 
		<a href="/main">
			<span class="glyphicon glyphicon-send" aria-hidden="true"></span>
		</a>
	</div>

	<c:forEach var="p" items="${posting}"> <!-- java의 for문과 같다 posting list를 for문돌림 -->
		<div class="r">
			<div class="ii">
				
				
			</div>
			<div id="gallery_wrap">
				<ul class="slide_gallery">
					<c:forEach var="img" items="${img}">
						
							<li><img src="/images/${principal.userid}/${img.filename}" class="imgg"></li>
						
					</c:forEach>
				</ul>
			</div>
			
			
			<div class="bar">
				<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span> <!-- 이부분은 나중에 하트 설정할때 할거다 --> 
				<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
			</div>
			<div class="write">
				<span>${p.description}</span>
			</div>
		</div>
	</c:forEach>

</div>