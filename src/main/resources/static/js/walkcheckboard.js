let wachboObject = {
	//init 함수 선언
	init : function() {
		let _this = this;
		
		//#btn-save라는 버튼을 클릭하는 이벤트가 발생하면 insertUser() 함수 호출
		$("#btn-insertwach").on("click", () => {
			_this.insertwachbo();
		});
	} , 
	
	insertwachbo : function() {
		alert("글 등록이 요청되었습니다");
		//사용자가 입력한 값 (jsp화면에서  )
	let wachbo = {
		wachboTitle : $("#wachboTitle").val(),
		wachboContent : $("#wachboContent").val()
	}	  
	//Ajax 비동기 호출 동기 호출: 맨위에서 순서대로, 비동기 호출: 순서대로가 아님
	//done() 함수 : 요청 처리에 성고했을때
	//fail() 함수 : done()과 반대
	$.ajax({
		type : "POST", //요청방식
		url : "/insertwachbo", //요청 경로
		data : JSON.stringify(wachbo),//user 객체를 json의 형태로 변환
		contentType : "application/json; charset=utf-8"
	}).done(function(response) {
		console.log(response); //콘솔 창에 응답 메세지 출력
		location = "/"; //메인페이지로 이동
		
	}).fail(function(error) {
		alert("예외 발생 : " + error)
	});
	
	} , 
	
		
}
	//userObject 객체의 init() 함수 호출
	wachboObject.init();	