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
		
		  $(document).on("click", ".btn-delete-comment", function() {
            // 클릭된 버튼의 데이터 속성에서 댓글 번호(co-seq)를 가져옴
            let co_seq = $(this).data("co-seq");

            // 댓글 삭제 함수 호출
            _this.deleteComment(co_seq);
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
		
		let data =  {
			frboCoContent : $("#frboCoContent").val()
		}
		
		$.ajax({
		type: "POST",
		url: "/insertFreeComent/" + $("#frboSeq").val(),
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
	
	
	deleteComment: function(co_seq) {
		alert("댓글 삭제가 요청되었습니다." )
		
		
		$.ajax({
		type: "DELETE",
		url: "/deleteFreeComment/" + co_seq

		
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

