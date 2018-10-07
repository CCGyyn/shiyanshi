/**
 * qwc 
 */
$(function(){
	var teacherSubjectJson = [{
		"id": 1,
		"name": "语文",
		"type": 0,
		"grade": [{
			"id": 1,
			"name": "一年级"
		}, {
			"id": 2,
			"name": "二年级"
		}, {
			"id": 3,
			"name": "三年级"
		}, {
			"id": 4,
			"name": "四年级"
		}, {
			"id": 5,
			"name": "五年级"
		}, {
			"id": 6,
			"name": "六年级"
		}, {
			"id": 7,
			"name": "初一"
		}, {
			"id": 8,
			"name": "初二"
		}, {
			"id": 9,
			"name": "初三"
		}, {
			"id": 10,
			"name": "高一"
		}, {
			"id": 11,
			"name": "高二"
		}, {
			"id": 12,
			"name": "高三"
		}]
	}, {
		"id": 2,
		"name": "数学",
		"type": 0,
		"grade": [{
			"id": 1,
			"name": "一年级"
		}, {
			"id": 2,
			"name": "二年级"
		}, {
			"id": 3,
			"name": "三年级"
		}, {
			"id": 4,
			"name": "四年级"
		}, {
			"id": 5,
			"name": "五年级"
		}, {
			"id": 6,
			"name": "六年级"
		}, {
			"id": 7,
			"name": "初一"
		}, {
			"id": 8,
			"name": "初二"
		}, {
			"id": 9,
			"name": "初三"
		}, {
			"id": 10,
			"name": "高一"
		}, {
			"id": 11,
			"name": "高二"
		}, {
			"id": 12,
			"name": "高三"
		}]
	}, {
		"id": 3,
		"name": "英语",
		"type": 0,
		"grade": [{
			"id": 3,
			"name": "三年级"
		}, {
			"id": 4,
			"name": "四年级"
		}, {
			"id": 5,
			"name": "五年级"
		}, {
			"id": 6,
			"name": "六年级"
		}, {
			"id": 7,
			"name": "初一"
		}, {
			"id": 8,
			"name": "初二"
		}, {
			"id": 9,
			"name": "初三"
		}, {
			"id": 10,
			"name": "高一"
		}, {
			"id": 11,
			"name": "高二"
		}, {
			"id": 12,
			"name": "高三"
		}]
	}, {
		"id": 4,
		"name": "生物",
		"type": 0,
		"grade": [{
			"id": 7,
			"name": "初一"
		}, {
			"id": 8,
			"name": "初二"
		}]
	}, {
		"id": 5,
		"name": "化学",
		"type": 1,
		"grade": [{
			"id": 9,
			"name": "初三"
		}]
	}, {
		"id": 6,
		"name": "物理",
		"type": 1,
		"grade": [{
			"id": 8,
			"name": "初二"
		}, {
			"id": 9,
			"name": "初三"
		}]
	}, {
		"id": 7,
		"name": "地理",
		"type": 0,
		"grade": [{
			"id": 7,
			"name": "初一"
		}, {
			"id": 8,
			"name": "初二"
		}]
	}, {
		"id": 8,
		"name": "政治",
		"type": 2,
		"grade": [{
			"id": 7,
			"name": "初一"
		}, {
			"id": 8,
			"name": "初二"
		}, {
			"id": 9,
			"name": "初三"
		}]
	}, {
		"id": 9,
		"name": "历史",
		"type": 0,
		"grade": [{
			"id": 7,
			"name": "初一"
		}, {
			"id": 8,
			"name": "初二"
		}]
	}]
	var teacher_optType=["tea_subject","tea_grade"];//两个select的ID
	//var teacher_opt0 = ["学科","年级"];//初始值
	var selectedOptType=document.getElementById(teacher_optType[0]);//
	var selectedOptType1=document.getElementById(teacher_optType[1]);//
	var obj=document.getElementById(teacher_optType[0]);
	obj.options.length=0;
	var obj1=document.getElementById(teacher_optType[1]);
	obj1.options.length=0;
	
	// 初始化学科
	for(var j=0;j<teacherSubjectJson.length;j++){
		if($("#hidden-subject").val()!=null&&$("#hidden-subject").val()!=""&&teacherSubjectJson[j].name==$("#hidden-subject").val()){
			selectedOptType.options.add(
				new Option(teacherSubjectJson[j].name,teacherSubjectJson[j].name,true,true)
			);
		} else {
			selectedOptType.options.add(
				new Option(teacherSubjectJson[j].name,teacherSubjectJson[j].name)
			);
		}

	}
	var index = document.getElementById(teacher_optType[0]).selectedIndex; // 获取学科下拉选项初始后的默认索引值
	var value = document.getElementById(teacher_optType[0]).options[index].value; // 获取学科下拉选项初始后的默认值
	change(value);//初始化年级下拉选项
	
	// 当学科发生变化时年级发生初始化
	$('#tea_subject').change(function(){ 
		index = document.getElementById(teacher_optType[0]).selectedIndex;
		value = document.getElementById(teacher_optType[0]).options[index].value;
		change(value);
	})
	function change(value){
		var obj=document.getElementById(teacher_optType[1]);
		obj.options.length=0;
		var grade;
		for(var i = 0;i<teacherSubjectJson.length;i++){
			if(value == teacherSubjectJson[i].name){
				grade = teacherSubjectJson[i].grade;
				break;
			}
		}
	
		for(var j = 0;j<grade.length;j++){
			selectedOptType1.options.add(
				new Option(grade[j].name,grade[j].name)
			);
		}	
	}
	
	/**
	 * 页面初始函数
	 */
	function getApplicationData(page,rows){
		var dataList,teacherLesson,teacherGrade;
		if($("#hidden-subject").val()!=null && $("#hidden-subject").val()!=""){
			teacherLesson=$("#hidden-subject").val();
		} else {
			teacherLesson=$("#tea_subject").val();
		}
		$.ajax({  
            type:'post',  
            url:"/ezsh/educateInfoW/select",  
            dataType:'json',  
            data:{startPage:page,pageSize:rows,
            	teacherLesson:teacherLesson,teacherGrade:$("#tea_grade").val(),
            	placeProvince:$("#prov").val(),placeCity:$("#city").val(),
            	placeDistrict:$("#district").val(),otherExplain:$("#keyWord").val()},  
            beforeSend:function(){  
     
            },success:function(data){
                if(data.status == 1){
                	dataList = data.data.familyEducateInfoList;
                	var html = '';
                	if(data.data.totalPage>0){
                		for(var i = 0;i<dataList.length;i++){
                	        html += setLi(dataList[i]);
                	    }
                	    applicationRecord.innerHTML = html;
                	    totalPage=data.data.totalPage;
                	    if(startPage==totalPage){
                    		document.getElementById('getMore').innerHTML="暂无更多！";
                    	}
                	} else {
                		document.getElementById('getMore').innerHTML="暂无更多！"
                	}
                    //成功  
                }else{  
                    //出错   
                }  
            }  
    	});
	}
	
	window.onload=getApplicationData(1,10);
})