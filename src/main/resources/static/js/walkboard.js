
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
			location = "/updateWalkBoard/" + $("#waboSeq").val();
		}),
		$("#btn-deleteWaBoard").on("click",()=>{
			_this.delete();
		})



	},

	insert: function() {
		let post = {
			waboTitle: $("#walkBoardTitle").val(),
			waboContent: $("#walkBoardContent").val()
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
			waboSeq : $("#waboSeq").val(),
			waboTitle: $("#walkBoardTitle").val(),
			waboContent: $("#walkBoardContent").val()
		}
		
		$.ajax({
			type: "PUT",
			url: "/updateWalkBoard/"+post.waboSeq,
			data: JSON.stringify(post),
			contentType: "application/json; charset = utf-8"
		}).done(function() {
			alert("글 수정 완료");
			location = "/getWalkBoard/"+post.waboSeq;
		}).fail(function(error){
			let message = error["data"];
			alert("에러발생 : " + message)
		});
	},
	
	delete: function(){
		$.ajax({
			type: "DELETE",
			url: "/deleteWalkBoard/"+$("#waboSeq").val()
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