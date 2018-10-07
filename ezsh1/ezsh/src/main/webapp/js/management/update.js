var pProvinceId, pCityId, pDistrictId;
$(function() {
	var win = parent.$("iframe[title='管理处']").get(0).contentWindow;//返回ifram页面窗体对象（window)
	var row = win.$('#dg').datagrid("getSelected");
	pProvinceId = row.pProvinceId;
	pCityId = row.pCityId;
	pDistrictId = row.pDistrictId;
	$('#ff').form('load',{
		managerId:row.managerId,
		managerName:row.managerName,
		managerAddr:row.managerAddr,
		heightBuildNum:row.heightBuildNum,
		manyBuildNum:row.manyBuildNum,
		buildArea:row.buildArea,
		floorArea:row.floorArea,
		greenArea:row.greenArea,
		publicArea:row.publicArea,
		parkingNum:row.parkingNum,
		parkingArea:row.parkingArea,
		contactNum:row.contactNum,
		reamrk:row.reamrk,
		contact:row.contact,
		contactNum:row.contactNum,
		remark:row.remark,
		head:row.head,
		pProvinceId:row.pProvinceId,
		pCityId:row.pCityId,
		pDistrictId:row.pDistrictId,
	});

	$("[name='managerId']").validatebox({
		required : true,
		missingMessage : '请填写编号！'
	});
	$("[name='managerName']").validatebox({
		required : true,
		missingMessage : '请填写管理处名称！'
	});
	$("[name='managerAddr']").validatebox({
		required : true,
		missingMessage : '请填写地址！'
	});
	//禁用验证
	$("#ff").form("disableValidation");
	
	getProvince();
});

function submitForm() {
	var win = parent.$("iframe[title='管理处']").get(0).contentWindow;//返回ifram页面窗体对象（window)
	$("#ff").form("enableValidation");
	if ($("#ff").form("validate")) {
		$('#ff').form('submit', {
			url : '/ezsh/management/update.action',
			onSubmit : function(param) {
				param.provinceName = $("#pProvinceId").find("option:selected").text();
				param.cityName = $("#pCityId").find("option:selected").text();
				param.districtName = $("#pDistrictId").find("option:selected").text();
				var isValid = $(this).form('validate'); // 验证表单中的一些控件的值是否填写正确，比如某些文本框中的内容必须是数字 
				if (!isValid) { 
				} 
		        return isValid; // 如果验证不通过，返回false终止表单提交 
			},
			success : function(count) {							
				//可以定义为对应消息框
				if (count > 0) {
					alert("修改成功！");									
				} else {
					alert("修改失败！");
				}
				parent.$("#win").window("close");
				win.$("#dg").datagrid("reload");
				win.$("#dg").datagrid("clearSelections");
			}
		});
	}
}

/* 窗口关闭 */
function closeWin(){
	parent.$("#win").window("close");
}

/* 获取省份列表 */
function getProvince() {
	var provinceId,select = "";
	$.ajax({
		url:"/ezsh/prCiDistr/gtProvince",
		type:'post',
		dataType:'json',
		success:function(data) {
			data = data.data;
			for (var i = 0; i < data.length; i++) {
				if(data[i].id == pProvinceId) {
					select += "<option selected='selected' value='" + data[i].id + "'>" + data[i].name + "</option>";
					provinceId = data[i].id;
				}else{
					select += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
				}
			}
			if (provinceId == "" || provinceId == null) {
				provinceId = data[0].id;
			}
			document.getElementById('pProvinceId').innerHTML = select;
			getCity(provinceId);
		}
	});
}

/* 获取城市列表 */
function getCity(provinceId) {
	var cityId,select = "";
	if (provinceId >= 0) {
		$.ajax({
			url:"/ezsh/prCiDistr/gtCity?provinceId="+provinceId,
			type:'post',
			dataType:'json',
			success:function(data) {
				data = data.data;
				for (var i = 0; i < data.length; i++) {
					if (data[i].id == pCityId) {
						cityId = data[i].id;
						select += "<option selected='selected' value='" + data[i].id + "'>" + data[i].name + "</option>";
					} else {
						select += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
					}	
				}
				
				if (cityId == "" || cityId == null) {
					cityId = data[0].id;
				}
				document.getElementById('pCityId').innerHTML = select;
				getRegion(cityId);
			}
		});
	}
}

/* 获取城区列表*/
function getRegion(cityId) {
	var select = "";
	if (cityId >= 0) {
		$.ajax({
			url:"/ezsh/prCiDistr/gtDistrict?cityId=" + cityId,
			type:'post',
			dataType:'json',
			success:function(data) {
				data = data.data;
				for (var i = 0; i < data.length; i++) {
					if (data[i].id == pDistrictId) {
						select += "<option selected='selected' value='" + data[i].id + "'>" + data[i].name + "</option>";
					} else {
						select += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
					}
				}
				document.getElementById('pDistrictId').innerHTML = select;
			}
		});
	}
}

/**
 * @author qwc
 * 2017年10月8日下午6:56:33 
 * void 省份发生变化时触发城市列表
 */
function changeProvince() {
	var pProvinceId, pCityId, pDistrictId;
	var proviceId = document.getElementById("pProvinceId").value;
	changeCity(proviceId);
}

function changeCity(provinceId) {
	var cityId,select = "";
	if (provinceId >= 0) {
		$.ajax({
			url:"/ezsh/prCiDistr/gtCity?provinceId="+provinceId,
			type:'post',
			dataType:'json',
			success:function(data) {
				data = data.data;
				for (var i = 0; i < data.length; i++) {	
					select += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
				}
				
				if (cityId == "" || cityId == null) {
					cityId = data[0].id;
				}
				document.getElementById('pCityId').innerHTML = select;
				changeRegion(cityId);
			}
		});
	}
}

/* 获取城区列表*/
function changeRegion(cityId) {
	var select = "";
	if (cityId >= 0) {
		$.ajax({
			url:"/ezsh/prCiDistr/gtDistrict?cityId=" + cityId,
			type:'post',
			dataType:'json',
			success:function(data) {
				data = data.data;
				for (var i = 0; i < data.length; i++) {
					select += "<option value='" + data[i].id+"'>" + data[i].name + "</option>";
				}
				document.getElementById('pDistrictId').innerHTML = select;
			}
		});
	}
}