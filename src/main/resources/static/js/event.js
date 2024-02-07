let eventBoardObject = {
	
	init : function() {
		let _this = this;
		
		$("#btn-inserteventBoard").on("click", () => {
			_this.insert();
		}),
		
		$("#btn-writeEventBoard").on("click", () => {
			location = "/insertEvent"
		}),
		
		$("#go-updateEvnetboard").on("click", () => {
			location = "/updateEventBoard/" + $("#eventSeq").val()
		}),
		
		$("#btn-updateEventBoard").on("click", () => {
			_this.update();
		}),
		
		$("#btn-deleteEventboard").on("click", () => {
			_this.delete();
		})
		
		
	},
	
	
	insert : function() {
		alert("글 등록이 요청되었습니다.");
		
		let data = {
			eventTitle : $("#eventTitle").val(),
			eventContent : $("#eventContent").val()
		}
		
		$.ajax({
			type: "POST",
			url: "/insertEvent",
			data: JSON.stringify(data),
			contentType: "application/json; charset=uft-8"
		}).done(function(response) {
			
			let message = response["data"];
			alert(message);
			
			if(response.status == 200) {
				location = "/event";
			}
			
		}).fail(function(error) {
			let message = error["data"];
			alert("에러발생 : " + message);
		});
		
	},
	
	
	
	update : function() {
		alert("글 수정이 요청되었습니다.");
		
		let data = {
			eventTitle : $("#eventTitle").val(),
			eventContent : $("#eventContent").val()
		}
		
		$.ajax({
			type: "PUT",
			url: "/updateEventBoard/" + $("#eventSeq").val(),
			data: JSON.stringify(data),
			contentType: "application/json; charset=uft-8"
		}).done(function(response) {
			
			let message = response["data"];
			alert(message);
			
			if(response.status == 200) {
				location = "/getEventBoard/" + $("#eventSeq").val();
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
		url: "/deleteEventBoard/" + $("#eventSeq").val()

		
	}).done(function(response) {
		
		let message = response["data"];
		alert(message);
		
		if(response.status === 200) {
			location = "/event";
		}
		
	}).fail(function(error) {
		let message = error["data"];
		alert("에러발생 : " + message);
	});
	
	
	},
	
	
	
	
	
	
}

eventBoardObject.init();