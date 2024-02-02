<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    <div class="contents">
	<sec:authentication property="user.userid" var="currentid" />
	<!-- 2장이상의 사진을 저장하려면 enctype="multipart/form-data" 필수  -->
	<form action="/main/posting" method="post" enctype="multipart/form-data" autocomplete="off">
		<input type="hidden" id="userid" name="userid" value="${currentid}"> 
		<div class="form-group">
			<label for="file">파일 업로드 </label> 
			<input multiple="multiple" type="file" id="gdsImg" name="files" required />
		</div>
		<div class="miribogi">
			<div class="select_img">
				<img src=""> <!-- 이곳에 타이틀 사진이미지가 보인다 -->
			</div>
			<script>
			$("#gdsImg").change(
			function() {
				if (this.files && this.files[0]) {
					var reader = new FileReader;
					reader.onload = function(data) {
						$(".select_img img").attr("src", data.target.result).width(100);
					}
					reader.readAsDataURL(this.files[0]);
				}
			});
			</script>
			<button type="button" class="btn cancel_btn">접기</button>
			<script>
				$(".cancel_btn").click(function() {
					$(".miribogi").slideUp();
					$(".orderOpne_bnt").slideDown();
				});
			</script>
		</div>
		
		
		
		<div class="orderOpne">
			<button type="button" class="btn orderOpne_bnt">
				<span style="margin: 4px;">업로드 메인 사진보기</span>
			</button>
			<script>
				$(".orderOpne_bnt").click(function() {
					$(".miribogi").slideDown();
					$(".orderOpne_bnt").slideUp();
				});
			</script>
		</div>

		<div class="form-group">
			<input type="text" class="form-control w300" id="description"
				name="description" placeholder="문구 입력..." />
		</div>

		<div class="form-group">
			<input type="text" class="form-control w200" id="location"
				name="location" placeholder="위치 추가" />
		</div>
		<button class="btn btn-primary">완료</button>
	</form>
</div>


<a href="/insertSns">
	<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
</a>

		