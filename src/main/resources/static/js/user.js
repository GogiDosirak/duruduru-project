let userObject = {
	
  init: function() {
    let _this = this;
    
		$("#btn-login").on("click", () => {
			_this.login();
		}),


			$("#btn-join").on("click", () => {
				_this.joinUser();
			}),


			$("#btn-findid").on("click", () => {
				_this.findidUser();
			}),
			$("#btn-findpw").on("click", () => {
				_this.findpwUser();
			}),
			$("#btn-chid").on("click", () => {
				_this.checkId();
			}),
			$("#btn-updateUser").on("click", () => {
			_this.updateUser();
			
		});
	},
  
  login: function() {
    alert("로그인이 요청되었습니다.");
    
    let data = {
      userid: $("#userid").val(),
      password: $("#password").val()
    };
    
    $.ajax({
      type: "POST",
      url: "/login",
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8"
    }).done(function(response) {
      let message = response["data"];
      alert(message);
      if (response.status === 200) {
        location = "/";
      }
    }).fail(function(error) {
      let message = error.responseJSON["data"];
      alert("에러 발생: " + message);
    });
  },
  
  
   joinUser: function() {
      alert("회원가입 요청되었습니다");

      let user = {
         userid: $("#userid").val(),
         password: $("#password").val(),
         passwordConfirm : $("#passwordConfirm").val(),
         nickname: $("#nickname").val(),
         email: $("#email").val(),
         phonenumber: $("#phonenumber").val(),
         address: $("#address").val(),
         address_detail: $("#address_detail").val(),
         zipcode: $("#zipcode").val()
      }

      // 이메일 주소의 유효성 검사
       // this를 사용하여 객체 내부의 함수 호출
    if (!this.isValidEmail(user.email)) {
        alert("올바른 이메일 주소를 입력해주세요.");
        return; // 이메일이 올바르지 않으면 함수 종료
    }
    
    if (!this.isValidPassword(user.password)) {
			alert("올바른 비밀번호를 입력해주세요.")
			return;
		}
		
		
		if(!this.isPasswordMatch(user.password, user.passwordConfirm)){
	alert("비밀번호가 일치하지 않습니다.")
	return;
}

      $.ajax({
         type: "POST",
         url: "/join",
         data: JSON.stringify(user),
         contentType: "application/json; charset=utf-8"
      }).done(function(response) {
			alert(response.data)
			console.log(response);
			if (response.status == 200) {
				location = "/login";
			}
		}).fail(function(error) {
			alert("에러발생 : " + error)
		});

	},

isValidPassword: function(password) {
		 const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*-])[A-Za-z\d!@#$%^&*-]{8,}$/;
		return passwordRegex.test(password);
	},
	
   isValidEmail: function(email) {
      // 간단한 이메일 형식 정규식을 사용하여 유효성을 검사
      const emailRegex = /\S+@\S+\.\S+/;
      return emailRegex.test(email);
   },
   
   
   isPasswordMatch: function(password, passwordConfirm) {
		return password === passwordConfirm;
	},

	checkId: function() {
		let user = {
			userid: $("#userid").val()
		}
		$.ajax({
			type: "POST",
			url: "/checkId",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			alert(response.data);
			console.log(response);
		}).fail(function(error) {
			alert("에러발생 : " + error);
		});
	},
   
   
   
   findidUser : function(){
		alert("아이디 찾기 요청되었습니다");
		
		let userid = {
			email : $("#email").val(),
			phonenumber : $("#phonenumber").val()
		}
		$.ajax({
			type : "POST",
			url : "/findid",
			data : JSON.stringify(userid),
			contentType : "application/json; charset=utf-8"
		}).done(function(response){
			alert(response.data)
			console.log(response);
			location="/login";
		}).fail(function(error){
			alert("에러발생 : " + error)
		});
		},
		
		
		
		findpwUser : function(){
		alert("비밀번호 찾기 요청되었습니다");
		
		let userpw = {
			userid : $("#userid").val(),
			email : $("#email2").val(),
			phonenumber : $("#phonenumber2").val()
		}
		$.ajax({
			type : "POST",
			url : "/findpw",
			data : JSON.stringify(userpw),
			contentType : "application/json; charset=utf-8"
		}).done(function(response){
			alert(response.data)
			console.log(response);
			location="/login";
		}).fail(function(error){
			alert("에러발생 : " + error)
		});
	},
	
	updateUser : function() {
		alert("회원정보 수정 요청됨");
		let updateUser = {
			password : $("#password").val(),
			nickname : $("#nickname").val(),
			user_seq : $("#user_seq").val()
		}
		
		$.ajax({
			type : "PUT",
			url : "/updateUser/" + updateUser.user_seq,
			data : JSON.stringify(updateUser),
			contentType : "application/json; charset=utf-8"
			
		}).done(function(response){
			let message = response["data"];
			alert(message);
			location = "/mypage" 
		}).fail(function(error){
			let message = error["data"];
			alert("에러 발생 : " + message);
			
		});
		
		
	},
  
  
  
  
  
}

userObject.init();