
let snsBoardObject = {
	init: function() {
		let _this = this;

		$(document).on("click", ".btn-delete-snsbo", function() {
			let sns_seq = $(this).data("sns-seq");

			_this.delete(sns_seq);
		}),

			$(document).on("click", ".btn-snsBoardInsertComment", function() {

				let co_seq = $(this).data("co-seq");
 let snsboCoContent = $(this).closest('.comment-input-container').find('textarea').val();
				_this.insertComment(co_seq, snsboCoContent);
			}),

			$(document).on("click", ".btn-delete-comment", function() {
				let co_seq = $(this).data("co-seq");

				_this.deleteComment(co_seq);
			}),
			$(document).on("click", ".btn-insertLike", function() {
				let like_seq = $(this).data("like-seq");

				_this.insertLike(like_seq);
			}),
			$(document).on("click", ".btn-deleteLike", function() {
				let unlike_seq = $(this).data("unlike-seq");
				let snsbo_seq = $(this).data("unlike-snsbo-seq");
				_this.deleteLike(unlike_seq, snsbo_seq);
			}),

/*랭킹 사진 클릭시 게시글로 스크롤 시작*/
			document.addEventListener("DOMContentLoaded", function() {
				const snsImages = document.querySelectorAll('.sns-pet-image');

				snsImages.forEach(function(image) {
					image.addEventListener('click',
							function() {
								const snsboSeq = this
										.getAttribute('data-snsbo-seq');
								const targetElement = document
										.getElementById(snsboSeq);

								if (targetElement) {
									targetElement.scrollIntoView({
										behavior : 'smooth'
									});
								}
							});
				});
			});
/*스크롤 끝*/
	},




	delete: function(sns_seq) {
		$.ajax({
			type: "DELETE",
			url: "/deleteSns/" + sns_seq
		}).done(function(response) {
			alert(response.data);
			location = "/sns";
		}).fail(function(error) {
			let message = error["data"];
			alert("에러발생 : " + message)
		});
	},



	insertComment: function(co_seq, snsboCoContent) {
		let post = {
			snsboCoContent: snsboCoContent
		}

		$.ajax({
			type: "POST",
			url: "/insertSnsComment/" + co_seq,
			data: JSON.stringify(post),
			contentType: "application/json; charset = utf-8"
		}).done(function(response) {
			alert(response.data);
			location = "/sns";
		}).fail(function(error) {
			let message = error["data"];
			alert("에러발생 : " + message)
		});
	},



	deleteComment: function(co_seq) {
		$.ajax({
			type: "DELETE",
			url: "/deleteSnsComment/" + co_seq,
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			alert(response.data);
			location = "/sns";
		}).fail(function(error) {
			let message = error["data"];
			alert("에러발생 : " + message);
		});
	},


	insertLike: function(like_seq) {

		$.ajax({
			type: "POST",
			url: "/insertSnsLike/" + like_seq,

		}).done(function(response) {
			console.log(response.data);
			location = "/sns"
		}).fail(function(error) {
			let message = error["data"];
			alert("에러발생 : " + message)
		});
	},



	deleteLike: function(unlike_seq, snsbo_seq) {

		$.ajax({
			type: "DELETE",
			url: "/deleteSnsLike/" + unlike_seq + "/" + snsbo_seq,
			contentType: "application/json; charset = utf-8"
		}).done(function() {
			location = "/sns"
		}).fail(function(error) {
			let message = error["data"];
			alert("에러발생 : " + message)
			location = "/sns"
		});
	},

}


snsBoardObject.init();