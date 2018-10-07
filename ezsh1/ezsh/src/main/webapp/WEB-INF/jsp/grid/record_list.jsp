<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>抄表记录列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="抄表记录列表">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:10% 0px 0px 10%;
			}
		</style>
	</head>
	
	<body class="easyui-layout">
		<div id="tb" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
			<%--	<a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
				<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
				<a href="javascript:remove()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>--%>
				<a href="javascript:importExcel()" class="easyui-linkbutton" iconCls="icon-redo" plain="true">导入</a>
				<a href="javascript:exportExcel()" class="easyui-linkbutton" iconCls="icon-undo" plain="true">导出</a>
			</div>
		</div>
		
		<div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				<!-- 起始时间: <input class="easyui-datebox" style="width:100px">
				终止时间: <input class="easyui-datebox" style="width:100px"> -->
				管理处：
				<input id="pManagerId" class="easyui-combobox" name="pManagerId" style="width:100px"
    				data-options="valueField:'managerId', <%--选择的数据的ID--%>
    				textField:'managerName',   <%--显示数据--%>
     				required:true,
     				  panelHeight: 80,
    				url:'/ezsh/build/findManager',
    				onSelect: function(rec){
    				    console.log(rec.managerId);
					 <%--   var url = '/ezsh/build/gtBuildListById?buildManagerId='+rec.managerId;
					    $('#pBuildId').combobox('reload', url);--%>
					    var urlOne='/ezsh/grid/selectSimple?pManagerId='+rec.managerId;
					    $('#pGridId').combobox('reload', urlOne);
				    }
    				">
			<%--	楼栋：
				<input id="pBuildId" class="easyui-combobox" name="pBuildId" style="width:100px"
					   data-options="valueField:'buildId',
	   				textField:'buildName',
	   				required:true,
	   				">--%>
				表计类别：
	   			<input id="pGridId" class="easyui-combobox" name="pGridId" style="width:100px"
    				data-options="valueField:'chargeId',
    				textField:'chargeName',
    				required:true,
    				  panelHeight: 80,
    				">
			<%--	类别名查询:
				<input class="easyui-textbox" style="width:100px;height:25px">
				选择月份：
				<!-- <input id="chargeEndDate" class="easyui-datebox" data-options="formatter:myYMformatter,parser:myYMparser" required="required" type="text" name="chargeEndDate" /> -->
				<input class="textbox" style="width:98px;height:23px;"id="date04" type="text" placeholder="YYYY-MM"  readonly>--%>
				<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div>
		
		<div data-options="region:'center',title:'抄表记录列表',iconCls:'icon-ok'">
			<table id="formTable" class="easyui-datagrid"
					data-options="method:'get',
					border:false,
					fit:true,
					fitColumns:true,
					striped:true,
					pagination:true,
					toolbar:'#tb',
					rownumbers:true,
					singleSelect:true,
					">
			</table>
		</div>
		
		<%--<div id="win" class="easyui-window" title="抄表记录-修改" style="width:350px;height:300px"
		    data-options="iconCls:'icon-edit',
		    modal:true,
		    closed:true,
		    collapsible:false,
		    minimizable:false,
		    maximizable:false,
		    resizable:false
		    ">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false" >
					<form id="formInfo" method="post">
				    	<table class="tableStyle" cellpadding="5">
				    		<tr>
				    			<td style="display:none;">
				    				<input id="gridRecordId" class="easyui-textbox" type="text" name="gridRecordId"></input>
				    			</td>   		
				    			<td>管理处:</td>
				    			<td>
				    				<input id="managerName" class="easyui-textbox" type="text" name="managerName" disabled="disabled"></input>
				    			</td>
				    		</tr>
				    		
				    		<tr>	
				    			<td>楼栋:</td>
				    			<td><input id="buildName" class="easyui-textbox" type="text" name="buildName"  disabled="disabled"></input></td>
				    		</tr>
				    		
				    		<tr>	
				    			<td>房间号:</td>
				    			<td><input id="roomNum" class="easyui-textbox" type="text" name="roomNum"  disabled="disabled"></input></td>
				    		</tr>
				    		
				    		<tr>	
				    			<td>表计类别:</td>
				    			<td><input id="gridName" class="easyui-textbox" type="text" name="gridName"  disabled="disabled"></input></td>
				    		</tr>
				    		
				    		<tr>	
				    			<td>用量:</td>
				    			<td>
				    				<input id="dosage"  name="dosage"   
	                          			data-options="required:true	
                          			" />
                          		</td>
				    		</tr>
				    		
				    	</table>
			    	</form>
				</div>
				
				<div region="south" border="true" style="text-align:right;height:50px;line-height:30px;padding:10px 15px 0px 0px;">
					<a class="easyui-linkbutton" icon="icon-ok" href="javascript:sub()">确定</a>
					<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:close()">取消</a>
				</div>
			</div>
		</div>--%>
		
		<!-- exportExcel window start -->
		<div id="win1" class="easyui-window" title="导出表格" style="width:350px;height:300px"
		    data-options="iconCls:'icon-edit',
		    modal:true,
		    closed:true,
		    collapsible:false,
		    minimizable:false,
		    maximizable:false,
		    resizable:false
		    ">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false" >
					<form id="formInfo1" method="post">
						<div style="margin:5% 0 0 5%;"><span style="font-size:20px;">提示:</span>导出的表格用于抄表！</div>
				    	<table class="tableStyle" cellpadding="5">
				    		<tr>				    		    			
				    			<td>管理处:</td>
				    			<td>
				    				<input  id="ptManagerId" class="easyui-combobox" name="ptManagerId"   
	                      				data-options="valueField:'managerId',
	                      				required:true,
	                      				textField:'managerName',
	                      				editable:false,
	                      				 panelHeight: 80,
	                      				url:'/ezsh/build/findManager',
	                      				onSelect: function(rec){
		                      			<%--	var url = '/ezsh/build/gtBuildListById?buildManagerId='+rec.managerId;
						    				$('#ptBuildId').combobox('reload', url);	--%>
										   var urlOne='/ezsh/grid/selectSimple?pManagerId='+rec.managerId;
					                       $('#pGridId').combobox('reload', urlOne);
									    },
									    onLoadSuccess:function(data){
										    var url = '/ezsh/grid/selectSimple?pManagerId='+data[0].managerId;
											$('#ptGridId').combobox('reload', url);
									    }
	                      				"/> 
				    			</td>
				    		</tr>	
				    		
				    	<%--	<tr>
				    			<td>楼宇ID:</td>
				    			<td>
				    				<input id="ptBuildId" class="easyui-combobox" name="ptBuildId"
						   				data-options="valueField:'buildId',
						   				textField:'buildName',
						   				editable:false,
						   				required:true,
						   				">
				    			</td>	
				    		</tr>--%>
				    		
				    		<tr>
				    			<td>表计类别:</td>
				    			<td>
				    				<input  id="ptGridId" class="easyui-combobox" name="ptGridId"   
	                        			data-options="valueField:'chargeId',
	                        			required:true,
	                        			editable:false,
	                        			textField:'chargeName'
	                       			" />
	                       		</td>			    			
				    		</tr>
				    	</table>
			    	</form>
				</div>
				<div region="south" border="true" style="text-align:right;height:50px;line-height:30px;padding:10px 15px 0px 0px;">
					<a class="easyui-linkbutton" icon="icon-ok" href="javascript:down()">确定</a>
					<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:close()">取消</a>
				</div>
			</div>
		</div>
		<!-- exportExcel window start -->
	</body>
	<script type="text/javascript" src="/ezsh/jedate/jquery.jedate.js"></script>
	<%--<script type="text/javascript" src="/ezsh/js/grid/recordList.js"></script>--%>
	<script type="text/javascript">
