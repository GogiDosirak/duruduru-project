let freeBoardObject =  {
	
	init : function() {
		let _this = this;
		
		$("#btn-insertFreeBoard").on("click", () => {
			_this.insert();
		}),
		
		$("#btn-writeFreeBoard").on("click", () => {
			location = "/insertfreeboard"
		}),
		
		$("#go-updatefreeoard").on("click", () => {
			location = "/updateFreeBoard/" + $("#frboSeq").val()
		}), 
		
		$("#btn-updateFreeBoard").on("click", () => {
			_this.update();
		}),
		
		$("#btn-deletefreeboard").on("click", () => {
			_this.delete();
		}), 
		
		$("#btn-FreeInsertComment").on("click", () => {
			_this.insertComment();
		}),
		
		$("#btn-deletefreeComment").on("click", () => {
			_this.deleteComment();
		})
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
			location = "/freeboard";
		}
		
	}).fail(function(error) {
		let message = error["data"];
		alert("에러발생 : " + message);
	});
	
	
	},
	
	
	
	update : function() {
		alert("글 수정이 요청되었습니다.");
		
		let data =  {
			frboTitle : $("#freeBoardTitle").val(),
			frboContent : $("#freeBoardContent").val(),
			frboSeq : $("#frboSeq").val()
		}
		
		$.ajax({
		type: "PUT",
		url: "/updateFreeBoard/" + data.frboSeq,
		data:JSON.stringify(data),
		contentType: "application/json; charest=utf-8"
		
	}).done(function(response) {
		
		let message = response["data"];
		alert(message);
		
		if(response.status === 200) {
			location = "/getfreeboard/" + data.frboSeq;
		}
		
	}).fail(function(error) {
		let message = error["data"];
		alert("에러발생 : " + message);
	});
	
	
	},
	
	
	
	delete : function() {
		alert("글 삭제가 요청되었습니다.");
		
		
		$.ajax({
		type: "DELETE",
		url: "/deleteFreeBoard/" + $("#frboSeq").val()

		
	}).done(function(response) {
		
		let message = response["data"];
		alert(message);
		
		if(response.status === 200) {
			location = "/freeboard";
		}
		
	}).fail(function(error) {
		let message = error["data"];
		alert("에러발생 : " + message);
	});
	
	
	},
	
	
	insertComment : function() {
		alert("댓글 등록이 요청되었습니다.");
		
		let data =  {
			frboCoContent : $("#frboCoContent").val()
		}
		
		$.ajax({
		type: "POST",
		url: "/insertFreeComent",
		data:JSON.stringify(data),
		contentType: "application/json; charset=utf-8"
		
	}).done(function(response) {
		
		let message = response["data"];
		alert(message);
		
		if(response.status === 200) {
			location = "/getfreeboard/" + $("#frboSeq").val();
		}
		
	}).fail(function(error) {
		let message = error["data"];
		alert("에러발생 : " + message);
	});
	
	
	},
	
	
	deleteComment: function() {
		alert("댓글 삭제가 요청되었습니다.");
		
		
		$.ajax({
		type: "DELETE",
		url: "/deleteFreeComment/" + $("#frboCoSeq").val()

		
	}).done(function(response) {
		
		let message = response["data"];
		alert(message);
		
		if(response.status === 200) {
			location = "/getfreeboard/" + $("#frboSeq").val();
		}
		
	}).fail(function(error) {
		let message = error["data"];
		alert("에러발생 : " + message);
	});
	
	
	},
	
	
	
	
}


freeBoardObject.init();

