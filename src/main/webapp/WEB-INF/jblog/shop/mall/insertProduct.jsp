

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>duruduru</title>
</head>
<body>
	<style>
.logo img {
	max-width: 200px;
	height: auto;
}

.center-align {
	text-align: center;
}

hr {
	border-top: 2px solid #ccc;
	margin: 1.5rem 0;
}
</style>
	<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>
	<div class="container mt-3">
		<div class="logo center-align">
			<a href="/"> <img src="/images/duruduru.png" alt="로고 이미지">
			</a>
		</div>
		<div class="logo center-align"></div>
		<center>

			<hr>
			<h2>상품 등록</h2>
			<br>
			<center>
				<form action="/insertProduct" method="post"
					enctype="multipart/form-data">
					<div class="col-md-8">
						<div class="mb-3 mt-3">
							<label for="productName"></label> <input type="text"
								class="form-control" id="productName" placeholder="상품명"
								name="productName">
						</div>
						<div class="mb-3">
							<label for="productPrice"></label> <input type="text"
								class="form-control" id="productPrice" placeholder="상품가격"
								name="productPrice">
						</div>
						<div class="mb-3">
							<label for="productStock"></label> <input type="text"
								class="form-control" id="productStock" placeholder="상품재고"
								name="productStock">
						</div>
					</div>
					<div class="col-md-8">
						<div class="mb-3">
							<textarea id="productContent" name="productContent"></textarea>
							<script>
								// 파일명을 표시하는 함수
								function displayFileName(input) {
									// 선택된 파일이 있는지 확인
									if (input.files.length > 0) {
										// 파일명을 가져와서 표시
										var fileName = input.files[0].name;
										document
												.getElementById('file-name-display').innerText = '선택된 파일: '
												+ fileName;
									} else {
										// 파일이 선택되지 않았을 경우 메시지 지우기
										document
												.getElementById('file-name-display').innerText = '';
									}
								}
								$(document).ready(function() {
									$("#productContent").summernote({
										height : 500
									});
								});
							</script>

						</div>
						<style>
#productContent+.note-editor .note-editable {
	text-align: left;
}

.btn-upload {
	width: 150px;
	height: 30px;
	background: #fff;
	border: 1px solid rgb(77, 77, 77);
	border-radius: 10px;
	font-weight: 500;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center; &: hover { background : rgb( 77, 77, 77);
	color: #fff;
}

}
#file {
	display: none;
}
</style>
						<label for="file">
							<div class="btn-upload">썸네일 업로드하기</div> <br>
							<div id="file-name-display"></div>
						</label> <input type="file" name="file" id="file"
							onchange="displayFileName(this)">
						<!-- 여기서의 name과 매개변수의 MultipartFile 이름은 일치시켜줘야함  -->
					</div>
	</div>
	<br>
	</center>
	<center>
		<button type="submit" class="btn btn-warning">상품등록</button>

	</center>
	</form>
	<%@ include file="/WEB-INF/jblog/layout/footer.jsp"%>
	<script src="/js/shop.js"></script>

</body>
</html>