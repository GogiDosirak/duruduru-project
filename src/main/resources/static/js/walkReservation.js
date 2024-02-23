
let walkReservationObject = {

	init: function() {

		let _this = this;

		$("#btn-insertWalkReservation").on("click", () => {
			_this.insert();
		}),
			$("#btn-writeWalkReservation").on("click", () => {
				location = "/insertWalkReservation"
			}),
			$("#btn-updateWalkReservation").on("click", () => {
				_this.update();
			}),
			$("#go-updateWalkReservation").on("click", () => {
				location = "/updateWalkReservation/" + $("#wareSeq").val();
			}),
			$("#btn-deleteWalkReservation").on("click", () => {
				_this.delete();
			}),

			/*여기부터 코멘트 버튼*/

			$("#btn-WalkReservationInsertComment").on("click", () => {
				_this.insertComment();
			}),
			$(document).on("click", ".btn-delete-comment", function() {
				// 클릭된 버튼의 데이터 속성에서 댓글 번호(co-seq)를 가져옴
				let co_seq = $(this).data("co-seq");

				// 댓글 삭제 함수 호출
				_this.deleteComment(co_seq);
			});


		/*여기서부터 함수 정의*/

	},

	insert: function() {
		let post = {
			wareTitle: $("#walkReservationTitle").val(),
			wareContent: $("#walkReservationContent").val()
		}

		$.ajax({
			type: "POST",
			url: "/insertWalkReservation",
			data: JSON.stringify(post),
			contentType: "application/json; charset = utf-8"
		}).done(function(response) {
			alert(response.data);
			location = "/walkReservation";
		}).fail(function(error) {
			let message = error["data"];
			alert("에러발생 : " + message)
		});

	},

	update: function() {

		let post = {
			wareSeq: $("#wareSeq").val(),
			wareTitle: $("#walkReservationTitle").val(),
			wareContent: $("#walkReservationContent").val()
		}

		$.ajax({
			type: "PUT",
			url: "/updateWalkReservation/" + post.wareSeq,
			data: JSON.stringify(post),
			contentType: "application/json; charset = utf-8"
		}).done(function() {
			alert("글 수정 완료");
			location = "/getWalkReservation/" + post.wareSeq;
		}).fail(function(error) {
			let message = error["data"];
			alert("에러발생 : " + message)
		});
	},

	delete: function() {
		$.ajax({
			type: "DELETE",
			url: "/deleteWalkReservation/" + $("#wareSeq").val()
		}).done(function(response) {
			alert(response.data);
			location = "/walkReservation";
		}).fail(function(error) {
			let message = error["data"];
			alert("에러발생 : " + message)
		});
	},


	/*여기부터 코멘트 메소드*/

	insertComment: function() {
		let post = {
			wareCoContent: $("#wareCoContent").val(),
		}

		$.ajax({
			type: "POST",
			url: "/insertWalkReservationComment/" + $("#wareSeq").val(),
			data: JSON.stringify(post),
			contentType: "application/json; charset = utf-8"
		}).done(function(response) {
			alert(response.data);
			location = "/getWalkReservation/" + $("#wareSeq").val()
		}).fail(function(error) {
			let message = error["data"];
			alert("에러발생 : " + message)
		});
	},

	deleteComment: function(co_seq) {
		// 댓글 번호를 이용하여 AJAX 요청
		$.ajax({
			type: "DELETE",
			url: "/deleteWalkReservationComment/" + co_seq,
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			// 삭제 성공 시 알림 및 페이지 새로고침
			alert(response.data);
			location = "/getWalkReservation/" + $("#wareSeq").val();
		}).fail(function(error) {
			// 실패 시 에러 메시지 출력
			let message = error["data"];
			alert("에러발생 : " + message);
		});
	}


}
walkReservationObject.init();