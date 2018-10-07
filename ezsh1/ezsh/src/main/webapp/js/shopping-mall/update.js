function submitForm(){
	var goodsParameter=editor.txt.html();
	var formdata=new FormData($('#forminfo')[0]);
	formdata.append("goodsParameter",goodsParameter);
	/*formdata.append("pManagerId",$("#pManagerId").textbox('getValue'));
	formdata.append("pGoodsClassfyId",$("#pGoodsClassfyId").val());*/
	goodsImageText=editorImgText.txt.html();
	formdata.append("goodsImageText",goodsImageText);
	
	if(!$("#pGoodsClassfyIdValue").textbox("isValid")){//
		return;
	}
	if($("#forminfo").form('enableValidation').form('validate')){
		$.ajax({
			url:"/ezsh/goodsAd/updateGoodsInfo",
			type:'post',
			data: formdata,
		    processData: false,
		    contentType: false,
			dataType:'json',
			success:function(data){
				if(data.status>0){
					alert(data.message);
				} 
			}
		}); 
	}		
}