<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp" %>
<title>${getProduct.product_name}- duruduru</title>
<style>
<title>${getProduct.product_name} - duruduru</title>
    <style>
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
        hr {
            border-top: 2px solid #ccc;
            margin: 1.5rem 0;
        }
img {
	max-width: 100%;
	height: auto;
	#product_content+.note-editor .note-editable {
	text-align: left;
}

} 
</style>
</head>
<body>
    <div class="logo center-align">
        <a href="/">
            <img src="/images/duruduru.png" alt="로고 이미지">
        </a>
    </div>
	<div class="logo center-align">
	</div>
	<hr>
	<div class="container mt-3">
<center>
		<h2>상품 수정</h2>
		</center>
		<br>
		<center>
			<form action="/updateProduct/${getProduct.product_seq }" method="post"
				enctype="multipart/form-data">
				<div class="col-md-8">
					<div class="mb-3 mt-3">
						<label for="product_name"></label> <input type="text"
								class="form-control" id="product_name" 
							name="product_name" value="${getProduct.product_name } (상품명)")>
					</div>
					<div class="mb-3">
						<label for="product_price"></label> <input type="text"
							class="form-control" id="product_price" value="${getProduct.product_price } (가격)"
							name="product_price">
					</div>
					<div class="mb-3">
						<label for="product_stock"></label> <input type="text"
							class="form-control" id="product_stock" value="${getProduct.product_stock } (재고)"
							name="product_stock">
					</div>
				</div>
				<div class="col-md-8">
					<div class="mb-3">
						<textarea id="product_content" name="product_content">${getProduct.product_content }</textarea>
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
								$("#product_content").summernote({
									height : 500
								});
							});
						</script>

					</div>

					<label for="file">
						<div class="btn-upload">썸네일 업로드하기</div>
						<br>
						<div id="file-name-display"></div>
					</label> <input type="file" name="file" id="file"
						onchange="displayFileName(this)">
					<!-- 여기서의 name과 매개변수의 MultipartFile 이름은 일치시켜줘야함  -->
				</div>
	</div>
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
	<br>
	</center>
	<center>
		<button type="submit" class="btn btn-warning">상품수정</button>

	</center>
	</form>
	<script src="/js/shop.js"></script>
</body>
<%@ include file="/WEB-INF/jblog/layout/footer.jsp" %>
</html>