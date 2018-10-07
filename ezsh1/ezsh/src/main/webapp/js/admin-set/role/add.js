var powerList=''; 
function getCompetenceArrayId(){
	if(powerList!=null||powerList!=""){
		powerList='';
	}
	var obj=document.getElementsByName('assgin-power'); 
	for(var i=0; i<obj.length;i++){ 
		if(obj[i].checked==true&&obj[i].value!=null&&obj[i].value!="") {
		powerList+=obj[i].value+',';
		} 
	};
}

$("#sub-power").click(function(){
	getCompetenceArrayId();
	if($('#role-name').val()==null||$('#role-name').val()==""){
		$.messager.alert('提示','角色名不能为空!','warning');
	}else if($("#role-desc").val()==null||$("#role-desc").val()==""){
		$.messager.alert('提示','描述不能为空!','warning');
	}else if(powerList!=null&&powerList!=""){
		$.ajax({
			url:"/ezsh/ro/addRo.html",
			type:'post',
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			data:{roName:"("+$('#ptManagerId').combobox('getText')+")"+$('#role-name').val(),
				roDescr:$("#role-desc").val(),ptManagerId:$('#ptManagerId').combobox('getValue'),roPrevNames:powerList},
			dataType:'json',
			success:function(data){
				if(data.status==1){
					$.messager.alert('提示',data.message,'info',function(e){
						location.reload();}
					);
				}else if(data.status == 0){
					$.messager.alert('提示',data.message,'error');
				} else if(data.status==-1){
					$.messager.alert('提示',data.message,'error');
				} else if(data.status==-2){
					$.messager.alert('提示',data.message,'error');
				}
			}
		});
	}else{
		$.messager.alert('提示','至少分配一项权限!','warning');
	}
})