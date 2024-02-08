
let petObject = {
	init: function() {
		
		let _this = this;

		//insert
		$("#btn-insertPet").on("click", () => {
			location = "/insertPet";
		});
		
		//update
		$(document).on("click", ".btn-updatePet", function() {
			let petSeq = $(this).data("pet-seq");
			location = "/updatePet/"+petSeq;
		})


		//delete11
		$(document).on("click", ".btn-deletePet", function() {
			let petSeq = $(this).data("pet-seq");
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