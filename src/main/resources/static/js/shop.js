let ProductObject = {
	init : function() {
		let _this = this;
		$("#btn-insertProduct").on("click", () => {
			_this.insertProduct();
			
		}),
			$("#btn-deleteProduct").on("click", () => {
			_this.deleteProduct();
			
		});
		
		
	},
	
	insertProduct : function() {
		alert("글등록 요청됨");
		let insertProductData = {
			product_name : $("#product_name").val(),
			product_content : $("#product_content").val(),
			product_price : $("#product_price").val(),
			product_stock : $("#product_stock").val()
		}
		
		$.ajax({
			type : "POST",
			url : "/insertProduct",
			data : JSON.stringify(insertProductData),
			contentType : "application/json; charset=utf-8"
			
		}).done(function(response){
			let message = response["data"];
			alert(message);
			location = "/mall"    // /auth/getBoardList
		}).fail(function(error){
			let message = error["data"];
			alert(	"에러 발생 : " + message);
			
		});
		
		
	},
	
		deleteProduct : function() {
		alert("글삭제 요청됨");
		let deleteProductData = {
			product_seq : $("#product_seq").val()
			};
		$.ajax({
			type : "DELETE",
			url : "/deleteProduct/" + deleteProductData.product_seq,
			data : JSON.stringify(deleteProductData),
			contentType : "application/json; charset=utf-8"
			
		}).done(function(response){
			let message = response["data"];
			alert(message);
			location = "/mall"   
		}).fail(function(error){
			let message = error["data"];
			alert(	"에러 발생 : " + message);
			
		});
		
		
	},
	
	
	
}
ProductObject.init();