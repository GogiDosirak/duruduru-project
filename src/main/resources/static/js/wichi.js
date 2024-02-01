let wichiObject={
	init:function(){
		let _this=this;
	
	$("#btn-wichi").on("click",()=>{
		_this.check();
	})
	},
	check:function(){
		alert("내가 입력한"+sido+"주소찾기"+comsido)
	}
}

wichiObject.init();