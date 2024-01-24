<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>

<br>
<center>
	<h2>산책게시판 글등록</h2>
	<div class="col-md-8">
		<div class="mb-3 mt-3">
			<label for="title"></label> <input type="text" class="form-control"
				id="walkBoard_title" placeholder="제목을 입력하세요" name="walkBoard_title">
		</div>
	</div>


	<div class="col-md-8">
		<div class="mb-3">
			<label for="walkBoard_content"></label>
			<textarea id="walkBoard_content"></textarea>
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
				$(document).ready(
						function() {
							$("#walkBoard_content").summernote({
								height : 500,
								placeholder : "내용을 입력하세요"
							}).siblings('.note-editor').find('.note-editable')
									.css('text-align', 'left');
						});
			</script>

			<style>
			
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
</style>
		</div>
	</div>
</center>
<center>
	<button id="btn-insertWalkBoard" class="btn btn-warning">글등록</button>
</center>

<script src="/js/walkBoard.js"></script>

<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>
