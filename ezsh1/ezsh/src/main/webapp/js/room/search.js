$("#search").click(function(){
	var conditions=$("#conditions").val();
	var keyword=$("#keyword").val();
	var managerId= $('#managerId').combobox('getValue');    
	var buildId= $('#buildId').combobox('getValue'); 
	var roomStatus=$("#roomStatus").val();
	var roomType=$("#roomType").val();

	$.ajax({
		type: "POST",
		url: "/ezsh/room/setConditions",
		data: "conditions="+conditions+"&keyword="+keyword+"&managerId="+managerId+"&buildId="+buildId+"&roomStatus="+roomStatus+"&roomType="+roomType,
		dataType:"json",
		success: function(room){
			if(room.roomId==null){room.roomId="";}
			if(room.managerId==null){room.managerId="";}
			if(room.buildId==null){room.buildId="";}
			if(room.roomStatus==null){room.roomStatus="";}
			if(room.roomType==null){room.roomType="";}
			if(room.buildArea==null){room.buildArea="";}
			if(room.roomFloor==null){room.roomFloor="";}
			if(room.chargeMan==null){room.chargeMan="";}
			if(room.roomUse==null){room.roomUse="";}
			if(room.position==null){room.position="";}
			if(room.toward==null){room.toward="";}
			if(room.rent==null){room.rent="";}
			if(room.managementFee==null){room.managementFee="";}
			if(room.sumPrice==null){room.sumPrice="";}
			if(room.singlePrice==null){room.singlePrice="";}
			$('#dg').datagrid('load',{
			roomId:room.roomId,
			managerId:room.managerId,
			buildId:room.buildId,
			roomStatus:room.roomStatus,
			roomType:room.roomType,
			buildArea:room.buildArea,
			roomFloor:room.roomFloor,
			chargeMan:'%'+room.chargeMan+'%',
			roomUse:'%'+room.roomUse+'%',
			position:'%'+room.position+'%',
			toward:'%'+room.toward+'%',
			rent:room.rent,
			managementFee:room.managementFee,
			sumPrice:room.sumPrice,
			singlePrice:room.singlePrice,
			});
		}
	});
});