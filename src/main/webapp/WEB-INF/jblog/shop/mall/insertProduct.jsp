<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>duruduru</title>
</head>
<body>
	<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>
	<div class="container mt-3">

		<h2>상품 등록</h2>
		<br>
		<center>
			<div class="col-md-8">
				<div class="mb-3 mt-3">
					<label for="title"></label> <input type="text" class="form-control"
						id="product_name" placeholder="상품명" name="product_name">
				</div>
			</div>
			<div class="col-md-8">
				<div class="mb-3 mt-3">
					<label for="product_price"></label> <input type="text"
						class="form-control" id="product_price" placeholder="상품가격"
						name="product_price">
				</div>
			</div>
			<div class="col-md-8">
				<div class="mb-3 mt-3">
					<label for="product_stock"></label> <input type="text"
						class="form-control" id="product_stock" placeholder="상품재고"
						name="product_stock">
				</div>
			</div>

<div class="col-md-8">
    <div class="mb-3">
        <label for="product_content"></label>
        <textarea id="product_content"></textarea>
        <script>
            $(document).ready(function() {
                $("#product_content").summernote({
                    height: 500
                });
            });
        </script>
        <style>
            #product_content+.note-editor .note-editable {
                text-align: left;
            }
        </style>
    </div>
</div>
		</center>
		<center>
			<button id="btn-insertProduct" class="btn btn-warning">상품등록</button>
		</center>
		<%@ include file="/WEB-INF/jblog/layout/footer.jsp"%>
		<script src="/js/shop.js"></script>
</body>
</html>