/*	function myYMformatter(date){
		var y = date.getFullYear();  
		var m = date.getMonth()+1;  
		return y+'-'+(m<10?('0'+m):m);  
	}
	
	function myYMparser(s){  
	    if (!s) return new Date();  
	    var ss = (s.split('-'));  
	    var y = parseInt(ss[0],10);  
	    var m = parseInt(ss[1],10);
	    if (!isNaN(y) && !isNaN(m)){
	        return new Date(y,m-1);
	    } else {
	        return new Date();
	    */


	function importExcel(){
		parent.$('#win').window({    
			title :'导入抄表记录',
		    width:350,    
		    height:350,    
		    modal:true,
		    maximizable:false,
		    minimizable:false,
		    resizable:false,
		    content:"<iframe src='/ezsh/base/goURL/grid/import' height='100%' width='100%' frameborder='0px' ></iframe>"  
		}); 
	}

	function exportExcel(){
		$('#win1').window('open');
		$('#formInfo1').form('load',{
			ptManagerId:$("#pManagerId").combobox("getValue"),
			ptGridId:$("#pGridId").combobox("getValue")
		});
	}
/*
	$("#date04").jeDate({
	    isinitVal: true,
	    festival: true,
	    ishmsVal: false,
	    minDate: '2016-06-16 23:59:59',
	    maxDate: $.nowDate(0),
	    format:"YYYY-MM",
	    zIndex: 3000,
	})*/
	
	function down(){
		var url = "/ezsh/gridRecord/export?pManagerId="+$("#ptManagerId").combobox("getValue")+"&pGridId="+$("#ptGridId").combobox("getValue");
		window.open(url)
		$('#win1').close();
	}


    function search(){
        if($("#pManagerId").combobox('isValid')&&$("#pGridId").combobox('isValid')){
            /*$('#formTable').datagrid({
                queryParams: {
                    pManagerId:$("#pManagerId").combobox('getValue'),
                    pGridId:$("#pGridId").combobox('getValue')
                }
            });	*/
            var pManagerId=$("#pManagerId").combobox('getValue');
            var pGridId=$("#pGridId").combobox('getValue');
            console.log(pGridId,pManagerId);
            $('#formTable').datagrid({
                url:'/ezsh/gridRecord/select?pManagerId='+pManagerId+'&pGridId='+pGridId,
                columns:[[
                    {field:'roomNum',title:'房间编号',width:100,align:'center'},
                    {field:'pGridId',hidden:'true',title:'表计类别ID',width:100,align:'center'},
                    {field:'beginDosage',title:'起数',width:100,align:'center'},
                    {field:'dosage',title:'用量',width:100,align:'center'},
                    {field:'enteringTime',title:'录入时间',width:100,align:'center'},
                    {field:'gridReadTime',title:'抄表时间',width:100,align:'center'},
                    {field:'gridReadType',title:'抄表方式',width:100,align:'center'},
                    {field:'gridReadPeople',title:'抄表人员',width:100,align:'center'}
                ]]
            });

        }
    }
    function edit(){
        var row = $('#formTable').datagrid('getSelected');
        if (row){
            $('#win').window('open');
            $('#formInfo').form('load',{
                gridRecordId:row.gridRecordId,
                managerName:$("#pManagerId").combobox('getText'),
                roomNum:row.roomNum,
                gridName:$("#pGridId").combobox('getText'),
                dosage:row.dosage,
            });
        }else{
            alert("请至少选中一条数据！");
        }
    }

    function sub(){
        $.messager.progress();
        $('#formInfo').form('submit', {
            url: "/ezsh/gridRecord/update",
            onSubmit: function(param){

            },
            success: function(data){
                $.messager.progress('close');
                var data = eval('(' + data + ')');
                if(data.status==1){
                    $.messager.alert('提示','修改成功!','info');
                }else{
                    $.messager.alert('提示','修改失败!',function(){
                    });
                }
            }
        });
    }

    function reload(){
        location.reload();
    }

    function close(){
        $('#win1').window('close');
    }

    function add(){
        parent.$('#win').window({
            title :'添加抄表记录',
            width:450,
            height:380,
            modal:true,
            maximizable:false,
            minimizable:false,
            resizable:false,
            content:"<iframe src='/ezsh/base/goURL/grid/record_add' height='100%' width='100%' frameborder='0px' ></iframe>"
        });
    }

    function remove(){
        var row = $('#formTable').datagrid('getSelected');
        $.ajax({
            type: "GET",
            url:"/ezsh/gridRecord/delete",
            data:{"gridRecordId":row.gridRecordId},
            dataType:"json",
            success: function(data){
                if(data.status>0){
                    $.messager.alert('提示','删除成功!','info');
                }else{
                    $.messager.alert('提示','删除失败!','info');
                }
                $('#formTable').datagrid('reload');
            }
        })
    }


	</script>
</html>
