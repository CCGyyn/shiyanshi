function sub(){
	/* $.messager.progress(); */
	$('#formInfo').form('submit', {
		url:"/ezsh/adminLg/login",
		onSubmit: function(){
			var isValid = $(this).form('validate');	
			return isValid;
		},
		success: function(data){
			var data = eval('(' + data + ')');
			if (data.status==1){
				location.href="/ezsh/ad/main";
			}else if(data.status==2){
				alert("验证码错误！");
			}else if(data.status==0){
				alert("用户不存在！");
			}else if(data.status==-1){
				alert("密码错误！");
			}
		}
	});
}