let insertProductObject = {
	init : function() {
		let _this = this;
		$("#btn-insertProduct").on("click", () => {
			_this.insertProduct();
			
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
	
	
	
}
insertProductObject.init();