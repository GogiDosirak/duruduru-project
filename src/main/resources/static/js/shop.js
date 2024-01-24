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
			productName : $("#productName").val(),
			productContent : $("#productContent").val(),
			productPrice : $("#productPrice").val(),
			productStock : $("#productStock").val()
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
			productSeq : $("#productSeq").val()
			};
		$.ajax({
			type : "DELETE",
			url : "/deleteProduct/" + deleteProductData.productSeq,
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