let usedBoardObject = {
	init: function() {
		let _this = this;
		
		$("#btn-deleteusedboard").on("click", () => {
			_this.delete()
		}), 
		
		$("#btn-usedInsertComment").on("click", () => {
			_this.insertComment()
		}),
		
		  $(document).on("click", ".btn-delete-comment", function() {
            // 클릭된 버튼의 데이터 속성에서 댓글 번호(co-seq)를 가져옴
            let co_seq = $(this).data("co-seq");

            // 댓글 삭제 함수 호출
            _this.deleteComment(co_seq);
         });
		
		
			
	},

	delete : function() {
		
		
		
		$.ajax({
		type: "DELETE",
		url: "/deleteUsedBoard/" + $("#usboSeq").val()

		
	}).done(function(response) {
		
		let message = response["data"];
		alert(message);
		
		if(response.status === 200) {
			location = "/usedboard";
		}
		
	}).fail(function(error) {
		let message = error["data"];
		alert("에러발생 : " + message);
	});
	
	
	},
	
	
	insertComment : function() {
		
		let data =  {
			usboCoContent : $("#usboCoContent").val()
		}
		
		$.ajax({
		type: "POST",
		url: "/insertUsedComment/" + $("#usboSeq").val(),
		data:JSON.stringify(data),
		contentType: "application/json; charset=utf-8"
		
	}).done(function(response) {
		
		let message = response["data"];
		alert(message);
		
		if(response.status === 200) {
			location = "/getUsedBoard/" + $("#usboSeq").val();
		}
		
	}).fail(function(error) {
		let message = error["data"];
		alert("에러발생 : " + message);
	});
	
	
	},
	
	
	deleteComment: function(co_seq) {
		
		
		$.ajax({
		type: "DELETE",
		url: "/deleteUsedComment/" + co_seq

		
	}).done(function(response) {
		
		let message = response["data"];
		alert(message);
		
		if(response.status === 200) {
			location = "/getUsedBoard/" + $("#usboSeq").val();
		}
		
	}).fail(function(error) {
		let message = error["data"];
		alert("에러발생 : " + message);
	});
	
	
	},
	

}
usedBoardObject.init();