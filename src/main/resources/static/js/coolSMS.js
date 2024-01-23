
let smsObject = {
   init: function() {
      $("#btn-requestSMS").click(() => this.request());
      $("#btn-checknumber").click(() => this.check());
   },

   request: function() {
      alert("번호 요청");
      let phonenumber = $("#phonenumber").val();

      $.post("/send-one", { phonenumber })
         .done(response => {
            console.log(response);
            alert("메시지 전송");
         })
         .fail(error => {
            alert("에러 발생: " + error);
         });
   },

   check: function() {
      alert("인증번호 확인");
      let checknumber = $("#checknumber").val();

      $.post("/checkSMS", { checknumber })
         .done(response => {
            console.log(response.data);
            alert(response.data);
            if(response.status!=200){
               location = "/";
            }
         })
         .fail(error => {
            alert("에러 발생: " + error);
         });
   }
};

smsObject.init();