
let petObject = {
	init: function() {
		let _this = this;

		$("#btn-insertPet").on("click", () => {
			location = "/insertPet";
		});
		
		$(document).on("click", ".btn-deletePet",function(){
			let petSeq = $(this).data("petSeq");
			 console.log(petSeq); // 값을 출력해보세요
			_this.deletePet(petSeq);
		})
	},
	
		deletePet: function(petSeq) {
		$.ajax({
			type: "DELETE",
			url: "/deletePet/" + petSeq
		}).done(function(response) {
			alert(response.data);
			location.reload(); // 현재 페이지 새로고침
		}).fail(function(error) {
			let message = error["data"];
			alert("에러발생 : " + message)
		});
	}
};

petObject.init();