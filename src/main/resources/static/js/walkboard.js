
let walkBoardObject = {

	init: function() {

		let _this = this;

		$("#btn-insertWalkBoard").on("click", () => {
			_this.insert();
		}),
		$("#btn-writeWalkBoard").on("click",()=>{
			location = "/insertWalkBoard"
		}),
		$("#btn-updateWaBoard").on("click",()=>{
			_this.update();
		}),
		$("#go-updateWaBoard").on("click",()=>{
			location = "/updateWalkBoard/" + $("#wabo_seq").val();
		}),
		$("#btn-deleteWaBoard").on("click",()=>{
			_this.delete();
		})



	},

	insert: function() {
		let post = {
			wabo_title: $("#walkBoard_title").val(),
			wabo_content: $("#walkBoard_content").val()
		}

		$.ajax({
			type: "POST",
			url: "/insertWalkBoard",
			data: JSON.stringify(post),
			contentType: "application/json; charset = utf-8"
		}).done(function(response) {
			alert(response.data);
			location = "/walk";
		}).fail(function(error){
			let message = error["data"];
			alert("에러발생 : " + message)
		});

	},
	
	update: function(){
		
		let post = {
			wabo_seq : $("#wabo_seq").val(),
			wabo_title: $("#walkBoard_title").val(),
			wabo_content: $("#walkBoard_content").val()
		}
		
		$.ajax({
			type: "PUT",
			url: "/updateWalkBoard/"+post.wabo_seq,
			data: JSON.stringify(post),
			contentType: "application/json; charset = utf-8"
		}).done(function() {
			alert("글 수정 완료");
			location = "/getWalkBoard/"+post.wabo_seq;
		}).fail(function(error){
			let message = error["data"];
			alert("에러발생 : " + message)
		});
	},
	
	delete: function(){
		$.ajax({
			type: "DELETE",
			url: "/deleteWalkBoard/"+$("#wabo_seq").val()
		}).done(function() {
			alert("글 삭제 완료");
			location = "/walk";
		}).fail(function(error){
			let message = error["data"];
			alert("에러발생 : " + message)
		});
	}
	
	

}
walkBoardObject.init();