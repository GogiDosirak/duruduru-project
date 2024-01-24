let freeBoardObject =  {
	
	init : function() {
		let _this = this;
		
		$("#btn-inserFreeBoard").on("click", () => {
			_this.insert();
		}),
		
		$("#btn-writeFreeBoard").on("click", () => {
			location = "/insertfreeboard"
		});
	},
	
	
	
	insert : function() {
		alert("글 등록이 요청되었습니다.");
		
		let data =  {
			frboTitle : $("#freeBoardTitle").val(),
			frboContent : $("#freeBoardContent").val()
		}
		
		$.ajax({
		type: "POST",
		url: "/insertfreeboard",
		data:JSON.stringify(data),
		contentType: "application/json; charest=utf-8"
		
	}).done(function(response) {
		
		let message = response["data"];
		alert(message);
		
		if(response.status === 200) {
			location = "/";
		}
		
	}).fail(function(error) {
		let message = error["data"];
		alert("에러발생 : " + message);
	});
	
	
	},
	
	
	
}


freeBoardObject.init();

