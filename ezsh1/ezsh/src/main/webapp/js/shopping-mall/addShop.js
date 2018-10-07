var trText='<tr>'+
	'<td>'+
		'<input type="text" class="sectionOneValue" name="sectionOneValue" style="height:20px;width:80px;" readonly="readonly"/>'+
	'</td>'+
	'<td>'+
		'<input type="text" class="sectionTwoValue" name="sectionTwoValue" style="height:20px;width:80px;" readonly="readonly"/>'+
	'</td>'+
	'<td>'+
		'<input type="text" class="sectionThreeValue" name="sectionThreeValue" style="height:20px;width:80px;" readonly="readonly"/>'+
	'</td>'+	
	'<td>'+
		'<input type="text" name="price" class="price" style="height:20px;width:100px;" data-options="required:true"/>'+
	'</td>'+
	'<td>'+
		'<input type="text" name="amount" class="amount" style="height:20px;width:100px;" data-options="required:true"/>'+
	'</td>'+
	'</tr>';

	$(function(){
		$(".easyui-filebox").filebox({
			buttonText:'选取图片'
		})
	})
	
	$(".goodsSlideShow").filebox({
	    onChange: function (event) {	
			
	    }
	});
	
	//属性一名称checkbox点击时触发
	$("#section_one_name").click(function(){
		if($("#section_one_name").prop("checked")){// 被选中
			document.getElementById("section_one_value_head").innerText=$("#sectionOneName").val();
			$("#sectionOneName").validatebox({
		        required: true
		    });
			var el = document.getElementsByClassName('section_one_value');
			el[0].checked=true;
			var sectionOneNameCheckeds=$("#section_one_value").find("input[type='checkbox']:checked").next();
			sectionOneNameCheckeds.each(function(){
				$(this).validatebox({
			        required: true
			    });
			})
			section_value_select();
		}else{// 取消选中
			document.getElementById("section_one_value_head").innerHTML="";
			$("#sectionOneName").validatebox({
		        required: false
		    });
			//属性一名称下的所有checkbox对应的值的required设置为false
			var sectionOneNameCheckedNextValues=$("#section_one_value").find("input[type='checkbox']:checked").next();
			sectionOneNameCheckedNextValues.each(function(){
				$(this).validatebox({
			        required: false
			    });
			})
			//属性一名称下的所有checkbox设置为false
			var sectionOneNameCheckeds=$("#section_one_value").find("input[type='checkbox']:checked");
			sectionOneNameCheckeds.each(function(){	
				$(this).prop("checked",false);
			})
			section_value_select();
		}	
	})
	
	
	//属性二名称checkbox点击时触发
	$("#section_two_name").click(function(){
		if($("#section_two_name").prop("checked")){
			document.getElementById("section_two_value_head").innerText=$("#sectionTwoName").val();
			$("#sectionTwoName").validatebox({
		        required: true
		    });
			var el = document.getElementsByClassName('section_two_value');
			el[0].checked=true;
			var sectionTwoNameCheckeds=$("#section_two_value").find("input[type='checkbox']:checked").next();
			sectionTwoNameCheckeds.each(function(){
				$(this).validatebox({
			        required: true
			    });
			})
			section_value_select();
		}else{
			document.getElementById("section_two_value_head").innerText="";
			$("#sectionTwoName").validatebox({
		        required: false
		    });
			//属性二名称下的所有checkbox对应的值的required设置为false
			var sectionTwoNameCheckedNextValues=$("#section_two_value").find("input[type='checkbox']:checked").next();
			sectionTwoNameCheckedNextValues.each(function(){
				$(this).validatebox({
			        required: false
			    });
			})
			//属性一名称下的所有checkbox设置为false
			var sectionTwoNameCheckeds=$("#section_two_value").find("input[type='checkbox']:checked");
			sectionTwoNameCheckeds.each(function(){	
				$(this).prop("checked",false);
			})
			section_value_select();
		}
	})
	//属性二名称的input失去焦距时触发
	$("#sectionTwoName").blur(function(){
		if($("#section_two_name").prop("checked")){
		document.getElementById("section_two_value_head").innerText=$("#sectionTwoName").val();
		var el = document.getElementsByClassName('section_two_value');
		el[0].checked=true;
		}else{
		document.getElementById("section_two_value_head").innerText="";
		}
	});
	
	
	//属性三名称的checkbox点击时触发
	$("#section_three_name").click(function(){
		if($("#section_three_name").prop("checked")){
			document.getElementById("section_three_value_head").innerText=$("#sectionThreeName").val();
			$("#sectionThreeName").validatebox({
		        required: true
		    });
			var el = document.getElementsByClassName('section_three_value');
			el[0].checked=true;
			var sectionThreeNameCheckeds=$("#section_three_value").find("input[type='checkbox']:checked").next();
			sectionThreeNameCheckeds.each(function(){
				$(this).validatebox({
			        required: true
			    });
			})
			section_value_select();
		}else{
			document.getElementById("section_three_value_head").innerText="";
			$("#sectionThreeName").validatebox({
		        required: false
		    });
			//属性二名称下的所有checkbox对应的值的required设置为false
			var sectionThreeNameCheckedNextValues=$("#section_three_value").find("input[type='checkbox']:checked").next();
			sectionThreeNameCheckedNextValues.each(function(){
				$(this).validatebox({
			        required: false
			    });
			})
			//属性一名称下的所有checkbox设置为false
			var sectionThreeNameCheckeds=$("#section_three_value").find("input[type='checkbox']:checked");
			sectionThreeNameCheckeds.each(function(){	
				$(this).prop("checked",false);
			})
			section_value_select();
		}
	})		
	
	$("#sectionThreeName").blur(function(){
		if($("#section_three_name").prop("checked")){
		document.getElementById("section_three_value_head").innerText=$("#sectionThreeName").val();
		}else{
		document.getElementById("section_three_value_head").innerText="";	
		}
	});
	
	
	/**
	 * @author qwc
	 * 2017年11月16日下午8:34:38
	 * @param obj
	 * @param obj1 
	 * void 当属性输入框的值发生变化时触发
	 */
	function changeText(obj,obj1){
		if(obj1==1){
			var el = document.getElementsByClassName('section_one_value');
			if(el[obj].checked == true){
				section_value_select(obj);
			} 
		}
		if(obj1==2){
			var el = document.getElementsByClassName('section_two_value');
			if(el[obj].checked == true){
				section_value_select(obj);
			} 
		}
		if(obj1==3){
			var el = document.getElementsByClassName('section_three_value');
			if(el[obj].checked == true){
				section_value_select(obj);
			} 
		}
	}
	
	/**
	 * @author qwc
	 * 2017年10月14日下午2:07:15
	 * @param obj 表格循环创建和赋值
	 * void
	 */
	function section_value_select(obj){
		var section_one_value_length=$("#section_one_value").find("input[type='checkbox']:checked").length;// 属性一中被选中的checkbox
		var section_two_value_length=$("#section_two_value").find("input[type='checkbox']:checked").length;// 属性二中被选中的checkbox
		var section_three_value_length=$("#section_three_value input[type='checkbox']:checked").length;// 属性三中被选中的checkbox
		
		var section_one_value_next=$("#section_one_value").find("input[type='checkbox']:checked").next();// 属性一中被选中的input
		var section_two_value_next=$("#section_two_value").find("input[type='checkbox']:checked").next();// 属性二中被选中的input
		var section_three_value_next=$("#section_three_value").find("input[type='checkbox']:checked").next();// 属性三中被选中的input
		
		/**
		 * 获取未勾选的checkbox
		 */
		var section_one_value_next1=$("#section_one_value").find("input[type='checkbox']").not("input:checked").next();// 属性一中未被选中的checkbox
		var section_two_value_next1=$("#section_two_value").find("input[type='checkbox']").not("input:checked").next();// 属性二中未被选中的checkbox
		var section_three_value_next1=$("#section_three_value").find("input[type='checkbox']").not("input:checked").next();// 属性三中未被选中的checkbox
		
		var section_one_value_next_length,section_two_value_next_length,section_three_value_next_length;
		
		//当属性一名称下的checkbox都为空时，属性一名称的checkbox设置为未选中
		if(section_one_value_length==0){
			$("#section_one_name").prop("checked",false);
			$("#sectionOneName").validatebox({
		        required: false
		    });
			document.getElementById("section_one_value_head").innerText="";
			section_one_value_length=1;
		}
		//当属性二名称下的checkbox都为空时，属性一名称的checkbox设置为未选中
		if(section_two_value_length==0){
			$("#section_two_name").prop("checked",false);
			$("#sectionTwoName").validatebox({
		        required: false
		    });
			document.getElementById("section_two_value_head").innerText="";
			section_two_value_length=1;
		}
		//当属性三名称下的checkbox都为空时，属性一名称的checkbox设置为未选中
		if(section_three_value_length==0){
			$("#section_three_name").prop("checked",false);
			$("#sectionThreeName").validatebox({
		        required: false
		    });
			document.getElementById("section_three_value_head").innerText="";
			section_three_value_length=1;
		}
		
		if(section_one_value_next.length==0){
			section_one_value_next_length=1;
		}else{
			section_one_value_next_length=section_one_value_next.length;
		}
		if(section_two_value_next.length==0){
			section_two_value_next_length=1;
		}else{
			section_two_value_next_length=section_two_value_next.length;
		}
		if(section_three_value_next.length==0){
			section_three_value_next_length=1;
		}else{
			section_three_value_next_length=section_three_value_next.length;
		}
		
		
		/* 创建table */
		var total_length=section_one_value_length*section_two_value_length*section_three_value_length;//计算出总共行数
		var trT='';
		for(var i=0;i<total_length;i++){
			trT+=trText;
		}
		var value_list=document.getElementById("value_list");
		value_list.innerHTML=trT;
		
		//当属性一名称下的checkbox都不为空时，属性一名称的checkbox设置为选中
		if(section_one_value_next.length>0){
			$("#section_one_name").prop("checked",true);
			$("#sectionOneName").validatebox({
		        required: true
		    });
			document.getElementById("section_one_value_head").innerText=$("#sectionOneName").val();
		}
		section_one_value_next.each(function(){
			$(this).validatebox({
		        required: true
		    });
		})
		section_one_value_next1.each(function(){
			$(this).validatebox({
		        required: false
		    });
		})
		if(section_one_value_next.length>0){
			var tr_sectionOneValue=$("#value_list").find("input[class='sectionOneValue']");//值出现次数
			oneForeach=tr_sectionOneValue.length/section_one_value_next_length;//每个值的循环次数
			for(var j=0;j<section_one_value_next_length;j++){
				for(var i=0;i<oneForeach;i++){
					tr_sectionOneValue[j*oneForeach+i].value=section_one_value_next[j].value;
				}
			}
		}
		
		//当属性二名称下的checkbox都不为空时，属性一名称的checkbox设置为选中
		if(section_two_value_next.length>0){
			$("#section_two_name").prop("checked",true);
			$("#sectionTwoName").validatebox({
		        required: true
		    });
			document.getElementById("section_two_value_head").innerText=$("#sectionTwoName").val();
		}
		section_two_value_next.each(function(){
			$(this).validatebox({
		        required: true
		    });
		})
		section_two_value_next1.each(function(){
			$(this).validatebox({
		        required: false
		    });
		})
		
		if(section_two_value_next.length>0){
			var tr_sectionTwoValue=$("#value_list").find("input[class='sectionTwoValue']");//值出现次数
			twoEachForeach=section_three_value_next_length;//整体内每个值的循环次数
			twoForeach=section_one_value_next_length;//整体循环次数
			for(var k=0;k<twoForeach;k++){
				for(var j=0;j<section_two_value_next_length;j++){
					for(var i=j*twoEachForeach;i<(j+1)*twoEachForeach;i++){
						tr_sectionTwoValue[k*section_two_value_next_length*section_three_value_next_length+i].value=section_two_value_next[j].value;
					}
				}
			}
		}
		
		//当属性三名称下的checkbox都不为空时，属性一名称的checkbox设置为选中
		if(section_three_value_next.length>0){
			$("#section_three_name").prop("checked",true);
			$("#sectionThreeName").validatebox({
		        required: true
		    });
			document.getElementById("section_three_value_head").innerText=$("#sectionThreeName").val();
		}
		section_three_value_next.each(function(){
			$(this).validatebox({
		        required: true
		    });
		})
		section_three_value_next1.each(function(){
			$(this).validatebox({
		        required: false
		    });
		})
		
		if(section_three_value_next.length>0){
			var tr_sectionThreeValue=$("#value_list").find("input[class='sectionThreeValue']");//值出现次数
			threeForeach=tr_sectionThreeValue.length/section_three_value_next_length;
			for(var k=0;k<threeForeach;k++){
				for(var j=0;j<section_three_value_next_length;j++){
					tr_sectionThreeValue[k*section_three_value_next_length+j].value=section_three_value_next[j].value;
				}
			}
		}
		
		$(".amount").each(function(){
			$(this).validatebox({
		        required: true
		    });
			$(this).numberbox({
			    min:0
			});
		})
		$(".price").each(function(){
			$(this).validatebox({
		        required: true
		    });
			$(this).numberbox({
			    min:0,
			    precision:2
			});
		})
	}