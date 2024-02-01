<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/jblog/layout/header.jsp"%>


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

/* 썸네일 */
#product_content+.note-editor .note-editable {
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
/* 썸네일 끝 */
</style>

<div class="container">
<div class="login-container">
<img id="duruduru-image" src="images/duruduru.png"
				alt="Duruduru Logo">
				<br><br>
<form id="petForm" action="/insertSNS" method="post"
	enctype="multipart/form-data">
	<!-- 사진등록 -->
	<div align="center">
		<label for="file">
			<div class="btn-upload">SNS사진 업로드하기</div> <br>
			<div id="file-name-display"></div>
		</label> <input type="file" name="file" id="file"
			onchange="displayFileName(this)">
	</div>
	<script>
		// 파일명을 표시하는 함수
		function displayFileName(input) {
			// 선택된 파일이 있는지 확인
			if (input.files.length > 0) {
				// 파일명을 가져와서 표시
				var fileName = input.files[0].name;
				document.getElementById('file-name-display').innerText = '선택된 파일: '
						+ fileName;
			} else {
				// 파일이 선택되지 않았을 경우 메시지 지우기
				document.getElementById('file-name-display').innerText = '';
			}
		}
	</script>
	<!-- 사진등록 끝 -->
	<div class="form-group">
		<label for="snsboContent">문구 작성</label> <input type="text" id="snsboContent"
			name="snsboContent" required>
	</div>
	<br>
	<div class="form-group">
		<button id="btn-insertSNS">SNS 게시글 작성</button>
	</div>
</form>
</div>
</div>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>