<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>已审核页面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="description" content="已审核列表">
</head>
<body class="easyui-layout">

<div data-options="region:'west',title:'楼宇',split:true" style="width:120px;">
    <ul class="easyui-tree" id="tree">

    </ul>
</div>
<div data-options="region:'center',title:'已审核列表',iconCls:'icon-ok'" >
    <table id="formTable" class="easyui-datagrid"
           data-options="method:'get',
					border:false,
					fit:true,
					fitColumns:false,
					striped:true,
					pagination:true,
					rownumbers:true,
					singleSelect:true,
					">
    </table>
</div>
</body>
<script>

    /* 构建管理处树 */
    $(function(){
        $('#tree').tree({
            url:'/ezsh/tree/gtTree',
            lines:true
        });

    })

    var managerId,managerName,roomId=0,roomNum;
    $('#tree').tree({
        onClick: function(node) {
            // alert node text property when clicked
            if (node.id.substring(0, node.id.indexOf("-")) == 2) {//房间ID
                var swap = node.id.substring((node.id.indexOf("-") + 1));//继承解析ID
                var selected = $('#tree').tree('getSelected');//获取选中对象
                var nodeParent = $("#tree").tree("getParent", selected.target);//父节点
                var nodeParent1 = $("#tree").tree("getParent", nodeParent.target);//父父节点
                managerId = swap.substring(0, swap.indexOf("-"));//从ID中获取管理处ID
                managerName = nodeParent1.text;//从ID中获取管理处名称
                swap = swap.substring((swap.indexOf("-") + 1));
                roomId = swap.substring((swap.indexOf("-") + 1));//从ID中获取房间ID
                roomNum = node.text.substring(0, node.text.indexOf("|"));
                /*
                                    $('#formTable').datagrid({
                                        queryParams: {
                                            pRoomId:roomId
                                        }
                                    });*/

                /*初始化列表*/
                $('#formTable').datagrid({
                    url: '/ezsh/chargeRecord/select?pRoomId=' + roomId+'&checkStatus=1',
                    columns: [[
                        {field: 'chargeItemName', title: '收费项目名称', width: 100, align: 'center'},
                        {field: 'unitPrice', title: '收费项目单价', width: 100, align: 'center'},
                        {field: 'chargeAmount', title: '数量', width: 100, align: 'center'},
                        {field: 'totalPrice', title: '总价', width: 100, align: 'center'},
                        {field: 'chargeOfDate', title: '费用所属月份', width: 100, align: 'center'},
                        {field: 'chargeEndDate', title: '收费结束时间', width: 100, align: 'center'},
                        {field: 'overdueTime', title: '超期开始时间', width: 100, align: 'center'},
                        {
                            field: 'status', title: '缴费状态', width: 100, align: 'center',
                            formatter: function (value, row, index) {
                                if (row.status == -1) {
                                    return "欠费";
                                } else if (row.status == 0) {
                                    return "待缴费";
                                } else if (row.status == 1) {
                                    return "已缴费";
                                }

                            }
                        },
                        {
                            field: 'checkStatus', title: '审核状态', width: 100, align: 'center',
                            formatter: function (value, row, index) {
                                if (row.checkStatus == 0) {
                                    return "待审核";
                                } else if (row.checkStatus == 1) {
                                    return "已审核";

                                }

                            }
                        },
                    ]]
                });
            }
        }
        })

</script>
</html>
