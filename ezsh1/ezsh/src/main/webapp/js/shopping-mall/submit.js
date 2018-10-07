function submitForm(){
	var goodsParameter=editor.txt.html();
	var formdata=new FormData($('#forminfo')[0]);
	formdata.append("goodsParameter",goodsParameter);
	/*formdata.append("pManagerId",$("#pManagerId").textbox('getValue'));
	formdata.append("pGoodsClassfyId",$("#pGoodsClassfyId").val());*/
	goodsImageText=editorImgText.txt.html();
	formdata.append("goodsImageText",goodsImageText);
	var checkeds = $("#section_one_value").find("input[type='checkbox']");
	var checkedAmount = 0;
	if($(".goodsColorImage1").get(0).files!= null && checkeds[0].checked){
		checkedAmount+=1;
		formdata.append("goodsColorImage1",$(".goodsColorImage1").get(0).files[0]);
	} 
	
	if($(".goodsColorImage2").get(0).files!= null && checkeds[1].checked){
		checkedAmount+=1;
		if(!checkeds[0].checked){
			formdata.append("goodsColorImage1",$(".goodsColorImage2").get(0).files[0]);
		} else {
			formdata.append("goodsColorImage2",$(".goodsColorImage2").get(0).files[0]);
		}
		
	} 
	
	if($(".goodsColorImage3").get(0).files!= null && checkeds[2].checked){
		checkedAmount+=1;
		if(!checkeds[0].checked && !checkeds[1].checked){
			formdata.append("goodsColorImage1",$(".goodsColorImage3").get(0).files[0]);
		} else if(!checkeds[0].checked && checkeds[1].checked || checkeds[0].checked && !checkeds[1].checked) {
			formdata.append("goodsColorImage2",$(".goodsColorImage3").get(0).files[0]);
		} else {
			formdata.append("goodsColorImage3",$(".goodsColorImage3").get(0).files[0]);
		}
	}
	
	if($(".goodsColorImage4").get(0).files!= null && checkeds[3].checked){
		checkedAmount+=1;
		if(!checkeds[0].checked && !checkeds[1].checked && !checkeds[2].checked){
			formdata.append("goodsColorImage1",$(".goodsColorImage4").get(0).files[0]);
		} else if(checkeds[0].checked && !checkeds[1].checked && !checkeds[2].checked ||
				!checkeds[0].checked && checkeds[1].checked && !checkeds[2].checked ||
				!checkeds[0].checked && !checkeds[1].checked && checkeds[2].checked){
			
			formdata.append("goodsColorImage2",$(".goodsColorImage4").get(0).files[0]);
		} else if(checkeds[0].checked && checkeds[1].checked && !checkeds[2].checked ||
				!checkeds[0].checked && checkeds[1].checked && checkeds[2].checked ||
				checkeds[0].checked && !checkeds[1].checked && checkeds[2].checked){
			formdata.append("goodsColorImage3",$(".goodsColorImage4").get(0).files[0]);
		} else {
			formdata.append("goodsColorImage4",$(".goodsColorImage4").get(0).files[0]);
		}
		
	} 
	
	if($(".goodsColorImage5").get(0).files!= null && checkeds[4].checked){
		checkedAmount+=1;
		if(!checkeds[0].checked && !checkeds[1].checked && !checkeds[2].checked && !checkeds[3].checked){
			formdata.append("goodsColorImage1",$(".goodsColorImage5").get(0).files[0]);
		} else if(checkeds[0].checked && !checkeds[1].checked && !checkeds[2].checked && !checkeds[3].checked ||
				!checkeds[0].checked && checkeds[1].checked && !checkeds[2].checked && !checkeds[3].checked ||
				!checkeds[0].checked && !checkeds[1].checked && checkeds[2].checked && !checkeds[3].checked ||
				!checkeds[0].checked && !checkeds[1].checked && !checkeds[2].checked && checkeds[3].checked ){
			formdata.append("goodsColorImage2",$(".goodsColorImage5").get(0).files[0]);
		} else if(checkeds[0].checked && checkeds[1].checked && !checkeds[2].checked && !checkeds[3].checked ||
				!checkeds[0].checked && checkeds[1].checked && checkeds[2].checked && !checkeds[3].checked ||
				!checkeds[0].checked && !checkeds[1].checked && checkeds[2].checked && checkeds[3].checked ||
				checkeds[0].checked && !checkeds[1].checked && !checkeds[2].checked && checkeds[3].checked ||
				!checkeds[0].checked && checkeds[1].checked && !checkeds[2].checked && checkeds[3].checked ||
				checkeds[0].checked && !checkeds[1].checked && checkeds[2].checked && !checkeds[3].checked ){
			formdata.append("goodsColorImage3",$(".goodsColorImage5").get(0).files[0]);
		} else if(checkeds[0].checked && checkeds[1].checked && checkeds[2].checked && !checkeds[3].checked ||
				!checkeds[0].checked && checkeds[1].checked && checkeds[2].checked && checkeds[3].checked ||
				checkeds[0].checked && !checkeds[1].checked && checkeds[2].checked && checkeds[3].checked ||
				checkeds[0].checked && checkeds[1].checked && !checkeds[2].checked && checkeds[3].checked ){
			formdata.append("goodsColorImage4",$(".goodsColorImage5").get(0).files[0]);
		}
		formdata.append("goodsColorImage5",$(".goodsColorImage5").get(0).files[0]);
	} 
	
	formdata.append("checkedAmount",checkedAmount);
	//如果类别属性被选中，则把值提交上去
	var sectionOneName = document.getElementById("section_one_name");
	if(sectionOneName.checked){//如果被选中
		formdata.append("sectionOneName",$("#sectionOneName").val());
	}
	var sectionTwoName = document.getElementById("section_two_name");
	if(sectionTwoName.checked){//如果被选中
		formdata.append("sectionTwoName",$("#sectionTwoName").val());
	} 
	var sectionThreeName = document.getElementById("section_three_name");
	if(sectionThreeName.checked){//如果被选中
		alert($("#sectionThreeName").val());
		formdata.append("sectionThreeName",$("#sectionThreeName").val());
	} 
	
	
	//检测是否选了商品属性类别
	if(!$("#pGoodsClassfyIdValue").textbox("isValid")){//
		return;
	}
	
	// 检测产品主轮播图是否都为空
	var goodsSlideShowFiles = document.getElementsByName("goodsSlideShowFiles");
	var amount=0;
	for (var i=0;i<goodsSlideShowFiles.length;i++) {
		if (goodsSlideShowFiles[i].value == '') {
			amount += 1;
		}
	}
	
	// 检测优惠背景图是否为空
	var amount1=0;
	if($('#goods_show_classfy').combobox("getValue")==3){
		var fileBackgroundImage = document.getElementsByName("fileBackgroundImage");
		if(fileBackgroundImage[0].value == ''){
			amount1=1;	
		}
	}
	
	//判断
	$(".w-e-text-container").each(function(){
		$(this).css('z-index',10); 
	})
	if (amount == goodsSlideShowFiles.length) {
		$.messager.alert('提示','请选择至少一张轮播图!','info');
	}
	if (amount1!=0) {
		$.messager.alert('提示','请选择优惠商品背景图!','info');
	}
	
	
	if($("#forminfo").form('enableValidation').form('validate') && amount != goodsSlideShowFiles.length && amount1 ==0){
		$.ajax({
			url:"/ezsh/goodsAd/addCommodity",
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