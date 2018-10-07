/**
 * 按条件查找管理处
 */
$("#search").click(function(){
    var conditions=$("#conditions").combobox('getValue');
    var keyword=$("#keyword").val();
	$.ajax({
		type: "POST",
	    url: "/ezsh/management/setConditions",
	    data: "conditions="+conditions+"&keyword="+keyword,
	    dataType:"json",
	    success: function(management){
		    if(management.managerName==null){
		   		management.managerName="";
		    }
		    if(management.managerId==null){
				management.managerId="";
			}
		    if(management.managerAddr==null){
			    management.managerAddr="";
			}
		    if(management.head==null){
		    	management.head="";
		    }
		    if(management.contact==null){
		    	management.contact="";
		    }
		    if(management.contactNum==null){
		    	management.contactNum="";
		    }
		    $('#dg').datagrid('load',{
				managerName:management.managerName,
				managerAddr:management.managerAddr,
				head:management.head,
				contact:management.contact,
				contactNum:management.contactNum
			});
	    }
	});
});